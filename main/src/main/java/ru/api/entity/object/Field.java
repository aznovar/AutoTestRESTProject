package main.java.ru.api.entity.object;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;

import static ru.jd.sup.single.Const.listNvl;
import static ru.jd.sup.single.Const.nvl;

public class Field {

    private String name;
    private String type;
    private Integer size;
    private Integer precision;
    private String defaultValue;
    private String indexes;
    private List<String> enumValues;

    private Field(FieldBuilder builder) {
        this.name = builder.name;
        this.type = builder.type;
        this.size = builder.size;
        this.precision = builder.precision;
        this.defaultValue = builder.defaultValue;
        this.indexes = builder.indexes;
        this.enumValues = builder.enumValues;
    }

    public Field() {
    }

    public static FieldBuilder builder() {
        return new FieldBuilder();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getPrecision() {
        return precision;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public String getIndexes() {
        return indexes;
    }

    public List<String> getEnumValues() {
        return enumValues;
    }

    @Override
    public String toString() {
        return "Field{" +
                "name='" + nvl(name) + '\'' +
                ", type='" + nvl(type) + '\'' +
                ", size='" + size + '\'' +
                ", precision=" + precision +
                ", defaultValue='" + nvl(defaultValue) + '\'' +
                ", indexes='" + nvl(indexes) + '\'' +
                ", enumValues='" + listNvl(enumValues) + '\'' +
                '}';
    }

    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
    public static class FieldBuilder {

        private String name;
        private String type;
        private Integer size;
        private Integer precision;
        private String defaultValue;
        private String indexes;
        private List<String> enumValues;

        public FieldBuilder name(String name) {
            this.name = name;
            return this;
        }

        public FieldBuilder type(String type) {
            this.type = type;
            return this;
        }

        public FieldBuilder size(Integer size) {
            this.size = size;
            return this;
        }

        public FieldBuilder precision(Integer precision) {
            this.precision = precision;
            return this;
        }

        public FieldBuilder defaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        public FieldBuilder indexes(String indexes) {
            this.indexes = indexes;
            return this;
        }

        public FieldBuilder enumValues(List<String> enumValues) {
            this.enumValues = enumValues;
            return this;
        }

        public Field build() {
            return new Field(this);
        }
    }
}
