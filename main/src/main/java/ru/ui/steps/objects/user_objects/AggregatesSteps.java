package main.java.ru.ui.steps.objects.user_objects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.jd.ui.blocks.base.Table;
import ru.jd.ui.blocks.base.TableRow;
import ru.jd.ui.model.navigation.NavigationMenu;
import ru.jd.ui.model.user_objects.NominationColumnHeaderEnum;
import ru.jd.ui.pages.user_objects.AggregatesPage;
import ru.jd.ui.steps.BaseSteps;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

/**
 * Класс шагов взаимодействия со страницей 'Пользовательские объекты - Агрегаты'
 */
public class AggregatesSteps extends BaseSteps {

    private AggregatesPage aggregatesPage;

    public AggregatesSteps(WebDriver driver) {
        super(driver);
        this.aggregatesPage = new AggregatesPage(driver);
    }

    /**
     * Общие шаги страницы 'Агрегаты'
     */

    @Step("Check. Все основные элементы страницы присутствуют")
    public void checkBaseElementsPresence() {
        Table table = aggregatesPage.getTable();

        WebElement actualTab = aggregatesPage.getAggregatesTab();
        String expectedTab = NavigationMenu.AGGREGATES.getMenuName();

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

    @Step("Step. Открыть агрегат с именем: {name} в списке")
    public void openAggregateInListByName(String name, Integer tableSize) {
        List<TableRow> aggregates = aggregatesPage.getTable().getTableRows();
        openElementRowInListByNameByDoubleClick(aggregates, name, tableSize);
    }

    @Step("Check. Проверить расчет агрегата")
    public void checkAggregateCalculation(List<String> expectedResults) {
        String[] actualRows = aggregatesPage.getAggregateTabFrame().getRows().stream()
                .map(row -> row.getText()).toArray(String[]::new);
        assertThat("Таблица содержит не все строки.", expectedResults,
                containsInAnyOrder(actualRows));
    }
}
