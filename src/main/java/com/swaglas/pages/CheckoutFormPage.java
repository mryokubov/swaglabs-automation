package com.swaglas.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CheckoutFormPage extends BasePage{


    public CheckoutFormPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[text()='Checkout: Your Information']")
    WebElement checkoutFormHeader;

    @FindBy(id = "first-name")
    WebElement firstNameInput;

    @FindBy(id = "last-name")
    WebElement lastNameInput;

    @FindBy(id = "postal-code")
    WebElement zipCodeInput;

    @FindBy(id = "cancel")
    WebElement cancelBtn;

    @FindBy(id = "continue")
    WebElement continueBtn;

    @FindBy(xpath = "//h3[@data-test='error']")
    WebElement formError;

    public void verifyPageTitle(){
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, "Swag Labs");
        Assert.assertTrue(checkoutFormHeader.isDisplayed(), "Checkout Form Header was not displayed");
    }

    public void fillOutCheckoutForm(String firstName, String lastName, String zipCode){
        firstNameInput.clear();
        lastNameInput.clear();
        zipCodeInput.clear();

        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        zipCodeInput.sendKeys(zipCode);
    }

    public void clickContinueBtn(){
        Assert.assertTrue(continueBtn.isEnabled(), "Continue btn was not enabled");
        continueBtn.click();
    }

    public void verifyFormError(){
        Assert.assertTrue(formError.isDisplayed(), "Form Error was not displayed");
    }

}
