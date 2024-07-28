package com.swaglas.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CartPage extends BasePage{

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[text()='Your Cart']")
    WebElement yourCartHeader;

    @FindBy(className = "cart_item")
    WebElement cartItem;

    @FindBy(id = "continue-shopping")
    WebElement continueShoppingBtn;

    @FindBy(id = "checkout")
    WebElement checkoutBtn;

    public void verifyPageTitle(){
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, "Swag Labs");
        Assert.assertTrue(yourCartHeader.isDisplayed(), "You Cart Header was not displayed");
    }

    public void verifyCartItem(){
        Assert.assertTrue(cartItem.isDisplayed(), "Cart Item was not displayed");
    }

    public void clickCheckoutBtn(){
        Assert.assertTrue(checkoutBtn.isEnabled(), "Checkout Btn was not enabled");
        checkoutBtn.click();
    }



}
