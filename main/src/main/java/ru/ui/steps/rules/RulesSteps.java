package main.java.ru.ui.steps.rules;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomUtils;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import ru.jd.api.entity.rule.Rule;
import ru.jd.api.entity.rule.RulesGroup;
import ru.jd.api.enums.RuleType;
import ru.jd.sup.excpt.CoreProblem;
import ru.jd.ui.blocks.base.DropDownList;
import ru.jd.ui.blocks.base.Table;
import ru.jd.ui.blocks.base.TableRow;
import ru.jd.ui.model.PopupMessageEnum;
import ru.jd.ui.model.rules.DefaultRuleFieldEnum;
import ru.jd.ui.pages.cep_coordinator.investigation_tool.RulesPage;
import ru.jd.ui.steps.BaseSteps;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

/**
 * Класс шагов взаимодействия со страницей 'Правила'
 */
public class RulesSteps extends BaseSteps {
    private RulesPage rulesPage;
    private Actions actions;

    public RulesSteps(WebDriver driver) {
        super(driver);
        this.rulesPage = new RulesPage(driver);
    }

    //Общие шаги страницы 'Правила'

    @Step("Check. Все основные элементы страницы присутствуют")
    public void checkBaseElementsPresence() {
        Table table = rulesPage.getTable();

        WebElement actualTab = rulesPage.getRulesListTab();
        String expectedTab = "Группы правил";

        String actualTableHeaders = table.getTableHeader().getText();
        String[] expectedTableHeaders = {"Наименование", "Статус", "Описание"};

        MatcherAssert.assertThat("Элемент страницы 'Таблица' не отображается.", table.isDisplayed(),
                equalTo(true));
        MatcherAssert.assertThat(String.format("Таб '%s' не отображается.", expectedTab), actualTab.isDisplayed(),
                equalTo(true));
        MatcherAssert.assertThat(String.format("Таб '%s' имеет неверный текст.", expectedTab), actualTab.getText(),
                equalTo(expectedTab));
        MatcherAssert.assertThat("Наименования столбцов таблицы отображаются неверно.",
                Arrays.stream(expectedTableHeaders).allMatch(actualTableHeaders::contains), is(true));
        MatcherAssert.assertThat("Кнопка 'Обновить' не отображается.", table.getRefreshButton().isDisplayed(),
                equalTo(true));
        MatcherAssert.assertThat("Кнопка 'Создать' не отображается.", table.getCreateButton().isDisplayed(),
                equalTo(true));
        MatcherAssert.assertThat("Кнопка 'Изменить' не отображается",
                table.getChangeButton().isDisplayed(), equalTo(true));
        MatcherAssert.assertThat("Кнопка 'Удалить' не отображается.", table.getRemoveButton().isDisplayed(),
                equalTo(true));
        MatcherAssert.assertThat("Кнопка 'Копировать' не отображается.", table.getCopyButton().isDisplayed(),
                equalTo(true));
    }

    @Step("Step.Нажать кнопку 'Создать' группу правил")
    public void createRulesGroupButtonClick() {
        rulesPage.getTable().getCreateButton().click();
        rulesPage.getAddRulesGroupButton().click();
    }

    //Шаги по работе с окном создания группы правил

    @Step("Step.Ввести наименование новой группы правил: {nomination}")
    public void addNominationText(String nomination) {
        rulesPage.getAddRulesGroupDialog().getNominationTextField().sendKeys(nomination);
    }

    @Step("Step.Ввести описание новой группы правил: {description}")
    public void addDescriptionText(String description) {
        rulesPage.getAddRulesGroupDialog().getDescriptionTextField().sendKeys(description);
    }

    @Step("Step.Нажать кнопку 'Сохранить'")
    public void clickSaveButton() {
        WebElement button = rulesPage.getAddRulesGroupDialog().getSaveButton();
        button.click();
        waitWebElementDisappears(rulesPage.getAddRulesGroupDialog());
    }

    @Step("Check. Группа с именем: {name} есть в списке групп")
    public void checkGroupIsInList(String name, Integer tableSize) {
        Table table = rulesPage.getTable();
        checkElementIsInList(table, name, tableSize);
    }

