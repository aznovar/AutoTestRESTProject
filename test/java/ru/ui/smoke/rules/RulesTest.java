package ru.ui.smoke.rules;

import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import main.java.ru.api.Param;
import main.java.ru.api.entity.object.BomObject;
import main.java.ru.api.entity.rules.RulesGroup;
import main.java.ru.api.enums.MetaType;
import main.java.ru.api.enums.RuleType;
import main.java.ru.db.model.Rule;
import main.java.ru.ui.blocks.navigation.NavigationMenu;
import main.java.ru.ui.steps.DBSteps;
import main.java.ru.ui.steps.LoginSteps;
import main.java.ru.ui.steps.preconditions.BomObjectPreconditionSteps;
import main.java.ru.ui.steps.preconditions.RulePreconditionSteps;
import main.java.ru.ui.steps.rules.RulesSteps;
import org.testng.annotations.*;
import ru.ui.BaseUITest;

@Test(groups = {"smoke"})
@Feature("Cep-coordinator.Правила.Проверка настройки правил через интерфейс")
public class RulesTest extends BaseUITest {
    private Rule newRule;
    private Integer rulesGroupCount;
    private BomObject testEvent;
    private RulesGroup testRulesGroup;
    private BomObjectPreconditionSteps bomSteps;
    private RulePreconditionSteps ruleSteps;
    private LoginSteps userOnLoginPage;
    private RulesSteps userOnRulePage;
    private DBSteps dbSteps;

    @BeforeClass(description = "Настройки перед тестовым классом")
    public void createEntities() {
        bomSteps = new BomObjectPreconditionSteps();
        ruleSteps = new RulePreconditionSteps();
        testRulesGroup = ruleSteps.createRulesGroup();
        testEvent = bomSteps.createBomObject(true, MetaType.event);
        bomSteps.applyBomObject(testEvent);
    }

    @BeforeMethod(description = "Настройки перед тестовым методом")
    @Override
    public void setup() throws CoreProblem {
        super.setup();
        userOnLoginPage = new LoginSteps(driver);
        userOnRulePage = new RulesSteps(driver);
        dbSteps = new DBSteps();

        rulesGroupCount = dbSteps.getRulesGroupsCountInDB();

        newRule = Rule.builder()
                .ruleType(RuleType.CONDITIONAL)
                .eventType(testEvent.getCaption())
                .name(Param.genAutoName(15) + "_Rule_")
                .checkScore(false)
                .build();

        userOnLoginPage.navigateAndLogin(NavigationMenu.RULES, smokeUser);
    }

    @Issue("AFS-3089")
    @Test(description = "Создание правила. Простое правило без использования скоринга", timeOut = 300000)
    @AutoTest(testRailId = 409114)
    public void createSimpleRuleWithoutScoring() throws CoreProblem {
        userOnRulePage.createRule(
                testRulesGroup,
                newRule,
                rulesGroupCount,
                "Константа", "0", "Логический"
        );
        userOnRulePage.openRulesListTab();
        userOnRulePage.checkRuleAtGroup(newRule, testRulesGroup, rulesGroupCount);
        dbSteps.checkRuleInDB(newRule.getName());
    }

    @Issue("AFS-3089")
    @Test(description = "Создание правила. Простое правило с использованием скоринга", timeOut = 300000)
    @AutoTest(testRailId = 409115)
    public void createSimpleRuleWithScoring() throws CoreProblem {
        userOnRulePage.createRule(
                testRulesGroup,
                newRule,
                rulesGroupCount,
                "Константа", "0", "Логический"
        );
        userOnRulePage.openRulesListTab();
        userOnRulePage.checkRuleAtGroup(newRule, testRulesGroup, rulesGroupCount);
        dbSteps.checkRuleInDB(newRule.getName());
    }

    @Issue("AFS-3089")
    @Test(description = "Создание правила. Агрегативное правило без использования скоринга", timeOut = 300000)
    @AutoTest(testRailId = 412339)
    public void createAggregateRuleWithoutScoring() throws CoreProblem {
        userOnRulePage.createRule(
                testRulesGroup,
                newRule,
                rulesGroupCount,
                "Константа", "0", "Логический"
        );
        userOnRulePage.openRulesListTab();
        userOnRulePage.checkRuleAtGroup(newRule, testRulesGroup, rulesGroupCount);
        dbSteps.checkRuleInDB(newRule.getName());
    }

    @Issue("AFS-3089")
    @Test(description = "Создание правила. Агрегативное правило с использованием скоринга", timeOut = 300000)
    @AutoTest(testRailId = 412340)
    public void createAggregateRuleWithScoring() throws CoreProblem {
        userOnRulePage.createRule(
                testRulesGroup,
                newRule,
                rulesGroupCount,
                "Константа", "0", "Логический"
        );
        userOnRulePage.openRulesListTab();
        userOnRulePage.checkRuleAtGroup(newRule, testRulesGroup, rulesGroupCount);
        dbSteps.checkRuleInDB(newRule.getName());
    }
}