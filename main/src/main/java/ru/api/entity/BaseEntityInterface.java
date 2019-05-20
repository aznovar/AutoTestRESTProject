package main.java.ru.api.entity;

public interface BaseEntityInterface<T> {
    String singleEntityUrl();

    String multipleEntitiesUrl();

    String entityByIdUrl();

    void update(T fromObj);
}
