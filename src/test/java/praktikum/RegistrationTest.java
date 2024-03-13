package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import praktikum.api.Api;
import praktikum.model.AuthorizationRequest;
import praktikum.model.UserRequest;
import praktikum.pages.LoginPage;
import praktikum.pages.MainPage;
import praktikum.pages.RegisterPage;

public class RegistrationTest {

    @Rule
    public DriverRule driverRule = new DriverRule();
    private Api api;
    private UserRequest userRequest;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;

    @Before
    public void setUp() {
        WebDriver webDriver = driverRule.getWebDriver();
        mainPage = new MainPage(webDriver);
        loginPage = new LoginPage(webDriver);
        registerPage = new RegisterPage(webDriver);

        api = new Api();
        userRequest = Utils.createRandomUser();
    }

    @Test
    @DisplayName("Проверка успешноц регистрации")
    public void testSuccessfulRegistration() {
        mainPage.open();
        mainPage.waitForLoad();
        mainPage.clickEnterAccountButton();

        loginPage.clickRegisterLink();
        registerPage.waitForLoad();
        registerPage.enterNewAccountData(
                userRequest.getName(),
                userRequest.getEmail(),
                userRequest.getPassword()
        );
        registerPage.clickRegisterButton();
        loginPage.waitForLoad();
        loginPage.enterLoginData(userRequest.getEmail(), userRequest.getPassword());
        loginPage.clickEnterButton();
        mainPage.waitForLoad();

        Assert.assertTrue("Successful registration failed", mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Проверка регистрации с невалидным паролем")
    public void testRegistrationWithInvalidPassword() {
        api.createUser(userRequest);
        mainPage.open();
        mainPage.waitForLoad();
        mainPage.clickEnterAccountButton();

        loginPage.clickRegisterLink();
        registerPage.waitForLoad();
        int maxInvalidPasswordLength = 5;
        registerPage.enterNewAccountData(
                userRequest.getName(),
                userRequest.getEmail(),
                Utils.randomPassword(maxInvalidPasswordLength)
        );
        registerPage.clickRegisterButton();
        Assert.assertTrue("There is no invalid password error",
                registerPage.isIncorrectPasswordLabelVisible());
    }

    @After
    public void tearDown() {
        AuthorizationRequest authorizationRequest = new AuthorizationRequest(
                userRequest.getEmail(),
                userRequest.getPassword());
        String accessToken = api.loginUser(authorizationRequest);
        api.deleteUser(accessToken);
    }
}