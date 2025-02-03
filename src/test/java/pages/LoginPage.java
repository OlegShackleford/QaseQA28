package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class LoginPage {

    private final String USER_INPUT = "//input[@name = 'email']";
    private final String PASSWORD_INPUT = "//input[@name = 'password']";
    private final String BUTTON_SIGN_IN = "//button[@type = 'submit']";

    @Step("Open login page")
    public LoginPage openLoginPage() {
        log.info("Method: openLoginPage");
        open("https://app.qase.io/login");
        return this;
    }

    @Step("Login with credentials ")
    public LoginPage login(String user, String password) {
        log.info("Method: login '{}','{}'",user,password);
        $x(USER_INPUT).setValue(user);
        $x(PASSWORD_INPUT).setValue(password);
        $x(BUTTON_SIGN_IN).click();
        return this;
    }
}
