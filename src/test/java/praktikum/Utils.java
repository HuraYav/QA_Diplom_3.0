package praktikum;

import io.qameta.allure.Step;
import praktikum.model.UserRequest;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public final class Utils {

    @Step("Генерация имён")
    public static String randomName() {
        return randomAlphabetic(5);
    }

    @Step("Генерация email")
    public static String randomEmail() {
        return randomAlphabetic(5) + "@yandex.ru";
    }

    @Step("Генерация пароля")
    public static String randomPassword(int length) {
        return randomAlphanumeric(length);
    }

    @Step("Создание пользователя")
    public static UserRequest createRandomUser() {
        return new UserRequest(
                randomName(),
                randomEmail(),
                randomPassword(6)
        );
    }
}