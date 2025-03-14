package cucumber;

import io.cucumber.testng.*;

@CucumberOptions(features = {"src/test/java/cucumber"},glue = {"stepDefs"}
,monochrome = true,plugin = {"pretty","html:target/testResults.html"},dryRun = true)
public class TestNGRunner extends AbstractTestNGCucumberTests {
}
