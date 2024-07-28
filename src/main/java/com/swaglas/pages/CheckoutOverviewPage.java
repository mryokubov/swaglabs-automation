package com.swaglas.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CheckoutOverviewPage extends BasePage{

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[text()='Checkout: Overview']")
    WebElement checkoutOverviewHeader;

    @FindBy(className = "cart_item")
    WebElement cartItem;

    @FindBy(className = "summary_info")
    WebElement summaryInfo;

    @FindBy(id = "finish")
    WebElement finishBtn;

    @FindBy(id = "cancel")
    WebElement cancelBtn;

    public void verifyPageTitle(){
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, "Swag Labs");
        Assert.assertTrue(checkoutOverviewHeader.isDisplayed(), "Checkout Overview Header was not displayed");
    }

    public void verifyCartItems(){
        Assert.assertTrue(cartItem.isDisplayed(), "Cart item was not displayed");
    }

    public void verifySummaryInfo(){
        Assert.assertTrue(summaryInfo.isDisplayed(), "Summary info was not displayed");
    }

    public void clickFinishBtn(){
        Assert.assertTrue(finishBtn.isEnabled(), "Finish btn is not enabled");
        finishBtn.click();
    }

    public void clickCancelBtn(){
        Assert.assertTrue(cancelBtn.isEnabled(), "Cancel btn is not enabled");
        cancelBtn.click();
    }

}
