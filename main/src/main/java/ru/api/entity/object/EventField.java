package main.java.ru.api.entity.object;

import ru.jd.api.enums.JDConstantExpressionValueType;

public class EventField {

    private String name;
    private String displayName;
    private String group;
    private JDConstantExpressionValueType valueType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JDConstantExpressionValueType getValueType() {
        return valueType;
    }

    public void setValueType(JDConstantExpressionValueType valueType) {
        this.valueType = valueType;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
