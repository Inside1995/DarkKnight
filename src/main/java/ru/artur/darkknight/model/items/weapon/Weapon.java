package ru.artur.darkknight.model.items.weapon;

import ru.artur.darkknight.model.items.Equipment;
import ru.artur.darkknight.model.items.EquipmentType;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "weapon")
@PrimaryKeyJoinColumn(name = "id")
public class Weapon extends Equipment {
    private int attack;

    public Weapon() {
    }

    public Weapon(String name, BigDecimal price, EquipmentType type, byte[] avatar, int attack) {
        super(name, price, type, avatar);
        this.attack = attack;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public String toString() {
        return String.format("Название: %s <br/>" +
                "Атака: %d <br/>" +
                "Выносливость: %d", this.getName(), this.getAttack(), this.getStamina());
    }
}
