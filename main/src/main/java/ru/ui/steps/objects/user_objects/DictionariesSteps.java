package main.java.ru.ui.steps.objects.user_objects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.jd.ui.blocks.base.Table;
import ru.jd.ui.model.navigation.NavigationMenu;
import ru.jd.ui.model.user_objects.NominationColumnHeaderEnum;
import ru.jd.ui.pages.user_objects.DictionariesPage;
import ru.jd.ui.steps.BaseSteps;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Класс шагов взаимодействия со страницей 'Пользовательские объекты - Справочники'
 */
public class DictionariesSteps extends BaseSteps {

    private DictionariesPage dictionariesPage;

    public DictionariesSteps(WebDriver driver) {
        super(driver);
        this.dictionariesPage = new DictionariesPage(driver);
    }

    /**
     * Общие шаги страницы 'Справочники'
     */

    @Step("Check. Все основные элементы страницы присутствуют")
    public void checkBaseElementsPresence() {
        Table table = dictionariesPage.getTable();

        WebElement actualTab = dictionariesPage.getDictionariesTab();
        String expectedTab = NavigationMenu.DICTIONARIES.getMenuName();

        String actualTableHeader = table.getTableHeader().getText();
        String expectedTableHeader = NominationColumnHeaderEnum.NOMINATION.getTableHeaderName();

        assertThat("Элемент страницы 'Таблица' не отображается.", table.isDisplayed(), equalTo(true));
        assertThat(String.format("Таб '%s' не отображается.", expectedTab), actualTab.isDisplayed(),
                equalTo(true));
        assertThat(String.format("Таб '%s' имеет неверный текст.", expectedTab), actualTab.getText(),
                equalTo(expectedTab));
        assertThat("Наименования столбцов таблицы отображаются неверно.", actualTableHeader,
                equalTo(expectedTableHeader));
        assertThat("Кнопка 'Обновить' не отображается.", table.getRefreshButton().isDisplayed(),
                equalTo(true));
    }

    @Step("Step. Нажать кнопку 'Обновить' справочники")
    public void refreshDictionaryList() {
        dictionariesPage.getTable().getRefreshButton().click();
        waitWebElementDisappears(dictionariesPage.getTable().getLoadmask());
    }

    @Step("Check. Присутствие справочника с именем: {name} в Справочниках")
    public void checkDictionaryIsInList(String name, Integer tableSize) {
        Table rows = dictionariesPage.getTable();
        checkElementIsInList(rows, name, tableSize);
    }

    @Step("Check. Отсутствие справочника с именем: {name} в Справочниках")
    public void checkDictionaryIsNotInList(String name, Integer tableSize) {
        Table rows = dictionariesPage.getTable();
        checkElementIsNotInList(rows, name, tableSize);
    }
}
