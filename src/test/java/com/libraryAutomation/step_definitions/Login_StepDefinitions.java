package com.libraryAutomation.step_definitions;

import com.libraryAutomation.pages.LoginPage;
import com.libraryAutomation.utilities.ConfigurationReader;
import com.libraryAutomation.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login_StepDefinitions {
    LoginPage loginPage = new LoginPage();
    @Given("the user login")
    public void theUserLogin() {
Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }
    @Given("The user navigates to login page")
    public void the_user_navigates_to_login_page() {

    }

    @When("The user enters {string} and {string}")
    public void the_user_enters_and(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("{string} on {string}")
    public void on(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
