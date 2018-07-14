package ru.artur.darkknight.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "statistic")
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "total_battle", nullable = false)
    private int totalBattle;
    @Column(name = "total_win", nullable = false)
    private int winBattle;
    @Column(name = "total_lose", nullable = false)
    private int loseBattle;
    @Column(name = "win_percentage", nullable = false)
    private BigDecimal winPercentage = BigDecimal.ZERO;

    @OneToOne(mappedBy = "statistic")
    private Knight knight;

    public int getTotalBattle() {
        return totalBattle;
    }

    public void setTotalBattle(int totalBattle) {
        this.totalBattle = totalBattle;
    }

    public int getWinBattle() {
        return winBattle;
    }

    public void setWinBattle(int winBattle) {
        this.winBattle = winBattle;
    }

    public int getLoseBattle() {
        return loseBattle;
    }

    public void setLoseBattle(int loseBattle) {
        this.loseBattle = loseBattle;
    }

    public BigDecimal getWinPercentage() {
        return winPercentage;
    }

    public void setWinPercentage(BigDecimal winPercentage) {
        this.winPercentage = winPercentage;
    }

    public void setKnight(Knight knight) {
        this.knight = knight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Knight getKnight() {
        return knight;
    }
}
