import com.google.gson.Gson;
import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.dynamic.input.DynamicTesting;
import org.hyperskill.hstest.exception.outcomes.UnexpectedError;
import org.hyperskill.hstest.exception.outcomes.WrongAnswer;
import org.hyperskill.hstest.mocks.web.request.HttpRequest;
import org.hyperskill.hstest.mocks.web.response.HttpResponse;
import org.hyperskill.hstest.stage.SpringTest;
import org.hyperskill.hstest.testcase.CheckResult;

import static org.hyperskill.hstest.testing.expect.json.JsonChecker.*;
import static org.hyperskill.hstest.testing.expect.Expectation.expect;

import org.hyperskill.hstest.testing.expect.json.builder.JsonArrayBuilder;
import recipes.RecipesApplication;

import java.util.*;

import static org.hyperskill.hstest.testcase.CheckResult.correct;


public class RecipesApplicationTest extends SpringTest {

    public RecipesApplicationTest() {
        super(RecipesApplication.class, "../recipes_db.mv.db");
    }

    // Initialization ---

    static class Recipe {
        final String name;
        final String category;
        final String description;
        final String[] ingredients;
        final String[] directions;

        Recipe(String name, String category, String description, String[] ingredients, String[] directions) {
            this.name = name;
            this.category = category;
            this.description = description;
            this.ingredients = ingredients;
            this.directions = directions;
        }
    }

    final Recipe[] RECIPES = {
        // 0
        new Recipe(
            "Fresh Mint Tea /Test",
            "beverage /Test",
            "Light, aromatic and refreshing beverage, ... /Test",
            new String[]{"boiled water", "honey", "fresh mint leaves /Test"},
            new String[]{"Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again /Test"}
        ),
        // 1
        new Recipe(
            "Warming Ginger Tea /Test",
            "beverage /Test",
            "Ginger tea is a warming drink for cool weather, ... /Test",
            new String[]{"1 inch ginger root, minced", "1/2 lemon, juiced", "1/2 teaspoon manuka honey /Test"},
            new String[]{"Place all ingredients in a mug and fill with warm water (not too hot so you keep the beneficial honey compounds in tact)", "Steep for 5-10 minutes", "Drink and enjoy /Test"}
        ),
        // 2
        new Recipe(
            "ice-cream",
            "Dessert",
            "-",
            new String[]{"--", "---", "-"},
            new String[]{"----", "---"}
        ),
        // 3
        new Recipe(
            "tea r 4",
            "DesserT",
            "---",
            new String[]{"-", "----", "-"},
            new String[]{"----", "--", "--"}
        ),
        // 4
        new Recipe(
            "recipe ice-cream 5",
            "Desser",
            "---",
            new String[]{"-", "--", "-"},
            new String[]{"-", "--", "---"}
        ),
        // 5
        new Recipe(
            "--",
            "DeSSert",
            "---",
            new String[]{"---", "-", "--"},
            new String[]{"---", "-"}
        ),
        // 6
        new Recipe(
            "ICE-CREAM",
            "desserT",
            "----",
            new String[]{"-", "-", "--"},
            new String[]{"---", "--", "--"}
        ),
        // 7
        new Recipe(
            "---",
            "dessert",
            "--",
            new String[]{"-", "----"},
            new String[]{"-----", "-", "---"}
        ),
        // 8
        new Recipe(
            "9 recipe Tea test",
            "-",
            "----",
            new String[]{"-", "-", "----"},
            new String[]{"-----", "-", "--"}
        ),
        // 9
        new Recipe(
            "10 ice recipe test",
            "-",
            "--",
            new String[]{"----", "--;", "---"},
            new String[]{"--", "-"}
        ),
        // 10
        new Recipe(
            "11 ice-creamrecipe test",
            "veryDessert",
            "-",
            new String[]{"-", "--"},
            new String[]{"-----", "-", "---"}
        ),
        // 11
        new Recipe(
            "cream",
            "BEVerage",
            "--",
            new String[]{"---", "-", "-"},
            new String[]{"-", "--"}
        ),
        // 12
        new Recipe(
            "ice-cre",
            "---Dessert",
            "-",
            new String[]{"---", "-"},
            new String[]{"----", "-", "-"}
        ),

        // 13
        new Recipe(
            "ice-cream",
            "DESSERT",
            "-",
            new String[]{"----", "--"},
            new String[]{"-", "--", "-"}
        ),
        // 14
        new Recipe(
            "15 recipe test ice-CREAM",
            "Dessert",
            "-",
            new String[]{"-", "---'", "-----"},
            new String[]{"---", "-"}
        )
    };

