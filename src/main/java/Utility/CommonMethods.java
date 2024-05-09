package Utility;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.jayway.jsonpath.JsonPath;
import org.skyscreamer.jsonassert.*;
import init.BaseClass;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CommonMethods extends BaseClass {

    // Request specification objects for API calls with and without API key
    private final RequestSpecification reqSpecWithApiKey;
    private final RequestSpecification reqSpecWithoutApiKey;
 
    HashMap<String, String> param = new HashMap<String, String>() {};
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JsonSchemaFactory schemaFactory = JsonSchemaFactory.byDefault();

    public CommonMethods() {
        // Initialize request specification objects using base class methods
        reqSpecWithApiKey = getReqSpecWithApiKey();
        reqSpecWithoutApiKey = getReqSpecWithoutApiKey();
       
    }

    /**
     * Method to make an API call
     *
     * @param method     HTTP method type (GET, POST, PUT, PATCH)
     * @param requestBody   Request body data for POST, PUT, PATCH methods
     * @param endpoint   API endpoint URL
     * @return Response object containing the API response
     */
    public Response callAPI(String method, List<String> requestBody, String endpoint) {
        Response res = null;
        // Set the request specification based on whether API key is required
        RequestSpecification reqSpec = reqSpecWithApiKey;
        System.out.println(endpoint);
        
        if (method.equalsIgnoreCase("GET")) {
            // Send GET request and extract the response
            res = given(reqSpec)
                    .when()
                    .get(endpoint)
                    .then()
                    .extract()
                    .response();
        } else if (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT") || method.equalsIgnoreCase("PATCH")) {
            // Send POST, PUT, or PATCH request with request body data and extract the response
            res = given(reqSpec)
                    .when()
                    .body(requestBody)
                    .request(method, endpoint)
                    .then()
                    .extract()
                    .response();
        }

        return res;
    }
    
    public Response callAPIWithJson(String method, String requestBody, String endpoint) {
        Response res = null;
        // Set the request specification based on whether API key is required
        RequestSpecification reqSpec = reqSpecWithApiKey;
      
        
        if (method.equalsIgnoreCase("GET")) {
            // Send GET request and extract the response
            res = given(reqSpec)
                    .when()
                    .get(endpoint)
                    .then()
                    .extract()
                    .response();
        } else if (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT") || method.equalsIgnoreCase("PATCH")) {
            // Send POST, PUT, or PATCH request with request body data and extract the response
            res = given(reqSpec)
                    .when()
                    .body(requestBody)
                    .request(method, endpoint)
                    .then()
                    .extract()
                    .response();
        }
        return res;
    }
    
    
    
    public Response callAPIWithParam(String method, Map<String, String> testData, String endpoint,String Key,String Value) {
    	
    	 Response res = null;
         // Set the request specification based on whether API key is required
         RequestSpecification reqSpec = reqSpecWithApiKey;
         
        String endpointwithpathparam = endpoint + "?" + Key + "=" + Value;
      
        
      
        if (method.equalsIgnoreCase("GET")) {
            // Send GET request and extract the response
            res = given(reqSpec)
                    .when()
                    .get(endpointwithpathparam)
                    .then()
                    .extract()
                    .response();
        } else if (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT") || method.equalsIgnoreCase("PATCH")) {
            // Send POST, PUT, or PATCH request with request body data and extract the response
            res = given(reqSpec)
                    .when()
                    .body(testData)
                    .request(method, endpoint)
                    .then()
                    .extract()
                    .response();
        }
        return res;
    }
    
    public Response callAPIWithoutKey(String method, Map<String, String> testData, String endpoint) {
        Response res = null;
        // Set the request specification based on whether API key is required
        RequestSpecification reqSpec = reqSpecWithoutApiKey;

        if (method.equalsIgnoreCase("GET")) {
            // Send GET request and extract the response
            res = given(reqSpec)
                    .when()
                    .get(endpoint)
                    .then()
                    .extract()
                    .response();
        } else if (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT") || method.equalsIgnoreCase("PATCH")) {
            // Send POST, PUT, or PATCH request with request body data and extract the response
            res = given(reqSpec)
                    .when()
                    .body(testData)
                    .request(method, endpoint)
                    .then()
                    .extract()
                    .response();
        }

        return res;
    }

    /**
     * Method to verify the JSON schema of the API response
     *
     * @param res            Response object containing the API response
     * @param schemaFileName Name of the JSON schema file to validate against
     * @throws IOException if there is an error reading the schema file
     * @throws JSONException 
     */
    
    
    

    public void verifyJsonSchema(Response res, String schemaFileName) throws IOException, ParseException, JSONException {
        String data = new String(Files.readAllBytes(Paths.get("src/test/java/schemas/" + schemaFileName).toAbsolutePath()));
 
        if(data.startsWith("[")) {
            // Handle array JSON data if needed
        } else {
            UnityJSONParser parser = new UnityJSONParser(data);
            for (String singlePath : parser.getPathList()) {
                Object object = JsonPath.parse(res.getBody()).read(singlePath);   
            }
        }
        
        // Check if response matches the schema
        String failureMessage = "Field is Missing:";
        try {
            JSONAssert.assertEquals(failureMessage, data, res.getBody().asString(), JSONCompareMode.NON_EXTENSIBLE);
        } catch (AssertionError ae) {
            // Re-throw the assertion error so that the test case fails
            throw ae;
        }
    }
   
}
