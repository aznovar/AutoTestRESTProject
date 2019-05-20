package main.java.ru.ui.steps.objects;

import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.jd.ui.blocks.base.DropDownList;
import ru.jd.ui.blocks.base.Table;
import ru.jd.ui.blocks.base.TableRow;
import ru.jd.ui.model.user_objects.SetupObjectTabEnum;
import ru.jd.ui.pages.setup.object_model.BomObjectsPage;
import ru.jd.ui.steps.BaseSteps;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

/**
 * Класс шагов взаимодействия со страницей 'Объекты'
 */

public class SetupObjectSteps extends BaseSteps {

    private BomObjectsPage bomObjectsPage;

    public BomObjectsSteps(WebDriver driver) {
        super(driver);
        this.bomObjectsPage = new BomObjectsPage(driver);
    }

    // Общие шаги страницы 'Объекты'

    @Step("Check. Все основные элементы страницы присутствуют")
    public void checkBaseElementsPresence(Integer tableSize) {
        Table table = bomObjectsPage.getTable();

        WebElement actualTab = bomObjectsPage.getObjectsListTab();
        String expectedTab = "Список объектов";

        String actualTableHeaders = table.getTableHeader().getText();
        String[] expectedTableHeaders = {"Тип объекта", "Имя объекта", "Имя таблицы", "Наименование объекта", "Статус"};
        String[] expectedDefaultBomObject = {"ENTITY_TO_ENTITY", "INCIDENT"};

        assertThat("Элемент страницы 'Таблица' не отображается.", table.isDisplayed(),
                equalTo(true));
        assertThat(String.format("Таб '%s' не отображается.", expectedTab), actualTab.isDisplayed(),
                equalTo(true));
        assertThat(String.format("Таб '%s' имеет неверный текст.", expectedTab), actualTab.getText(),
                equalTo(expectedTab));
        assertThat("Наименования столбцов таблицы отображаются неверно.",
                Arrays.stream(expectedTableHeaders).allMatch(actualTableHeaders::contains), is(true));

        //Прокручиваем таблицу к первому дефолтному объекту
        Optional<TableRow> object = scrollToElement(table.getTableRows(), expectedDefaultBomObject[0], tableSize);
        assertThat(String.format("Предустановленный объект с именем '%s' отсутствует в таблице.",
                expectedDefaultBomObject[0]), object.isPresent(), equalTo(true));
        assertThat("Предустановленный объект имеет не верное имя.",
                object.get().getText(), containsString(expectedDefaultBomObject[0]));

        //Прокручиваем таблицу к второму дефолтному объекту
        object = scrollToElement(table.getTableRows(), expectedDefaultBomObject[1], tableSize);
        assertThat(String.format("Предустановленный объект с именем '%s' отсутствует в таблице.",
                expectedDefaultBomObject[1]), object.isPresent(), equalTo(true));
        assertThat("Предустановленный объект имеет не верное имя.",
                object.get().getText(), containsString(expectedDefaultBomObject[1]));

        assertThat("Кнопка 'Обновить' не отображается.", table.getRefreshButton().isDisplayed(),
                equalTo(true));
        assertThat("Кнопка 'Добавить' не отображается.", table.getAddButton().isDisplayed(),
                equalTo(true));
        assertThat("Кнопка 'Изменить' не отображается",
                table.getChangeButton().isDisplayed(), equalTo(true));
        assertThat("Кнопка 'Копировать' не отображается.", table.getCopyUserObjectButton().isDisplayed(),
                equalTo(true));
        assertThat("Кнопка 'Применить изменения' не отображается.", table.getApplyButton().isDisplayed(),
                equalTo(true));
    }

    @Step("Step. Нажать кнопку 'Обновить' список объектов")
    public void refreshObjectsList() {
        Table table = bomObjectsPage.getTable();
        table.getRefreshButton().click();
        waitWebElementDisappears(table.getLoadmask());
    }

    protected void checkButtonAbility(WebElement button, Boolean status) {
        if (status) {
            waitForElementIsClickable(button);
        }
        assertThat("Кнопка имеет неверный статус.",
                button.getAttribute("aria-disabled").equals("false"), is(status));
    }

    @Step("Check. Кнопка 'Добавить' объект доступна: {expectedStatus")
    public void checkAddButtonAbility(Boolean expectedStatus) {
        WebElement button = bomObjectsPage.getTable().getAddButton();
        checkButtonAbility(button, expectedStatus);
    }

    @Step("Check. Кнопка 'Применить' объект доступна: {expectedStatus")
    public void checkApplyButtonAbility(Boolean expectedStatus) {
        WebElement button = bomObjectsPage.getTable().getApplyButton();
        checkButtonAbility(button, expectedStatus);
    }

    protected void checkWebElementIsNotDisplayed(WebElement element) {
        Boolean isDisplayed;
        try {
            isDisplayed = element.isDisplayed();
        } catch (NoSuchElementException e) {
            isDisplayed = false;
        }
        assertThat("Элемент отображается.", isDisplayed, is(false));
    }

    @Step("Check. Кнопка 'Добавить' не отображается")
    public void checkCreateButtonIsNotDisplayed() {
        WebElement button = bomObjectsPage.getTable().getCreateButton();
        checkWebElementIsNotDisplayed(button);
    }

