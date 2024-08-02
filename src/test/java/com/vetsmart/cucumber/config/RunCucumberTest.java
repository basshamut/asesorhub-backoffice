package com.vetsmart.cucumber.config;

import io.cucumber.junit.platform.engine.Cucumber;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.test.context.ContextConfiguration;

@Suite
@Cucumber
@SelectClasspathResource("integration/cucumber")
@ContextConfiguration(classes = CucumberSpringConfiguration.class)
public class RunCucumberTest {
}