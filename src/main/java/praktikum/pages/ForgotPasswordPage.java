package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends Page {

    private final By forgotPasswordLabel = By.xpath("//h2[text() = 'Восстановление пароля']");
    private final By enterButton = By.xpath("//a[text() = 'Войти']");

    public ForgotPasswordPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    @Step("Ожидание видимости элемента")
    public void waitForLoad() {
        waitForVisibility(forgotPasswordLabel);
    }

    @Step("Клик по кнопке Войти")
    public void clickEnterButton() {
        webDriver.findElement(enterButton).click();
    }
}