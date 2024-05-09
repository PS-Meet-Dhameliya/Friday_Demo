package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/test/resources/features",
        glue={"stepDefinations","Utility"},
        plugin={"pretty",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", 
        "html:target/cucumber-reports"})
public class TestRunner {
}
