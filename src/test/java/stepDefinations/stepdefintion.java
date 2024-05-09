package stepDefinations;

import Utility.CommonMethods;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

public class stepdefintion{

    CommonMethods cm = new CommonMethods();
    public Response res;

    @Given("setup legislation api {string} with form parama with {string} and {string}")
    public Response set_up_legislation_api(String endpoint,String Key,String Value){
       // res = cm.hitapiforget(endpoint);
        res = cm.callAPIWithParam("get", null, endpoint,Key,Value);
        return res;
    }
    
    @Given("setup legislation api {string}")
    public Response set_up_legislation_api(String endpoint){
       // res = cm.hitapiforget(endpoint);
        res = cm.callAPI("get", null, endpoint);
        return res;
    }
    
    
    @Then("verify the response code is (\\d+)$")
    public void verify_response_code(int response_code){	
       cm.verifyResponseCode(response_code,res);
   	  

    }

    @And("^verify the (.+) json schema$")
    public void verify_json_schema(String schema_file_name) throws IOException, ParseException, JSONException {
    	
    	cm.verifyJsonSchema(res,schema_file_name);
    }

    @And("^verify response time is within configured value$")
    public void verify_response_time() {
        cm.verifyResponseTime(res, 4000);
    }
    
    
    @Given("^setup post legislation api ([^,]+),([^,]+),([^,]+)$")
    public Response setupPostLegislationApi(String endpoint, String key1, String key2){
       // res = cm.hitapiforget(endpoint);
    	List<String> requestBody = Arrays.asList(key1, key2);
   
        res = cm.callAPI("post", requestBody, endpoint);
       
        return res;
    }
    
    @Given("^setup post legislation api for ref ([^,]+)$")
    public Response setupPostRefrenceApi(String endpoint) throws JSONException{
    	
    	JSONObject refBody = new JSONObject();
    	refBody.put("businessKey","VSK-00000000-0000-0000-0000-000000088125");
    	refBody.put("resourceType","SUBSTANCE");
    	
    	JSONObject refBody1 = new JSONObject();
    	refBody1.put("businessKey","EU-CHEM_AGENTS");
    	refBody1.put("resourceType","LEGISLATION");
    	
    	JSONObject refBody2 = new JSONObject();
    	refBody2.put("businessKey","EU-CHEM_AGENTS-ART_2_b_i_HZD_AGNT");
    	refBody2.put("resourceType","LEGISLATION_LIST");
    	
    	JSONObject refBody3 = new JSONObject();
    	refBody3.put("businessKey","VSK-001A62");
    	refBody3.put("resourceType","LEGISLATION_LIST_ENTRY");
    	
    	JSONArray ar = new JSONArray();
    	ar.put(refBody);
    	ar.put(refBody1);
    	ar.put(refBody2);
    	ar.put(refBody3);
    
        res = cm.callAPIWithJson("post", ar.toString() , endpoint);
       
        return res;
    }
    
    

}