    final Recipe[] INCORRECT_RECIPES = {
        //0
        new Recipe(
            null,
            "beverage",
            "Light, aromatic and refreshing beverage, ...",
            new String[]{"boiled water", "honey", "fresh mint leaves"},
            new String[]{"Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"}
        ),
        //1
        new Recipe(
            "Fresh Mint Tea",
            null,
            "Light, aromatic and refreshing beverage, ...",
            new String[]{"boiled water", "honey", "fresh mint leaves"},
            new String[]{"Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"}
        ),
        //2
        new Recipe(
            "Warming Ginger Tea",
            "beverage",
            null,
            new String[]{"1 inch ginger root, minced", "1/2 lemon, juiced", "1/2 teaspoon manuka honey"},
            new String[]{"Place all ingredients in a mug and fill with warm water (not too hot so you keep the beneficial honey compounds in tact)", "Steep for 5-10 minutes", "Drink and enjoy"}
        ),
        //3
        new Recipe(
            "Fresh Mint Tea",
            "beverage",
            "Light, aromatic and refreshing beverage, ...",
            null,
            new String[]{"Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"}
        ),
        //4
        new Recipe(
            "Warming Ginger Tea",
            "beverage",
            "Ginger tea is a warming drink for cool weather, ...",
            new String[]{"1 inch ginger root, minced", "1/2 lemon, juiced", "1/2 teaspoon manuka honey"},
            null
        ),
        //5
        new Recipe(
            "  ",
            "beverage",
            "Light, aromatic and refreshing beverage, ...",
            new String[]{"boiled water", "honey", "fresh mint leaves"},
            new String[]{"Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"}
        ),
        //6
        new Recipe(
            "Fresh Mint Tea",
            "  ",
            "Light, aromatic and refreshing beverage, ...",
            new String[]{"boiled water", "honey", "fresh mint leaves"},
            new String[]{"Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"}
        ),
        //7
        new Recipe(
            "Warming Ginger Tea",
            "beverage",
            "  ",
            new String[]{"1 inch ginger root, minced", "1/2 lemon, juiced", "1/2 teaspoon manuka honey"},
            new String[]{"Place all ingredients in a mug and fill with warm water (not too hot so you keep the beneficial honey compounds in tact)", "Steep for 5-10 minutes", "Drink and enjoy"}
        ),
        //8
        new Recipe(
            "Fresh Mint Tea",
            "beverage",
            "Light, aromatic and refreshing beverage, ...",
            new String[]{},
            new String[]{"Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"}
        ),
        //9
        new Recipe(
            "Warming Ginger Tea",
            "beverage",
            "Ginger tea is a warming drink for cool weather, ...",
            new String[]{"1 inch ginger root, minced", "1/2 lemon, juiced", "1/2 teaspoon manuka honey"},
            new String[]{}
        )
    };

    final Recipe[] RECIPES_CATEGORY_DESSERT_REVERSED = new Recipe[]{RECIPES[14], RECIPES[13], RECIPES[7], RECIPES[6],
        RECIPES[5], RECIPES[3], RECIPES[2]};

    final Recipe[] RECIPES_NAME_CONTAINS_ICE_CREAM_REVERSED = new Recipe[]{RECIPES[14], RECIPES[13], RECIPES[10], RECIPES[6],
        RECIPES[4], RECIPES[2]};

