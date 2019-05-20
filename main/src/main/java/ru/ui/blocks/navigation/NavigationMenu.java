package main.java.ru.ui.blocks.navigation;

public enum NavigationMenu {

    DASHBOARD("Рабочий стол", "#dashboard"),

    AGGREGATES("Агрегаты", "#aggregate"),
    EVENTS("События", "#event"),
    LISTS("Списки", "#list"),
    DICTIONARIES("Справочники", "#reference-data"),

    OBJECTS("Объекты", "#bom/object"),
    RULES("Правила", "#cep/rules");


//    Данные пункты меню исключены из тестирования
//    TRAINING_MODELS("Модели обучения", "#models/editor"),
//    TRAINING_SAMPLES("Обучающие выборки", "#models/training-sample");

    private String menuName;
    private String uri;

    NavigationMenu(String menuName, String uri) {
        this.menuName = menuName;
        this.uri = uri;
    }

    public String getMenuName() {
        return menuName;
    }

    public String getUri() {
        return uri;
    }
}
