package main.java.ru.ui.steps.objects.user_objects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.jd.ui.blocks.base.Table;
import ru.jd.ui.model.navigation.NavigationMenu;
import ru.jd.ui.model.user_objects.NominationColumnHeaderEnum;
import ru.jd.ui.pages.user_objects.ListsPage;
import ru.jd.ui.steps.BaseSteps;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Класс шагов взаимодействия со страницей 'Пользовательские объекты - Списки'
 */
public class ListsSteps extends BaseSteps {

    private ListsPage listsPage;

    public ListsSteps(WebDriver driver) {
        super(driver);
        this.listsPage = new ListsPage(driver);
    }

    /**
     * Общие шаги страницы 'Списки'
     */

    @Step("Check. Все основные элементы страницы присутствуют")
    public void checkBaseElementsPresence() {
        Table table = listsPage.getTable();

        WebElement actualTab = listsPage.getListsTab();
        String expectedTab = NavigationMenu.LISTS.getMenuName();

        String actualTableHeader = table.getTableHeader().getText();
        String expectedTableHeader = NominationColumnHeaderEnum.NOMINATION.getTableHeaderName();

        assertThat("Элемент страницы 'Таблица' не отображается.", table.isDisplayed(), equalTo(true));
        assertThat(String.format("Таб '%s' не отображается.", expectedTab), actualTab.isDisplayed(),
                equalTo(true));
        assertThat(String.format("Таб '%s' не отображается.", expectedTab), actualTab.getText(), equalTo(expectedTab));
        assertThat("Наименования столбцов таблицы отображаются неверно.", actualTableHeader,
                equalTo(expectedTableHeader));
        assertThat("Кнопка 'Обновить' не отображается.", table.getRefreshButton().isDisplayed(),
                equalTo(true));
    }
}

