package tests;

import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static org.testng.Assert.assertEquals;

@Log4j2
public class CreateNewCaseTest extends BaseTest {

    @Test(testName = "Create new testcase")
    @Description("Create new testcase with name - QS." +
            " Before run this test make sure that the project this name exist ")
    public void checkCreateTestCase() {
        log.info("Method: checkCreateTestCase");
        loginPage
                .openLoginPage()
                .login(user, password);
        projectsPage
                .openProjectsPage()
                .waitTillOpened().openCreatedProject("QS");

        repositoryProjectPage.waitTillClickable()
                .clickPlusCase()
                .inputTitle("Test manage system")
                .selectDropDown("Status", "Draft")
                .selectDropDown("Severity", "Major")
                .selectDropDown("Is flaky", "Yes")
                .selectDropDown("Type", "Smoke")
                .clickButtonSafe();
        String testCaseTitle = repositoryProjectPage.getNameOfTestCase("Test manage system");
        assertEquals(testCaseTitle, "Test manage system", "Incorrect test case title");
    }
}
