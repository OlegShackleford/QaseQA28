package tests.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.CreateTestCasePage;
import pages.LoginPage;
import pages.ProjectsPage;
import pages.RepositoryProjectPage;
import utils.PropertyReader;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class BaseTest {

    protected LoginPage loginPage;
    protected ProjectsPage projectsPage;
    protected RepositoryProjectPage repositoryProjectPage;
    protected CreateTestCasePage createTestCasePage;

    protected String user = System.getProperty("user", PropertyReader.getProperty("user"));
    protected String password = System.getProperty("password", PropertyReader.getProperty("password"));

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        Configuration.timeout = 90000;
        Configuration.browserCapabilities = options; //Кидаем опции какие нам нужны
//        Configuration.browser = "chrome"; //указываем какой браузер we want
//        Configuration.headless = false;
//        Configuration.clickViaJs = true; //вместо JavaScriptExecuter; Всегда будет кликать через негож
//        Configuration.baseUrl = "https://app.qase.io/";
//        getWebDriver().navigate();
        loginPage = new LoginPage();
        projectsPage = new ProjectsPage();
        repositoryProjectPage = new RepositoryProjectPage();
        createTestCasePage = new CreateTestCasePage();

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (getWebDriver() != null) {
            getWebDriver().quit();
        }

    }
}
