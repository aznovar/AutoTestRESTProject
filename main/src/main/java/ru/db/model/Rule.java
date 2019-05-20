package main.java.ru.db.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Класс, описывающий объектную модель БД 'Правило'
 */
@Entity
@Table(name = "rule")
public class Rule implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "group_id", nullable = false)
    private int groupId;

    @Column(name = "rule_type", nullable = false)
    private String ruleType;

    @Column(name = "event_type", nullable = false)
    private String eventType;

    @Column(name = "`comment`")
    private String comment;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "properties", nullable = false)
    private String properties;

    @Column(name = "epl")
    private String epl;

    @Column(name = "ownership_id", nullable = false)
    private int ownershipId;

    @Column(name = "last_user", nullable = false)
    private String lastUser;

    @Column(name = "last_change", nullable = false)
    private Date lastChange;

    @Column(name = "score")
    private int score;

    @Column(name = "check_score", nullable = false)
    private String checkScore;

    @Column(name = "check_results", nullable = false)
    private String checkResults;

    public Rule() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getRuleType() {
        return ruleType;
    }

    public String getEventType() {
        return eventType;
    }

    public String getComment() {
        return comment;
    }

    public String getStatus() {
        return status;
    }

    public String getProperties() {
        return properties;
    }

    public String getEpl() {
        return epl;
    }

    public int getOwnershipId() {
        return ownershipId;
    }

    public String getLastUser() {
        return lastUser;
    }

    public Date getLastChange() {
        return lastChange;
    }

    public int getScore() {
        return score;
    }

    public String isCheckScore() {
        return checkScore;
    }

    public String isCheckResults() {
        return checkResults;
    }
}
