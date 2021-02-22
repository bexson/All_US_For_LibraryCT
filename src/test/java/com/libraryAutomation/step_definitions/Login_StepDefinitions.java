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

    @When("the user login as student")
    public void theUserLoginAsStudent() {
        loginPage.loginAsStudent2();
    }

    @Then("the user landing on the books page")
    public void theUserLandingOnTheBooksPage() {
    }
}
