package main.java.ru.ui.blocks.rules;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Класс описания элементов блока 'Таб Создание правила' и геттеров к ним
 * страницы ''Инструменты анализа - Правила'
 */
@Name("Страница настройки выбранного правила")

public class AddRuleTab extends HtmlElement {

    @FindBy(xpath = ".//label/span[text()='Тип']/../..//input")
    private WebElement typeField;

    @FindBy(xpath = ".//label/span[contains(text(),'Наименование')]/../..//input")
    private WebElement nominationField;

    @FindBy(xpath = ".//label/span[contains(text(),'Группа')]/../..//input")
    private WebElement groupField;

    @FindBy(xpath = ".//label/span[contains(text(),'Тип события')]/../..//input")
    private WebElement eventTypeField;

    @FindBy(xpath = ".//label/span[contains(text(),'Комментарий')]/../..//input")
    private WebElement commentField;

    @FindBy(xpath = ".//div[contains(text(),'Использовать скор')]/..//input")
    private WebElement scoreCheckboxField;

    @FindBy(xpath = ".//div[contains(@class,'x-panel-header')]//div[contains(text(),'Условия')]")
    private WebElement conditionTabLink;

    @FindBy(xpath = ".//div[contains(@class,'x-panel-header')]//div[contains(text(),'Условия')]" +
            "/../../../../..//span[contains(text(),'Атрибут')]")
    private WebElement conditionAttrField;

    @FindBy(xpath = ".//div[contains(@class,'x-panel-header')]//div[contains(text(),'Агрегаты')]")
    private WebElement aggrFieldLink;

    @FindBy(xpath = ".//div[contains(@class,'x-panel-header')]//div[contains(text(),'Агрегаты')]" +
            "/../../../../..//span[contains(text(),'Атрибут')]")
    private WebElement aggrAttrField;

    @FindBy(xpath = ".//label/span[contains(text(),'Скор')]/../..//input")
    private WebElement scoreInputField;

    @FindBy(xpath = ".//label/span[contains(text(),'Группировать по')]/../..//input")
    private WebElement groupByField;

    @FindBy(xpath = ".//label/span[contains(text(),'Время (сек.)')]/../..//input")
    private WebElement timeField;

    @FindBy(xpath = ".//a[contains(@class,'blue-small')][contains(@data-qtip,'Сохранить')]")
    private WebElement saveButton;

    @FindBy(xpath = ".//a[contains(@class,'toolbar-small')][contains(@data-qtip, 'Отменить')]")
    private WebElement cancelButton;

    public WebElement getTypeField() {
        return typeField;
    }

    public WebElement getTimeField() {
        return timeField;
    }

    public WebElement getNominationField() {
        return nominationField;
    }

    public WebElement getGroupField() {
        return groupField;
    }

    public WebElement getGroupByField() {
        return groupByField;
    }

    public WebElement getEventTypeField() {
        return eventTypeField;
    }

    public WebElement getCommentField() {
        return commentField;
    }

    public WebElement getScoreCheckboxField() {
        return scoreCheckboxField;
    }

    public WebElement getConditionTabLink() {
        return conditionTabLink;
    }

    public WebElement getConditionAttrField() {
        return conditionAttrField;
    }

    public WebElement getAggrAttrField() {
        return aggrAttrField;
    }

    public WebElement getAggrFieldLink() {
        return aggrFieldLink;
    }

    public WebElement getScoreInputField() {
        return scoreInputField;
    }

    public WebElement getSaveButton() {
        return saveButton;
    }

    public WebElement getCancelButton() {
        return cancelButton;
    }
}
