package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$x;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class RepositoryProjectPage {

    private final String BUTTON_PLUS_CASE = "//button[@id = 'create-case-button']";
    private final String NAME_OF_TESTCASE = "//div[@data-suite-body-id = '0']/descendant::div[text() = '%s']";

    @Step("Get name of testcase - 'nameOfTestCase'")
    public String getNameOfTestCase(String nameOfTestCase) {
        log.info("Method: getNameOfTestCase '{}'",nameOfTestCase);
        return $x(String.format(NAME_OF_TESTCASE, nameOfTestCase)).getText();
    }

    @Step("Wait till clickable button +Case")
    public RepositoryProjectPage waitTillClickable() {
        log.info("Method: waitTillClickable (+Case)");
        $x(BUTTON_PLUS_CASE).shouldBe(Condition.visible).shouldBe(Condition.clickable);
        return this;
    }

    @Step("Click to button +Case")
    public CreateTestCasePage clickPlusCase() {
        log.info("Method: clickPlusCase");
        $x(BUTTON_PLUS_CASE).click();
        return new CreateTestCasePage();
    }
}
