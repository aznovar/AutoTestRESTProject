package main.java.ru.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.jd.ui.blocks.base.Table;
import ru.jd.ui.blocks.cep_coordinator.rules.AddRulesGroupDialog;
import ru.jd.ui.blocks.cep_coordinator_rules.AddRuleDialog;
import ru.jd.ui.blocks.cep_coordinator_rules.AddRuleTab;
import ru.jd.ui.blocks.cep_coordinator_rules.ExpressionEditorDialog;

/**
 * Класс описания элементов страницы Инструменты анализа - Правила
 */
public class RulesPage extends BasePage {

    @FindBy(xpath = "//div[contains(@id,'tabbar')][@role='tablist']//span[text()='Группы правил']")
    private WebElement rulesListTab;

    @FindBy(xpath = ".//div[contains(@id, 'menu')]/a[@tabindex=-1]//span[text()='Правило']")
    private WebElement addRuleButton;

    @FindBy(xpath = ".//div[contains(@id, 'menu')]/a[@tabindex=-1]//span[text()='Группа правил']")
    private WebElement addRulesGroupButton;

    @FindBy(xpath = "//div[contains(@id,'rules')][@data-ref = 'bodyWrap']" +
            "//div[contains(@id,'grid')][@role = 'tabpanel']")
    private Table table;

    @FindBy(xpath = "//div[contains(@id,'group-add-window')][contains(@class,'x-window-resizable')]")
    private AddRulesGroupDialog addRulesGroupDialog;

    @FindBy(xpath = "//div[starts-with(@id,'rules-')][@data-ref='bodyWrap']")
    private AddRuleTab addRuleTab;

    @FindBy(xpath = "//div[contains(@id,'rule-add-window')][contains(@class,'x-window-resizable')]")
    private AddRuleDialog addRuleDialog;

    @FindBy(xpath = "//div[contains(@id,'cep-rules-expression-editor')][contains(@class,'x-window-resizable')]")
    private ExpressionEditorDialog expressionEditorDialog;

    @FindBy(xpath ="//table[contains(@role,'presentation')]//span[contains(text(),'Атрибут')]")
    private WebElement attributeEditor;

    @FindBy(xpath = "//label/span[contains(text(),'Тип выражения')]/../..//input")
    private WebElement expressionEditor;

    @FindBy(xpath ="//div[contains(@id,'')][contains(text(),'Константа')]")
    private WebElement attributeCheck;

    @FindBy(xpath = "//div[5]/div/div[2]/div/div[2]/table/tbody/tr/td[3]/div")
    private WebElement concerneCheck;

    public RulesPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getAddRuleButton() {
        return addRuleButton;
    }

    public WebElement getAddRulesGroupButton() {
        return addRulesGroupButton;
    }

    public WebElement getRulesListTab() {
        return rulesListTab;
    }

    public Table getTable() {
        return table;
    }

    public AddRulesGroupDialog getAddRulesGroupDialog() {
        return addRulesGroupDialog;
    }

    public AddRuleDialog getAddRuleDialog() {
        return addRuleDialog;
    }

    public AddRuleTab getAddRuleTab() {
        return addRuleTab;
    }

    public ExpressionEditorDialog getExpressionEditorDialog() {
        return expressionEditorDialog;
    }

    public WebElement getAttributeEditor() {
        return attributeEditor;
    }

    public WebElement getExpressionEditor() {
        return expressionEditor;
    }

    public WebElement getAttributeCheck() {
        return attributeCheck;
    }

    public WebElement getConcerneCheck() {
        return concerneCheck;
    }
}
