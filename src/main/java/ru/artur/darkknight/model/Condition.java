package ru.artur.darkknight.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "knight_condition")
public class Condition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "condition_value")
    private int condition;
    @Column(name = "last_update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateTime;

    @OneToOne(mappedBy = "condition")
    private Char aChar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Char getaChar() {
        return aChar;
    }

    public void setaChar(Char aChar) {
        this.aChar = aChar;
    }
}
