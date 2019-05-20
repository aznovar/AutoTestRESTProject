package main.java.ru.ui.steps.preconditions;

import io.restassured.response.Response;
import ru.jd.api.dao.ProviderImpl;
import ru.jd.api.entity.bom.BomObject;
import ru.jd.api.entity.bom.EventField;
import ru.jd.api.entity.rule.Rule;
import ru.jd.api.entity.rule.RulesGroup;
import ru.jd.api.entity.rule.subentity.ConstantExpr;
import ru.jd.api.enums.JDConstantExpressionValueType;
import ru.jd.api.enums.MetaType;
import ru.jd.api.enums.RuleType;
import ru.jd.sup.excpt.CoreProblem;
import ru.jd.sup.single.Const;
import ru.jd.sup.single.Param;
import ru.jd.ui.steps.api.RuleBuilderSteps;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static ru.jd.api.enums.RuleType.CONDITIONAL;
import static ru.jd.api.mapper.JsonMapperFactory.newMapper;

public class RulePreconditionSteps {

    private RuleBuilderSteps ruleBuilderSteps;
    private BomObjectPreconditionSteps bomObjectPreconditionSteps;

    public RulePreconditionSteps() {
        ruleBuilderSteps = new RuleBuilderSteps();
        bomObjectPreconditionSteps = new BomObjectPreconditionSteps();
    }

    public RulesGroup createRulesGroup() {
        ProviderImpl<RulesGroup> provider = new ProviderImpl<>(Const.JD_API_URL);
        return provider.create(RulesGroup.builder().randomize().build(), newMapper());
    }

    public RulesGroup createRulesGroup(String name) {
        ProviderImpl<RulesGroup> provider = new ProviderImpl<>(Const.JD_API_URL);
        String groupName = new Param.ParamBuilder()
                .prefix("Group_" + name)
                .maxLen(7)
                .generate()
                .build()
                .getName();
        return provider.create(RulesGroup.builder().name(groupName).build(), newMapper());
    }

    public Rule getRuleExample(RuleType ruleType,
                               RulesGroup rulesGroup,
                               String eventName,
                               Boolean isScore) throws CoreProblem {
        ProviderImpl<Rule> provider = new ProviderImpl<>(Const.JD_API_URL);
        Rule.RuleBuilder ruleBuilder = ruleBuilderSteps.getRuleBuilder(ruleType, rulesGroup, eventName, isScore, false);
        ConstantExpr filterExpr = ruleBuilderSteps.buildConstantFilterExpr("0", JDConstantExpressionValueType.BOOLEAN);
        if (ruleType.equals(CONDITIONAL)) {
            return ruleBuilderSteps.build(ruleBuilder.build(), filterExpr);
        } else {
            String sendReq = "/jd-cep-coordinator/cep/event/types/" + eventName;
            List<EventField> groupByFieldAll;
            List<String> groupByField;
            try {
                // Этот подзапрос в точности соответствует логике обращений с правилами,
                // для агрегатных правил требуется группировка по полям событий иначе не сработает
                Response response = provider.requestGet(sendReq);
                groupByFieldAll = provider.deserializeList(newMapper(), response.asString(), new EventField());
                groupByField = Collections.singletonList(groupByFieldAll.get(0).getName());
                ruleBuilder = ruleBuilder.time("100").timeUnit("S").groupByFields(groupByField);
            } catch (IOException e) {
                throw Const.rethrow("Не удалось получить список полей событий", e);
            }
            ConstantExpr aggregationExpr = ruleBuilderSteps.buildConstantFilterExpr("0", JDConstantExpressionValueType.BOOLEAN);
            return ruleBuilderSteps.build(ruleBuilder.build(), filterExpr, aggregationExpr);
        }
    }

