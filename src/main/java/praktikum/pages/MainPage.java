package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import praktikum.constans.Paths;

public class MainPage extends Page {

    private final By createBurgerHeader = By.xpath("//h1[text() = 'Соберите бургер']");
    private final By profileButton = By.xpath("//p[text()='Личный Кабинет']");
    private final By orderButton = By.xpath("//button[text() = 'Оформить заказ']");
    private final By enterAccountButton = By.xpath("//button[text() = 'Войти в аккаунт']");
    private final By sauceButton = By.xpath("//span[text() = 'Соусы']");
    private final By bunButton = By.xpath("//span[text() = 'Булки']");
    private final By fillingButton = By.xpath("//span[text() = 'Начинки']");
    private final By currentSection = By.className("tab_tab_type_current__2BEPc");
    private final By childSpanNode = By.tagName("span");

    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    @Step("Ожидание видимости элемента - Соберите бургер")
    public void waitForLoad() {
        waitForVisibility(createBurgerHeader);
    }

    @Step("Открыть страницу")
    public void open() {
        webDriver.get(Paths.BASE_URL);
    }

    @Step("Клик по элементу - Войти в аккаунт")
    public void clickEnterAccountButton() {
        webDriver.findElement(enterAccountButton).click();
    }

    @Step("Клик по элементу - Личный Кабинет")
    public void clickEnterProfileLink() {
        webDriver.findElement(profileButton).click();
    }

    @Step("Проверка видимости элемента - Оформить заказ")
    public boolean isOrderButtonVisible() {
        return webDriver.findElement(orderButton).isDisplayed();
    }

    @Step("Проверка видимости элемента - Соберите бургер")
    public boolean isCreateOrderHeaderVisible() {
        return webDriver.findElement(createBurgerHeader).isDisplayed();
    }

    @Step("Клик по элементу - Булки")
    public void clickBunButton() {
        webDriver.findElement(bunButton).click();
    }

    @Step("Клик по элементу - Начинки")
    public void clickFillingButton() {
        webDriver.findElement(fillingButton).click();
    }

    @Step("Клик по элементу - Соусы")
    public void clickSauceButton() {
        webDriver.findElement(sauceButton).click();
    }

    @Step("Проверка на соответствие элементов")
    public boolean isSauceSectionSelected() {
        return isCorrectSectionSelected(sauceButton);
    }

    @Step("Проверка на соответствие элементов")
    public boolean isBunSectionSelected() {
        return isCorrectSectionSelected(bunButton);
    }

    @Step("Проверка на соответствие элементов")
    public boolean isFillingSectionSelected() {
        return isCorrectSectionSelected(fillingButton);
    }

    private boolean isCorrectSectionSelected(By currentButtonLocator) {
        WebElement currentSectionElement = webDriver.findElement(this.currentSection);
        WebElement currentButton = webDriver.findElement(currentButtonLocator);
        return currentButton.getText().equals(currentSectionElement.findElement(childSpanNode).getText());
    }
}