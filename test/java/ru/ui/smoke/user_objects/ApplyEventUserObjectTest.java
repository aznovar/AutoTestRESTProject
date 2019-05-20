package ru.ui.smoke.user_objects;

import io.qameta.allure.Feature;
import main.java.ru.api.enums.MetaType;
import main.java.ru.ui.blocks.navigation.NavigationMenu;
import main.java.ru.ui.steps.DBSteps;
import main.java.ru.ui.steps.LoginSteps;
import main.java.ru.ui.steps.objects.SetupObjectSteps;
import main.java.ru.ui.steps.objects.user_objects.EventsSteps;
import main.java.ru.ui.steps.preconditions.BomObjectPreconditionSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.ui.BaseUITest;

import static ru.jd.sup.single.Const.JD_URL;

@Test(groups = {"smoke"})
@Feature("Bom.Пользовательские объекты.События")
public class ApplyEventUserObjectTest extends BaseUITest {

    private SetupObjectSteps userOnObjectPage;
    private EventsSteps userOnEventsPage;
    private DBSteps dbSteps;
    private BomObjectPreconditionSteps preConditionSteps;
    private String objectName;
    private Integer objectCount;

    @BeforeMethod(description = "Настройки перед тестовым методом")
    @Override
    public void setup() throws CoreProblem {

        super.setup();

        userOnObjectPage = new SetupObjectSteps(driver);
        userOnEventsPage = new EventsSteps(driver);
        preConditionSteps = new BomObjectPreconditionSteps();
        LoginSteps userOnLoginPage = new LoginSteps(driver);
        dbSteps = new DBSteps();

        objectCount = dbSteps.getObjectsCountInDB();

        // Создание объекта типа 'Уровень приложения'
        objectName = preConditionSteps.createBomObject(false, MetaType.event).getName().toUpperCase();

        driver.get(URL + NavigationMenu.OBJECTS.getUri());
        userOnLoginPage.doLogin(smokeUser.getUserName(), smokeUser.getPassword());
    }

    @Test(description = "Применение изменений нового объекта 'Событие' в статусе 'Новый'", timeOut = 300000)
    @AutoTest(testRailId = 344990)
    public void applyingOfCreatedEventObjectInObjectListTest() {
        userOnObjectPage.chooseObjectInListByName(objectName, objectCount);
        userOnObjectPage.applyObjectChanges();
        userOnObjectPage.checkPopupMessage(PopupMessageEnum.SUCCESS.getMessageText());
        userOnObjectPage.refreshObjectsList();
        userOnObjectPage.checkObjectIsInList(objectName, StatusEnum.APPLIED.getStatus(), objectCount);
        userOnObjectPage.navigateThroughMenu(NavigationMenu.EVENTS.getMenuName());
        userOnEventsPage.checkEventIsInList(objectName.toLowerCase(), objectCount);
        dbSteps.checkTableExistsInDB(objectName, true);
    }
}