    @Step("Step. Выделить группу правил с именем: {name} из списка")
    public Optional<TableRow> chooseRulesGroupInListByName(List<TableRow> table, String name, Integer tableSize) {
        Optional<TableRow> object = scrollToElement(table, name, tableSize);
        actions = new Actions(driver);
        if (!object.isPresent()) {
            Assert.fail(String.format("Элемент с именем '%s' отсутствует в таблице.", name));
        }
        if (!object.get().getAttribute("class").contains("selected")) {
            // Выбор элемента (выделяется чекбокс) осуществляется через action по строке таблицы, потому что
            // selenium click срабатывает не всегда
            actions.moveToElement(object.get().getExpander()).perform();
            actions.click(object.get().getExpander()).perform();
            object.get().getCells().get(1).click();
            waitForElementIsVisible(object.get().findElement(By.xpath(".//div[contains(text(),'Правила группы')]")));
        }
        return object;
    }

    @Step("Step. Выделить группу правил с именем: {groupName} из списка")
    public void chooseRulesGroupInListByName(String groupName, Integer tableSize) {
        List<TableRow> rulesGroup = rulesPage.getTable().getTableRows();
        chooseRulesGroupInListByName(rulesGroup, groupName, tableSize);
    }

    @Step("Step. Нажать кнопку 'Создать' правило")
    public void createRuleButtonClick() {
        rulesPage.getTable().getCreateButton().click();
        rulesPage.getAddRuleButton().click();
        waitForElementIsVisible(rulesPage.getAddRuleDialog());
    }

    @Step("Step. Создать группу правил со случайными параметрами через UI")
    public void createRulesGroup() {
        RulesGroup rg = RulesGroup.builder().randomize().build();
        createRulesGroup(rg);
    }

    @Step("Step. Создать группу правил с задаными параметрами через UI")
    public void createRulesGroup(RulesGroup rg) {
        createRulesGroupButtonClick();
        addNominationText(rg.getName());
        addDescriptionText(rg.getDescription());
        clickSaveButton();
    }

    @Step("Step. Открыть диалог создания правила")
    public void openAddRuleDialog(RulesGroup rg, Integer tableSize) {
        chooseRulesGroupInListByName(rg.getName(), tableSize);
        createRuleButtonClick();
    }

    @Step("Step. Задать значения в первичном диалоге создания правила")
    public void fillAddRuleDialog(Rule r) {
        DropDownList dropDownList;
        rulesPage.getAddRuleDialog().getTypeTextDropdownArrow().click();
        dropDownList = getDropdownList(getWebElementId(rulesPage.getAddRuleDialog().getTypeText()));
        selectOptionByValue(dropDownList, r.getRuleType().getDisplayName());

        rulesPage.getAddRuleDialog().getEventTypeTextDropdownArrow().click();
        dropDownList = getDropdownList(getWebElementId(rulesPage.getAddRuleDialog().getEventTypeText()));
        selectOptionByValue(dropDownList, r.getEventType());
    }

    @Step("Step. Открыть tab создания правила")
    public void openAddRuleTabAndFillFirstForm(Rule r) {
        WebElement furtherButton = rulesPage.getAddRuleDialog().getFurtherButton();
        fillAddRuleDialog(r);
        waitForElementIsClickable(furtherButton);
        furtherButton.click();
        waitForElementIsVisible(rulesPage.getAddRuleTab().getNominationField());
    }

