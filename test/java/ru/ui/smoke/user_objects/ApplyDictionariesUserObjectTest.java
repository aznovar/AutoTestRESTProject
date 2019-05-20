package ru.ui.smoke.user_objects;

import io.qameta.allure.Feature;
import main.java.ru.api.enums.MetaType;
import main.java.ru.ui.blocks.navigation.NavigationMenu;
import main.java.ru.ui.steps.DBSteps;
import main.java.ru.ui.steps.LoginSteps;
import main.java.ru.ui.steps.objects.SetupObjectSteps;
import main.java.ru.ui.steps.objects.user_objects.DictionariesSteps;
import main.java.ru.ui.steps.preconditions.BomObjectPreconditionSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.ui.BaseUITest;


@Test(groups = {"smoke"})
@Feature("Bom.Пользовательские объекты.Справочники")
public class ApplyDictionariesUserObjectTest extends BaseUITest {

    private SetupObjectSteps userOnObjectPage;
    private DictionariesSteps userOnDictionariesPage;
    private DBSteps dbSteps;
    private BomObjectPreconditionSteps preConditionSteps;
    private String objectName;
    private Integer objectCount;

    @BeforeMethod(description = "Настройки перед тестовым методом")
    @Override
    public void setup() throws CoreProblem {

        super.setup();

        userOnObjectPage = new SetupObjectSteps(driver);
        userOnDictionariesPage = new DictionariesSteps(driver);
        LoginSteps userOnLoginPage = new LoginSteps(driver);
        dbSteps = new DBSteps();
        preConditionSteps = new BomObjectPreconditionSteps();

        objectCount = dbSteps.getObjectsCountInDB();

        // Создание объекта типа 'Справочники'
        objectName = preConditionSteps.createBomObject(false, MetaType.refdata).getName().toUpperCase();

        driver.get(JD_URL + NavigationMenu.OBJECTS.getUri());
        userOnLoginPage.doLogin(smokeUser.getUserName(), smokeUser.getPassword());
    }

    @Test(description = "Применение изменений нового объекта 'Справочники' в статусе 'Новый'", timeOut = 300000)
    @AutoTest(testRailId = 310063)
    public void applyingOfCreatedDictionaryObjectInObjectListTest() throws InterruptedException {
        userOnObjectPage.chooseObjectInListByName(objectName, objectCount);
        userOnObjectPage.applyObjectChanges();
        userOnObjectPage.checkPopupMessage(PopupMessageEnum.SUCCESS.getMessageText());
        userOnObjectPage.refreshObjectsList();
        userOnObjectPage.navigateThroughMenu(NavigationMenu.DICTIONARIES.getMenuName());
        userOnDictionariesPage.checkDictionaryIsInList(objectName.toLowerCase(), objectCount);
        dbSteps.checkTableExistsInDB(objectName, true);
    }
}