    final String[] JSON_RECIPES = toJson(RECIPES);
    final String[] JSON_INCORRECT_RECIPES = toJson(INCORRECT_RECIPES);

    final String API_RECIPE_NEW = "/api/recipe/new";
    final String API_RECIPE = "/api/recipe/";
    final String API_RECIPE_SEARCH = "/api/recipe/search/";
    final String API_REGISTER = "/api/register";

    // recipes ids will be saved when testing POST requests and used later to test GET/PUT/DELETE requests
    final List<Integer> recipeIds = new ArrayList<>();

    final String CATEGORY = "category";
    final String NAME = "name";

    final static Gson gson = new Gson();

    final String[] MAIN_LOGIN_PASS = {"LoginTest2@test.com", "Test2222222"};
    final String[] ADDITIONAL_LOGIN_PASS_1 = {"LoginTest1@test.com", "Test1111"};
    final String[] ADDITIONAL_LOGIN_PASS_2 = {"LoginTest3@test.com", "Test33333333333"};

    final String[] INCORRECT_LOGIN_CORRECT_PASS_1 = {"Test3@testcom", "Test3333"};
    final String[] INCORRECT_LOGIN_CORRECT_PASS_2 = {"Test4test.com", "Test44444444"};

    final String[] CORRECT_LOGIN_INCORRECT_PASS_1 = {"Test5@test.com", "Test555"};
    final String[] CORRECT_LOGIN_INCORRECT_PASS_2 = {"Test6@test.com", "        "};

    final String[] UNREGISTERED_LOGIN_PASS = {"abc@test.com", "password99"};


    // Helper functions ---

    static String[] toJson(Object[] objects) {
        return Arrays
            .stream(objects)
            .map(gson::toJson)
            .toArray(String[]::new);
    }

    static void throwIfIncorrectStatusCode(HttpResponse response, int status) {
        if (response.getStatusCode() != status) {
            throw new WrongAnswer(response.getRequest().getMethod() +
                " " + response.getRequest().getLocalUri() +
                " should respond with status code " + status +
                ", responded: " + response.getStatusCode() + "\n\n" +
                "Response body:\n" + response.getContent());
        }
    }

    CheckResult reloadServer() {
        try {
            reloadSpring();
        } catch (Exception e) {
            throw new UnexpectedError(e.getMessage());
        }

        return correct();
    }

    static Map<String, String> generateUrlParams(int len) {
        Map<String, String> params = new LinkedHashMap<>();

        for (int i = 0; i < len; i++) {
            params.put("key" + i, "value" + i);
        }

        return params;
    }

