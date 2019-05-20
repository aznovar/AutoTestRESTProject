package main.java.ru.api.enums;

public enum StatusEnum {
    CREATED(""),
    UPDATED(""),
    DELETED(""),
    DEPLOYED(""),
    UNDEPLOYED(""),
    TESTING(""),
    TESTED(""),
    NEW("Новый"),
    CHANGED("Изменён"),
    APPLIED("Применён"),
    SUCCESS(""),
    STOPPED("");

    private String status;

    StatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
