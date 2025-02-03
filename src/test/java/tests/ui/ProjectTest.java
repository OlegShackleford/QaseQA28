package tests.ui;

import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static org.testng.Assert.assertTrue;

@Log4j2
public class ProjectTest extends BaseTest {

    @Test(testName = "Create new project")
    @Description("Check create new project with name QA ")
    public void checkCreateNewProject() {
        log.info("Method: checkCreateNewProject");
        loginPage
                .openLoginPage()
                .login(user, password);
        projectsPage
                .openProjectsPage()
                .waitTillOpened()
                .createProject("QA")
                .openProjectsPage();
        assertTrue(projectsPage.isProjectExist("QA", "QA")
                , "Project not found on the page");
        projectsPage.removeProject("QA");
    }
}