    @Step("Step. Заполнить ExpressionEditor в tab создания правила")
    public void setExpressionEditor(String... expr) throws CoreProblem {
        DropDownList dropDownList;
        switch (expr[0]) {
            case "Константа":
                rulesPage.getExpressionEditorDialog().getExpressTypeField().click();
                dropDownList = getDropdownList(getWebElementId(rulesPage.getExpressionEditorDialog()
                        .getExpressTypeField()));
                selectOptionByValue(dropDownList, expr[0]);
                rulesPage.getExpressionEditorDialog().getValueField().sendKeys(expr[1]);
                rulesPage.getExpressionEditorDialog().getTypeValueFieldDropdownArrow().click();
                dropDownList = getDropdownList(getWebElementId(rulesPage.getExpressionEditorDialog()
                        .getTypeValueField()));
                selectOptionByValue(dropDownList, expr[2]);
                rulesPage.getExpressionEditorDialog().getApplyButton().click();
                break;
            case "Сравнение":
                rulesPage.getExpressionEditorDialog().getExpressTypeField().click();
                dropDownList = getDropdownList(getWebElementId(rulesPage.getExpressionEditorDialog()
                        .getExpressTypeField()));
                selectOptionByValue(dropDownList, expr[0]);
                rulesPage.getExpressionEditorDialog().getRelationalOpFieldDropdownArrow().click();
                dropDownList = getDropdownList(getWebElementId(rulesPage.getExpressionEditorDialog()
                        .getRelationalOpField()));
                selectOptionByValue(dropDownList, expr[1]);
                rulesPage.getExpressionEditorDialog().getApplyButton().click();
                // Первый атрибут
                WebElement conditionAttrField = rulesPage.getAddRuleTab().getConditionAttrField();
                if (!isWebElementDisplayed(conditionAttrField)) {
                    rulesPage.getAddRuleTab().getConditionTabLink().click();
                    waitForElementIsVisible(conditionAttrField);
                }
                doubleClick(rulesPage.getAttributeEditor());
                rulesPage.getExpressionEditor().click();
                dropDownList = getDropdownList(getWebElementId(rulesPage.getExpressionEditorDialog()
                        .getExpressTypeField()));
                selectOptionByValue(dropDownList, expr[2]);
                rulesPage.getExpressionEditorDialog().getAggregateFieldDropdownArrow().click();
                dropDownList = getDropdownList(getWebElementId(rulesPage.getExpressionEditorDialog()
                        .getAggregateField()));
                selectOptionByValue(dropDownList, expr[3]);
                rulesPage.getExpressionEditorDialog().getResultFieldDropdownArrow().click();
                dropDownList = getDropdownList(getWebElementId(rulesPage.getExpressionEditorDialog()
                        .getResultField()));
                selectOptionByValue(dropDownList, expr[4]);

                //1 atribut
                doubleClick(rulesPage.getAttributeCheck());
                WebElement yourElement1 = driver.findElement(By.xpath("//div[2]/div/div/div/div/input"));
                yourElement1.click();
                WebElement yourElement2 = driver.findElement(By.xpath("//li[contains(@role,'option')][contains(@data-recordindex,'1')][contains(text(),'Атрибут')]"));
                yourElement2.click();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                doubleClick(rulesPage.getConcerneCheck());

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                driver.findElement(By.xpath("//td[3]/div[2]/div/div/div/div[2]")).click();
                driver.findElement(By.xpath("//li[contains(@role,'option')][contains(@data-recordindex,'0')][contains(text(),'Автор последнего изменения')]")).click();
                doubleClick(rulesPage.getAttributeCheck());
                driver.findElement(By.xpath("//div[2]/div/div/div/div/input")).click();
                driver.findElement(By.xpath("//li[contains(@role,'option')][contains(@data-recordindex,'1')][contains(text(),'Атрибут')]")).click();
                driver.findElement(By.xpath("//span[contains(text(),'Часть ключа')]")).click();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                doubleClick(rulesPage.getConcerneCheck());

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                driver.findElement(By.xpath("//td[3]/div[2]/div/div/div/div[2]")).click();
                driver.findElement(By.xpath("//li[contains(@role,'option')][contains(@data-recordindex,'0')][contains(text(),'Владелец записи')]")).click();
                rulesPage.getExpressionEditorDialog().getApplyButton().click();

                //2 atribut
                doubleClick(rulesPage.getAttributeEditor());
                rulesPage.getExpressionEditor().click();
                dropDownList = getDropdownList(getWebElementId(rulesPage.getExpressionEditorDialog()
                        .getExpressTypeField()));
                selectOptionByValue(dropDownList, expr[9]);
                rulesPage.getExpressionEditorDialog().getValueField().sendKeys("1");
                rulesPage.getExpressionEditorDialog().getTypeValueFieldDropdownArrow().click();
                dropDownList = getDropdownList(getWebElementId(rulesPage.getExpressionEditorDialog()
                        .getTypeValueField()));
                selectOptionByValue(dropDownList, expr[10]);
                rulesPage.getExpressionEditorDialog().getApplyButton().click();
                break;
            default:
                throw new CoreProblem("Не получилось задать условия в ExpressionEditor tab'е создания правила");
        }
    }

