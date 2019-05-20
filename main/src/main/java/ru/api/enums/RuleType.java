package main.java.ru.api.enums;

public enum RuleType {
    CONDITIONAL("Простое правило"),
    AGGREGATION("Агрегативное правило");

    private String displayName;

    RuleType(String str) {
        this.displayName = str;
    }

    public String getDisplayName() {
        return displayName;
    }

}
