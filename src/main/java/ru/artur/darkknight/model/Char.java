package ru.artur.darkknight.model;

import org.apache.commons.lang3.time.DateUtils;
import ru.artur.darkknight.exception.ConditionNotEnoughException;
import ru.artur.darkknight.exception.GoldNotEnoughException;
import ru.artur.darkknight.exception.TrainingNotPossible;
import ru.artur.darkknight.model.enums.CharType;
import ru.artur.darkknight.model.enums.DuelResult;
import ru.artur.darkknight.model.enums.TrainingType;
import ru.artur.darkknight.model.enums.TypeConverter;
import ru.artur.darkknight.model.items.Equipment;
import ru.artur.darkknight.model.items.armor.Armor;
import ru.artur.darkknight.model.items.weapon.Weapon;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "knight")
public class Char implements Comparable<Char> {
    /**
     * Hero's conditions
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int lvl = 1;
    @Column(name = "gold_amount")
    private int goldAmount = 100;
    @Column(name = "crystal_amount")
    private int crystalAmount = 100;
    @Column(name = "health")
    private int health = 100;
    @Convert(converter = TypeConverter.class)
    @Column(name = "type")
    private CharType type = CharType.HUMAN;

    /**
     * All the equipments on the hero
     */
    @ManyToOne
    @JoinTable(name = "knight_helm",
            joinColumns = @JoinColumn(name = "knight_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id"))
    private Armor helm;
    @ManyToOne
    @JoinTable(name = "knight_main_armor",
            joinColumns = @JoinColumn(name = "knight_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id"))
    private Armor mainArmor;
    @ManyToOne
    @JoinTable(name = "knight_boots",
            joinColumns = @JoinColumn(name = "knight_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id"))
    private Armor boots;
    @ManyToOne
    @JoinTable(name = "knight_jewellery",
            joinColumns = @JoinColumn(name = "knight_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id"))
    private Armor jewellery;
    @ManyToOne
    @JoinTable(name = "knight_ring",
            joinColumns = @JoinColumn(name = "knight_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id"))
    private Armor ring;
    @ManyToOne
    @JoinTable(name = "knight_weapon",
            joinColumns = @JoinColumn(name = "knight_id"),
            inverseJoinColumns = @JoinColumn(name = "weapon_id"))
    private Weapon weapon;

    /**
     * Relationship classes
     */
    @OneToOne
    @JoinColumn(name = "work_id")
    private Work work;
    @OneToOne
    @JoinColumn(name = "statistic_id")
    private Statistic statistic;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_training_date")
    private Date lastTrainingDate;
    @OneToOne
    @JoinColumn(name = "condition_id")
    private Condition condition;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "knight_bag",
            joinColumns = @JoinColumn(name = "knight_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id"))
    private List<Equipment> bag;

    /**
     * Embeddable classes
     */
    private Skills skills;

    public Char() {
    }

    public Char(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Getters and Setters
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getGoldAmount() {
        return goldAmount;
    }

    public void setGoldAmount(int goldAmount) {
        this.goldAmount = goldAmount;
    }

    public int getCrystalAmount() {
        return crystalAmount;
    }

    public void setCrystalAmount(int crystalAmount) {
        this.crystalAmount = crystalAmount;
    }

    public Armor getHelm() {
        return helm;
    }

    public void setHelm(Armor helm) {
        this.helm = helm;
    }

    public Armor getMainArmor() {
        return mainArmor;
    }

    public void setMainArmor(Armor mainArmor) {
        this.mainArmor = mainArmor;
    }

    public Armor getBoots() {
        return boots;
    }

    public void setBoots(Armor boots) {
        this.boots = boots;
    }

    public Armor getJewellery() {
        return jewellery;
    }

    public void setJewellery(Armor jewellery) {
        this.jewellery = jewellery;
    }

    public Armor getRing() {
        return ring;
    }

    public void setRing(Armor ring) {
        this.ring = ring;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public List<Equipment> getBag() {
        return bag;
    }

    public void setBag(List<Equipment> bag) {
        this.bag = bag;
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
    }

    public Skills getSkills() {
        return skills;
    }

    public void setSkills(Skills skills) {
        this.skills = skills;
    }

    public Date getLastTrainingDate() {
        return lastTrainingDate;
    }

    public void setLastTrainingDate(Date lastTrainingDate) {
        this.lastTrainingDate = lastTrainingDate;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public CharType getType() {
        return type;
    }

    public void setType(CharType type) {
        this.type = type;
    }

    /**
     * Behavior methods
     */
    public void unEquip(Equipment equipment) {
        switch (equipment.getType()) {
            case MAIN_ARMOR:
                replaceEquip(null, this.mainArmor);
                this.mainArmor = null;
                break;
            case BOOTS:
                replaceEquip(null, this.boots);
                this.boots = null;
                break;
            case RING:
                replaceEquip(null, this.ring);
                this.ring = null;
                break;
            case HELM:
                replaceEquip(null, this.helm);
                this.helm = null;
                break;
            case WEAPON:
                replaceEquip(null, this.weapon);
                this.weapon = null;
                break;
            case JEWELLERY:
                replaceEquip(null, this.jewellery);
                this.jewellery = null;
                break;
            default:
                break;
        }
    }

    public void train(int needToTrain, int conditionValue, int price, TrainingType trainingType) throws GoldNotEnoughException, ConditionNotEnoughException, TrainingNotPossible {
        //Если игрок проводил тренировку в последние 24 часа
        if (lastTrainingDate != null) {
            Date nextAvailableTraining = DateUtils.addDays(lastTrainingDate, 1);
            Date now = new Date();
            long timeDiff = nextAvailableTraining.getTime() - now.getTime();
            long hours = TimeUnit.MILLISECONDS.toHours(timeDiff);
            long minutes = TimeUnit.MILLISECONDS.toMinutes(timeDiff) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeDiff));
            long seconds = TimeUnit.MILLISECONDS.toSeconds(timeDiff) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeDiff));

            if (hours > 0 || minutes > 0 || seconds > 0)
                throw new TrainingNotPossible(String.format("Вы уже проводили тренировку в последние 24 часа. Пожалуйста подождите %02d:%02d:%02d до следующей тренировки",
                        hours, minutes, seconds));
        }
        //Если у игрока достаточно денег для проведения тренировок
        if (goldAmount - price < 0)
            throw new GoldNotEnoughException("Your gold is not enough!");

