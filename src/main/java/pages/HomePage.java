package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.sql.Driver;

public class HomePage
{
    static By shopPage = By.xpath("//div[@class='primary-navigation']/descendant::li/child::a[contains(text(),'Shop')]");
    static By MyAccount = By.xpath("//li[@id=\"menu-item-46\"]/child::a[contains(text(),'My account')]");
    static By orders=By.xpath("//li[@class=\"woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--orders\"]/child::a");
    public static By newOrder=By.xpath("\"(//td[@class=\\\"woocommerce-orders-table__cell woocommerce-orders-table__cell-order-number\\\"]/child::a)[1]");
    public static void clickShopTab(WebDriver driver)
    {
        WebElement shopTabElement=driver.findElement(shopPage);
        shopTabElement.click();
    }

    public void clickMyAccount(WebDriver driver)
    {
        WebElement accounttab=driver.findElement(MyAccount);
        accounttab.click();
        WebElement order=driver.findElement(orders);
        order.click();

    }
}
