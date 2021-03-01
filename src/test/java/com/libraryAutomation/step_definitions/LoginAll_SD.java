package com.libraryAutomation.step_definitions;

import com.libraryAutomation.pages.LandingPage;
import com.libraryAutomation.pages.LoginPage;
import com.libraryAutomation.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class LoginAll_SD {
//        workbook > sheet > row > cell
   public XSSFWorkbook workbook;
   public XSSFSheet sheet;
   public FileInputStream fileInputStream;
   public FileOutputStream fileOutputStream;
   LoginPage loginPage = new LoginPage();
   private final String path = "LibraryCT.xlsx";
   LandingPage landingPage = new LandingPage();

    @Given("The user goes to login page")
    public void the_user_goes_to_login_page() {
    }


    @When("The user enters email and password from excel")
    public void the_user_enters_email_and_password_from_excel() throws IOException {

        fileInputStream = new FileInputStream(path);

        workbook = new XSSFWorkbook(fileInputStream);

        sheet = workbook.getSheet("Sheet1");

        for (int rowNum = 1; rowNum <= sheet.getLastRowNum() ; rowNum++) {

        XSSFRow currentRow = sheet.getRow(rowNum);

        String email = currentRow.getCell(1).toString(); //email

        String password = currentRow.getCell(2).toString(); // password

        loginPage.emailField.sendKeys(email);

        loginPage.passwordField.sendKeys(password);

        loginPage.loginButton.click();

        fileOutputStream = new FileOutputStream(path);

        if (currentRow.getCell(4)==null){
            currentRow.createCell(4); // if I don't create the cell I will get NullPointerException
        }

        String actualUrl = Driver.getDriver().getCurrentUrl();
        //Passing the 'actualUrl' into Excel sheet 'ActualUrl' cell
        currentRow.getCell(4).setCellValue(actualUrl);
        String expectedUrl = currentRow.getCell(3).toString();
        System.out.println("expectedUrl = " + expectedUrl);
        System.out.println("actualUrl = " + actualUrl);

        if(currentRow.getCell(5)==null){
            currentRow.createCell(5);
        }

        if (actualUrl.equals(expectedUrl)) {
            assert actualUrl.equals(expectedUrl);
            currentRow.getCell(5).setCellValue("PASS!!");
        }else {
            currentRow.getCell(5).setCellValue("FAIL!!");
        }

        landingPage.testStudentOrLibrarian.click();
        landingPage.logOutButton.click();

        }

        workbook.write(fileOutputStream);

        workbook.close();
        fileInputStream.close();
        fileOutputStream.close();
    }

    @Then("The user should see expected URL from excel")
    public void the_user_should_see_expected_url_from_excel() throws FileNotFoundException {


    }

}