    static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) {
            throw new UnexpectedError(e.getMessage());
        }
    }

    static JsonArrayBuilder recipesToJsonArrayBuilder(Recipe[] recipes) {
        JsonArrayBuilder isArray = isArray();

        // just appends "item"(s)
        for (Recipe recipe : recipes) {

            isArray = isArray.item(
                isObject()
                    .value("name", isString(recipe.name))
                    .value("category", isString(recipe.category))
                    .value("date", isString(d -> d.length() >= 8))
                    .value("description", isString(recipe.description))
                    .value("ingredients", isArray(recipe.ingredients))
                    .value("directions", isArray(recipe.directions)));
        }

        return isArray;
    }

    public HttpRequest addAuthUnregisteredUser(HttpRequest request) {
        return request.basicAuth(UNREGISTERED_LOGIN_PASS[0], UNREGISTERED_LOGIN_PASS[1]);
    }


    // Tests ---

    @DynamicTest
    DynamicTesting[] dt = new DynamicTesting[]{
        () -> testPostRegister(ADDITIONAL_LOGIN_PASS_1, 200),
        () -> testPostRegister(ADDITIONAL_LOGIN_PASS_1, 400),
        () -> testPostRegister(MAIN_LOGIN_PASS, 200),
        () -> testPostRegister(ADDITIONAL_LOGIN_PASS_2, 200),
        () -> testPostRegister(INCORRECT_LOGIN_CORRECT_PASS_1, 400),
        () -> testPostRegister(INCORRECT_LOGIN_CORRECT_PASS_2, 400),
        () -> testPostRegister(CORRECT_LOGIN_INCORRECT_PASS_1, 400),
        () -> testPostRegister(CORRECT_LOGIN_INCORRECT_PASS_2, 400),

        // Tests with authentication
        () -> testPostRecipe(JSON_RECIPES[0], MAIN_LOGIN_PASS),
        () -> testPostRecipe(JSON_RECIPES[1], MAIN_LOGIN_PASS),

        () -> testGetRecipe(recipeIds.get(0), RECIPES[0], MAIN_LOGIN_PASS),
        () -> testGetRecipe(recipeIds.get(1), RECIPES[1], MAIN_LOGIN_PASS),
        this::reloadServer,
        () -> testGetRecipe(recipeIds.get(0), RECIPES[0], MAIN_LOGIN_PASS),
        () -> testGetRecipe(recipeIds.get(1), RECIPES[1], MAIN_LOGIN_PASS),

        () -> testUpdateRecipe(recipeIds.get(0), JSON_RECIPES[1], MAIN_LOGIN_PASS),
        () -> testGetRecipe(recipeIds.get(0), RECIPES[1], MAIN_LOGIN_PASS),

        () -> testDeleteRecipe(recipeIds.get(0), MAIN_LOGIN_PASS),

        () -> testDeleteRecipeNotFoundStatusCode(recipeIds.get(0), MAIN_LOGIN_PASS),
        () -> testGetRecipeNotFoundStatusCode(recipeIds.get(0), MAIN_LOGIN_PASS),
        () -> testUpdateRecipeNotFoundStatusCode(recipeIds.get(0), JSON_RECIPES[1], MAIN_LOGIN_PASS),


        // Add more recipes before testing if elems sorted correctly by date.
        // Sleep calls added because tests send requests very quickly and this can produce duplicate dates in rare cases.
        // And if a user uses "unstable" sort to sort recipes, tests will be unstable. Thread sleep prevents duplicates.
        () -> {
            sleep(10);
            return testPostRecipe(JSON_RECIPES[2], MAIN_LOGIN_PASS);
        },
        () -> {
            sleep(10);
            return testPostRecipe(JSON_RECIPES[3], MAIN_LOGIN_PASS);
        },
        () -> {
            sleep(10);
            return testPostRecipe(JSON_RECIPES[4], MAIN_LOGIN_PASS);
        },
        () -> {
            sleep(10);
            return testPostRecipe(JSON_RECIPES[5], MAIN_LOGIN_PASS);
        },
        () -> {
            sleep(10);
            return testPostRecipe(JSON_RECIPES[6], MAIN_LOGIN_PASS);
        },
        () -> {
            sleep(10);
            return testPostRecipe(JSON_RECIPES[7], MAIN_LOGIN_PASS);
        },
        () -> {
            sleep(10);
            return testPostRecipe(JSON_RECIPES[8], MAIN_LOGIN_PASS);
        },
        () -> {
            sleep(10);
            return testPostRecipe(JSON_RECIPES[9], MAIN_LOGIN_PASS);
        },
        () -> {
            sleep(10);
            return testPostRecipe(JSON_RECIPES[10], MAIN_LOGIN_PASS);
        },
        () -> {
            sleep(10);
            return testPostRecipe(JSON_RECIPES[11], MAIN_LOGIN_PASS);
        },
        () -> {
            sleep(10);
            return testPostRecipe(JSON_RECIPES[12], MAIN_LOGIN_PASS);
        },
        () -> {
            sleep(10);
            return testPostRecipe(JSON_RECIPES[13], MAIN_LOGIN_PASS);
        },
        () -> {
            sleep(10);
            return testPostRecipe(JSON_RECIPES[14], MAIN_LOGIN_PASS);
        },

        () -> testGetRecipesByCategorySorted(RECIPES_CATEGORY_DESSERT_REVERSED, "dessert", MAIN_LOGIN_PASS),
        () -> testGetRecipesByNameContainsSorted(RECIPES_NAME_CONTAINS_ICE_CREAM_REVERSED, "ice-cream", MAIN_LOGIN_PASS),

        () -> testGetRecipesByCategorySorted(new Recipe[]{}, "should_return_empty_array_1", MAIN_LOGIN_PASS),
        () -> testGetRecipesByNameContainsSorted(new Recipe[]{}, "should_return_empty_array_2", MAIN_LOGIN_PASS),

        () -> testGetRecipesBadRequestStatusCode(0, MAIN_LOGIN_PASS),
        () -> testGetRecipesBadRequestStatusCode(1, MAIN_LOGIN_PASS),
        () -> testGetRecipesBadRequestStatusCode(2, MAIN_LOGIN_PASS),
        () -> testGetRecipesBadRequestStatusCode(5, MAIN_LOGIN_PASS),

        () -> testPostIncorrectRecipeStatusCode(JSON_INCORRECT_RECIPES[0], MAIN_LOGIN_PASS),
        () -> testPostIncorrectRecipeStatusCode(JSON_INCORRECT_RECIPES[1], MAIN_LOGIN_PASS),
        () -> testPostIncorrectRecipeStatusCode(JSON_INCORRECT_RECIPES[2], MAIN_LOGIN_PASS),
        () -> testPostIncorrectRecipeStatusCode(JSON_INCORRECT_RECIPES[3], MAIN_LOGIN_PASS),
        () -> testPostIncorrectRecipeStatusCode(JSON_INCORRECT_RECIPES[4], MAIN_LOGIN_PASS),
        () -> testPostIncorrectRecipeStatusCode(JSON_INCORRECT_RECIPES[5], MAIN_LOGIN_PASS),
        () -> testPostIncorrectRecipeStatusCode(JSON_INCORRECT_RECIPES[6], MAIN_LOGIN_PASS),
        () -> testPostIncorrectRecipeStatusCode(JSON_INCORRECT_RECIPES[7], MAIN_LOGIN_PASS),
        () -> testPostIncorrectRecipeStatusCode(JSON_INCORRECT_RECIPES[8], MAIN_LOGIN_PASS),
        () -> testPostIncorrectRecipeStatusCode(JSON_INCORRECT_RECIPES[9], MAIN_LOGIN_PASS),

        () -> testUpdateIncorrectRecipeStatusCode(recipeIds.get(1), JSON_INCORRECT_RECIPES[0], MAIN_LOGIN_PASS),
        () -> testUpdateIncorrectRecipeStatusCode(recipeIds.get(1), JSON_INCORRECT_RECIPES[1], MAIN_LOGIN_PASS),
        () -> testUpdateIncorrectRecipeStatusCode(recipeIds.get(1), JSON_INCORRECT_RECIPES[2], MAIN_LOGIN_PASS),
        () -> testUpdateIncorrectRecipeStatusCode(recipeIds.get(1), JSON_INCORRECT_RECIPES[3], MAIN_LOGIN_PASS),
        () -> testUpdateIncorrectRecipeStatusCode(recipeIds.get(1), JSON_INCORRECT_RECIPES[4], MAIN_LOGIN_PASS),
        () -> testUpdateIncorrectRecipeStatusCode(recipeIds.get(1), JSON_INCORRECT_RECIPES[5], MAIN_LOGIN_PASS),
        () -> testUpdateIncorrectRecipeStatusCode(recipeIds.get(1), JSON_INCORRECT_RECIPES[6], MAIN_LOGIN_PASS),
        () -> testUpdateIncorrectRecipeStatusCode(recipeIds.get(1), JSON_INCORRECT_RECIPES[7], MAIN_LOGIN_PASS),
        () -> testUpdateIncorrectRecipeStatusCode(recipeIds.get(1), JSON_INCORRECT_RECIPES[8], MAIN_LOGIN_PASS),
        () -> testUpdateIncorrectRecipeStatusCode(recipeIds.get(1), JSON_INCORRECT_RECIPES[9], MAIN_LOGIN_PASS),
        this::reloadServer,

        // Use second registered user to check if endpoints are available.
        () -> testPostRecipe(JSON_RECIPES[0], ADDITIONAL_LOGIN_PASS_1),
        () -> testGetRecipe(recipeIds.get(1), RECIPES[1], ADDITIONAL_LOGIN_PASS_1),
        () -> testGetRecipesByCategorySorted(RECIPES_CATEGORY_DESSERT_REVERSED, "dessert", ADDITIONAL_LOGIN_PASS_1),
        () -> testGetRecipesByNameContainsSorted(RECIPES_NAME_CONTAINS_ICE_CREAM_REVERSED, "ice-cream", ADDITIONAL_LOGIN_PASS_1),
        // Use third registered user to check forbidden status code.
        () -> testDeleteRecipeForbiddenStatusCode(recipeIds.get(1), ADDITIONAL_LOGIN_PASS_2),
        () -> testUpdateRecipeForbiddenStatusCode(recipeIds.get(1), JSON_RECIPES[2], ADDITIONAL_LOGIN_PASS_2),

        // unregistered login and pass
        () -> testPostRecipeUnregisteredUser(JSON_RECIPES[0]),
        () -> testGetRecipeUnregisteredUser(recipeIds.get(0)),
        () -> testUpdateRecipeUnregisteredUser(recipeIds.get(0), JSON_RECIPES[1]),
        () -> testDeleteRecipeUnregisteredUser(recipeIds.get(0)),
        () -> testGetRecipesByCategorySortedUnregisteredUser("dessert"),
        () -> testGetRecipesByNameContainsSortedUnregisteredUser("ice-cream"),

        // without authentication
        () -> testPostRecipeNoAuth(JSON_RECIPES[0]),
        () -> testGetRecipeNoAuth(recipeIds.get(0)),
        () -> testUpdateRecipeNoAuth(recipeIds.get(0), JSON_RECIPES[1]),
        () -> testDeleteRecipeNoAuth(recipeIds.get(0)),
        () -> testGetRecipesByCategorySortedNoAuth("dessert"),
        () -> testGetRecipesByNameContainsSortedNoAuth("ice-cream")
    };

    CheckResult testPostRegister(String[] loginAndPass, int statusCode) {
        final String JSON_LOGIN_PASS = "{\"email\":\"" + loginAndPass[0] + "\",\"password\":\"" + loginAndPass[1] + "\"}";

        HttpResponse response = post(API_REGISTER, JSON_LOGIN_PASS).send();

        throwIfIncorrectStatusCode(response, statusCode);

        return correct();
    }

    // Tests with authentication
    CheckResult testPostRecipe(String jsonRecipe, String[] loginPass) {
        HttpResponse response = post(API_RECIPE_NEW, jsonRecipe).basicAuth(loginPass[0], loginPass[1]).send();

        throwIfIncorrectStatusCode(response, 200);

        expect(response.getContent()).asJson().check(
            isObject()
                .value("id", isInteger(recipeId -> {
                    recipeIds.add(recipeId);
                    return true;
                })));

        return correct();
    }

    CheckResult testGetRecipe(int recipeId, Recipe recipe, String[] loginPass) {
        HttpResponse response = get(API_RECIPE + recipeId).basicAuth(loginPass[0], loginPass[1]).send();

        throwIfIncorrectStatusCode(response, 200);

        expect(response.getContent()).asJson().check(
            isObject()
                .value("name", isString(recipe.name))
                .value("category", isString(recipe.category))
                .value("date", isString(d -> d.length() >= 8))
                .value("description", isString(recipe.description))
                .value("ingredients", isArray(recipe.ingredients))
                .value("directions", isArray(recipe.directions)));

        return correct();
    }

    CheckResult testUpdateRecipe(int recipeId, String jsonRecipe, String[] loginPass) {
        HttpResponse response = put(API_RECIPE + recipeId, jsonRecipe).basicAuth(loginPass[0], loginPass[1]).send();

        throwIfIncorrectStatusCode(response, 204);

        return correct();
    }

    CheckResult testDeleteRecipe(int recipeId, String[] loginPass) {
        HttpResponse response = delete(API_RECIPE + recipeId).basicAuth(loginPass[0], loginPass[1]).send();

        throwIfIncorrectStatusCode(response, 204);

        return correct();
    }

    CheckResult testGetRecipesByCategorySorted(Recipe[] recipes, String paramValue, String[] loginPass) {
        HttpResponse response = get(API_RECIPE_SEARCH).addParam(CATEGORY, paramValue).basicAuth(loginPass[0], loginPass[1]).send();

        throwIfIncorrectStatusCode(response, 200);

        expect(response.getContent()).asJson().check(
            recipesToJsonArrayBuilder(recipes)
        );

        return correct();
    }

    private CheckResult testGetRecipesByNameContainsSorted(Recipe[] recipes, String paramValue, String[] loginPass) {
        HttpResponse response = get(API_RECIPE_SEARCH).basicAuth(loginPass[0], loginPass[1]).addParam(NAME, paramValue).send();

        throwIfIncorrectStatusCode(response, 200);

        expect(response.getContent()).asJson().check(
            recipesToJsonArrayBuilder(recipes)
        );

        return correct();
    }

    // Status codes tests with authentication
    CheckResult testGetRecipeNotFoundStatusCode(int recipeId, String[] loginPass) {
        HttpResponse response = get(API_RECIPE + recipeId).basicAuth(loginPass[0], loginPass[1]).send();

        throwIfIncorrectStatusCode(response, 404);

        return correct();
    }

    CheckResult testUpdateRecipeNotFoundStatusCode(int recipeId, String jsonRecipe, String[] loginPass) {
        HttpResponse response = put(API_RECIPE + recipeId, jsonRecipe).basicAuth(loginPass[0], loginPass[1]).send();

        throwIfIncorrectStatusCode(response, 404);

        return correct();
    }

    CheckResult testDeleteRecipeNotFoundStatusCode(int recipeId, String[] loginPass) {
        HttpResponse response = delete(API_RECIPE + recipeId).basicAuth(loginPass[0], loginPass[1]).send();

        throwIfIncorrectStatusCode(response, 404);

        return correct();
    }

    CheckResult testGetRecipesBadRequestStatusCode(int numberOfQueryParams, String[] loginPass) {
        Map<String, String> params = generateUrlParams(numberOfQueryParams);

        HttpResponse response = get(API_RECIPE_SEARCH).addParams(params).basicAuth(loginPass[0], loginPass[1]).send();

        throwIfIncorrectStatusCode(response, 400);

        return correct();
    }

    CheckResult testPostIncorrectRecipeStatusCode(String incorrectJsonRecipe, String[] loginPass) {
        HttpResponse response = post(API_RECIPE_NEW, incorrectJsonRecipe).basicAuth(loginPass[0], loginPass[1]).send();

        throwIfIncorrectStatusCode(response, 400);

        return correct();
    }

    CheckResult testUpdateIncorrectRecipeStatusCode(int recipeId, String incorrectJsonRecipe, String[] loginPass) {
        HttpResponse response = put(API_RECIPE + recipeId, incorrectJsonRecipe).basicAuth(loginPass[0], loginPass[1]).send();

        throwIfIncorrectStatusCode(response, 400);

        return correct();
    }

    CheckResult testDeleteRecipeForbiddenStatusCode(int recipeId, String[] loginPass) {
        HttpResponse response = delete(API_RECIPE + recipeId).basicAuth(loginPass[0], loginPass[1]).send();

        throwIfIncorrectStatusCode(response, 403);

        return correct();
    }

    CheckResult testUpdateRecipeForbiddenStatusCode(int recipeId, String jsonRecipe, String[] loginPass) {
        HttpResponse response = put(API_RECIPE + recipeId, jsonRecipe).basicAuth(loginPass[0], loginPass[1]).send();

        throwIfIncorrectStatusCode(response, 403);

        return correct();
    }

    // Not registered user tests
    CheckResult testPostRecipeUnregisteredUser(String jsonRecipe) {
        HttpResponse response = addAuthUnregisteredUser(post(API_RECIPE_NEW, jsonRecipe)).send();

        throwIfIncorrectStatusCode(response, 401);

        return correct();
    }

    CheckResult testGetRecipeUnregisteredUser(int recipeId) {
        HttpResponse response = addAuthUnregisteredUser(get(API_RECIPE + recipeId)).send();

        throwIfIncorrectStatusCode(response, 401);

        return correct();
    }

    CheckResult testUpdateRecipeUnregisteredUser(int recipeId, String jsonRecipe) {
        HttpResponse response = addAuthUnregisteredUser(put(API_RECIPE + recipeId, jsonRecipe)).send();

        throwIfIncorrectStatusCode(response, 401);

        return correct();
    }

    CheckResult testDeleteRecipeUnregisteredUser(int recipeId) {
        HttpResponse response = addAuthUnregisteredUser(delete(API_RECIPE + recipeId)).send();

        throwIfIncorrectStatusCode(response, 401);

        return correct();
    }

    CheckResult testGetRecipesByCategorySortedUnregisteredUser(String paramValue) {
        HttpResponse response = addAuthUnregisteredUser(get(API_RECIPE_SEARCH)).addParam(CATEGORY, paramValue).send();

        throwIfIncorrectStatusCode(response, 401);


        return correct();
    }

    private CheckResult testGetRecipesByNameContainsSortedUnregisteredUser(String paramValue) {
        HttpResponse response = addAuthUnregisteredUser(get(API_RECIPE_SEARCH)).addParam(NAME, paramValue).send();

        throwIfIncorrectStatusCode(response, 401);

        return correct();
    }

    // No authentication tests
    CheckResult testPostRecipeNoAuth(String jsonRecipe) {
        HttpResponse response = post(API_RECIPE_NEW, jsonRecipe).send();

        throwIfIncorrectStatusCode(response, 401);

        return correct();
    }

    CheckResult testGetRecipeNoAuth(int recipeId) {
        HttpResponse response = get(API_RECIPE + recipeId).send();

        throwIfIncorrectStatusCode(response, 401);

        return correct();
    }

    CheckResult testUpdateRecipeNoAuth(int recipeId, String jsonRecipe) {
        HttpResponse response = put(API_RECIPE + recipeId, jsonRecipe).send();

        throwIfIncorrectStatusCode(response, 401);

        return correct();
    }

    CheckResult testDeleteRecipeNoAuth(int recipeId) {
        HttpResponse response = delete(API_RECIPE + recipeId).send();

        throwIfIncorrectStatusCode(response, 401);

        return correct();
    }

    CheckResult testGetRecipesByCategorySortedNoAuth(String paramValue) {
        HttpResponse response = get(API_RECIPE_SEARCH).addParam(CATEGORY, paramValue).send();

        throwIfIncorrectStatusCode(response, 401);


        return correct();
    }

    private CheckResult testGetRecipesByNameContainsSortedNoAuth(String paramValue) {
        HttpResponse response = get(API_RECIPE_SEARCH).addParam(NAME, paramValue).send();

        throwIfIncorrectStatusCode(response, 401);

        return correct();
    }
}
