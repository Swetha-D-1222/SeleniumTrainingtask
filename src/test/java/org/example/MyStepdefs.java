package org.example;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.CartPage;
import pages.CheckOutPage;
import pages.HomePage;
import pages.ShopPage;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyStepdefs
{
    WebDriver driver=new ChromeDriver();
    Actions action=new Actions(driver);
    HomePage home=new HomePage();
    ShopPage shop=new ShopPage();
    CartPage cart=new CartPage();
    CheckOutPage check=new CheckOutPage();
    List<Double> price=new ArrayList<>();
    @Given("Open the website")
    public void openTheWebsite()
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.navigate().to("https://www.edgewordstraining.co.uk/demo-site/");
        driver.manage().window().maximize();
    }

    @When("click the Shop tab")
    public void clickTheGivenTab()
    {
        home.clickShopTab(driver);
    }
    @Then("verify if you are navigated to Shop page")
    public void verifyIfYouAreNavigatedToShopPage()
    {
        String title=driver.findElement(ShopPage.pageTitle).getText();
        Assert.assertEquals(title,"Shop","Page not available");

    }

    @When("I select the filter price high to low")
    public void iSelectTheFilterPriceHighToLow()
    {

        shop.applyFilter(driver);
        price=shop.getprices(driver);
        shop.printProductWithSameprices(driver);
    }
    @Then("check if the products are sorted accordingly")
    public void checkIfTheProductsAreSortedAccordingly()
    {
        Double prices[]={18.00,55.00,16.00,42.00,45.00,35.00,45.00,25.00,20.00,90.00,18.00,18.00};
        List<Double> unsortedPrices=new ArrayList<>(Arrays.asList(prices));
        Collections.sort(unsortedPrices,Collections.reverseOrder());
        Assert.assertEquals(unsortedPrices,price,"The products are not sorted according to prices hight to low");

    }
    @And("Check if the product Hoodie is available")
    public void checkIfTheProductIsAvailable()
    {
        Boolean isPresent=shop.checkProduct(driver,"Hoodie");
        Assert.assertTrue(isPresent, "The product Hoodie is not present on the page");
    }

    @When("select the Hoodie product and add it to the cart")
    public void selectTheHoodieProductAndAddItToTheCart()
    {

        shop.selectProduct(driver);
    }

    @And("click the view cart button")
    public void clickTheViewCartButton()
    {
        shop.clickViewCart(driver);
    }

    int updatedPrice=0;
    @And("in the cart page add quantity {string} if needed")
    public void inTheCartPageAddQuantityIfNeededAndClickProceedToCheckOut(String quantity) throws InterruptedException
    {
        updatedPrice=cart.updateQuantity(driver,quantity);
    }


    @Then("check whether the cart and the price is updated {string},{string}")
    public void checkWhetherTheCartAndThePriceIsUpdated(String price,String quantity)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("woocommerce-message")));
//        assert alert.isDisplayed() : "Alert message is not displayed";
        Assert.assertTrue(alert.isDisplayed(),"Alert message displayed");
        String alertText = alert.getText();
//        assert alertText.contains("Cart updated.") : "Unexpected alert message: " + alertText;
        Assert.assertEquals(alertText,"Cart updated.","invalid alert");
        Assert.assertEquals(updatedPrice,Integer.parseInt(price)*(Integer.parseInt(quantity)),"Expected prices match!!");

    }

    @When("click proceed to check out")
    public void clickProceedToCheckOut()
    {
        cart.clickProceedButton(driver,action);
    }

    @And("login with the registered account to proceed {string}, {string}")
    public void loginWithTheRegisteredAccountToProceed(String username, String password) throws InterruptedException
    {
        cart.userLogin(driver,username,password);
    }

    @Then("validate whether your billing details are correct {string},{string},{string},{string},{string},{string},{string},{string},{string}")
    public void validateWhetherYourDetailsAreCorrect(String firstname, String lastname, String country,
                                                     String address, String city, String state,
                                                     String pincode, String phone, String email)
    {
        List<String> billingDetails=check.getDetails(driver);
        Assert.assertEquals(firstname,billingDetails.get(0),"First name is not correct");
        Assert.assertEquals(lastname,billingDetails.get(1),"last name is not correct");
        Assert.assertEquals(country,billingDetails.get(2),"country is not correct");
        Assert.assertEquals(address,billingDetails.get(3),"Address is not correct");
        Assert.assertEquals(city,billingDetails.get(4),"City is not correct");
        Assert.assertEquals(state,billingDetails.get(5),"State is not correct");
        Assert.assertEquals(pincode,billingDetails.get(6),"Pincode is not correct");
        Assert.assertEquals(phone,billingDetails.get(7),"Phone is not correct");
        Assert.assertEquals(email,billingDetails.get(8),"Email is not correct");
    }

    @And("verify your order details are correct {string},{string},{string}")
    public void verifyYourOrderDetailsAreCorrect(String quantity, String subtotal, String total)
    {
        List<String> orderDetails=check.getorderDetails(driver);
        Assert.assertEquals(quantity,orderDetails.get(0),"Order quantity is not the same");
        Assert.assertEquals(subtotal,orderDetails.get(1),"subtotal is not the same");
        Assert.assertEquals(total,orderDetails.get(2),"Total is not the same");
        total=orderDetails.get(2);
        System.out.println("-----Order details are correct-----");
    }

    @When("click cash on delivery option and click place order")
    public void clickCashOnDeliveryOptionAndClickPlaceOrder() throws InterruptedException
    {
        cart.placeorder(driver,action);
    }

    int orderId=0;

    @Then("verify the final order details {string},{string},{string}")
    public void verifyTheFinalOrderDetails(String paymentType, String total, String email)
    {
        WebElement checkOutTitle=driver.findElement(By.className("entry-title"));
        Assert.assertTrue(checkOutTitle.isDisplayed(),"Order placed successfully");
        WebElement paymentMethod= driver.findElement(By.xpath("//li[@class=\"woocommerce-order-overview__payment-method method\"]/child::strong"));
        String paymentMode=paymentMethod.getText();
        Assert.assertEquals(paymentType,paymentMode,"Payment method incorrect");
        WebElement finalPrice=driver.findElement(By.xpath("//strong/descendant::bdi"));
        String finalAmountToBepayed=finalPrice.getText();
        Boolean result=finalAmountToBepayed.contains(total);
        Assert.assertTrue(result,"Amount to be payed is incorrect");
        WebElement emailField=driver.findElement(By.xpath("//li[@class=\"woocommerce-order-overview__email email\"]/child::strong"));
        Assert.assertEquals(emailField.getText(),email,"Email don't match");
        WebElement order=driver.findElement(By.xpath("//li[@class=\"woocommerce-order-overview__order order\"]/child::strong"));
        orderId=Integer.parseInt(order.getText());

    }

    @When("move to MyAccount tab and move to orders")
    public void moveToMyAccountTab()
    {
        home.clickMyAccount(driver);
    }

    @Then("Check if the order id  for the ordered product is available")
    public void checkIfTheOrderIdForTheOrderedProductIsAvailable()
    {
        WebElement orderIdEntered=driver.findElement(HomePage.newOrder);
        int OrderId=Integer.parseInt(orderIdEntered.getText().replaceAll("#",""));
        Assert.assertEquals(orderId,OrderId,"Order not present");
        driver.quit();
    }

}
