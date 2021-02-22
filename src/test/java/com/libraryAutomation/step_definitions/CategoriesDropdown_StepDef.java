package com.libraryAutomation.step_definitions;

import com.libraryAutomation.pages.LandingPage;
import com.libraryAutomation.pages.LoginPage;
import com.libraryAutomation.utilities.BrowserUtils;
import com.libraryAutomation.utilities.ConfigurationReader;
import com.libraryAutomation.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CategoriesDropdown_StepDef {

    @Given("Student is on the landing page")
    public void student_is_on_the_landing_page() {
    }

    @When("Student clicks categories dropdown")
    public void student_clicks_categories_dropdown() {
    }

    @Then("Student should see all the options below")
    public void student_should_see_all_the_options_below(List<String> expectedOptions) {
        LandingPage landingPage = new LandingPage();
        Select categoriesDD = new Select(landingPage.categoriesDD);
        List<String> actualOptions = BrowserUtils.getElementText(categoriesDD.getOptions());

        Assert.assertEquals(actualOptions, expectedOptions );

    }

    @When("Student selects all the options like below:")
    public void student_selects_all_the_options_like_below(List<String> options) {
        LandingPage landingPage = new LandingPage();
        Select categoriesDD = new Select(landingPage.categoriesDD);
        int count =  0;
        for (String each : options) {
            categoriesDD.selectByVisibleText(each);
            Assert.assertTrue(landingPage.searchBox.isDisplayed());
            System.out.println("DOING ASSERTION");
            count++;
            BrowserUtils.sleep(1);
        }
        System.out.println(count);

    }

    @Then("Student verifies searchBox is displayed")
    public void student_verifies_search_box_is_displayed() {
    }

}
