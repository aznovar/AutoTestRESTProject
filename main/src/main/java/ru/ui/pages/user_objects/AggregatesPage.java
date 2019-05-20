package main.java.ru.ui.pages.user_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.jd.ui.blocks.base.Table;
import ru.jd.ui.blocks.user_objects.AggregateTab;
import ru.jd.ui.pages.BasePage;

/**
 * Класс описания элементов страницы 'Пользовательские объекты - Агрегаты' и геттеров к ним
 */
public class AggregatesPage extends BasePage {

    @FindBy(xpath = "//div[contains(@id,'tabbar')][@role='tablist']//span[text()='Агрегаты']")
    private WebElement aggregatesTab;

    @FindBy(xpath = "//div[contains(@id,'aggregate-')][@data-ref='body']//div[contains(@id,'grid')][@role='tabpanel']")
    private Table table;

    @FindBy(xpath = "//div[contains(@id,'meta-tab-panel')][@role='tabpanel']")
    private AggregateTab aggregateTabFrame;

    public AggregatesPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getAggregatesTab() {
        return aggregatesTab;
    }

    public Table getTable() {
        return table;
    }

    public AggregateTab getAggregateTabFrame() {
        return aggregateTabFrame;
    }
}
