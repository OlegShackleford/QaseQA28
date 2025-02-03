package tests.api;

import adapters.ProjectApi;
import dto.CreateProjectRq;
import dto.CreateProjectRs;
import dto.ProjectRs;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static adapters.ProjectApi.*;
import static org.testng.Assert.assertTrue;

public class ProjectCRUDTest {

    public String title = "Project API";
    public String code = "Projects".toUpperCase();
    public String description = "This is description for testing";
    public String access = "all";
    public String group = "Students";

    SoftAssert softAssert = new SoftAssert();

    @Test(testName = "Check created project with API",description = "Create project, get project, delete project")
    @Description("Test with three API steps: create project,get project,delete project")
    public void checkCreateProject() {
        CreateProjectRq projectRq = CreateProjectRq
                .builder()
                .title(title)
                .code(code)
                .description(description)
                .access(access)
                .group(group)
                .build();

        CreateProjectRs createResponse = createProjectWithApi(projectRq);
        softAssert.assertTrue(createResponse.getStatus(),"Status not true");
        softAssert.assertEquals(createResponse.getResult().getCode(),"PROJECTS","Incorrect code of project");

        ProjectRs getResponse = getProjectWithApi(code);
        softAssert.assertEquals(getResponse.getResult().getTitle(),"Project API","Incorrect title");

        CreateProjectRs deleteResponse = deleteProjectWithApi(code);
        softAssert.assertTrue(deleteResponse.getStatus(),"Status not true");
        softAssert.assertAll();
    }

}
