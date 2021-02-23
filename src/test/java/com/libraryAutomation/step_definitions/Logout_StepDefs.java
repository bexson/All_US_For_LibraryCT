package com.libraryAutomation.step_definitions;

import com.libraryAutomation.pages.LandingPage;
import com.libraryAutomation.pages.LoginPage;
import com.libraryAutomation.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class Logout_StepDefs {
    LoginPage loginPage = new LoginPage();
    LandingPage landingPage = new LandingPage();

    @Given("the user navigates to login page")
    public void the_user_navigates_to_login_page() {
    }

    @When("the user enters {string} and {string}")
    public void the_user_enters_and(String email, String password) {
    loginPage.emailField.sendKeys(email);
    loginPage.passwordField.sendKeys(password);
    }
    @When("the user clicks sign in button")
    public void the_user_clicks_sign_in_button() {
    loginPage.loginButton.click();
    }
    @When("The user logs out")
    public void the_user_logs_out() {
        landingPage.testStudentOrLibrarian.click();
        landingPage.logOutButton.click();
    }

    @Then("The user verifies title is {string}")
    public void theUserVerifiesTitleIs(String expectedTitle) {
        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertTrue(expectedTitle.equals(actualTitle));
    }
}
