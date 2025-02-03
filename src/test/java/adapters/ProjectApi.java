package adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CreateProjectRq;
import dto.CreateProjectRs;
import dto.ProjectRs;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;
import utils.PropertyReader;

import static io.restassured.RestAssured.given;

@Log4j2
public class ProjectApi {

    protected static String token = System.getProperty("token", PropertyReader.getProperty("token"));
    private static String URL = "https://api.qase.io/v1/project";

    public static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public static RequestSpecification spec =
            given().
                    log().all().
                    contentType(ContentType.JSON).
                    header("Token", token);


    @Step("Create project with API {projectRq}")
    public static CreateProjectRs createProjectWithApi(CreateProjectRq projectRq) {
        log.info("Method: createProjectWithApi ,{},{}",projectRq.getTitle(),projectRq.getCode());
        return given().
                spec(spec).
                body(gson.toJson(projectRq)).
                when().
                post(URL).
                then().
                statusCode(200).
                log().
                body().
                log().
                ifError().
                extract().as(CreateProjectRs.class);
    }

    @Step("Get project with API {code}")
    public static ProjectRs getProjectWithApi(String code) {
        log.info("Method: getProjectWithApi ,{}",code);
        return given().
                spec(spec).
                when().
                get(URL + "/" + code).
                then().
                log().
                body().
                log().
                ifError().
                extract().as(ProjectRs.class);
    }

    @Step("Delete project with API {code}")
    public static CreateProjectRs deleteProjectWithApi(String code) {
        log.info("Method: deleteProjectWithApi , {}",code);
        return given().
                spec(spec).
                when().
                delete(URL + "/" + code).
                then().
                log().
                body().
                log().
                ifError().
                statusCode(200).
                extract().as(CreateProjectRs.class);
    }
}
