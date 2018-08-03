package ru.artur.darkknight.model.items.armor;

import ru.artur.darkknight.model.items.Equipment;
import ru.artur.darkknight.model.items.EquipmentType;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "armor")
@PrimaryKeyJoinColumn(name = "id")
public class Armor extends Equipment {
    private int defence;

    public Armor() {
    }

    public Armor(Long id, String name, BigDecimal price, EquipmentType type, byte[] avatar, int defence) {
        super(name, price, type, avatar);
        this.setId(id);
        this.defence = defence;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    //Оформлено специально для корректного отображения на html странице. Использованы HTML теги.
    @Override
    public String toString() {
        return String.format("Название: %s <br/>" +
                "Защита: %d <br/>" +
                "Выносливость: %d", this.getName(), this.getDefence(), this.getStamina());
    }
}
