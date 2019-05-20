package main.java.ru.api.entity.object;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;

import static ru.jd.sup.single.Const.*;

public class Attribute {

    private String type;
    private String valueType;
    private String name;
    private String caption;
    private String refDict;
    private Boolean hidden;
    private Boolean readonly;
    private Boolean createOnly;
    private Boolean required;
    private Boolean refAttrHidden;
    private Boolean persist;
    private List<String> fields;

    private Attribute(AttributeBuilder builder) {
        this.type = builder.type;
        this.valueType = builder.valueType;
        this.name = builder.name;
        this.caption = builder.caption;
        this.refDict = builder.refDict;
        this.hidden = builder.hidden;
        this.readonly = builder.readonly;
        this.createOnly = builder.createOnly;
        this.required = builder.required;
        this.refAttrHidden = builder.refAttrHidden;
        this.persist = builder.persist;
        this.fields = builder.fields;
    }

    private Attribute(){}

    public static AttributeBuilder builder() {
        return new AttributeBuilder();
    }

    public String getType() {
        return type;
    }

    public String getValueType() {
        return valueType;
    }

    public String getName() {
        return name;
    }

    public String getCaption() {
        return caption;
    }

    public String getRefDict() {
        return refDict;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public Boolean getReadonly() {
        return readonly;
    }

    public Boolean getCreateOnly() {
        return createOnly;
    }

    public Boolean getRequired() {
        return required;
    }

    public Boolean getRefAttrHidden() {
        return refAttrHidden;
    }

    public Boolean getPersist() {
        return persist;
    }

    public List<String> getFields() {
        return fields;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "name='" + nvl(name) + '\'' +
                ", type='" + nvl(type) + '\'' +
                ", valueType='" + nvl(valueType) + '\'' +
                ", caption=" + nvl(caption) +
                ", refDict='" + nvl(refDict) + '\'' +
                ", hidden='" + hidden + '\'' +
                ", readonly='" + boolNvl(readonly) + '\'' +
                ", createOnly='" + boolNvl(createOnly) + '\'' +
                ", required='" + boolNvl(required) + '\'' +
                ", refAttrHidden='" + boolNvl(refAttrHidden) + '\'' +
                ", persist='" + boolNvl(persist) + '\'' +
                ", fields='" + listNvl(fields) + '\'' +
                '}';
    }

    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
    public static class AttributeBuilder {

        private String type;
        private String valueType;
        private String name;
        private String caption;
        private String refDict;
        private Boolean hidden;
        private Boolean readonly;
        private Boolean createOnly;
        private Boolean required;
        private Boolean refAttrHidden;
        private Boolean persist;
        private List<String> fields;

        public AttributeBuilder type(String type) {
            this.type = type;
            return this;
        }

        public AttributeBuilder valueType(String valueType) {
            this.valueType = valueType;
            return this;
        }

        public AttributeBuilder caption(String caption) {
            this.caption = caption;
            return this;
        }

        public AttributeBuilder name(String name) {
            this.name = name;
            return this;
        }

        public AttributeBuilder refDict(String refDict) {
            this.refDict = refDict;
            return this;
        }

        public AttributeBuilder hidden(Boolean hidden) {
            this.hidden = hidden;
            return this;
        }

        public AttributeBuilder readonly(Boolean readonly) {
            this.readonly = readonly;
            return this;
        }

        public AttributeBuilder createOnly(Boolean createOnly) {
            this.createOnly = createOnly;
            return this;
        }

        public AttributeBuilder required(Boolean required) {
            this.required = required;
            return this;
        }

        public AttributeBuilder refAttrHidden(Boolean refAttrHidden) {
            this.refAttrHidden = refAttrHidden;
            return this;
        }

        public AttributeBuilder persist(Boolean persist) {
            this.persist = persist;
            return this;
        }

        public AttributeBuilder fields(List<String> fields) {
            this.fields = fields;
            return this;
        }

        public Attribute build() {
            return new Attribute(this);
        }
    }
}
