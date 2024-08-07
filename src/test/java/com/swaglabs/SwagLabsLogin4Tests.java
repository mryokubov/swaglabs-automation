package com.swaglabs;

import com.aventstack.extentreports.Status;
import com.swaglas.config.ConfigReader;
import com.swaglas.pages.LoginPage;
import com.swaglas.pages.ProductsPage;
import org.testng.annotations.Test;

public class SwagLabsLogin4Tests extends BaseTest {

   @Test(priority = 1)
   public void swagLabsLoginPositiveTest() {
      startTest("SwagLabs Login Positive Test");

      LoginPage loginPage = new LoginPage(driver);
      test.get().log(Status.INFO, "Navigating to Login Page");

      loginPage.verifyLoginPageTitle();
      test.get().log(Status.PASS, "Login Page Title Verified");

      loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
      test.get().log(Status.INFO, "Entered Username and Password");

      ProductsPage productsPage = new ProductsPage(driver);
      productsPage.verifyPageTitle();
      test.get().log(Status.PASS, "Products Page Title Verified");
   }

   @Test(priority = 2)
   public void swagLabsLoginIncorrectCredentialsTest() {
      startTest("SwagLabs Login Incorrect Credentials Test");

      LoginPage loginPage = new LoginPage(driver);
      test.get().log(Status.INFO, "Navigating to Login Page");

      loginPage.verifyLoginPageTitle();
      test.get().log(Status.PASS, "Login Page Title Verified");

      loginPage.login("wrong_username", "wrong_password");
      test.get().log(Status.INFO, "Entered Incorrect Username and Password");

      loginPage.verifyLoginError();
      test.get().log(Status.PASS, "Login Error Verified");
   }

   @Test(priority = 3)
   public void swagLabsEmptyCredentialsTest() {
      startTest("SwagLabs Empty Credentials Test");

      LoginPage loginPage = new LoginPage(driver);
      test.get().log(Status.INFO, "Navigating to Login Page");

      loginPage.verifyLoginPageTitle();
      test.get().log(Status.PASS, "Login Page Title Verified");

      loginPage.login("", "");
      test.get().log(Status.INFO, "Left Username and Password Blank");

      loginPage.verifyLoginError();
      test.get().log(Status.PASS, "Login Error Verified");
   }

}
