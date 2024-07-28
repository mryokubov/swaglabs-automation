package com.swaglas.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ProductDetailsPage extends BasePage{

    public ProductDetailsPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//div[contains(@class, 'inventory_details_name')]")
    WebElement productTitle;

    @FindBy(xpath = "//div[contains(@data-test, 'inventory-item-desc')]")
    WebElement productDescription;

    @FindBy(xpath = "//div[contains(@class, 'inventory_details_price')]")
    WebElement productPrice;

    @FindBy(className = "inventory_details_img")
    WebElement productImg;

    @FindBy(id = "add-to-cart")
    WebElement addToCartBtn;

    @FindBy(id = "remove")
    WebElement removeBtn;

    @FindBy(id = "back-to-products")
    WebElement backToProductsLink;

    public void verifyPageTitle(){
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, "Swag Labs", "Products Page titles do not match");
        Assert.assertTrue(backToProductsLink.isDisplayed(), "Back to products link was not displayed");
    }


    public void verifyProductDetails(){
        Assert.assertTrue(productTitle.isDisplayed());
        Assert.assertTrue(productDescription.isDisplayed());
        Assert.assertTrue(productPrice.isDisplayed());
        Assert.assertTrue(productImg.isDisplayed());
    }

    public void addProductToCart(){
        Assert.assertTrue(addToCartBtn.isEnabled(), "Add To Cart Btn was not enabled");
        addToCartBtn.click();
    }

    public void removeProductFromCart(){
        Assert.assertTrue(removeBtn.isEnabled(), "Remove Btn was not enabled");
        removeBtn.click();
    }

    public void goBackToProducts(){
        Assert.assertTrue(backToProductsLink.isDisplayed(), "Back to products link was not displayed");
        backToProductsLink.click();
    }


}
