package main.java.ru.ui.model.user_objects;

/**
 * Перечисление заголовков столбцов страниц Пользотельские объекты (События, Списки, Справочники)
 */
public enum NominationColumnHeaderEnum {

    NOMINATION("Наименование");

    private String tableHeaderName;

    NominationColumnHeaderEnum(String tableHeaderName) {
        this.tableHeaderName = tableHeaderName;
    }

    public String getTableHeaderName() {
        return tableHeaderName;
    }
}
