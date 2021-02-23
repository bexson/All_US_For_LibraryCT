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
            assert basePage.booksModuleStudent.isDisplayed();
            assert booksModuleText.equalsIgnoreCase(page);
        }

        if (email.contains("librarian")){
            String dashboardText = basePage.dashboardModule.getText();

            assert basePage.dashboardModule.isDisplayed();
            assert dashboardText.equalsIgnoreCase(page);
        }

    }
//-----------------------------------------DOING 2ND SCENARIO-----------------------------------------------

    @When("The user login as a librarian")
        public void the_user_login_as_a_librarian() {
        loginPage.loginAsLibrarian();
    }


    @Then("dashboard should be displayed")
    public void dashboard_should_be_displayed() {
        assert basePage.dashboardModule.isDisplayed();
    }


//-----------------------------------------DOING 3RD SCENARIO-----------------------------------------------
@When("the user login as a student")
public void the_user_login_as_a_student() {
    loginPage.loginAsStudent1();
}

    @Then("books module should be displayed")
    public void books_module_should_be_displayed() {
        assert basePage.booksModuleStudent.isDisplayed();
        Driver.closeDriver();
    }


}
