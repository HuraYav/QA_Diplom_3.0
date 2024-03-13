package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends Page {

    private final By logoutButton = By.xpath("//button[text() = 'Выход']");

    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    @Step("Ожидание видимости элемента")
    public void waitForLoad() {
        waitForVisibility(logoutButton);
    }

    @Step("Клик по элементу - Выход")
    public void clickLogoutButton() {
        webDriver.findElement(logoutButton).click();
    }
}