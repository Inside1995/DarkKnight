package ru.artur.darkknight.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Skills {
    private int strength;
    private int stamina;
    private int defence;
    private int spirit;
    @Column(name = "magic_defence")
    private int magicDefence;

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getMagicDefence() {
        return magicDefence;
    }

    public void setMagicDefence(int magicDefence) {
        this.magicDefence = magicDefence;
    }

    public int getSpirit() {
        return spirit;
    }

    public void setSpirit(int spirit) {
        this.spirit = spirit;
    }
}
