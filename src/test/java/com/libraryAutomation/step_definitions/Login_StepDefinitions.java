package com.libraryAutomation.step_definitions;

import com.libraryAutomation.pages.BasePage;
import com.libraryAutomation.pages.LandingPage;
import com.libraryAutomation.pages.LoginPage;
import com.libraryAutomation.utilities.BrowserUtils;
import com.libraryAutomation.utilities.ConfigurationReader;
import com.libraryAutomation.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class Login_StepDefinitions {
    LoginPage loginPage = new LoginPage();
    LandingPage landingPage = new LandingPage();
    BasePage basePage = new BasePage();

    @Given("The user navigates to login page")
    public void the_user_navigates_to_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }

    @When("The user enters {string} and {string}")
    public void the_user_enters_and(String email, String password) {
        loginPage.emailField.sendKeys(email);
        loginPage.passwordField.sendKeys(password);
    }

    @And("The user clicks sign in button")
    public void theUserClicksSignInButton() {
        loginPage.loginButton.click();
    }

    @Then("The user see {string} on this {string}")
    public void theUserSeeThis(String page , String email) {
        if (email.contains("student")){
            String booksModuleText = basePage.booksModuleStudent.getText(); //Books
            Assert.assertTrue(basePage.booksModuleStudent.isDisplayed());
            Assert.assertTrue(booksModuleText.equalsIgnoreCase(page));
        }

        if (email.contains("librarian")){
            String dashboardText = basePage.dashboardModule.getText();

            Assert.assertTrue(basePage.dashboardModule.isDisplayed());
            Assert.assertTrue(dashboardText.equalsIgnoreCase(page));
        }

        landingPage.studentButton.click();
        BrowserUtils.sleep(1);
        landingPage.logOutButton.click();
        BrowserUtils.sleep(1);
        if (email.contains("librarian")){
            Driver.closeDriver();
        }
    }
}
