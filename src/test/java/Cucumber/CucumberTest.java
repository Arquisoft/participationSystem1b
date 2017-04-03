package Cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

public class CucumberTest {

    @BeforeClass
    public static void setUp() {
        CucumberSteps.setUp();
    }

    @AfterClass
    public static void tearDown() {
        CucumberSteps.tearDown();
    }
}