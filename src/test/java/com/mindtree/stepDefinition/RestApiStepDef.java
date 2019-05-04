package com.mindtree.stepDefinition;

import java.io.IOException;

import com.mindtree.pages.RestApiPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RestApiStepDef {
	RestApiPage objRestApiPages = new RestApiPage();

	@Given("I search repository by {string}")
	public void i_search_repository_by(String keyword) {
		objRestApiPages.launchGithub(keyword);
	}

	@When("I make the rest call")
	public void i_make_the_rest_call() throws IOException {
		objRestApiPages.searchTheRepository();
	}

	@Then("response should contain: {int}")
	public void response_should_contain(Integer code) {
		objRestApiPages.checkResponseCodeInOutput(code);
	}

}
