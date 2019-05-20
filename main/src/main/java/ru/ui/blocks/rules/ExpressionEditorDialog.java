package main.java.ru.ui.blocks.rules;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Класс описания элементов блока 'Dialog настройки ExpressionEditor' и геттеров к ним
 * страницы 'Инструменты анализа - Правила'
 */
@Name("Окно 'ExpressionEditor'")

public class ExpressionEditorDialog extends HtmlElement {

    @FindBy(xpath = ".//label/span[contains(text(),'Тип выражения')]/../..//input")
    private WebElement expressTypeField;

    @FindBy(xpath = ".//label/span[contains(text(),'Тип выражения')]/../..//div[contains(@class,'arrow')]")
    private WebElement expressTypeFieldDropdownArrow;

    @FindBy(xpath = ".//label/span[contains(text(),'Значение свойства')]/../..//input")
    private WebElement valueField;

    @FindBy(xpath = ".//label/span[contains(text(),'Тип свойства')]/../..//input")
    private WebElement typeValueField;

    @FindBy(xpath = ".//label/span[contains(text(),'Тип свойства')]/../..//div[contains(@class,'arrow')]")
    private WebElement typeValueFieldDropdownArrow;

    @FindBy(xpath = ".//a[contains(@class,'blue-small')]//span[contains(text(),'Применить')]")
    private WebElement applyButton;

    @FindBy(xpath = ".//a[contains(@class,'toolbar-small')]//span[contains(text(), 'Отмена')]")
    private WebElement cancelButton;

    @FindBy(xpath = ".//label/span[contains(text(),'Сравнение')]/../..//div[contains(@class,'arrow')]")
    private WebElement relationalOpFieldDropdownArrow;

    @FindBy(xpath = ".//label/span[contains(text(),'Сравнение')]/../..//input")
    private WebElement relationalOpField;

    @FindBy(xpath = ".//label/span[contains(text(),'Агрегат')]/../..//div[contains(@class,'arrow')]")
    private WebElement aggregateFieldDropdownArrow;

    @FindBy(xpath = ".//label/span[contains(text(),'Агрегат')]/../..//input")
    private WebElement aggregateField;

    @FindBy(xpath = ".//label/span[contains(text(),'Рез. поле')]/../..//div[contains(@class,'arrow')]")
    private WebElement resultFieldDropdownArrow;

    @FindBy(xpath = ".//label/span[contains(text(),'Рез. поле')]/../..//input")
    private WebElement resultField;

    @FindBy(xpath = ".//table[contains(id, 'record')][0]//td[1]/div[contains(@class,'arrow')]")
    private WebElement valueType1FieldDropdownArrow;

    @FindBy(xpath = ".//table[contains(id, 'record')][0]//td[1]//input")
    private WebElement valueType1Field;

    public WebElement getExpressTypeField() {
        return expressTypeField;
    }

    public WebElement getExpressTypeFieldDropdownArrow() {
        return expressTypeFieldDropdownArrow;
    }

    public WebElement getValueField() {
        return valueField;
    }

    public WebElement getTypeValueField() {
        return typeValueField;
    }

    public WebElement getTypeValueFieldDropdownArrow() {
        return typeValueFieldDropdownArrow;
    }

    public WebElement getApplyButton() {
        return applyButton;
    }

    public WebElement getCancelButton() {
        return cancelButton;
    }

    public WebElement getRelationalOpFieldDropdownArrow() {
        return relationalOpFieldDropdownArrow;
    }

    public WebElement getRelationalOpField() {
        return relationalOpField;
    }

    public WebElement getAggregateFieldDropdownArrow() {
        return aggregateFieldDropdownArrow;
    }

    public WebElement getAggregateField() {
        return aggregateField;
    }

    public WebElement getResultFieldDropdownArrow() {
        return resultFieldDropdownArrow;
    }

    public WebElement getResultField() {
        return resultField;
    }

    public WebElement getValueType1FieldDropdownArrow() {
        return valueType1FieldDropdownArrow;
    }

    public WebElement getValueType1Field() {
        return valueType1Field;
    }
}
