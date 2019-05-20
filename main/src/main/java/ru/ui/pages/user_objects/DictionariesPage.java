package main.java.ru.ui.pages.user_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.jd.ui.blocks.base.Table;
import ru.jd.ui.pages.BasePage;

/**
 * Класс описания элементов страницы 'Пользовательские объекты - Справочники' и геттеров к ним
 */
public class DictionariesPage extends BasePage {

    @FindBy(xpath = "//div[contains(@id,'tabbar')][@role='tablist']//span[text()='Справочники']")
    private WebElement dictionariesTab;

    @FindBy(xpath = "//div[contains(@id,'reference-data')][@data-ref='body']//div[contains(@id,'grid')][@role='tabpanel']")
    private Table table;

    public DictionariesPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getDictionariesTab() {
        return dictionariesTab;
    }

    public Table getTable() {
        return table;
    }
}
