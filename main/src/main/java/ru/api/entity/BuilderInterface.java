package main.java.ru.api.entity;

public interface BuilderInterface<T extends BaseEntityInterface<T>> {
    BuilderInterface randomize();

    T build();
}
