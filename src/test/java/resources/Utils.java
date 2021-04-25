package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class Utils {
    public static RequestSpecification req;
    public static ResponseSpecification res_spec;

    public RequestSpecification requestSpecification() throws IOException {
        if (req==null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL"))
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();
            return req;
        }
        return req;
    }

    public ResponseSpecification responseSpecification() throws IOException {
        ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder();
        if (res_spec==null) {
            res_spec = responseBuilder.expectStatusCode(200).build();
            return res_spec;
        }
        return res_spec;
    }

    public static String getGlobalValue(String key) throws IOException {
        Properties prop=new Properties();
        FileInputStream fileInputStream=new FileInputStream("src/test/java/resources/global.properties");
        prop.load(fileInputStream);
        return prop.getProperty(key);
    }

    public String getJsonPath(Response response, String key){
        String resp= response.asString();
        JsonPath jsonPath=new JsonPath(resp);
        return jsonPath.get(key).toString().replaceAll("(^\\[|\\]$)", "");
    }
}
