package main.java.ru.ui.blocks.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Класс описания элементов блока 'Таб настройки объекта' и геттеров к ним
 * страницы 'Объектная модель - Объекты'
 */
@Name("Страница настройки выбранного объекта")
public class SetupObjectTab extends HtmlElement {

    @FindBy(xpath = ".//a[contains(@id,'tab')][@role='tab']")
    private List<WebElement> tabList;

    @FindBy(xpath = ".//label/span[contains(text(),'Тип объекта')]/../../div[contains(@id,'combo')]//input")
    private WebElement typeField;

    @FindBy(xpath = ".//label/span[contains(text(),'Наименование:')]/../..//input")
    private WebElement nominationField;

    @FindBy(xpath = ".//label/span[contains(text(),'Имя:')]/../..//input")
    private WebElement nameField;

    @FindBy(xpath = ".//label/span[contains(text(),'Таблица:')]/../..//input")
    private WebElement tableField;

    @FindBy(xpath = ".//label/span[contains(text(),'Хранение')]/../../div[contains(@id,'combo')]//input")
    private WebElement storageField;

    public List<WebElement> getTabList() {
        return tabList;
    }

    public WebElement getTypeField() {
        return typeField;
    }

    public WebElement getNominationField() {
        return nominationField;
    }

    public WebElement getNameField() {
        return nameField;
    }

    public WebElement getTableField() {
        return tableField;
    }

    public WebElement getStorageField() {
        return storageField;
    }
}
