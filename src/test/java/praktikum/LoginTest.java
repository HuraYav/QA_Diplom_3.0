package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import praktikum.api.Api;
import praktikum.model.UserRequest;
import praktikum.pages.ForgotPasswordPage;
import praktikum.pages.LoginPage;
import praktikum.pages.MainPage;
import praktikum.pages.RegisterPage;


public class LoginTest {

    @Rule
    public DriverRule driverRule = new DriverRule();
    private Api api;
    private UserRequest userRequest;
    private LoginPage loginPage;
    private MainPage mainPage;
    private RegisterPage registerPage;
    private ForgotPasswordPage forgotPasswordPage;
    private String accessToken;

    @Before
    public void setUp() {
        WebDriver webDriver = driverRule.getWebDriver();
        api = new Api();
        userRequest = Utils.createRandomUser();
        loginPage = new LoginPage(webDriver);
        mainPage = new MainPage(webDriver);
        registerPage = new RegisterPage(webDriver);
        forgotPasswordPage = new ForgotPasswordPage(webDriver);
        accessToken = api.createUser(userRequest);
    }

    @Test
    @DisplayName("Проверка регистрации через аккаунт")
    public void testLoginByEnterAccountButton() {
        mainPage.open();
        mainPage.waitForLoad();
        mainPage.clickEnterAccountButton();
        loginPage.waitForLoad();
        loginPage.enterLoginData(userRequest.getEmail(), userRequest.getPassword());
        loginPage.clickEnterButton();
        mainPage.waitForLoad();

        Assert.assertTrue("Login by account button failed", mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Проверка регистрации через лк")
    public void testLoginByProfileLink() {
        mainPage.open();
        mainPage.waitForLoad();
        mainPage.clickEnterProfileLink();
        loginPage.waitForLoad();
        loginPage.enterLoginData(userRequest.getEmail(), userRequest.getPassword());
        loginPage.clickEnterButton();
        mainPage.waitForLoad();

        Assert.assertTrue("Login by profile button failed", mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Проверка регистрации")
    public void testLoginOnRegistrationPage() {
        mainPage.open();
        mainPage.waitForLoad();
        mainPage.clickEnterAccountButton();
        loginPage.waitForLoad();
        loginPage.clickRegisterLink();
        registerPage.waitForLoad();
        registerPage.clickLoginLink();
        loginPage.waitForLoad();
        loginPage.enterLoginData(userRequest.getEmail(), userRequest.getPassword());
        loginPage.clickEnterButton();
        mainPage.waitForLoad();

        Assert.assertTrue("Login throw register form failed", mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Проверка регистрации через Восстановить пароль")
    public void testLoginOnPasswordRecoveryPage() {
        mainPage.open();
        mainPage.waitForLoad();
        mainPage.clickEnterAccountButton();
        loginPage.waitForLoad();
        loginPage.clickForgotPasswordLink();
        forgotPasswordPage.waitForLoad();
        forgotPasswordPage.clickEnterButton();
        loginPage.waitForLoad();
        loginPage.enterLoginData(userRequest.getEmail(), userRequest.getPassword());
        loginPage.clickEnterButton();
        mainPage.waitForLoad();

        Assert.assertTrue("Login throw forgot password page failed", mainPage.isOrderButtonVisible());
    }

    @After
    public void tearDown() {
        api.deleteUser(accessToken);
    }
}