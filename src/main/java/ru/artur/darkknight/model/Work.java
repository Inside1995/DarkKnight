package ru.artur.darkknight.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "work")
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_time")
    private Date endTime;
    @Column(name = "gold_taked")
    private boolean goldTaked;

    @OneToOne(mappedBy = "work")
    private Knight knight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public boolean isGoldTaked() {
        return goldTaked;
    }

    public void setGoldTaked(boolean goldTaked) {
        this.goldTaked = goldTaked;
    }

    public Knight getKnight() {
        return knight;
    }

    public void setKnight(Knight knight) {
        this.knight = knight;
    }

    @Override
    public String toString() {
        return "Work{" +
                "id=" + id +
                ", endTime=" + endTime +
                ", goldTaked=" + goldTaked +
                '}';
    }
}
