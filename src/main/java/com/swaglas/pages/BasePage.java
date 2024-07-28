package com.swaglas.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public abstract class BasePage {

    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(id = "react-burger-menu-btn")
    WebElement burgerMenu;

    @FindBy(id = "logout_sidebar_link")
    WebElement logoutLink;

    @FindBy(id = "inventory_sidebar_link")
    WebElement allItemsLink;

    @FindBy(id = "about_sidebar_link")
    WebElement aboutLink;

    @FindBy(id = "reset_sidebar_link")
    WebElement resetAppStateLink;

    @FindBy(className = "shopping_cart_link")
    WebElement cart;

    @FindBy(className = "app_logo")
    WebElement logo;

    public void logout() throws InterruptedException {
        Assert.assertTrue(burgerMenu.isDisplayed(), "Burger menu was not displayed");
        burgerMenu.click();
        //hey, driver! i need you to wait for the visibility of the logoutLink upto 10 seconds
        wait.until(ExpectedConditions.visibilityOf(logoutLink));
        Assert.assertTrue(logoutLink.isDisplayed(), "Logout link was not displayed");
        logoutLink.click();
    }
    
    public void clickOnCart(){
        Assert.assertTrue(cart.isEnabled(), "Cart icon was not enabled to click");
        cart.click();
    }

}
