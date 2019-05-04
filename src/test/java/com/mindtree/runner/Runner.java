package com.mindtree.runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, dryRun = false, strict = false, features = "src/test/resources/features", glue = "com.mindtree.stepDefinition", plugin = {"pretty", "html:target/cucumber-reports", "json:target/cucumber-reports/cucumber.json"},
tags = {"@TemperatureConverter,@Github"})

public class Runner {

}
