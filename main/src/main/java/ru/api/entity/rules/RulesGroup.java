package main.java.ru.api.entity.rules;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import main.java.ru.api.Param;
import main.java.ru.api.entity.BaseEntityInterface;
import main.java.ru.api.entity.BuilderInterface;
import main.java.ru.api.enums.StatusEnum;

@JsonRootName("groups")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RulesGroup implements BaseEntityInterface<RulesGroup> {

    private String id;
    private String name;
    private String description;
    private StatusEnum status;

    private RulesGroup(RulesGroupBuilder builder) {
        id = builder.id;
        name = builder.name;
        description = builder.description;
        status = builder.status;
    }

    private RulesGroup(){}

    public static RulesGroupBuilder builder() {
        return new RulesGroupBuilder();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public StatusEnum getStatus() {
        return status;
    }

    @Override
    public String singleEntityUrl() {
        return "/some_address";
    }

    @Override
    public String multipleEntitiesUrl() {
        return "/some_address";
    }

    @Override
    public String entityByIdUrl() {
        return "/some_address/" + id;
    }

    @Override
    public void update(RulesGroup fromObj) {
        description = fromObj.description;
        status = fromObj.status;
    }

    @Override
    public String toString() {
        return "RulesGroup{" +
                "id='" + nvl(id) + '\'' +
                ", name='" + nvl(name) + '\'' +
                ", description='" + nvl(description) + '\'' +
                ", status=" + status +
                '}';
    }

    @JsonIgnoreProperties("rules")
    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
    public static class RulesGroupBuilder implements BuilderInterface<RulesGroup> {

        protected String id;
        protected String name;
        protected String description;
        private StatusEnum status;

        public RulesGroupBuilder id(String id) {
            this.id = id;
            return this;
        }

        public RulesGroupBuilder name(String name) {
            this.name = name;
            return this;
        }

        public RulesGroupBuilder description(String description) {
            this.description = description;
            return this;
        }

        public RulesGroupBuilder status(StatusEnum status) {
            this.status = status;
            return this;
        }

        @Override
        public RulesGroupBuilder randomize() {
            name = new Param.ParamBuilder()
                    .prefix("JDAUTO_Group_")
                    .maxLen(7)
                    .generate()
                    .build()
                    .getName();
            description = Param.genAutoName(15);
            return this;
        }

        @Override
        public RulesGroup build() {
            return new RulesGroup(this);
        }
    }

}
