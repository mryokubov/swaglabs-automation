package com.swaglabs;

import com.aventstack.extentreports.Status;
import com.swaglas.config.ConfigReader;
import com.swaglas.pages.*;
import org.testng.annotations.Test;

public class SwagLabsPlaceOrder1Tests extends BaseTest{

    @Test
    public void swagLabsOrderTest() throws InterruptedException {

        startTest("Swag Labs Place Order Positive Test");

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        ProductDetailsPage detailsPage = new ProductDetailsPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutFormPage checkoutFormPage = new CheckoutFormPage(driver);
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);
        test.get().log(Status.INFO, "Created page objects");

        loginPage.verifyLoginPageTitle();
        test.get().log(Status.PASS, "Login Page title is verified");

        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
        test.get().log(Status.PASS, "Use Logged in with " + ConfigReader.getProperty("username") + " and " +
                ConfigReader.getProperty("password"));

        productsPage.verifyPageTitle();
        test.get().log(Status.PASS, "Products page title is verified");

        productsPage.filter("lohi");
        test.get().log(Status.INFO, "Filtered the products from low to high");

        productsPage.clickFirstProduct();
        test.get().log(Status.INFO, "Clicked on the first product");

        detailsPage.verifyPageTitle();
        test.get().log(Status.PASS, "Product details page title is verified");

        detailsPage.verifyProductDetails();
        test.get().log(Status.PASS, "Product details are verified");

        detailsPage.addProductToCart();
        test.get().log(Status.INFO, "Added the product to cart");

        detailsPage.goBackToProducts();
        test.get().log(Status.INFO, "Navigated back to products page");

        productsPage.clickOnCart();
        test.get().log(Status.INFO, "Clicked on the shopping cart");

        cartPage.verifyPageTitle();
        test.get().log(Status.PASS, "Cart Page title is verified");

        cartPage.verifyCartItem();
        test.get().log(Status.PASS, "Verified Cart Item(s)");

        cartPage.clickCheckoutBtn();
        test.get().log(Status.INFO, "Clicked on the checkout button");

        checkoutFormPage.verifyPageTitle();
        test.get().log(Status.PASS, "Checkout Form Page title is verified");

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String zipCode = faker.address().zipCode();
        checkoutFormPage.fillOutCheckoutForm(firstName, lastName, zipCode);
        test.get().log(Status.INFO, "Filled out the form with " + firstName + " " + lastName +
                " " + zipCode + " as user details");

        checkoutFormPage.clickContinueBtn();
        test.get().log(Status.INFO, "Clicked on the continue button");

        overviewPage.verifyPageTitle();
        test.get().log(Status.PASS, "Overview Page title is verified");

        overviewPage.verifyCartItems();
        test.get().log(Status.PASS, "Verified cart items");

        overviewPage.verifySummaryInfo();
        test.get().log(Status.PASS, "Verified summary info");

        overviewPage.clickFinishBtn();
        test.get().log(Status.INFO, "Clicked on finish button");

        completePage.verifyPageTitle();
        test.get().log(Status.PASS, "Complete Page title is verified");

        completePage.verifyOrder();
        test.get().log(Status.PASS, "Verified order placed");

        completePage.clickBackToProducts();
        test.get().log(Status.INFO, "Navigated back to products");

        productsPage.logout();
        test.get().log(Status.INFO, "Logged out from the application");

        loginPage.verifyLoginPageTitle();
        test.get().log(Status.PASS, "Login Page title is verified");
    }

    @Test
    public void SwagLabsNegativeOrderTest() throws InterruptedException {
        startTest("Swag Labs Place Order Positive Test");

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        ProductDetailsPage detailsPage = new ProductDetailsPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutFormPage checkoutFormPage = new CheckoutFormPage(driver);
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        test.get().log(Status.INFO, "Created page objects");

        loginPage.verifyLoginPageTitle();
        test.get().log(Status.PASS, "Login Page title is verified");

        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
        test.get().log(Status.PASS, "Use Logged in with " + ConfigReader.getProperty("username") + " and " +
                ConfigReader.getProperty("password"));

        productsPage.verifyPageTitle();
        test.get().log(Status.PASS, "Products page title is verified");

        productsPage.filter("lohi");
        test.get().log(Status.INFO, "Filtered the products from low to high");

        productsPage.clickFirstProduct();
        test.get().log(Status.INFO, "Clicked on the first product");

        detailsPage.verifyPageTitle();
        test.get().log(Status.PASS, "Product details page title is verified");

        detailsPage.verifyProductDetails();
        test.get().log(Status.PASS, "Product details are verified");

        detailsPage.addProductToCart();
        test.get().log(Status.INFO, "Added the product to cart");

        detailsPage.goBackToProducts();
        test.get().log(Status.INFO, "Navigated back to products page");

        productsPage.clickOnCart();
        test.get().log(Status.INFO, "Clicked on the shopping cart");

        cartPage.verifyPageTitle();
        test.get().log(Status.PASS, "Cart Page title is verified");

        cartPage.verifyCartItem();
        test.get().log(Status.PASS, "Verified Cart Item(s)");

        cartPage.clickCheckoutBtn();
        test.get().log(Status.INFO, "Clicked on the checkout button");

        checkoutFormPage.verifyPageTitle();
        test.get().log(Status.PASS, "Checkout Form Page title is verified");

        checkoutFormPage.clickContinueBtn();
        test.get().log(Status.INFO, "Clicked on continue button");

        checkoutFormPage.verifyFormError();
        test.get().log(Status.PASS, "Verified the form errors");

        checkoutFormPage.fillOutCheckoutForm("Kevin", "", "");
        test.get().log(Status.INFO, "Entered username only: Kevin");

        checkoutFormPage.clickContinueBtn();
        test.get().log(Status.INFO, "Clicked on continue button");

        checkoutFormPage.verifyFormError();
        test.get().log(Status.PASS, "Verified the form errors");

        checkoutFormPage.fillOutCheckoutForm("Kevin", "Lee", "");
        test.get().log(Status.INFO, "Entered username and lastname only: Kevin and Lee");

        checkoutFormPage.clickContinueBtn();
        test.get().log(Status.INFO, "Clicked on continue button");

        checkoutFormPage.verifyFormError();
        test.get().log(Status.PASS, "Verified the form errors");

        checkoutFormPage.fillOutCheckoutForm("Kevin", "Lee", "22102");
        test.get().log(Status.INFO, "Entered username, lastname, and zip: Kevin, Lee, 22102");

        checkoutFormPage.clickContinueBtn();
        test.get().log(Status.INFO, "Clicked on continue button");

        overviewPage.verifyPageTitle();
        test.get().log(Status.PASS, "Overview page title is verified");

        overviewPage.clickCancelBtn();
        test.get().log(Status.INFO, "Clicked on cancel button");

        productsPage.logout();
        test.get().log(Status.INFO, "Logged out from the application");

        loginPage.verifyLoginPageTitle();
        test.get().log(Status.PASS, "Login Page title is verified");
    }

}
