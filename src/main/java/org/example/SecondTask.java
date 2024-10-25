package org.example;
import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SecondTask {
    final static WebDriver itsDriver = new ChromeDriver();
    static String username = "standard_user";
    static String password = "secret_sauce";
    static String homeURL = "https://www.saucedemo.com/";
    static String exBackPackLabel = "Sauce Labs Backpack";
    static String exBackPackDesc = "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.";
    static String exBackPackPrice = "$29.99";
    static String exBikeLightLabel = "Sauce Labs Bike Light";
    static String exBikeLightDesc = "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.";
    static String exBikeLightPrice = "$9.99";
    static String exboltShirtLabel = "Sauce Labs Bolt T-Shirt";
    static String exboltShirtDesc = "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.";
    static String exboltShirtPrice = "$15.99";
    static String exfleeceJacketLabel = "Sauce Labs Fleece Jacket";
    static String exfleeceJacketDesc = "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.";
    static String exfleeceJacketPrice = "$49.99";
    static String exonesieLabel = "Sauce Labs Onesie";
    static String exonesieDesc = "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.";
    static String exonesiePrice = "$7.99";
    static String exredShirtLabel = "Test.allTheThings() T-Shirt (Red)";
    static String exredShirtDesc = "This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton.";
    static String exredShirtPrice = "$15.99";
    static String[] pricesLowerToHigher = {"$7.99", "$9.99", "$15.99", "$15.99", "$29.99","$49.99"};
    static String[] pricesHigherToLower = {"$49.99","$29.99","$15.99","$15.99","$9.99","$7.99"};
    public static void secondTestCase() throws InterruptedException {
        loginWithValidData();
        checkAllProductsAreVisible();
        sortBylowerPrice();
        verifySortingIsWorking();
        sortByHigherPrice();
        verifySortingIsWorking();
        Thread.sleep(5000);
        logout();
    }
    public static void sortBylowerPrice() {
        WebElement sortingMenu = itsDriver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select"));
        Select dropdown = new Select(sortingMenu);
        dropdown.selectByVisibleText("Price (low to high)");
    }
    public static void sortByHigherPrice() {
        WebElement sortingMenu = itsDriver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select"));
        Select dropdown = new Select(sortingMenu);
        dropdown.selectByVisibleText("Price (high to low)");
    }
    public static void verifySortingIsWorking() throws InterruptedException {
        for (int i = 1; i < 7; i++) {
            WebElement listedProducts = itsDriver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div["+i+"]/div[2]/div[2]/div"));
            System.out.println(listedProducts.getText());
//            System.out.println(pricesLowerToHigher[i-1]);
//            Assert.isTrue(listedProducts.equals(pricesLowerToHigher[i-1]), "incorrect prices");
            Thread.sleep(1000);
        }

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
    public static void checkAllProductsAreVisible() throws InterruptedException {
        backPackDetails();
        bikeLight();
        boltShirt();
        fleeceJacket();
        onesie();
        redShirt();
    }
    public static void backPackDetails() throws InterruptedException {
        WebElement backPackLabel = itsDriver.findElement(By.xpath("//*[text()=\"Sauce Labs Backpack\"]"));
        String acbackPackLabel = backPackLabel.getText();
        System.out.println(acbackPackLabel);
        Thread.sleep(1000);
        Assert.isTrue(acbackPackLabel.equals(exBackPackLabel), "backpack label is incorrect");
        WebElement backPackDesc = itsDriver.findElement(By.xpath("//*[text()=\"carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.\"]"));
        String acBackPackDesc = backPackDesc.getText();
        System.out.println(acBackPackDesc);
        Thread.sleep(1000);
        Assert.isTrue(acBackPackDesc.equals(exBackPackDesc), "backpack Desc is different");
        WebElement backPackPrice = itsDriver.findElement(By.xpath("//*[text()='29.99']"));
        String acBackPackPrice = backPackPrice.getText();
        System.out.println(acBackPackPrice);
        Thread.sleep(1000);
        Assert.isTrue(acBackPackPrice.equals(exBackPackPrice), "incorrect backpack price");
    }
    public static void bikeLight() throws InterruptedException {
        WebElement BikeLightLabel = itsDriver.findElement(By.xpath("//*[text()=\"Sauce Labs Bike Light\"]"));
        String acBikeLightLabel = BikeLightLabel.getText();
        System.out.println(acBikeLightLabel);
        Thread.sleep(1000);
        Assert.isTrue(acBikeLightLabel.equals(exBikeLightLabel), "backpack label is incorrect");
        WebElement BikeLightDesc = itsDriver.findElement(By.xpath("//*[text()=\"A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.\"]"));
        String acBikeLightDesc = BikeLightDesc.getText();
        System.out.println(acBikeLightDesc);
        Thread.sleep(1000);
        Assert.isTrue(acBikeLightDesc.equals(exBikeLightDesc), "backpack Desc is different");
        WebElement BikeLightPrice = itsDriver.findElement(By.xpath("//*[text()='9.99']"));
        String acBikeLightPrice = BikeLightPrice.getText();
        System.out.println(acBikeLightPrice);
        Thread.sleep(1000);
        Assert.isTrue(acBikeLightPrice.equals(exBikeLightPrice), "incorrect backpack price");
    }
    public static void boltShirt() throws InterruptedException {
        WebElement boltShirtLabel = itsDriver.findElement(By.xpath("//*[text()=\"Sauce Labs Bolt T-Shirt\"]"));
        String acboltShirtLabel = boltShirtLabel.getText();
        System.out.println(acboltShirtLabel);
        Thread.sleep(1000);
        Assert.isTrue(acboltShirtLabel.equals(exboltShirtLabel), "backpack label is incorrect");
        WebElement boltShirtDesc = itsDriver.findElement(By.xpath("//*[text()=\"Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.\"]"));
        String acboltShirtDesc = boltShirtDesc.getText();
        System.out.println(acboltShirtDesc);
        Thread.sleep(1000);
        Assert.isTrue(acboltShirtDesc.equals(exboltShirtDesc), "backpack Desc is different");
        WebElement boltShirtPrice = itsDriver.findElement(By.xpath("//*[text()='15.99']"));
        String acboltShirtPrice = boltShirtPrice.getText();
        System.out.println(acboltShirtPrice);
        Thread.sleep(1000);
        Assert.isTrue(acboltShirtPrice.equals(exboltShirtPrice), "incorrect backpack price");
    }
    public static void fleeceJacket() throws InterruptedException {
        WebElement fleeceJacketLabel = itsDriver.findElement(By.xpath("//*[text()=\"Sauce Labs Fleece Jacket\"]"));
        String acfleeceJacketLabel = fleeceJacketLabel.getText();
        System.out.println(acfleeceJacketLabel);
        Thread.sleep(1000);
        Assert.isTrue(acfleeceJacketLabel.equals(exfleeceJacketLabel), "backpack label is incorrect");
        WebElement fleeceJacketkDesc = itsDriver.findElement(By.xpath("//*[text()=\"It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.\"]"));
        String acfleeceJacketDesc = fleeceJacketkDesc.getText();
        System.out.println(acfleeceJacketDesc);
        Thread.sleep(1000);
        Assert.isTrue(acfleeceJacketDesc.equals(exfleeceJacketDesc), "backpack Desc is different");
        WebElement fleeceJacketPrice = itsDriver.findElement(By.xpath("//*[text()='49.99']"));
        String acfleeceJacketPrice = fleeceJacketPrice.getText();
        System.out.println(acfleeceJacketPrice);
        Thread.sleep(1000);
        Assert.isTrue(acfleeceJacketPrice.equals(exfleeceJacketPrice), "incorrect backpack price");
    }
    public static void onesie() throws InterruptedException {
        WebElement onesieLabel = itsDriver.findElement(By.xpath("//*[text()=\"Sauce Labs Onesie\"]"));
        String aconesieLabel = onesieLabel.getText();
        System.out.println(aconesieLabel);
        Thread.sleep(1000);
        Assert.isTrue(aconesieLabel.equals(exonesieLabel), "backpack label is incorrect");
        WebElement onesieDesc = itsDriver.findElement(By.xpath("//*[text()=\"Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.\"]"));
        String aconesieDesc = onesieDesc.getText();
        System.out.println(aconesieDesc);
        Thread.sleep(1000);
        Assert.isTrue(aconesieDesc.equals(exonesieDesc), "backpack Desc is different");
        WebElement onesiePrice = itsDriver.findElement(By.xpath("//*[text()='7.99']"));
        String aconesiePrice = onesiePrice.getText();
        System.out.println(aconesiePrice);
        Thread.sleep(1000);
        Assert.isTrue(aconesiePrice.equals(exonesiePrice), "incorrect backpack price");
    }
    public static void redShirt() throws InterruptedException {
        WebElement redShirtLabel = itsDriver.findElement(By.xpath("//*[text()=\"Test.allTheThings() T-Shirt (Red)\"]"));
        String acredShirtLabel = redShirtLabel.getText();
        System.out.println(acredShirtLabel);
        Thread.sleep(1000);
        Assert.isTrue(acredShirtLabel.equals(exredShirtLabel), "backpack label is incorrect");
        WebElement redShirtDesc = itsDriver.findElement(By.xpath("//*[text()=\"This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton.\"]"));
        String acredShirtDesc = redShirtDesc.getText();
        System.out.println(acredShirtDesc);
        Thread.sleep(1000);
        Assert.isTrue(acredShirtDesc.equals(exredShirtDesc), "backpack Desc is different");
        WebElement redShirtrice = itsDriver.findElement(By.xpath("//*[text()='15.99']"));
        String acredShirtPrice = redShirtrice.getText();
        System.out.println(acredShirtPrice);
        Thread.sleep(1000);
        Assert.isTrue(acredShirtPrice.equals(exredShirtPrice), "incorrect backpack price");
    }
}
