package com.automation;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class APITests {

    private static final String KEY = "your_api_key_here";
    private static final String TOKEN = "your_api_token_here";
    private String boardId;
    private String listId;
    private String cardId;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://api.trello.com/1";
    }

    @Test(priority = 1)
    public void createBoard() {
        Response response = given()
                .queryParam("key", KEY)
                .queryParam("token", TOKEN)
                .queryParam("name", "My Board")
                .queryParam("defaultLists", false)
                .when()
                .post("/boards")
                .then()
                .statusCode(200)
                .body("name", equalTo("My Board"))
                .body("prefs.permissionLevel", equalTo("private"))
                .body("prefs.background", equalTo("board"))
                .body("prefs.cardCovers", equalTo(true))
                .extract().response();

        boardId = response.path("id");
    }

    @Test(priority = 2)
    public void createLists() {
        String[] listNames = {"To-Do", "Inprogress", "Completed", "In testing", "Done", "Deployed"};

        for (String listName : listNames) {
            Response response = given()
                    .queryParam("key", KEY)
                    .queryParam("token", TOKEN)
                    .queryParam("name", listName)
                    .queryParam("idBoard", boardId)
                    .when()
                    .post("/lists")
                    .then()
                    .statusCode(200)
                    .body("name", equalTo(listName))
                    .body("idBoard", equalTo(boardId))
                    .body("closed", equalTo(false))
                    .extract().response();

            if (listName.equals("To-Do")) {
                listId = response.path("id");
            }
        }
    }

    @Test(priority = 3)
    public void createCard() {
        Response response = given()
                .queryParam("key", KEY)
                .queryParam("token", TOKEN)
                .queryParam("name", "My Card")
                .queryParam("idList", listId)
                .when()
                .post("/cards")
                .then()
                .statusCode(200)
                .body("name", equalTo("My Card"))
                .body("idList", equalTo(listId))
                .body("idBoard", equalTo(boardId))
                .extract().response();

        cardId = response.path("id");
    }

    @Test(priority = 4)
    public void moveCardToLists() {
        String[] listsToMove = {"Inprogress", "Completed", "In testing", "Done", "Deployed"};

        for (String listName : listsToMove) {
            given()
                    .queryParam("key", KEY)
                    .queryParam("token", TOKEN)
                    .queryParam("idList", listId)
                    .queryParam("idBoard", boardId)
                    .queryParam("pos", "bottom")
                    .when()
                    .post("/cards/{id}/idList", cardId)
                    .then()
                    .statusCode(200)
                    .body("id", equalTo(cardId))
                    .body("idList", not(equalTo(listId)));

            listId = RestAssured.given()
                    .queryParam("key", KEY)
                    .queryParam("token", TOKEN)
                    .when()
                    .get("/boards/{boardId}/lists", boardId)
                    .then()
                    .contentType(ContentType.JSON)
                    .extract()
                    .response()
                    .path("find {it.name == '" + listName + "'}.id");
        }
    }
}
