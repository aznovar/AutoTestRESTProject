package main.java.ru.ui.steps.objects.user_objects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.jd.ui.blocks.base.Table;
import ru.jd.ui.blocks.base.TableRow;
import ru.jd.ui.model.user_objects.NominationColumnHeaderEnum;
import ru.jd.ui.pages.user_objects.EventsPage;
import ru.jd.ui.steps.BaseSteps;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Класс шагов взаимодействия со страницей 'Пользовательские объекты - События'
 */
public class EventsSteps extends BaseSteps {

    private EventsPage eventsPage;

    public EventsSteps(WebDriver driver) {
        super(driver);
        this.eventsPage = new EventsPage(driver);
    }

    /**
     * Общие шаги страницы 'События'
     */

    @Step("Check. Все основные элементы страницы присутствуют")
    public void checkBaseElementsPresence() {
        Table table = eventsPage.getTable();

        WebElement actualTab = eventsPage.getEventTypesTab();
        String expectedTab = "Виды событий";

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

    @Step("Step. Нажать кнопку 'Обновить' события")
    public void refreshEventsList() {
        eventsPage.getTable().getRefreshButton().click();
        waitWebElementDisappears(eventsPage.getTable().getLoadmask());
    }

    @Step("Check. Присутсвие события с именем: {name} в Событиях")
    public void checkEventIsInList(String name, Integer tableSize) {
        Table rows = eventsPage.getTable();
        checkElementIsInList(rows, name, tableSize);
    }

    @Step("Check. Отсутствие события с именем: {name} в Событиях")
    public void checkEventIsNotInList(String name, Integer tableSize) {
        Table rows = eventsPage.getTable();
        checkElementIsNotInList(rows, name, tableSize);

    }

    @Step("Check.Открыть объект с именем : {name} в списке")
    public void openEventInListByName(String name, Integer tableSize){
        List<TableRow> events = eventsPage.getTable().getTableRows();
        openElementRowInListByNameByDoubleClick(events,name,tableSize);
    }

    @Step("Check.Проверить наличие записи в таблице")
    public void checkRecordInTable(String expectedResult){
        Optional<String> actualResult = eventsPage.getEventTabFrame().getRows().stream()
                .map(row -> row.getText()).filter(str -> str.contains(expectedResult)).findFirst();
        assertThat(String.format("Таблица не содержит строки %s", expectedResult), actualResult.isPresent(),
                is(true));
    }

    @Step("Check.Отсортировать по убыванию столбцы")
    public void clickOnActiveColumn(){
        eventsPage.getEventTabFrame().getActiveColumn().click();
        eventsPage.getEventTabFrame().getActiveColumn().click();
    }

    @Step("Check.Нажать кнопку обновить в таблице объекта")
    public void clickRefreshButtonInTable(){
        eventsPage.getEventTabFrame().getRefreshButton().click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
