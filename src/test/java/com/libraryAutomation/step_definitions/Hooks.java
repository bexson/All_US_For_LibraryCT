package com.libraryAutomation.step_definitions;

import com.libraryAutomation.pages.LoginPage;
import com.libraryAutomation.utilities.BrowserUtils;
import com.libraryAutomation.utilities.ConfigurationReader;
import com.libraryAutomation.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {
//----------------------------------------->CATEGORIES DROPDOWN HOOKS<-------------------------------------------------
    String url = ConfigurationReader.getProperty("url");
    @Before(value = "@categories")
    public void logining(){
        Driver.getDriver().get(url);
        LoginPage loginPage = new LoginPage();
        loginPage.loginAsStudent1();
    }

    @After(value = "@categories")
    public void closeDriver(Scenario scenario){

        if (scenario.isFailed()) {
            byte[] screenShot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenShot, "image/png", scenario.getName());
        }


        BrowserUtils.sleep(5);
        Driver.closeDriver();
    }
//----------------------------------------->LOGIN FEATURE HOOKS<-------------------------------------------------

    @Before(value = "@loginFeature")
    public void loginToEnv(){
        Driver.getDriver().get(url);
    }
    @After(value = "@loginFeature")
    public void close(){
        BrowserUtils.sleep(3);
        Driver.closeDriver();
    }
//----------------------------------------->LOGIN FEATURE HOOKS<-------------------------------------------------

}