//        if (condition == null) {
//            condition = new Condition();
//            //condition.setLastUpdateTime(new Date());
//            condition.setCondition(100);
//        }
        //Если у игрока достаточно кондиции для проведения тренировок
        if (this.condition.getCondition() - conditionValue < 0)
            throw new ConditionNotEnoughException("The character is not in good condition. Please give it a rest and try again!");

        //Если у персонажа достаточно ресурсов - провести тренировку!
        switch (trainingType) {
            case STRENGTH:
                int oldStrength = this.skills.getStrength();
                this.skills.setStrength(oldStrength + needToTrain);
                break;
            case DEFENCE:
                int oldDefence = this.skills.getDefence();
                this.skills.setDefence(oldDefence + needToTrain);
                break;
            case STAMINA:
                int oldStamina = this.skills.getStamina();
                this.skills.setStamina(oldStamina + needToTrain);
                break;
            default:
                break;
        }
        this.goldAmount -= price;
        this.condition.setCondition(this.condition.getCondition() - conditionValue);
    }

    public void startWork() {
        work.setEndTime(DateUtils.addHours(new Date(), 6));
        work.setGoldTaked(false);
//        if (workEndTime == null || new Date().getTime() > workEndTime.getTime()) {
//            workEndTime = DateUtils.addHours(new Date(), 6);
//        }
    }

    public void addGold(int gold) {
        this.goldAmount += gold;
        this.work.setGoldTaked(true);
    }

    public void addCrystal(int crystalAmount) {
        this.crystalAmount += crystalAmount;
    }

    public void buy(Equipment equipmentById) throws GoldNotEnoughException {
        if (equipmentById.getPrice().compareTo(new BigDecimal(this.goldAmount)) == 1)
            throw new GoldNotEnoughException("You can not buy this item. Your gold amount is not enough");
        putInBag(equipmentById);
        goldAmount -= equipmentById.getPrice().intValue();
    }

    public void sell(Equipment equipmentById) {
        bag.remove(equipmentById);
        addGold(equipmentById.getPrice().intValue() / 2);
    }

    private void replaceEquip(Equipment toEquip, Equipment toTakeOff) {
        if (toTakeOff != null)
            putInBag(toTakeOff);
        bag.remove(toEquip);
    }

    public void equip(Equipment equipment) {
        switch (equipment.getType()) {
            case MAIN_ARMOR:
                replaceEquip(equipment, this.mainArmor);
                this.mainArmor = (Armor) equipment;
                break;
            case BOOTS:
                replaceEquip(equipment, this.boots);
                this.boots = (Armor) equipment;
                break;
            case RING:
                replaceEquip(equipment, this.ring);
                this.ring = (Armor) equipment;
                break;
            case HELM:
                replaceEquip(equipment, this.helm);
                this.helm = (Armor) equipment;
                break;
            case WEAPON:
                replaceEquip(equipment, this.weapon);
                this.weapon = (Weapon) equipment;
                break;
            case JEWELLERY:
                replaceEquip(equipment, this.jewellery);
                this.jewellery = (Armor) equipment;
                break;
            default:
                break;
        }
    }

    public int getTotalStamina() {
        int totalStamina = 0;
        if (helm != null)
            totalStamina += helm.getStamina();
        if (mainArmor != null)
            totalStamina += mainArmor.getStamina();
        if (boots != null)
            totalStamina += boots.getStamina();
        if (jewellery != null)
            totalStamina += jewellery.getStamina();
        if (ring != null)
            totalStamina += ring.getStamina();
        return totalStamina + skills.getStamina();
    }

    public int getTotalStrength() {
        int totalStrength = weapon != null ? weapon.getAttack() : 0;
        return totalStrength + skills.getStrength();
    }

    public int getTotalDefence() {
        int totalDefence = 0;
        if (helm != null)
            totalDefence += helm.getDefence();
        if (mainArmor != null)
            totalDefence += mainArmor.getDefence();
        if (boots != null)
            totalDefence += boots.getDefence();
        if (jewellery != null)
            totalDefence += jewellery.getDefence();
        if (ring != null)
            totalDefence += ring.getDefence();
        return totalDefence + skills.getDefence();
    }

    public static Char initializeChar(String name) {
        Char aChar = new Char();
        aChar.setName(name);
        aChar.setLvl(1);
        aChar.setHealth(100);
        aChar.setSkills(new Skills());
        return aChar;
    }

    public Statistic initializeStatisticForChar() {
        Statistic statistic = new Statistic();
        statistic.setaChar(this);
        statistic.setWinPercentage(new BigDecimal(0));
        this.setStatistic(statistic);
        return statistic;
    }

    public Condition initializeOrUpdateConditionForChar() {
        if (this.condition == null) {
            Condition condition = new Condition();
            condition.setCondition(100);
            condition.setLastUpdateTime(new Date());
            this.setCondition(condition);
            condition.setaChar(this);
        } else {
            Date now = new Date();
            long timeDifference = now.getTime() - this.getCondition().getLastUpdateTime().getTime();
            int hours = (int) (timeDifference / (60 * 60 * 1000));
            //Если прошло больше 3х часов, значит, что игроку можно зачислить единицы кондиции
            if (hours >= 3) {
                int updatedCondition = (this.getCondition().getCondition() + (6 * (hours / 3)));
                if (updatedCondition > 100)
                    updatedCondition = 100;
                this.getCondition().setCondition(updatedCondition);
                this.getCondition().setLastUpdateTime(now);
            }
        }
        return condition;
    }

    public void putInBag(Equipment equipment) {
        bag.add(equipment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Char aChar = (Char) o;
        return Objects.equals(id, aChar.id) &&
                Objects.equals(name, aChar.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Char{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lvl=" + lvl +
                '}';
    }

    public void updateStatistic(DuelResult result) {
        Statistic statistic = getStatistic();
        BigDecimal winPercentage = null;
        switch (result) {
            case WIN:
                statistic.setTotalBattle(statistic.getTotalBattle() + 1);
                statistic.setWinBattle(statistic.getWinBattle() + 1);
                int winBattle = statistic.getWinBattle();
                int winBattle1 = statistic.getWinBattle();
                winPercentage = new BigDecimal(statistic.getWinBattle() * 100 / statistic.getTotalBattle());
                statistic.setWinPercentage(winPercentage);
                break;
            case LOSE:
                statistic.setTotalBattle(statistic.getTotalBattle() + 1);
                statistic.setLoseBattle(statistic.getLoseBattle() + 1);
                winPercentage = new BigDecimal(statistic.getWinBattle() * 100 / statistic.getTotalBattle());
                statistic.setWinPercentage(winPercentage);
                break;
            default:
                break;
        }
    }

    @Override
    public int compareTo(Char o) {
        return this.getStatistic().getWinPercentage().compareTo(o.getStatistic().getWinPercentage());
    }
}