package com.asesorhub.cucumber.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/asesorhub/cucumber/features/test.feature",
        glue = {
                "com.asesorhub.cucumber.steps",
                "com.asesorhub.cucumber.config"
        })
public class RunCucumberTest {
}