    @Step("Step. Задать условия/агрегаты в tab создания правила")
    public void setConditions(Rule r, String... expr) throws CoreProblem {
        WebElement conditionAttrField = rulesPage.getAddRuleTab().getConditionAttrField();
        if (!isWebElementDisplayed(conditionAttrField)) {
            rulesPage.getAddRuleTab().getConditionTabLink().click();
            waitForElementIsVisible(conditionAttrField);
        }
        doubleClick(conditionAttrField);
        // Параметры Условия
        setExpressionEditor(expr);
        // Параметры Агрегаты
        if (r.getRuleType().getDisplayName().equals(RuleType.AGGREGATION.getDisplayName())) {
            WebElement aggrAttrField = rulesPage.getAddRuleTab().getAggrAttrField();
            if (!isWebElementDisplayed(aggrAttrField)) {
                rulesPage.getAddRuleTab().getAggrFieldLink().click();
                waitForElementIsVisible(aggrAttrField);
            }
            doubleClick(aggrAttrField);
            setExpressionEditor(expr);
        }
    }

    @Step("Step. Задать значения в tab создания правила")
    public void fillAddRuleTab(Rule r, String... expr) throws CoreProblem {
        rulesPage.getAddRuleTab().getNominationField().sendKeys(r.getName());
        if (r.getRuleType().getDisplayName().equals(RuleType.AGGREGATION.getDisplayName())) {
            // Группировать по
            rulesPage.getAddRuleTab().getGroupByField().sendKeys(DefaultRuleFieldEnum.getRandomField().getDisplayName());
            new Actions(driver).sendKeys(Keys.ENTER).build().perform();
            new Actions(driver).sendKeys(Keys.ESCAPE).build().perform();
            // Время(сек.)
            String time = String.valueOf(RandomUtils.nextInt(1, 10));
            rulesPage.getAddRuleTab().getTimeField().sendKeys(time);
            new Actions(driver).sendKeys(Keys.ARROW_DOWN).build().perform();
            new Actions(driver).sendKeys(Keys.ENTER).build().perform();
        }
        if (r.getCheckScore()) {
            String score = String.valueOf(RandomUtils.nextInt(1, 100));
            rulesPage.getAddRuleTab().getScoreInputField().sendKeys(score);
        } else {
            rulesPage.getAddRuleTab().getScoreCheckboxField().click();
        }
        setConditions(r, expr);
    }

    @Step("Step. Создать правило через UI")
    public void createRule(RulesGroup rg, Rule r, Integer tableSize, String... expr) throws CoreProblem {
        openAddRuleDialog(rg, tableSize);
        openAddRuleTabAndFillFirstForm(r);
        fillAddRuleTab(r, expr);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        rulesPage.getAddRuleTab().getSaveButton().click();
        checkPopupMessage(PopupMessageEnum.SUCCESS.getMessageText());
    }

    @Step("Check. Правило: {r} есть в группе: {rg}")
    public void checkRuleAtGroup(Rule r, RulesGroup rg, Integer tableSize) {
        Table table = rulesPage.getTable();
        chooseRulesGroupInListByName(table.getTableRows(), rg.getName(), tableSize);
        WebElement elem = driver.findElement(By.xpath("//*[contains(text(), '" + r.getName() + "')]"));
        assertThat(isWebElementDisplayed(elem)).isTrue();
    }

    @Step("Step. Открыть таб 'Группы правил'")
    public void openRulesListTab() {
        rulesPage.getRulesListTab().click();
        waitWebElementDisappears(rulesPage.getTable().getLoadmask());
    }
}
