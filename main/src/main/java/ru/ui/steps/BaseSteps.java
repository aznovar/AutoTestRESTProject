package main.java.ru.ui.steps;

import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

/**
 * Класс шагов взаимодействия с базовой страницей
 */
public class BaseSteps {

    // Значение координаты Y положения скролла в таблице. Если скролла в таблице нет или он еще не в конце таблицы,
    // значение Y будет меньше данного значения
    protected WebDriver driver;
    private BasePage basePage;

    public BaseSteps(WebDriver driver) {
        this.driver = driver;
        this.basePage = new BasePage(driver);
    }

    protected boolean isWebElementDisplayed(WebElement element) {
        boolean presence;
        try {
            presence = element.isDisplayed();
        } catch (NoSuchElementException e) {
            presence = false;
        }
        return presence;
    }

    @Step("Step. Ожидание исчезновения элемента страницы")
    protected void waitWebElementDisappears(WebElement element) {
        try {
            new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOf(element));
        } catch (NoSuchElementException exp) {
            Assert.fail("Элемент не исчез.");
        }
    }

    @Step("Check. Логитип JD отображается")
    public void checkMainLogoIsDisplaying() {
        WebElement explicitWait = (new WebDriverWait(driver, 7))
                .until(ExpectedConditions.elementToBeClickable(basePage.getNavigationBar()));
        assertThat("Логотип JD не отображается.", basePage.getMainLogo().isDisplayed(), equalTo(true));
    }

    protected TableRow demandRowByName(List<TableRow> tableRows, String name) {
        Optional<TableRow> row = getRowByName(tableRows, name);
        assertThat("Искомой строки нет в таблице.", row.isPresent(), is(true));
        return row.get();
    }

    @Step("Step. Получить элемент таблицы по имени: {name}")
    protected Optional<TableRow> getRowByName(List<TableRow> tableRows, String name) {
        return tableRows.stream().filter(row -> row.getText().contains(name)).findFirst();
    }

    protected boolean isRowByNameInTable(List<TableRow> tableRows, String name) {
        return getRowByName(tableRows, name).isPresent();
    }

    @Step("Check. Элемент с именем: {name} есть в списке объектов и имеет статус: {status}")
    public void checkElementIsInListWithStatus(Table table, String name, String status, Integer tableSize) {
        List<TableRow> rows = table.getTableRows();
        waitForElementIsClickable(table.getTableHeader());
        Optional<TableRow> foundItem = scrollToElement(rows, name, tableSize);
        assertThat("Искомый элемент отсутствует в списке.", foundItem.isPresent(), is(true));
        assertThat("Искомый элемент имеет не ожидаемый статус.", foundItem.get().getText(), containsString(status));
    }

    @Step("Step. Ожидание кликабельности элемента")
    protected void waitForElementIsClickable(WebElement expectedElement) {
        WebDriverWait wait = new WebDriverWait(driver, 7, 500);
        wait.until(ExpectedConditions.elementToBeClickable(expectedElement));
    }

    private static ExpectedCondition<WebElement> toBeSelected(final WebElement element) {
        return new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return (element.getAttribute("class").contains("selected") ? element : null);
            }

            @Override
            public String toString() {
                return element + " is selected";
            }
        };
    }

    @Step("Step. Ожидание выделения элемента")
    protected void waitForElementIsSelected(WebElement element) {
        WebElement wait = new WebDriverWait(driver, 7, 500).until(toBeSelected(element));
    }

    @Step("Step. Прокрутить таблицу к элементу с именем: {name}")
    public Optional<TableRow> scrollToElement(List<TableRow> table, String name, Integer tableSize) {
        Optional<TableRow> row = getRowByName(table, name);
        int count = 0;
        int loadedCount = table.size();
        if (loadedCount != 0){
            do {
                row = getRowByName(table, name);
                if (row.isPresent()) {
                    moveToElement(row.get().getCells().get(0));
                    break;
                }
                count = count + loadedCount;
                moveToElement(table.get(loadedCount - 1).getCells().get(0));
            } while (count <= tableSize);
        }
        return row;
    }

    protected void moveToElement(WebElement element) {
        ((Locatable) element).getCoordinates().inViewPort();
    }

    @Step("Check. Присутствие элемента с именем: {name} в списке")
    public void checkElementIsInList(Table table, String name, Integer tableSize) {
        List<TableRow> rows = table.getTableRows();
        waitForElementIsClickable(table.getTableHeader());
        Optional<TableRow> foundItem = scrollToElement(rows, name, tableSize);
        assertThat("Искомый элемент отсутствует в списке.", foundItem.isPresent(), is(true));
    }

    @Step("Check. Отсутствие элемента с именем: {name} в списке")
    public void checkElementIsNotInList(Table table, String name, Integer tableSize) {
        List<TableRow> rows = table.getTableRows();
        waitForElementIsClickable(table.getTableHeader());
        Optional<TableRow> foundItem = scrollToElement(rows, name, tableSize);
        assertThat("Элемент должен отсутствовать в списке.", foundItem.isPresent(), is(false));
    }

    @Step("Step. Выделить чекбокс элемента с именем: {name} из списка")
    public void chooseElementByCheckboxInListByName(List<TableRow> table, String name, Integer tableSize) {
        Optional<TableRow> object = scrollToElement(table, name, tableSize);
        int counter = 0;
        if (!object.isPresent()) {
            Assert.fail(String.format("Элемент с именем '%s' отсутствует в таблице.", name));
        }
        while (!object.get().getAttribute("class").contains("selected") || counter < 5) {
            moveToElement(object.get().getCells().get(0));
            object.get().getCheckbox().click();
            counter++;
        }
        assertThat("Не удалось выбрать элемент.", object.get().getAttribute("class").contains("selected"),
                is(true));
    }

    @Step("Step. Выделить элемент с именем: {name} из списка")
    public void chooseElementRowInListByName(List<TableRow> table, String name, Integer tableSize) {
        Optional<TableRow> object = scrollToElement(table, name, tableSize);
        int counter = 0;
        if (!object.isPresent()) {
            Assert.fail(String.format("Элемент с именем '%s' отсутствует в таблице.", name));
        }
        while (!object.get().getAttribute("class").contains("selected") || counter < 5) {
            // Выбор элемента (выделяется чекбокс) осуществляется через action по строке таблицы, потому что
            // selenium click срабатывает не всегда
            moveToElement(object.get().getCells().get(0));
            new Actions(driver).click(object.get().getCells().get(0)).build().perform();
            counter++;
        }
    }

    @Step("Step. Открыть элемент с именем: {name} из списка двойным щелчком")
    public void openElementRowInListByNameByDoubleClick(List<TableRow> table, String name, Integer tableSize) {
        Optional<TableRow> object = scrollToElement(table, name, tableSize);
        if (!object.isPresent()) {
            Assert.fail(String.format("Элемент с именем '%s' отсутствует в таблице.", name));
        }
        new Actions(driver).doubleClick(object.get().getCells().get(0)).build().perform();
    }

    protected void checkValueAttribute(WebElement element, String text) {
        assertThat("Неверное значение 'value' элемента.", element.getAttribute("value"), equalTo(text));
    }

    @Step("Check. Popup сообщение отображается: {isDisplayed}")
    public void checkPopupMessageDisplayed(boolean isDisplayed) {
        assertThat("Неверный статус отображения Popup сообщения.", isWebElementDisplayed(basePage.getPopupMessage()), equalTo(isDisplayed));
    }

    @Step("Step. Навигация к пункту меню: {menuItem}")
    public void navigateThroughMenu(String menuItem) {
        WebElement navigationBar = basePage.getNavigationBar();
        waitForElementIsClickable(navigationBar);
        WebElement menuPoint = navigationBar.findElement(By.xpath(".//*[text() = '" + menuItem + "']"));
        menuPoint.click();
    }

    @Step("Check. Проверка отображения пункта меню: {menuItem}")
    public void checkMenuItemExists(String menuItem) {
        waitForElementIsClickable(basePage.getNavigationBar());
        Boolean check;
        try {
            basePage.getNavigationBar().findElement(By.xpath(".//*[text() = '" + menuItem + "']"));
            check = true;
        } catch (NoSuchElementException e) {
            check = false;
        }
        assertThat(String.format("Пункт меню '%s' не отображается", menuItem), check, is(true));
    }

    @Step("Check. Проверка отображения всего меню")
    public void checkAllMenuItemsExist() {
        List<String> menus = Arrays.stream(NavigationMenu.values()).map(item -> item.getMenuName())
                .collect(Collectors.toList());
        for (String menuItem : menus) {
            checkMenuItemExists(menuItem);
        }
    }

    @Step("Check. Проверка отсутствия пункта меню: {menuItem}")
    public void checkMenuItemDoesNotExist(String menuItem) {
        waitForElementIsClickable(basePage.getNavigationBar());
        Boolean check;
        WebElement element;
        try {
            element = basePage.getNavigationBar().findElement(By.xpath(".//*[text() = '" + menuItem + "']"));
            check = element.isDisplayed();
        } catch (NoSuchElementException e) {
            check = true;
        }
        assertThat(String.format("Пункт меню '%s' отображается", menuItem), check, is(true));
    }

    @Step("Check. Проверка отображения страницы с 403 ошибкой")
    public void check403PageOpened() {
        waitForElementIsClickable(basePage.getNavigationBar());
        Boolean check;
        WebElement element;
        try {
            element = basePage.getErrorPage();
            check = element.isDisplayed();
        } catch (NoSuchElementException e) {
            check = false;
        }
        assertThat(String.format("Страница с 403 ошибкой не отображается"), check, is(true));
    }

    @Step("Check. URL страницы содержит: {uri}")
    public void checkURLContains(String uri) {
        assertThat("URL страницы содержит неверный uri.", driver.getCurrentUrl(), endsWith(uri));
    }

    @Step("Check. Сообщение об успешности/неуспешности операции содержит текст: {expectedText}")
    public void checkPopupMessage(String expectedText) {
        PopupMessage message = basePage.getPopupMessage();
        waitForElementIsVisible(message.getMessageText());
        assertThat("Popup  сообщение не отображается.", message.isDisplayed(), equalTo(true));
        assertThat("Popup сообщение содержит неверный текст.", message.getMessageText().getText(),
                equalTo(expectedText));
        closePopupMessage();
    }

    @Step("Step. Закрытие popup сообщения")
    public void closePopupMessage() {
        ((JavascriptExecutor) driver).executeScript("Ext.override(Ext.window.Toast, {hideDuration: -1});");
        PopupMessage popup = basePage.getPopupMessage();
        try{
            if(popup.isDisplayed()){
                popup.getCloseButton().click();
            }
        }catch (NoSuchElementException e){
            return;
        }
    }

    @Step("Check. Закрыть таб созданной сущности с именем: {name}")
    public void closeCreatedEntityTab(String name) {
        driver.findElement(By.xpath(String.format("//span[contains(text(),'%s')]/../../.." +
                "//span[text()=' Закрыть эту вкладку']", name))).click();
    }

    @Step("Step. Ожидание видимости элемента")
    protected void waitForElementIsVisible(WebElement expectedElement) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(expectedElement));
    }

    @Step("Step. Выйти из системы")
    public void doLogout() {
        basePage.getTopBar().getLogoutButton().click();
    }

    @Step("Step. Выйти из системы со страницы с 403 ошибкой (Доступ запрещён)")
    public void doLogoutFrom403ErrorPage() {
        basePage.getLogoutButtonFrom403().click();
    }

    @Step("Step. Закрыть диалоговое окно")
    public void closeDialog() {
        WebElement button = basePage.getCloseDialogButton();
        button.click();
        waitWebElementDisappears(button);
    }

    @Step("Step. Нажать кнопку 'Да' в Alert Dialog")
    public void confirmApplyChanges() {
        WebElement button = basePage.getAlertDialog().getYesButton();
        button.click();
        waitWebElementDisappears(button);
    }

    @Step("Step. Получаем имя пользователя")
    public String getUserNameOnDashboard() {
        return basePage.getTopBar().getUserName().getText();
    }

    @Step("Check. Имя пользователя на иконке совпадает с реальным именем пользователя {userNameReal}")
    public void checkUserNameOnDashboard(String userNameReal) {
        Assertions.assertThat(basePage.getTopBar().getUserName().getText()).isEqualTo(userNameReal);
    }

    public void doubleClick(WebElement element) {
        new Actions(driver).doubleClick(element).build().perform();
    }

    public String getWebElementId(WebElement element) {
        return element.getAttribute("id").replaceAll("\\D+", "");
    }

    @Step("Step. Выбрать элемент из dropdown списка: {value}")
    public void selectOptionByValue(DropDownList list, String value) {
        waitForElementIsVisible(list);
        Optional<WebElement> option = list.getOptionByValue(value);
        assertThat("В dropdown списке элемент не найден.", option.isPresent(), is(true));
        option.get().click();
    }

    @Step("Step. Получить требуемый dropdown список")
    public DropDownList getDropdownList(String id) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Optional<DropDownList> list = basePage.getDropdownListById(id);
        assertThat("Нужный dropdown список не найден.", list.isPresent(), is(true));
        return list.get();
    }
}
