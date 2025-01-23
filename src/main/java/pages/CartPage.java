package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage
{
    static By footerField=By.id("colophon");
    static By proceed=By.xpath("//a[contains(text(),'Proceed to checkout')]");
    static By loginField=By.className("showlogin");
    static By usernameField=By.id("username");
    static By passwordField=By.id("password");
    static By loginButton=By.name("login");
    static By moveDown=By.id("ship-to-different-address");
    static By cashOnDeliveryOption= By.xpath("//div[@id=\"payment\"]/descendant::input[@id=\"payment_method_cod\"]/following-sibling::label[contains(text(),'Cash on delivery')]");
    static By placeOrderButton=By.name("woocommerce_checkout_place_order");
    static By quantityField=By.xpath("//input[@class='input-text qty text']");
    static By updateCartButton=By.name("update_cart");
    static By subtotalField=By.xpath("//td[@class=\"product-subtotal\"]/descendant::span[@class=\"woocommerce-Price-amount amount\"]/descendant::bdi");

    public int updateQuantity(WebDriver driver,String quantity) throws InterruptedException
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement quantityInput = wait.until(ExpectedConditions.elementToBeClickable(quantityField));
        quantityInput.clear();
        quantityInput.sendKeys(quantity);
        WebElement updateCart = wait.until(ExpectedConditions.elementToBeClickable(updateCartButton));
        updateCart.click();
        FluentWait<WebDriver> wait1 = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        Thread.sleep(3000);
        WebElement subtotal = wait1.until(ExpectedConditions.visibilityOfElementLocated(subtotalField));
        int quantityUpdated = 0;
        quantityUpdated = Integer.parseInt(subtotal.getText().replaceAll(".00", "").replaceAll("£", ""));

        return quantityUpdated;

    }


    public void clickProceedButton(WebDriver driver, Actions action)
    {

        WebElement footer=driver.findElement(footerField);
        action.scrollToElement(footer);
        WebElement proceedButton=driver.findElement(proceed);
        proceedButton.click();

    }

    public void userLogin(WebDriver driver,String userName,String password) throws InterruptedException {
        WebElement login= driver.findElement(loginField);
        login.click();
        WebElement enterUsername=driver.findElement(usernameField);
        enterUsername.sendKeys(userName);
        WebElement enterPassword= driver.findElement(passwordField);
        enterPassword.sendKeys(password);
        driver.manage().window().setSize(new Dimension(1500, 1000));
        WebElement loginButtonClick=driver.findElement(loginButton);
        loginButtonClick.click();

    }

    public void placeorder(WebDriver driver, Actions action) throws InterruptedException
    {
        WebElement down=driver.findElement(moveDown);
        action.scrollToElement(down);
        Thread.sleep(2000);
       /* FluentWait<WebDriver> wait1 = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        WebElement cashOnDelivery=wait1.until(ExpectedConditions.visibilityOfElementLocated(cashOnDeliveryOption));;*/
        WebElement cashOnDelivery=driver.findElement(cashOnDeliveryOption);
        cashOnDelivery.click();
        WebElement placeOrder=driver.findElement(placeOrderButton);
        placeOrder.click();
    }
}
