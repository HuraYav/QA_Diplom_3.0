package praktikum.api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import praktikum.constans.Paths;
import praktikum.model.AuthorizationRequest;
import praktikum.model.UserRequest;
import io.restassured.http.Header;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

public class Api {

    private final Header contentTypeHeader = new Header(CONTENT_TYPE, APPLICATION_JSON.getMimeType());

    public Api() {
        RestAssured.baseURI = Paths.BASE_URL;
    }

    @Step("Создание пользователя")
    public String createUser(UserRequest userRequest) {
        Response response = given()
                .header(contentTypeHeader)
                .body(userRequest)
                .when()
                .post(Paths.CREATE_USER_PATH);

        return response.getBody().jsonPath().getString("accessToken");
    }

    @Step("Авторизация пользователя")
    public String loginUser(AuthorizationRequest authorizationRequest) {
        Response response = given()
                .header(contentTypeHeader)
                .body(authorizationRequest)
                .when()
                .post(Paths.LOGIN_USER_PATH);
        return response.getBody().jsonPath().getString("accessToken");
    }

    @Step("Удаление пользователя")
    public void deleteUser(String accessToken) {
        Header authHeader = new Header(AUTHORIZATION, accessToken);
        given()
                .header(authHeader)
                .when()
                .delete(Paths.AUTH_USER_PATH);
    }
}
