package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ProjectsPage {

    private final String BUTTON_CREATE_NEW_PROJECT = "//span[text() = 'Create new project']";
    private final String BUTTON_ACTION_MENU = "button[aria-label='Open action menu']";
    private final String BUTTON_REMOVE = "[data-testid=remove]";
    private final String BUTTON_DELETE = "//span[text() = 'Delete project']/ancestor::button[@type = 'button']";
    private final String ALL_PROJECT = "//table//tbody";
    private final String INPUT_PROJECT_NAME = "//input[@id = 'project-name']";
    private final String BUTTON_CREATE_PROJECT = "//span[text() = 'Create project']/ancestor::button[@type = 'submit']";
    private final String LINK_OF_PROJECT = "https://app.qase.io/project/%s";

    @Step("Open Projects page")
    public ProjectsPage openProjectsPage() {
        log.info("Method: openProjectsPage");
        open("https://app.qase.io/projects");
        return this;
    }

    @Step("Open created project - '{name}'")
    public ProjectsPage openCreatedProject(String name) {
        log.info("Method: openCreatedProject '{}'",name);
        String project = String.format(LINK_OF_PROJECT, name.toUpperCase());
        open(project);
        return this;
    }

    @Step("Wait till opened - ProjectsPage")
    public ProjectsPage waitTillOpened() {
        log.info("Method: waitTillOpened");
        $x(BUTTON_CREATE_NEW_PROJECT).shouldBe(Condition.visible);
        return this;
    }

    @Step("Remove project - '{projectName}'")
    public ProjectsPage removeProject(String projectName) {
        log.info("Method: removeProject '{}'",projectName);
        $(byText(projectName))
                .ancestor("tr")
                .find(BUTTON_ACTION_MENU)
                .click();
        $(BUTTON_REMOVE).click();
        $x(BUTTON_DELETE).click();
        return this;
    }

    @Step("Create project - '{projectName}'")
    public ProjectsPage createProject(String projectName) {
        log.info("Method: createdProject '{}'",projectName);
        $x(BUTTON_CREATE_NEW_PROJECT).click();
        $x(INPUT_PROJECT_NAME).setValue(projectName);
        $x(BUTTON_CREATE_PROJECT).click();
        return this;
    }

    @Step("Is project name exist '{projectName}'")
    public Boolean isProjectExist(String projectName,String searchedElement) {
        log.info("Method: isProjectExist '{}','{}'",projectName,searchedElement);
        return projectName.equals($(byText(searchedElement)).getText());
    }
}
