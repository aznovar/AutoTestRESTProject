package main.java.ru.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.jd.ui.blocks.base.AlertDialog;
import ru.jd.ui.blocks.base.DropDownList;
import ru.jd.ui.blocks.base.PopupMessage;
import ru.jd.ui.blocks.base.TopBar;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.List;
import java.util.Optional;

/**
 * Класс описания элементов базовой страницы и геттеров к ним
 */
public class BasePage {

    // Элементы страницы

    @FindBy(xpath = "//*[contains(@class,'treelist-navigation')]")
    private WebElement navigationBar;

    @FindBy(className = "main-logo")
    private WebElement mainLogo;

    @FindBy(xpath = "//div[contains(@class,'headerbar')]")
    private TopBar topBar;

    @FindBy(xpath = "//div[contains(@id,'toast')][@role='dialog']")
    private PopupMessage popupMessage;

    // Выпадающий список находиться вне выделенных в блоки элементов
    @FindBy(xpath = "//div[contains(@class, 'boundlist')][contains(@data-componentid,'picker')]//ul")
    private List<DropDownList> dropdownList;

    @FindBy(xpath = "//div[@data-qtip='Close dialog']")
    private WebElement closeDialogButton;

    @FindBy(xpath = "//div[contains(@class,'message-box')][@role='alertdialog']")
    private AlertDialog alertDialog;

    @FindBy(xpath = "//div[contains(@class,'scroller')]")
    private WebElement scroller;

    @FindBy(xpath = "//label[contains(@class,'error-page-top-text')]")
    private WebElement errorPage;

    @FindBy(xpath = ".//div[contains(@data-qtip,'Выход из системы')]")
    private Button logoutButtonFrom403;

    // Конструктор

    public BasePage(WebDriver driver) {
        PageFactory.initElements
                (new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    // Геттеры к элементам страницы

    public WebElement getNavigationBar() {
        return navigationBar;
    }

    public WebElement getMainLogo() {
        return mainLogo;
    }

    public TopBar getTopBar() {
        return topBar;
    }

    public PopupMessage getPopupMessage() {
        return popupMessage;
    }

    public WebElement getCloseDialogButton() {
        return closeDialogButton;
    }

    public AlertDialog getAlertDialog() {
        return alertDialog;
    }

    public WebElement getScroller() {
        return scroller;
    }

    public WebElement getErrorPage() {
        return errorPage;
    }

    public Button getLogoutButtonFrom403() {
        return logoutButtonFrom403;
    }

    // Методы

    // Выпадающий список завязан по id с элементом, для которого он вызывается. Поэтому для получения нужного
    // выпадающего списка получаем id соответствующего веб-элемента (BaseSteps -> getWebElementId()) и
    // передаем его сюда.
    public Optional<DropDownList> getDropdownListById(String id) {
        return dropdownList.stream().filter(list -> list.getAttribute("id")
                .replaceAll("\\D+", "")
                .equals(id))
                .findFirst();
    }
}
