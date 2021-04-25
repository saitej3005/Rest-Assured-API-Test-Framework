package stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONObject;
import pojo.JSONUtil;
import pojo.RequestBuilder;
import resources.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class stepDefinitionFile extends Utils{

    RequestSpecification request_Spec;
    ResponseSpecification response_Spec;
    Response response;
    RequestBuilder requestBuilder = new RequestBuilder();
    ResponseBuilder responseBuilder = new ResponseBuilder();
    JSONUtil jsonUtil;
    static String id_Bin;
    static JsonPath json_response;
    static String res_string;

    java.util.logging.Logger logger =  java.util.logging.Logger.getLogger(this.getClass().getName());

    @Given("I add header for API")
    public void addHeaderAPI(DataTable table) throws IOException {
        request_Spec = given().spec(requestSpecification());


        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            request_Spec = request_Spec.header(columns.get("header"),columns.get("value"));
        }
    }

    @Given("I add param for Post request")
    public void addParamPost(DataTable table) throws IOException {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

            Map<String,String> map_Body = new HashMap<String,String>();
        for (Map<String, String> columns : rows) {
            map_Body.put(columns.get("param"),columns.get("value"));
        }
        List<Map<String,String>> jsonArrayPayload = new ArrayList<>();
        jsonArrayPayload.add(map_Body);
        request_Spec = request_Spec.body(jsonArrayPayload);
    }

    @When("I calls {string} API with {string} http request")
    public void callAPI(String apiName,String requestType) {

        if (requestType.equalsIgnoreCase("Post")) {
            response = request_Spec.when().post(requestBuilder.getRoute_Post(apiName));
            res_string = response.asString();
            json_response = new JsonPath(res_string);
            id_Bin = json_response.getString("metadata.id");
        } else if(requestType.equalsIgnoreCase("GET")){
            response = request_Spec.when().get(requestBuilder.getRoute_AllAPI(apiName, id_Bin));
            res_string = response.asString();
            json_response = new JsonPath(res_string);
        } else if(requestType.equalsIgnoreCase("PUT")){
            response = request_Spec.when().put(requestBuilder.getRoute_AllAPI(apiName, id_Bin));
            res_string = response.asString();
            json_response = new JsonPath(res_string);
        } else if(requestType.equalsIgnoreCase("DELETE")){
            response = request_Spec.when().delete(requestBuilder.getRoute_AllAPI(apiName, id_Bin));
            res_string = response.asString();
            json_response = new JsonPath(res_string);
        }
    }

    @Then("the status code is {int}")
    public void theStatusCodeIs(int code) {
        assertEquals(response.getStatusCode(), code);
    }

    @Then("validate the response details")
    public void validateResponseDetails(DataTable table) throws IOException {

        JSONObject jsonObject = new JSONObject(res_string);
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            if(res_string.contains(columns.get("param"))) {
                if ((json_response.get(columns.get("param"))) == null) {
                    assertEquals((jsonUtil.getKey(jsonObject,columns.get("param"))),columns.get("value"));
                } else {
                    json_response.get(columns.get("param")).equals(columns.get("value"));
                }
            } else {
                String key_NotAvailable = columns.get("param");
                logger.info ("The Provided Key " +  "\"" + key_NotAvailable + "\"" + " is not available in the Response");
                assertTrue(res_string.contains(columns.get("param")));
                }
            }
        }
    }

