package restassured.cucumberframework.TestRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.SnippetType;


@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"},
snippets = SnippetType.CAMELCASE,
features = "src/test/resources")

public class RunCucumberTest {


}
