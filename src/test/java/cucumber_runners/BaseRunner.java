package cucumber_runners;

import drivers.DriverFactory;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "test_models",
        plugin = {
                "pretty"
        }
)
public class BaseRunner extends AbstractTestNGCucumberTests {

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        DriverFactory.closeDriver();
    }

}
