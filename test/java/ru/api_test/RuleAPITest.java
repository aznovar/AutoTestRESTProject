package ru.api_test;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import main.java.ru.api.dao.DaoProviderImplementation;
import main.java.ru.db.model.Rule;
import main.java.ru.ui.steps.preconditions.RulePreconditionSteps;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static main.java.ru.api.enums.RuleType.AGGREGATION;
import static main.java.ru.api.enums.RuleType.CONDITIONAL;


@Test(groups = {"smoke"})
public class RuleAPITest {

    private RulePreconditionSteps ruleSteps;

    @BeforeClass(description = "Настройки перед RuleAPITest классом")
    public void setupClass() {
        ruleSteps = new RulePreconditionSteps();
    }

    @Test(description = "Пример создания Простого Правила по примеру", timeOut = 60000)
    public void testAPICreateSimpleRuleWithParams() throws CoreProblem {
        Rule response = ruleSteps.createRule(CONDITIONAL, false);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(response.getId()).isNotNull();
        softly.assertThat(response.getName()).isEqualTo(response.getName());
        softly.assertThat(response.getRuleType()).isEqualTo(CONDITIONAL);
        softly.assertThat(response.getCheckScore()).isEqualTo(false);
        softly.assertThat(response.getScore()).isEqualTo("0");
        softly.assertAll();
    }

    @Test(description = "Пример создания Агрегатного Правила по примеру", timeOut = 60000)
    public void testAPICreateAggregateRuleWithParams() throws CoreProblem {
        Rule response = ruleSteps.createRule(AGGREGATION, false);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(response.getId()).isNotNull();
        softly.assertThat(response.getName()).isEqualTo(response.getName());
        softly.assertThat(response.getRuleType()).isEqualTo(AGGREGATION);
        softly.assertThat(response.getCheckScore()).isEqualTo(false);
        softly.assertThat(response.getScore()).isEqualTo("0");
        softly.assertAll();
    }

    @Test(description = "Пример получения списка всех существующих Правил", timeOut = 60000)
    public void testAPIGetRulesList() throws IOException, CoreProblem {
        // Создание правила, чтобы список был не пустой точно
        Rule rule = ruleSteps.createRule(CONDITIONAL, false);
        DaoProviderImplementation<Rule> provider = new DaoProviderImplementation<>(Const.URL);
        List<Rule> response = provider.list(Rule.builder().build(), newMapper());

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(response).isNotEmpty();
        softly.assertThat(response.size()).isNotNull();
        softly.assertAll();
    }
}
