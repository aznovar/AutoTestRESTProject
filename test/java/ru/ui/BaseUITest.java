package ru.ui;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;


public abstract class BaseUITest {

    protected WebDriver driver;
    protected User smokeUser;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass(description = "Настройки перед BaseUI классом", alwaysRun = true)
    public void setupUser() {
        smokeUser = new AuthPreconditionSteps().createSmokeUser();
    }

    @BeforeMethod(description = "Настройки перед BaseUI методом", alwaysRun = true)
    public void setup() throws CoreProblem {
        driver = checkEnvExist(CIEnv.IS_CI) ? getRemoteDriver() : getBaseDriver(isBrowserHeadless());
        driver.manage().window().setSize(new Dimension(1280, 1024));
    }


    @AfterMethod(description = "Настройки после BaseUI метода", alwaysRun = true)
    public void teardown() throws CoreProblem
    {
        driver.close();
    }

}