    @Step("Check. Кнопка 'Добавить' отображается")
    public void checkCreateButtonIsDisplayed() {
        WebElement button = bomObjectsPage.getTable().getCreateButton();
        Boolean isDisplayed;
        try {
            isDisplayed = button.isDisplayed();
        } catch (NoSuchElementException e) {
            isDisplayed = true;
        }
        assertThat("Элемент отображается.", isDisplayed, is(true));
    }

    @Step("Check. Кнопка 'Применить' не отображается")
    public void checkApplyButtonIsNotDisplayed() {
        WebElement button = bomObjectsPage.getTable().getApplyButton();
        checkWebElementIsNotDisplayed(button);
    }

    @Step("Check. Кнопка 'Применить' отображается")
    public void checkApplyButtonIsDisplayed() {
        WebElement button = bomObjectsPage.getTable().getApplyButton();
        Boolean isDisplayed;
        try {
            isDisplayed = button.isDisplayed();
        } catch (NoSuchElementException e) {
            isDisplayed = true;
        }
        assertThat("Элемент отображается.", isDisplayed, is(true));
    }

    @Step("Check. Объект с именем: {name} есть в списке объектов и имеет статус: {status}")
    public void checkObjectIsInList(String name, String status, Integer tableSize) {
        Table rows = bomObjectsPage.getTable();
        checkElementIsInListWithStatus(rows, name, status, tableSize);
    }

    @Step("Check. Поле 'Тип объекта' содержит текст: {text}")
    public void checkObjectType(String text) {
        checkValueAttribute(bomObjectsPage.getSetupObjectFrame().getTypeField(), text);
    }

    @Step("Check. Поле 'Хранение' содержит текст: {text}")
    public void checkObjectStorage(String text) {
        checkValueAttribute(bomObjectsPage.getSetupObjectFrame().getStorageField(), text);
    }

    @Step("Check. Поле 'Имя' содержит текст: {text}")
    public void checkObjectName(String text) {
        checkValueAttribute(bomObjectsPage.getSetupObjectFrame().getNameField(), text);
    }

    @Step("Check. Поле 'Таблица' содержит текст: {text}")
    public void checkObjectTable(String text) {
        checkValueAttribute(bomObjectsPage.getSetupObjectFrame().getTableField(), text);
    }

    // Шаги по работе с закладкой объекта

    @Step("Check. Поле 'Наименование' содержит текст: {text}")
    public void checkObjectNomination(String text) {
        checkValueAttribute(bomObjectsPage.getSetupObjectFrame().getNominationField(), text);
    }

    @Step("Check. Таб настройки объекта содержит все закладки")
    public void checkObjectSetupTabContainsAllTabs() {
        List<String> actualTabList = bomObjectsPage.getSetupObjectFrame().getTabList()
                .stream().map(tab -> tab.getText()).filter(StringUtils::isNotEmpty).collect(Collectors.toList());
        List<String> expectedTabList = Arrays.asList(SetupObjectTabEnum.values())
                .stream().map(enumItem -> enumItem.getTabName()).collect(Collectors.toList());
        assertThat("Таб настройки объекта содержит не все закладки.", actualTabList, containsInAnyOrder(expectedTabList.toArray()));
    }

    // Шаги по работе с окном создания новой модели

    @Step("Step. Попытаться применить объект")
    public void tryToApplyBomObject(String objectName, String popupMessageText, Integer tableSize) {
        chooseObjectInListByName(objectName, tableSize);
        applyObjectChanges();
        checkPopupMessage(popupMessageText);
    }

    @Step("Step. Нажать кнопку 'Добавить' объект")
    public void openAddObjectDialog() {
        bomObjectsPage.getTable().getAddButton().click();
        waitForElementIsClickable(bomObjectsPage.getAddObjectDialog());
    }

    @Step("Step. Выбрать тип объекта: {type}")
    public void chooseObjectType(String type) {
        bomObjectsPage.getAddObjectDialog().getTypeDropDownField().click();
        String fieldId = getWebElementId(bomObjectsPage.getAddObjectDialog().getTypeDropDownField());
        DropDownList list = getDropdownList(fieldId);
        selectOptionByValue(list, type);
    }

    @Step("Step. Ввести наименование нового объекта: {nomination}")
    public void enterObjectNomination(String nomination) {
        bomObjectsPage.getAddObjectDialog().getNominationTextField().sendKeys(nomination);
    }

    @Step("Step. Нажать кнопку 'Создать'")
    public void clickCreateButton() {
        WebElement button = bomObjectsPage.getAddObjectDialog().getCreateButton();
        waitForElementIsClickable(button);
        button.click();
    }

    @Step("Step. Открыть таб 'Список объектов'")
    public void openObjectsListTab() {
        WebElement button = bomObjectsPage.getObjectsListTab();
        button.click();
        waitWebElementDisappears(bomObjectsPage.getTable().getLoadmask());
    }

    @Step("Step. Выделить объект с именем: {name} из списка")
    public void chooseObjectInListByName(String name, Integer tableSize) {
        List<TableRow> objects = bomObjectsPage.getTable().getTableRows();
        chooseElementByCheckboxInListByName(objects, name, tableSize);
    }

    @Step("Step. 'Применить изменения' объекта")
    public void applyObjectChanges() {
        WebElement button = bomObjectsPage.getTable().getApplyButton();
        button.click();
        confirmApplyChanges();
        waitWebElementDisappears(bomObjectsPage.getTable().getLoadmask());
    }
}