    public Rule createRule(
            RuleType ruleType,
            Boolean isScore) throws CoreProblem {
        ProviderImpl<Rule> provider = new ProviderImpl<>(Const.JD_API_URL);
        RulesGroup rulesGroup = createRulesGroup();
        BomObject event = bomObjectPreconditionSteps.createBomObject(isScore, MetaType.event);
        bomObjectPreconditionSteps.applyBomObject(event);
        Rule newRule = getRuleExample(ruleType, rulesGroup, event.getName(), isScore);
        return provider.create(newRule, newMapper());
    }

    public Rule createAggregateRule(
            String ruleName,
            RuleType ruleType,
            RulesGroup rulesGroup,
            BomObject event,
            Boolean isScore) throws CoreProblem {
        String name = new Param.ParamBuilder()
                .prefix("Rule_" + ruleName)
                .maxLen(4)
                .generate()
                .build()
                .getName();
        ProviderImpl<Rule> provider = new ProviderImpl<>(Const.JD_API_URL);
        Rule.RuleBuilder ruleBuilder = Rule.builder()
                .groupId(rulesGroup.getId())
                .eventType(event.getName())
                .ruleType(ruleType)
                .checkResults(false)
                .name(name)
                .checkScore(isScore)
                .score(isScore ? "100" : "0");
        ConstantExpr filterExpr = ruleBuilderSteps.buildConstantFilterExpr("0", JDConstantExpressionValueType.BOOLEAN);
        String sendReq = "/jd-cep-coordinator/cep/event/types/" + event.getName();
        List<EventField> groupByFieldAll;
        List<String> groupByField;
        try {
            // Этот подзапрос в точности соответствует логике обращений с правилами,
            // для агрегатных правил требуется группировка по полям событий иначе не сработает
            Response response = provider.requestGet(sendReq);
            groupByFieldAll = provider.deserializeList(newMapper(), response.asString(), new EventField());
            groupByField = Collections.singletonList(groupByFieldAll.get(0).getName());
            ruleBuilder = ruleBuilder.time("100").timeUnit("S").groupByFields(groupByField);
        } catch (IOException e) {
            throw Const.rethrow("Не удалось получить список полей событий", e);
        }
        ConstantExpr aggregationExpr = ruleBuilderSteps.buildConstantFilterExpr("0", JDConstantExpressionValueType.BOOLEAN);
        Rule rule = ruleBuilderSteps.build(ruleBuilder.build(), filterExpr, aggregationExpr);
        return provider.create(rule, newMapper());
    }

    public Rule createRelationalOpExprRule(String existedBomObjectName, RuleType ruleType,
                                           Boolean isScore, Boolean isCheckResults,
                                           String attributeName, String attributeValue) throws CoreProblem {
        ProviderImpl<Rule> provider = new ProviderImpl<>(Const.JD_API_URL);
        RulesGroup rulesGroup = createRulesGroup();
        Rule newRule = ruleBuilderSteps.getRuleRelationalOp(ruleType, rulesGroup, existedBomObjectName,
                isScore, isCheckResults, attributeName, attributeValue);
        return provider.create(newRule, newMapper());
    }

    public Rule createEnumExprRule(String existedBomObjectName, RuleType ruleType, Boolean isScore, String values,
                                   String propertyName) throws CoreProblem {
        ProviderImpl<Rule> provider = new ProviderImpl<>(Const.JD_API_URL);
        RulesGroup rulesGroup = createRulesGroup();
        Rule newRule = ruleBuilderSteps.getRuleEnum(ruleType, rulesGroup, existedBomObjectName, isScore, values, propertyName);
        return provider.create(newRule, newMapper());
    }

    public Rule createAttributeExprRule(String ruleName, RulesGroup groupName, BomObject object, RuleType ruleType, Boolean isScore) {
        ProviderImpl<Rule> provider = new ProviderImpl<>(Const.JD_API_URL);
        Rule newRule = ruleBuilderSteps.getRulePropertyValue(ruleName, ruleType, groupName, object, isScore);
        return provider.create(newRule, newMapper());
    }
}
