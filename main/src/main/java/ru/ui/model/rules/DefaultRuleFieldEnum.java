package main.java.ru.ui.model.rules;

import java.util.Random;

public enum DefaultRuleFieldEnum {

    ID("id"),
    IS_DELETED("is_deleted"),
    OWNERSHIP_ID("ownership_id"),
    LAST_CHANGE("last_change"),
    LAST_USER("last_user");

    private String displayName;

    DefaultRuleFieldEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static DefaultRuleFieldEnum getRandomField(){
        return values()[ new Random().nextInt(values().length)];
    }
}
