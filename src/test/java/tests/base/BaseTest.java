package tests.base;

import adapters.ProjectApi;
import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;
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

    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless","--window-size=1920,1080");
                Configuration.timeout = 90000;
                Configuration.browser = Browsers.CHROME;
                Configuration.headless = true;
                Configuration.browserCapabilities = options;
                break;

//            case "firefox":
//                FirefoxOptions firefoxOptions = new FirefoxOptions();
//                firefoxOptions.addArguments("--headless","--window-size=1920,1080");
//                Configuration.timeout = 90000;
//                Configuration.browser = Browsers.FIREFOX;
//                Configuration.headless = true;
//                Configuration.browserCapabilities = firefoxOptions;
//                break;
        }

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
