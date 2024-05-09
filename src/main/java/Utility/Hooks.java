package Utility;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;



public class Hooks {
    private static ExtentReports extent;
    private static ExtentTest test;
   // private final RequestSpecification reqSpecWithApiKey;
    

    @Before(order = 0)
    public static void setUpClass() {
        System.out.println("hooks called");
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Before(order = 1)
    public void setUp(Scenario scenario) {
        this.test = extent.createTest(scenario.getName());
    }

    public static void logRequest(RequestSpecification request) {
        test.info("Request: " + request.log().all().toString());
    }

    public static void logResponse(Response response) {
        test.info("Response: " + response.then().log().all().toString());
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            test.fail("Scenario failed. Check test steps for errors." );
        } else {
            test.pass("Passed");
        }
    }

    @After(order = 0)
    public static void tearDownClass() {
        extent.flush();
    }
}