package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ProjectTest extends BaseTest {

    @Test(testName = "Create new project")
    @Description("Check create new project with name QA")
    public void checkCreateNewProject() {
        log.info("Method: checkCreateNewProject");
        loginPage
                .openLoginPage()
                .login(user, password);
        projectsPage
                .openProjectsPage()
                .waitTillOpened()
                .createProject("QA");

        String projectName = projectsPage.getProjectName("QA");
        assertEquals(projectName, "QA", "Incorrect project name");
    }

    @Test(testName = "Remove project")
    @Description("Check remove project with name QA." +
            " Before run this test make sure that the project this name exist")
    public void checkRemoveProject() {
        log.info("Method: checkRemoveProject");
        loginPage
                .openLoginPage()
                .login(user, password);
        projectsPage
                .openProjectsPage()
                .waitTillOpened()
                .removeProject("QA")
                .elementsIsExist("QA");

        assertTrue(projectsPage.elementsIsExist("QA"), "Project does not delete");
    }
}
