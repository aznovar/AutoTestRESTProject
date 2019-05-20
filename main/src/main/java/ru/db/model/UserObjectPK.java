package main.java.ru.db.model;


import java.io.Serializable;

@Embeddable
public class UserObjectPK implements Serializable {
    /**
     * Класс составного ключа Пользовательского объекта (в статусе 'Новый')
     */

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    public UserObjectPK(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public UserObjectPK() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
