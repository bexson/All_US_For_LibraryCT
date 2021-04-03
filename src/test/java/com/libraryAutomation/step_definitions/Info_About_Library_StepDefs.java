package com.libraryAutomation.step_definitions;

import com.libraryAutomation.pages.LandingPage;
import com.libraryAutomation.pages.LoginPage;
import com.libraryAutomation.utilities.DB_Utility;
import com.libraryAutomation.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

public class Info_About_Library_StepDefs {

    LoginPage loginPage = new LoginPage();
    LandingPage landingPage = new LandingPage();


//---------------------------------1st Test Case---------------------------------
    @Given("librarian logged in")
    public void librarian_logged_in() {
    loginPage.loginAsLibrarian();
        String librarian16Text = LoginPage.librarian16.getText();
        String expectedText = "Test Librarian 16";
        assertEquals(librarian16Text , expectedText);
    }

    @Then("librarian should see the total number of users")
    public void librarian_should_see_the_total_number_of_users() {
        String expectedUsersCount = landingPage.userCount.getText();
        int expected_users_count_Num = Integer.parseInt(expectedUsersCount);

        DB_Utility.createConnection();
        DB_Utility.runQuery("SELECT count(*) FROM users");

        String userCount_DB = DB_Utility.getFirstRowFirstColumn();
        int userCountDB_num = Integer.parseInt(userCount_DB);
        assertEquals(userCountDB_num , expected_users_count_Num);
        System.out.println(expected_users_count_Num + " and " + userCountDB_num);

        DB_Utility.destroy();
    }
//---------------------------------2nd Test Case---------------------------------
//1.12



}
