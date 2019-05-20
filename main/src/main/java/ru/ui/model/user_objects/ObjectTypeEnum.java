package main.java.ru.ui.model.user_objects;

/**
 * Перечисление видов объектов на странице Объектная модель - Объекты (Список объектов)
 */
public enum ObjectTypeEnum {

    EVENT("Событие"),
    DICTIONARIES("Справочники"),
    TRAINING_SAMPLES("Обучающие выборки"),
    APP_LEVEL("Уровень приложения"),
    LIST("Список");

    private String type;

    ObjectTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
