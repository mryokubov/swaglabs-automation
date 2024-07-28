package com.swaglas.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class ProductsPage extends BasePage {

    public ProductsPage(WebDriver driver) {
       super(driver);
    }

    @FindBy(xpath = "//span[text()='Products']")
    WebElement productsHeader;

    @FindBy(className = "product_sort_container")
    WebElement filter;

    public void verifyPageTitle(){
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, "Swag Labs", "Products Page titles do not match");
        Assert.assertTrue(productsHeader.isDisplayed(), "Products Header was not displayed");
    }

    public void filter(String option){
        Select select = new Select(filter);
        select.selectByValue(option);
    }

    public void clickFirstProduct(){
        WebElement firstProduct = driver.findElement(By.xpath("(//div[@class='inventory_item_name '])[1]"));
        firstProduct.click();
    }


}
