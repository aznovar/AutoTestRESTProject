package ru.ui.smoke.user_objects;

import io.qameta.allure.Feature;
import main.java.ru.api.enums.StatusEnum;
import main.java.ru.ui.blocks.navigation.NavigationMenu;
import main.java.ru.ui.model.user_objects.DBObjectTypeEnum;
import main.java.ru.ui.model.user_objects.ObjectTypeEnum;
import main.java.ru.ui.steps.DBSteps;
import main.java.ru.ui.steps.LoginSteps;
import main.java.ru.ui.steps.objects.SetupObjectSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.ui.BaseUITest;

@Test(groups = {"smoke"})
@Feature("Bom.Объектная модель.Объекты.Объекты типа 'Уровень приложения'")
public class CreateAppLevelUserObjectsTest extends BaseUITest {

    private SetupObjectSteps userOnObjectPage;
    private DBSteps dbSteps;

    private String objectNomination;
    private String objectName;
    private Integer objectCount;
    private String objectType = ObjectTypeEnum.APP_LEVEL.getType();
    private String dbNewObjectType = DBObjectTypeEnum.EDITOR.name();

    @BeforeMethod(description = "Настройки перед тестовым методом")
    @Override
    public void setup() throws CoreProblem {

        super.setup();

        objectNomination = Param.genAutoName();
        objectName = objectNomination.toUpperCase();

        userOnObjectPage = new SetupObjectSteps(driver);
        dbSteps = new DBSteps();
        objectCount = dbSteps.getObjectsCountInDB();

        LoginSteps userOnLoginPage = new LoginSteps(driver);
        driver.get(URL + NavigationMenu.OBJECTS.getUri());
        userOnLoginPage.doLogin(smokeUser.getUserName(), smokeUser.getPassword());
    }

    @Test(description = "Создание макета объекта 'Уровень приложения'", timeOut = 300000)
    @AutoTest(testRailId = 310724)
    public void createAppLevelObjectTest() {
        userOnObjectPage.openAddObjectDialog();
        userOnObjectPage.chooseObjectType(objectType);
        userOnObjectPage.enterObjectNomination(objectNomination);
        userOnObjectPage.clickCreateButton();
        userOnObjectPage.checkObjectSetupTabContainsAllTabs();
        userOnObjectPage.checkObjectType(objectType);
        userOnObjectPage.checkObjectNomination(objectNomination);
        userOnObjectPage.checkObjectName(objectName);
        userOnObjectPage.checkObjectTable(objectName);
        userOnObjectPage.checkObjectStorage(DBName.ORACLE.getDisplayName());
    }

    @Test(description = "Сохранение макета объекта 'Уровнень приложения' в Списке объектов", timeOut = 300000)
    @AutoTest(testRailId = 310725)
    public void saveAppLevelObjectInObjectListTest() {
        userOnObjectPage.openAddObjectDialog();
        userOnObjectPage.chooseObjectType(objectType);
        userOnObjectPage.enterObjectNomination(objectNomination);
        userOnObjectPage.clickCreateButton();
        userOnObjectPage.openObjectsListTab();
        userOnObjectPage.checkObjectIsInList(objectNomination, StatusEnum.NEW.getStatus(), objectCount);
    }

    @Test(description = "Сохранение макета объекта 'Уровень приложения' в БД", timeOut = 300000)
    @AutoTest(testRailId = 310726)
    public void saveDictionaryInDBTest() {
        userOnObjectPage.openAddObjectDialog();
        userOnObjectPage.chooseObjectType(objectType);
        userOnObjectPage.enterObjectNomination(objectNomination);
        userOnObjectPage.clickCreateButton();
        userOnObjectPage.checkPopupMessage(PopupMessageEnum.SUCCESS.getMessageText());
        dbSteps.checkNewObjectInDBByNameAndType(objectName, dbNewObjectType);
        dbSteps.checkTableExistsInDB(objectName, false);
    }
}