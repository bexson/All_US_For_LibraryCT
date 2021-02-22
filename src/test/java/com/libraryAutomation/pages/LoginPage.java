package com.libraryAutomation.pages;

import com.libraryAutomation.utilities.ConfigurationReader;
import com.libraryAutomation.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(){
        PageFactory.initElements(Driver.getDriver() , this);
    }

    @FindBy(id = "inputEmail")
    public WebElement emailField;

    @FindBy(id = "inputPassword")
    public WebElement passwordField;

    @FindBy(xpath = "//button[.='Sign in']")
    public WebElement loginButton;


    public void loginAsStudent1(){

        emailField.sendKeys(ConfigurationReader.getProperty("student1"));
        passwordField.sendKeys(ConfigurationReader.getProperty("password1"));
        loginButton.click();

    }

    public void loginAsStudent2(){
        emailField.sendKeys(ConfigurationReader.getProperty("student2"));
        passwordField.sendKeys(ConfigurationReader.getProperty("password2"));
        loginButton.click();
    }

    public void loginAsStudent3(){
        emailField.sendKeys(ConfigurationReader.getProperty("student3"));
        passwordField.sendKeys(ConfigurationReader.getProperty("password3"));
        loginButton.click();
    }

    public void loginAsLibrarian(){
        emailField.sendKeys(ConfigurationReader.getProperty("librarian4"));
        passwordField.sendKeys(ConfigurationReader.getProperty("password4"));
        loginButton.click();
    }

}
