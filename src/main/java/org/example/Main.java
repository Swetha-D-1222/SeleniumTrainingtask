package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.edgewordstraining.co.uk/demo-site/");
        driver.manage().window().maximize();
        WebElement element=driver.findElement(By.xpath("//div[@class=\"primary-navigation\"]/descendant::li/child::a[contains(text(),'Shop')]"));
        element.click();
        WebElement filter=driver.findElement(By.name("orderby"));
        filter.click();
        Select select=new Select(filter);
        select.selectByVisibleText("Sort by price: high to low");
        WebElement Ad = driver.findElement(By.xpath("//a[@class=\"woocommerce-store-notice__dismiss-link\"]"));
        Ad.click();
//        span[contains(@class, 'woocommerce-Price-amount') and not(ancestor::del)]//following::a[contains(text(), "Add to cart")]
         WebElement addToCart = driver.findElement(By.xpath(".//following::a[contains(text(), \"Add to cart\")]/parent::li/following-sibling::li[@class=\"product type-product post-34 status-publish instock product_cat-hoodies has-post-thumbnail sale featured shipping-taxable purchasable product-type-simple\"]/descendant::a[contains(text(), \"Add to cart\")]"));
         addToCart.click();
        WebElement viewCart = driver.findElement(By.xpath(".//following::a[contains(text(), \"Add to cart\")]/parent::li/following-sibling::li[@class=\"product type-product post-34 status-publish instock product_cat-hoodies has-post-thumbnail sale featured shipping-taxable purchasable product-type-simple\"]/descendant::a[contains(text(), \"Add to cart\")]/following::a[@class=\"added_to_cart wc-forward\"]"));//
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));//
    wait.until(ExpectedConditions.elementToBeClickable(viewCart));
    viewCart.click();

         driver.quit();
//    }
//    @And("the user clicks on the view cart button")
//    public void theUserClicksOnTheViewCartButton() {
//    WebElement viewCart = highestPriceElement.findElement(By.xpath(".//following::a[contains(text(), \"View cart\")]"));//
//    WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));//
//    wait.until(ExpectedConditions.elementToBeClickable(viewCart));
//    viewCart.click();
//    }//
//    WebElement click=driver.findElement(By.xpath("//a[@href=\"https://www.edgewordstraining.co.uk/demo-site/product/hoodie/\"]"));
//        click.click();
//        WebElement addToCart=driver.findElement(By.name("add-to-cart"));
//        addToCart.click();
//        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
//        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//a[@data-product_id='34' and contains(@class, 'add_to_cart_button')]")
//        ));
//
//        // Click the Add to Cart button
//        addToCartButton.click();
//        System.out.println("Is displayed: " + addToCartButton.isDisplayed());
//        System.out.println("Is enabled: " + addToCartButton.isEnabled());
    }
}