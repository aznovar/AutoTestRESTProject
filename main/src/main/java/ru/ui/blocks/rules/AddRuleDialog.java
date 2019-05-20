package main.java.ru.ui.blocks.rules;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Класс описания элементов диалогового окна 'Создание правила' и геттеров к ним
 * страницы 'Правила'
 */

@Name("Окно 'Выберите основные параметры'")

public class AddRuleDialog extends HtmlElement {

    @FindBy(xpath = ".//label/span[contains(text(),'Тип')]/../..//input")
    private WebElement typeText;

    @FindBy(xpath = ".//label/span[contains(text(),'Тип')]/../..//div[contains(@class,'arrow')]")
    private WebElement typeTextDropdownArrow;

    @FindBy(xpath = ".//label/span[contains(text(),'Тип события')]/../..//input")
    private WebElement eventTypeText;

    @FindBy(xpath = ".//label/span[contains(text(),'Тип события')]/../..//div[contains(@class,'arrow')]")
    private WebElement eventTypeTextDropdownArrow;

    @FindBy(xpath = ".//a[contains (@class,'blue-small')][contains (@data-qtip,'Чтобы продолжить заполните поля')]")
    private WebElement furtherButton;

    @FindBy(xpath = ".//a[contains (@class,'toolbar-small')][contains(@data-qtip, 'Отменить')]")
    private WebElement cancelButton;

    public WebElement getTypeText() {
        return typeText;
    }

    public WebElement getEventTypeText() {
        return eventTypeText;
    }

    public WebElement getFurtherButton() {
        return furtherButton;
    }

    public WebElement getCancelButton() {
        return cancelButton;
    }

    public WebElement getEventTypeTextDropdownArrow() {
        return eventTypeTextDropdownArrow;
    }

    public WebElement getTypeTextDropdownArrow() {
        return typeTextDropdownArrow;
    }
}
