package com.swaglas.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CheckoutCompletePage extends BasePage{

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[text()='Checkout: Complete!']")
    WebElement checkoutCompleteHeader;

    @FindBy(className = "pony_express")
    WebElement orderCompleteImg;

    @FindBy(className = "complete-header")
    WebElement orderCompleteHeader;

    @FindBy(className = "complete-text")
    WebElement orderCompleteText;

    @FindBy(id = "back-to-products")
    WebElement backToProductsBtn;

    public void verifyPageTitle(){
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, "Swag Labs");
        Assert.assertTrue(checkoutCompleteHeader.isDisplayed(), "Checkout Complete Header was not displayed");
    }

    public void verifyOrder(){
        Assert.assertTrue(orderCompleteImg.isDisplayed());
        Assert.assertTrue(orderCompleteHeader.isDisplayed());
        Assert.assertTrue(orderCompleteText.isDisplayed());
    }

    public void clickBackToProducts(){
        Assert.assertTrue(backToProductsBtn.isEnabled(), "Back to Products btn was not enabled");
        backToProductsBtn.click();
    }
}
