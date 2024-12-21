package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import wrappers.Dropdown;

import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class CreateTestCasePage {

    private final String INPUT_TITLE = "//input[@id = 'title']";
    private final String BUTTON_SAFE = "//button[@id = 'save-case']";

    @Step("Input title of testcase - '{title}'")
    public CreateTestCasePage inputTitle(String title) {
        log.info("Method: inputTitle '{}'", title);
        $x(INPUT_TITLE).setValue(title);
        return this;
    }

    @Step("Select dropdown: '{label}' with option '{option}'")
    public CreateTestCasePage selectDropDown(String label, String option) {
        log.info("Method: selectDropDown. Select dropdown - '{}',option - '{}'", label, option);
        new Dropdown(label).select(option);
        return this;
    }

    @Step("Click button safe")
    public void clickButtonSafe() {
        log.info("Method: clickButtonSafe");
        $x(BUTTON_SAFE).should(Condition.clickable).click();
    }
}
