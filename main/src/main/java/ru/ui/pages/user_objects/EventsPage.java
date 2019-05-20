package main.java.ru.ui.pages.user_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.jd.ui.blocks.base.Table;
import ru.jd.ui.blocks.user_objects.EventTab;
import ru.jd.ui.pages.BasePage;

/**
 * Класс описания элементов страницы 'Пользовательские объекты - События' и геттеров к ним
 */
public class EventsPage extends BasePage {

    @FindBy(xpath = "//div[contains(@id,'tabbar')][@role='tablist']//span[text()='Виды событий']")
    private WebElement eventTypesTab;

    @FindBy(xpath = "//div[contains(@id,'event')][@data-ref='body']//div[contains(@id,'grid')][@role='tabpanel']")
    private Table table;

    @FindBy(xpath = "//div[contains(@id,'meta-tab-panel')][@role='tabpanel']")
    private EventTab eventTabFrame;

    public EventsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getEventTypesTab() {
        return eventTypesTab;
    }

    public Table getTable() {
        return table;
    }

    public EventTab getEventTabFrame() {
        return eventTabFrame;
    }
}
