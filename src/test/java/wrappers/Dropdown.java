package wrappers;

import com.codeborne.selenide.Condition;
import pages.CreateTestCasePage;
import static com.codeborne.selenide.Selenide.$x;

public class Dropdown {

    String label;
    private final String DROPBOX_PATTERN = "//label[text()='%s']/following-sibling::div[@role='combobox']";
    private final String OPTIONS_OF_DROPBOX = "//*[text() ='%s']";

    public Dropdown(String label){
        this.label = label;
    }

    public CreateTestCasePage select(String option){
        $x(String.format(DROPBOX_PATTERN, label)).should(Condition.visible).click();
        $x(String.format(OPTIONS_OF_DROPBOX, option)).should(Condition.visible).click();
        return new CreateTestCasePage();
    }
}
