package main.java.ru.ui.pages.user_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.jd.ui.blocks.base.Table;
import ru.jd.ui.pages.BasePage;

/**
 * Класс описания элементов страницы 'Пользовательские объекты - Списки' и геттеров к ним
 */
public class ListsPage extends BasePage {

    @FindBy(xpath = "//div[contains(@id,'tabbar')][@role='tablist']//span[text()='Списки']")
    private WebElement listsTab;

    @FindBy(xpath = "//div[contains(@id,'list')][@data-ref='body']//div[contains(@id,'grid')][@role='tabpanel']")
    private Table table;

    public ListsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getListsTab() {
        return listsTab;
    }

    public Table getTable() {
        return table;
    }
}
