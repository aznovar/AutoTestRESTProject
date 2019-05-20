package main.java.ru.ui.steps;

import io.qameta.allure.Step;
import main.java.ru.ui.blocks.navigation.NavigationMenu;
import main.java.ru.ui.pages.BasePage;
import main.java.ru.ui.pages.LoginPage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.util.NoSuchElementException;

/**
 * Класс шагов взаимодействия со страницей логина
 */
public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private BasePage basePage;

    public LoginSteps(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
        this.basePage = new BasePage(driver);
    }

    @Step("Step. Зайти в систему с '{login}/{password}'")
    public void doLogin(String login, String password) {
        driver.navigate().refresh();
        WebElement loginFieldWait = (new WebDriverWait(driver, 7))
                .until(ExpectedConditions.visibilityOf(loginPage.getLoginField()));
        loginPage.getLoginField().clear();
        loginPage.getLoginField().sendKeys(login);
        loginPage.getPasswordField().clear();
        loginPage.getPasswordField().sendKeys(password);
        WebElement enterButtonWait = (new WebDriverWait(driver, 7))
                .until(ExpectedConditions.elementToBeClickable(loginPage.getEnterButton()));
        loginPage.getEnterButton().click();
        try {
            basePage.getMainLogo().isDisplayed();
        } catch (NoSuchElementException e) {
            Assert.fail("Логин в систему не произошел");
        }
    }


}
