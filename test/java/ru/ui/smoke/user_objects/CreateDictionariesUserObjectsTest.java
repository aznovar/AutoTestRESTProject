package ru.ui.smoke.user_objects;

import io.qameta.allure.Feature;
import main.java.ru.api.Param;
import main.java.ru.api.enums.StatusEnum;
import main.java.ru.ui.blocks.navigation.NavigationMenu;
import main.java.ru.ui.model.user_objects.DBObjectTypeEnum;
import main.java.ru.ui.model.user_objects.ObjectTypeEnum;
import main.java.ru.ui.steps.DBSteps;
import main.java.ru.ui.steps.LoginSteps;
import main.java.ru.ui.steps.objects.SetupObjectSteps;
import main.java.ru.ui.steps.objects.user_objects.DictionariesSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.jd.api.enums.StatusEnum;
import ru.jd.sup.ann.AutoTest;
import ru.jd.sup.enums.db.DBName;
import ru.jd.sup.excpt.CoreProblem;
import ru.jd.sup.single.Param;
import ru.jd.ui.BaseUITest;
import ru.jd.ui.model.PopupMessageEnum;
import ru.jd.ui.model.navigation.NavigationMenu;
import ru.jd.ui.model.user_objects.DBObjectTypeEnum;
import ru.jd.ui.model.user_objects.ObjectTypeEnum;
import ru.jd.ui.steps.DBSteps;
import ru.jd.ui.steps.LoginSteps;
import ru.jd.ui.steps.setup.object_model.BomObjectsSteps;
import ru.jd.ui.steps.user_objects.DictionariesSteps;
import ru.ui.BaseUITest;

import static ru.jd.sup.single.Const.JD_URL;

@Test(groups = {"smoke"})
@Feature("Bom.Пользовательские объекты.Справочники")
public class CreateDictionariesUserObjectsTest extends BaseUITest {

    private SetupObjectSteps userOnObjectPage;
    private DictionariesSteps userOnDictionariesPage;
    private DBSteps dbSteps;

    private String objectNomination;
    private String objectName;
    private Integer objectCount;
    private String objectType = ObjectTypeEnum.DICTIONARIES.getType();
    private String dbNewObjectType = DBObjectTypeEnum.EDITOR.name();

    @BeforeMethod(description = "Настройки перед тестовым методом")
    @Override
    public void setup() throws CoreProblem {

        super.setup();

        objectNomination = Param.genAutoName();
        objectName = objectNomination.toUpperCase();
        userOnObjectPage = new SetupObjectSteps(driver);
        userOnDictionariesPage = new DictionariesSteps(driver);
        dbSteps = new DBSteps();
        objectCount = dbSteps.getObjectsCountInDB();

        LoginSteps userOnLoginPage = new LoginSteps(driver);
        driver.get(URL + NavigationMenu.OBJECTS.getUri());
        userOnLoginPage.doLogin(smokeUser.getUserName(), smokeUser.getPassword());
    }

    @Test(description = "Создание макета объекта 'Справочники'", timeOut = 300000)
    @AutoTest(testRailId = 304343)
    public void createDictionaryObjectTest() {
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

    @Test(description = "Сохранение макета объекта 'Справочники' в списке объектов", timeOut = 300000)
    @AutoTest(testRailId = 304344)
    public void saveDictionaryObjectInObjectListTest() {
        userOnObjectPage.openAddObjectDialog();
        userOnObjectPage.chooseObjectType(objectType);
        userOnObjectPage.enterObjectNomination(objectNomination);
        userOnObjectPage.clickCreateButton();
        userOnObjectPage.openObjectsListTab();
        userOnObjectPage.checkObjectIsInList(objectNomination, StatusEnum.NEW.getStatus(), objectCount);
    }

    @Test(description = "Сохранение макета объекта 'Справочники' в БД", timeOut = 300000)
    @AutoTest(testRailId = 304345)
    public void saveDictionaryInDBTest() {
        userOnObjectPage.openAddObjectDialog();
        userOnObjectPage.chooseObjectType(objectType);
        userOnObjectPage.enterObjectNomination(objectNomination);
        userOnObjectPage.clickCreateButton();
        dbSteps.checkNewObjectInDBByNameAndType(objectName, dbNewObjectType);
    }

    @Test(description = "Отсутствие созданного макета объекта 'Справочники' в Пользовательские объекты - Справочники",
            timeOut = 300000)
    @AutoTest(testRailId = 304346)
    public void createdDictionaryObjectIsNotInDictionaryPageTest() {
        userOnObjectPage.openAddObjectDialog();
        userOnObjectPage.chooseObjectType(objectType);
        userOnObjectPage.enterObjectNomination(objectNomination);
        userOnObjectPage.clickCreateButton();
        userOnObjectPage.navigateThroughMenu(NavigationMenu.DICTIONARIES.getMenuName());
        userOnDictionariesPage.checkDictionaryIsNotInList(objectNomination, objectCount);
        dbSteps.checkTableExistsInDB(objectName, false);
    }
}
