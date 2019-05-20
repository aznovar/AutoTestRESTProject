package ru.ui.smoke.user_objects;

import io.qameta.allure.Feature;
import main.java.ru.api.enums.MetaType;
import main.java.ru.ui.blocks.navigation.NavigationMenu;
import main.java.ru.ui.steps.DBSteps;
import main.java.ru.ui.steps.LoginSteps;
import main.java.ru.ui.steps.objects.SetupObjectSteps;
import main.java.ru.ui.steps.preconditions.BomObjectPreconditionSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.ui.BaseUITest;

@Test(groups = {"smoke"})
@Feature("Bom.Объектная модель.Объекты.Объекты типа 'Уровень приложения'")
public class ApplyAppLevelUserObjectTest extends BaseUITest {

    private String objectName;
    private SetupObjectSteps userOnObjectPage;
    private DBSteps dbSteps;
    private BomObjectPreconditionSteps preConditionSteps;
    private Integer objectCount;

    @BeforeMethod(description = "Настройки перед тестовым методом")
    @Override
    public void setup() throws CoreProblem {

        super.setup();

        LoginSteps userOnLoginPage = new LoginSteps(driver);
        userOnObjectPage = new SetupObjectSteps(driver);
        dbSteps = new DBSteps();
        preConditionSteps = new BomObjectPreconditionSteps();

        objectCount = dbSteps.getObjectsCountInDB();

        // Создание объекта типа 'Уровень приложения'
        objectName = preConditionSteps.createBomObject(false, MetaType.application).getName().toUpperCase();

        driver.get(URL + NavigationMenu.OBJECTS.getUri());
        userOnLoginPage.doLogin(smokeUser.getUserName(), smokeUser.getPassword());
    }

    @Test(description = "Применение изменений нового объекта 'Уровень приложения' в статусе 'Новый'", timeOut = 300000)
    @AutoTest(testRailId = 310754)
    public void applyingOfCreatedAppLevelObjectInObjectListTest() {
        userOnObjectPage.chooseObjectInListByName(objectName, objectCount);
        userOnObjectPage.applyObjectChanges();
        userOnObjectPage.checkPopupMessage(PopupMessageEnum.SUCCESS.getMessageText());
        userOnObjectPage.refreshObjectsList();
        userOnObjectPage.checkObjectIsInList(objectName, StatusEnum.APPLIED.getStatus(), objectCount);
        dbSteps.checkTableExistsInDB(objectName, true);
    }
}
