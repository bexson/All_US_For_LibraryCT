package com.libraryAutomation.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //add plugins to get the reports and generate

        features = "@target/rerun.txt",
        glue = "com/libraryAutomation/step_definitions"
)


public class FailedTestRunner {
}
