package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.pages.MainPage;

public class MainPageTest {

    @Rule
    public DriverRule driverRule = new DriverRule();
    private MainPage mainPage;

    @Before
    public void setUp() {
        WebDriver webDriver = driverRule.getWebDriver();
        mainPage = new MainPage(webDriver);
    }

    @Test
    @DisplayName("Проверка кнопки - соусы")
    public void testSauceSectionButton() {
        mainPage.open();
        mainPage.waitForLoad();

        mainPage.clickSauceButton();
        Assert.assertTrue("Sauce selection button is not working", mainPage.isSauceSectionSelected());
    }

    @Test
    @DisplayName("Проверка кнопки - булки")
    public void testBunSectionButton() {
        mainPage.open();
        mainPage.waitForLoad();

        mainPage.clickSauceButton();
        mainPage.clickBunButton();
        Assert.assertTrue("Bun selection button is not working", mainPage.isBunSectionSelected());
    }

    @Test
    @DisplayName("Проверка кнопки - начинки")
    public void testFillingSectionButton() {
        mainPage.open();
        mainPage.waitForLoad();

        mainPage.clickFillingButton();
        Assert.assertTrue("Filling selection button is not working", mainPage.isFillingSectionSelected());
    }
}