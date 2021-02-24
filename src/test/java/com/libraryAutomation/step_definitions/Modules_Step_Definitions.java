package com.libraryAutomation.step_definitions;

import com.libraryAutomation.pages.BasePage;
import com.libraryAutomation.pages.LandingPage;
import com.libraryAutomation.pages.LoginPage;
import com.libraryAutomation.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;

public class Modules_Step_Definitions {
    LoginPage loginPage = new LoginPage();
    BasePage basePage = new BasePage();


    @Given("the student on the home page")
    public void the_student_on_the_home_page() {
        loginPage.loginAsStudent1();
    }


    @Then("the user should see following modules")
    public void the_user_should_see_following_modules(List<String> modules) {
        String actualBooksModule = basePage.booksModuleStudent.getText();
        String actualBorrowBooksM = basePage.borrowingBooksModule.getText();

        String expectedBooksModule = modules.get(0);
        String expectedBorrowingBooksM = modules.get(1);

        Assert.assertTrue(actualBooksModule.equals(expectedBooksModule));
        Assert.assertTrue(actualBorrowBooksM.equals(expectedBorrowingBooksM));


    }


    @Given("the librarian on the home page")
    public void the_librarian_on_the_home_page() {
        loginPage.loginAsLibrarian();
    }

    @Then("the librarian should see following modules")
    public void the_librarian_should_see_following_modules(List<String> modules) {
        String actualDasboard = basePage.dashboardModule.getText();

        String actualUsers = basePage.usersLink.getText();
        String actualBooksModule = basePage.booksModuleLibrarian.getText();

        String expectedDashboard = modules.get(0);
        String expectedUsers = modules.get(1);
        String expectedBooks = modules.get(2);

        assert actualDasboard.equals(expectedDashboard);
        assert expectedUsers.equals(actualUsers);
        assert actualBooksModule.equals(expectedBooks);
    }


}
