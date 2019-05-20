package main.java.ru.api.entity.rules;

import main.java.ru.api.entity.BaseEntityInterface;

import java.util.List;

public class Rule implements BaseEntityInterface<Rule> {

    private Integer id;
    private String name;
    private Boolean checkResults;
    private Boolean checkScore;
    private String comment;
    private String epl;
    private String eventType;
    private FilterExpr filterExpr;
    private FilterExpr aggregationExpr;
    private List<String> groupByFields;
    private String groupId;
    private RuleType ruleType;
    private String score;
    private StatusEnum status;
    private String time;
    private String timeUnit;
    private Integer timeInSeconds;
    private Integer order;
    private String policyMatrixRowRuleType;
    private List<RuleLink> ruleLinks;

    private Rule() {
    }

    private Rule(RuleBuilder builder) {
        id = builder.id;
        name = builder.name;
        groupId = builder.groupId;
        eventType = builder.eventType;
        comment = builder.comment;
        filterExpr = builder.filterExpr;
        aggregationExpr = builder.aggregationExpr;
        score = builder.score;
        checkScore = builder.checkScore;
        checkResults = builder.checkResults;
        ruleType = builder.ruleType;
        status = builder.status;
        epl = builder.epl;
        groupByFields = builder.groupByFields;
        time = builder.time;
        order = builder.order;
        policyMatrixRowRuleType = builder.policyMatrixRowRuleType;
        ruleLinks = builder.ruleLinks;
        timeUnit = builder.timeUnit;
        timeInSeconds = builder.timeInSeconds;
    }

    public static RuleBuilder builder() {
        return new RuleBuilder();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public Boolean getCheckResults() {
        return checkResults;
    }

    public Boolean getCheckScore() {
        return checkScore;
    }

    public String getEpl() {
        return epl;
    }

    public String getEventType() {
        return eventType;
    }

    public FilterExpr getFilterExpr() {
        return filterExpr;
    }

    public String getGroupId() {
        return groupId;
    }

    public RuleType getRuleType() {
        return ruleType;
    }

    public String getScore() {
        return score;
    }

    public List<String> getGroupByFields() {
        return groupByFields;
    }

    public String getTime() {
        return time;
    }

    public FilterExpr getAggregationExpr() {
        return aggregationExpr;
    }

    public Integer getOrder() {
        return order;
    }

    public String getPolicyMatrixRowRuleType() {
        return policyMatrixRowRuleType;
    }

    public List<RuleLink> getRuleLinks() {
        return ruleLinks;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public Integer getTimeInSeconds() {
        return timeInSeconds;
    }

    @Override
    public String singleEntityUrl() {
        return "/some/address/to_single";
    }

    @Override
    public String multipleEntitiesUrl() {
        return "/some/address/to_multiple";
    }

    @Override
    public String entityByIdUrl() {
        return "/some/address/entity_by_id" + id;
    }

    @Override
    public void update(Rule fromObj) {
        filterExpr = fromObj.filterExpr;
        aggregationExpr = fromObj.aggregationExpr;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "id='" + id + '\'' +
                ", name='" + nvl(name) + '\'' +
                ", description='" + nvl(comment) + '\'' +
                ", status=" + status +
                '}';
    }

    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
    public static class RuleBuilder implements BuilderInterface<Rule> {
        protected Integer id;
        protected String name;
        protected StatusEnum status;
        protected String comment;
        protected Boolean checkResults;
        protected Boolean checkScore;
        protected String epl;
        protected String eventType;
        protected FilterExpr filterExpr;
        protected FilterExpr aggregationExpr;
        protected String groupId;
        protected RuleType ruleType;
        protected String score;
        protected List<String> groupByFields;
        protected String time;
        protected String timeUnit;
        protected Integer order;
        protected Integer timeInSeconds;
        protected String policyMatrixRowRuleType;
        protected List<RuleLink> ruleLinks;

        public RuleBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public RuleBuilder name(String name) {
            this.name = name;
            return this;
        }

        public RuleBuilder status(StatusEnum status) {
            this.status = status;
            return this;
        }

        public RuleBuilder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public RuleBuilder checkResults(Boolean checkResults) {
            this.checkResults = checkResults;
            return this;
        }

        public RuleBuilder checkScore(Boolean checkScore) {
            this.checkScore = checkScore;
            return this;
        }

        public RuleBuilder epl(String epl) {
            this.epl = epl;
            return this;
        }

        public RuleBuilder eventType(String eventType) {
            this.eventType = eventType;
            return this;
        }

        public RuleBuilder filterExpr(FilterExpr filterExpr) {
            this.filterExpr = filterExpr;
            return this;
        }

        public RuleBuilder aggregationExpr(FilterExpr aggregationExpr) {
            this.aggregationExpr = aggregationExpr;
            return this;
        }

        public RuleBuilder groupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        public RuleBuilder ruleType(RuleType ruleType) {
            this.ruleType = ruleType;
            return this;
        }

        public RuleBuilder score(String score) {
            this.score = score;
            return this;
        }

        public RuleBuilder groupByFields(List<String> groupByFields) {
            this.groupByFields = groupByFields;
            return this;
        }

        public RuleBuilder time(String time) {
            this.time = time;
            return this;
        }

        public RuleBuilder timeUnit(String timeUnit) {
            this.timeUnit = timeUnit;
            return this;
        }

        public RuleBuilder timeInSeconds(Integer timeInSeconds) {
            this.timeInSeconds = timeInSeconds;
            return this;
        }

        public RuleBuilder order(Integer order) {
            this.order = order;
            return this;
        }

        public RuleBuilder policyMatrixRowRuleType(String policyMatrixRowRuleType) {
            this.policyMatrixRowRuleType = policyMatrixRowRuleType;
            return this;
        }

        public RuleBuilder ruleLinks(List<RuleLink> ruleLinks) {
            this.ruleLinks = ruleLinks;
            return this;
        }

        @Override
        public Rule build() {
            return new Rule(this);
        }

        @Override
        public RuleBuilder randomize() {
            name = new Param.ParamBuilder()
                    .prefix("Rule_")
                    .maxLen(4)
                    .generate()
                    .build()
                    .getName();
            comment = Param.genAutoName(15);
            return this;
        }

        public RuleBuilder buildMatrixRule(
                Integer order,
                String policyMatrixRowRuleType,
                List<RuleLink> ruleLinks) {
            return Rule.builder()
                    .order(order)
                    .policyMatrixRowRuleType(policyMatrixRowRuleType)
                    .ruleLinks(ruleLinks);
        }
    }
}
