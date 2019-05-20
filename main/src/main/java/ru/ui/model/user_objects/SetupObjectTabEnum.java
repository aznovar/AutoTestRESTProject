package main.java.ru.ui.model.user_objects;

import java.util.Arrays;

/**
 * Перечисление вкладок страницы редактирования объекта на странице Объектная модель - Объекты (Список объектов)
 */
public enum SetupObjectTabEnum {

    OBJECT("Объект"),
    ATTRIBUTES("Атрибуты"),
    FIELDS("Поля"),
    ADDITIONAL_OPTIONS("Дополнительные опции"),
    VISUALIZATION("Визуализация");

    private String tabName;

    SetupObjectTabEnum(String tabName) {
        this.tabName = tabName;
    }

    public static SetupObjectTabEnum findTab(String soughtTab) {
        return Arrays.stream(values()).filter(tab -> tab.getTabName().equals(soughtTab))
                .findFirst().orElse(null);
    }

    public String getTabName() {
        return tabName;
    }
}
