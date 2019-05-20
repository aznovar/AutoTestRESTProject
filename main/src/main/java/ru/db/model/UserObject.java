package main.java.ru.db.model;

import java.io.Serializable;

/**
 * Класс, описывающий объектную модель БД 'Oбъект' (в статусе 'Новый')
 */
@Entity
@Table(name = "jafs.metadata_head")
public class UserObject implements Serializable {

    @EmbeddedId
    private UserObjectPK userObjectPK;

    @Column(name = "version_id", nullable = false)
    private int versionId;

    /**
     * Дефолтный конструктор
     */
    public UserObject() {
    }

    /**
     * Геттеры и сеттеры
     */
    public UserObjectPK getUserObjectPK() {
        return userObjectPK;
    }

    public void setUserObjectPK(UserObjectPK userObjectPK) {
        this.userObjectPK = userObjectPK;
    }

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int versionId) {
        this.versionId = versionId;
    }
}
