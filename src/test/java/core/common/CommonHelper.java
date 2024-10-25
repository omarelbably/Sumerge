package core.common;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommonHelper {
    public final static int WAIT_TIME_OUT_DEFAULT = 120;
    public static WebDriver itsDriver;
    protected final WebDriverWait itsWait;
    @Before
    public static void openChrome() throws InterruptedException{
        itsDriver = new ChromeDriver();

        // 3 Navigate to google.com + Maximize screen + Delay closing for 3seconds
        itsDriver.navigate().to("https://demo.nopcommerce.com/");
        itsDriver.manage().window().maximize();
        Thread.sleep(1000);
    }
    @After
    public void closeDriver() throws InterruptedException {
        Thread.sleep(3000);
        itsDriver.quit();
    }
    @FindBy(how = How.XPATH, using = "\"//*[@id=\\\"user-name\\\"]\"")
    public WebElement loginUserName;
    @FindBy(how = How.XPATH, using = "//*[@id=\"password\"]")
    public WebElement loginPassword;
    @FindBy(how = How.XPATH, using = "//*[@id=\"login-button\"]")
    public WebElement loginButton;
    @FindBy(how = How.XPATH, using = "/html/body/div/div/div[2]/div[1]/div/div/form/div[3]/h3")
    public WebElement loginErrorMessage;

    protected WebDriverWait createWebWaitDriver(){
        return new WebDriverWait(itsDriver, Duration.ofSeconds(CommonHelper.WAIT_TIME_OUT_DEFAULT));
    }
    public CommonHelper(final WebDriver driver) {
        itsDriver = driver;
        itsWait = createWebWaitDriver();
    }

    public void waitForVisible(final WebElement webElement){
        itsWait.until(ExpectedConditions.visibilityOf(webElement));
    }
    protected void  assertCheckIfTrue(final String message, final boolean conditionToCheck){
        assertTrue(conditionToCheck, message);
    }
    protected void  assertCheckIfFalse(final String message, final boolean conditionToCheck){
        assertFalse(conditionToCheck, message);
    }
    public void openBrowser(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }
    public void enterUserName(){
        String userName = "standard_user";
        loginUserName.sendKeys(userName);
    }
    public void enterPassword(){
        String password = "secret_sauce";
        loginUserName.sendKeys(password);
    }
    public void checkLoginErrorMessage(){
        waitForVisible(loginErrorMessage);
        String exErrorMessage = "Epic sadface: Username and password do not match any user in this service";
        String errorMessage = loginErrorMessage.getText();
        assertCheckIfTrue("Error message displayed", errorMessage.contains(exErrorMessage));
    }
    @Given("User enters his username")
    public void enteringUsername(){
        enterUserName();
    }
    @Then("User enters his password")
    public void enteringPassword(){
        enterPassword();
    }
    @Then("User clicks on login button")
    public void clickLoginButton(){
        loginButton.click();
    }


}

