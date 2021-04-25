package pojo;
import io.restassured.specification.RequestSpecification;

public class RequestBuilder {

    RequestSpecification res;


    public RequestSpecification addBodyParameter(RequestSpecification request_Spec,String param, String value)
    {
        request_Spec.param(param,value);
        return  request_Spec;
    }

    public RequestSpecification addHeader(RequestSpecification request_Spec,String  header, String value)
    {
        request_Spec.headers(header,value);
        return request_Spec;
    }

    private String route;

    public String getRoute_Post(String resource){
        route =  "/b/";
        return route;
    }
    public String getRoute_AllAPI(String apiName, String id){
        switch (apiName){
            case "readBin":
                route =  "/b/"+ id;
                break;
            case "updateBin":
                route = "/b/"+ id;
                break;
            case "deleteBin":
                route =  "/b/"+ id;
                break;
            case "countBinVersions":
                route =  "/b/"+ id+"/versions/count/";
                break;
            case "deleteBinVersions":
                route =  "/b/"+ id+"/versions";
                break;
        }
        return route;
    }
}
