package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import praktikum.api.Api;
import praktikum.model.UserRequest;
import praktikum.pages.LoginPage;
import praktikum.pages.MainPage;
import praktikum.pages.ProfilePage;

public class ProfilePageTest {

    @Rule
    public DriverRule driverRule = new DriverRule();
    private LoginPage loginPage;
    private MainPage mainPage;
    private ProfilePage profilePage;
    private Api api;
    private UserRequest userRequest;
    private String accessToken;

    @Before
    public void setUp() {
        WebDriver webDriver = driverRule.getWebDriver();
        loginPage = new LoginPage(webDriver);
        mainPage = new MainPage(webDriver);
        profilePage = new ProfilePage(webDriver);
        api = new Api();
        userRequest = Utils.createRandomUser();
        accessToken = api.createUser(userRequest);
    }

    @Test
    @DisplayName("Проверка кнопки - личный кабинет")
    public void testProfileButton() {
        mainPage.open();
        mainPage.waitForLoad();
        mainPage.clickEnterProfileLink();
        loginPage.waitForLoad();

        Assert.assertTrue("Profile link is not working", loginPage.isEnterHeaderVisible());
    }

    @Test
    @DisplayName("Проверка кнопки - логотип")
    public void testLogoButton() throws InterruptedException {
        mainPage.open();
        mainPage.waitForLoad();
        mainPage.clickEnterProfileLink();
        loginPage.waitForLoad();
        loginPage.clickLogoButton();
        mainPage.waitForLoad();
        Assert.assertTrue("Logo button is not working", mainPage.isCreateOrderHeaderVisible());
    }

    @Test
    @DisplayName("Проверка кнопки - Конструктор")
    public void testConstructorButton() {
        mainPage.open();
        mainPage.waitForLoad();
        mainPage.clickEnterProfileLink();
        loginPage.waitForLoad();

        loginPage.clickConstructorButton();
        mainPage.waitForLoad();
        Assert.assertTrue("Constructor button is not working", mainPage.isCreateOrderHeaderVisible());
    }

    @Test
    @DisplayName("Проверка кнопки - Выход")
    public void testLogoutButton() {
        mainPage.open();
        mainPage.waitForLoad();
        mainPage.clickEnterAccountButton();
        loginPage.waitForLoad();
        loginPage.enterLoginData(userRequest.getEmail(), userRequest.getPassword());
        loginPage.clickEnterButton();
        mainPage.waitForLoad();
        mainPage.isCreateOrderHeaderVisible();
        mainPage.clickEnterProfileLink();
        profilePage.waitForLoad();
        profilePage.clickLogoutButton();

        loginPage.waitForLoad();
        Assert.assertTrue("Logout button is not working", loginPage.isEnterHeaderVisible());
    }

    @After
    public void tearDown() {
        api.deleteUser(accessToken);
    }
}