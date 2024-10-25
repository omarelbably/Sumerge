package org.example;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ThirdTask {
    final static WebDriver itsDriver = new ChromeDriver();
    static String username = "standard_user";
    static String password = "secret_sauce";
    static String invalidPassword = "secret";
    static String exErrorMessage = "Epic sadface: Username and password do not match any user in this service";
    static String homeURL = "https://www.saucedemo.com/";
    static String cartExpectedNumberisOne = "1";
    static String cartExpectedNumberisTwo = "2";

    public static void thirdTestCase() throws InterruptedException {
        loginThenUseCard();
        logout();
    }
    public static void loginThenUseCard() throws InterruptedException {
        itsDriver.navigate().to("https://www.saucedemo.com/");
        itsDriver.manage().window().maximize();
        Thread.sleep(3000);
        WebElement loginUserName =itsDriver.findElement(By.xpath("//*[@id=\"user-name\"]"));
        loginUserName.sendKeys(username);
        Thread.sleep(1000);
        WebElement loginPassword =itsDriver.findElement(By.xpath("//*[@id=\"password\"]"));
        loginPassword.sendKeys(password);
        Thread.sleep(1000);
        WebElement loginButton = itsDriver.findElement(By.xpath("//*[@id=\"login-button\"]"));
        loginButton.click();
        Thread.sleep(1000);
        WebElement addToCart = itsDriver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]"));
        addToCart.click();
        Thread.sleep(1000);
        WebElement cartBadge = itsDriver.findElement(By.xpath("//*[@class=\"shopping_cart_badge\"]"));
        String cartNumber = cartBadge.getText();
        System.out.println(cartNumber);
        Assert.isTrue(cartNumber.equals(cartExpectedNumberisOne), "cart number is false");
        Thread.sleep(1000);
        WebElement addToCart2 = itsDriver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-bike-light\"]"));
        addToCart2.click();
        Thread.sleep(1000);
        String cartNumberSecondTrial = cartBadge.getText();
        System.out.println(cartNumberSecondTrial);
        Assert.isTrue(cartNumberSecondTrial.equals(cartExpectedNumberisTwo), "cart number is false");
        WebElement removeBikeFromCart = itsDriver.findElement(By.xpath("//*[@id=\"remove-sauce-labs-bike-light\"]"));
        removeBikeFromCart.click();
        Thread.sleep(1000);
        String cartNumberThirdTrial = cartBadge.getText();
        System.out.println(cartNumberThirdTrial);
        Thread.sleep(1000);
        Assert.isTrue(cartNumberThirdTrial.equals(cartExpectedNumberisOne), "cart number is false");
        WebElement cart = itsDriver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a"));
        cart.click();
        Thread.sleep(1000);
        WebElement cartItems = itsDriver.findElement(By.xpath("//*[@class=\"cart_item\"][1]"));
        Assert.isTrue(cartItems.isDisplayed(), "Cart item is not displayed");
        Thread.sleep(1000);
        WebElement removeButton = itsDriver.findElement(By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]"));
        removeButton.click();
        Thread.sleep(1000);
        Assert.isTrue(!cartItems.isDisplayed(), "Cart item is still displayed");
        WebElement continueShoppingBtn = itsDriver.findElement(By.xpath("//*[@id=\"continue-shopping\"]"));
        continueShoppingBtn.click();
    }
    public static void loginWithValidData() throws InterruptedException {
        itsDriver.navigate().to("https://www.saucedemo.com/");
        itsDriver.manage().window().maximize();
        Thread.sleep(3000);
        WebElement loginUserName =itsDriver.findElement(By.xpath("//*[@id=\"user-name\"]"));
        loginUserName.sendKeys(username);
        Thread.sleep(1000);
        WebElement loginPassword =itsDriver.findElement(By.xpath("//*[@id=\"password\"]"));
        loginPassword.sendKeys(password);
        Thread.sleep(1000);
        WebElement loginButton = itsDriver.findElement(By.xpath("//*[@id=\"login-button\"]"));
        loginButton.click();
    }
    public static void loginWithInvalidData() throws InterruptedException{
        WebDriver itsDriver = new ChromeDriver();
        itsDriver.navigate().to("https://www.saucedemo.com/");
        itsDriver.manage().window().maximize();
        Thread.sleep(3000);
        WebElement loginUserName =itsDriver.findElement(By.xpath("//*[@id=\"user-name\"]"));
        loginUserName.sendKeys(username);
        Thread.sleep(1000);
        WebElement loginPassword =itsDriver.findElement(By.xpath("//*[@id=\"password\"]"));
        loginPassword.sendKeys(invalidPassword);
        Thread.sleep(1000);
        WebElement loginButton = itsDriver.findElement(By.xpath("//*[@id=\"login-button\"]"));
        loginButton.click();
        WebElement loginErrorMessage = itsDriver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div/div/form/div[3]/h3"));
        String errorMessage = loginErrorMessage.getText();
        Thread.sleep(1000);
        Assert.isTrue(errorMessage.equals(exErrorMessage), "Incorrect username or PW");
        System.out.println(errorMessage);
        Thread.sleep(3000);
        itsDriver.quit();
    }
    public static void logout() throws InterruptedException{
        WebElement threeDotsMenu = itsDriver.findElement(By.xpath("//*[@id=\"react-burger-menu-btn\"]"));
        threeDotsMenu.isDisplayed();
        threeDotsMenu.click();
        Thread.sleep(1000);
        WebElement logoutButton = itsDriver.findElement(By.xpath("//*[@id=\"logout_sidebar_link\"]"));
        logoutButton.isDisplayed();
        logoutButton.click();
        Thread.sleep(1000);
        String currentUrl = itsDriver.getCurrentUrl();
        Assert.isTrue(currentUrl.equals(homeURL), "URL is not correct");
        Thread.sleep(1000);
        System.out.println(currentUrl);
        Thread.sleep(3000);
        itsDriver.quit();
    }
}
