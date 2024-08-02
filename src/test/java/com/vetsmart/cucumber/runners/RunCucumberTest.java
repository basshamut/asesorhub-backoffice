package com.vetsmart.cucumber.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/vetsmart/cucumber/features/test.feature",
        glue = {
                "com.vetsmart.cucumber.steps",
                "com.vetsmart.cucumber.config"
        })
public class RunCucumberTest {
}