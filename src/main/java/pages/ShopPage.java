package pages;

import io.cucumber.java.hu.Ha;
import io.cucumber.java.sl.In;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

public class ShopPage
{
    public static By pageTitle=By.xpath("//h1[contains(text(),'Shop')]");
    static By filterField = By.name("orderby");
    static By ad=By.className("woocommerce-store-notice__dismiss-link");
    static By addToCart=By.xpath(".//following::a[contains(text(), \"Add to cart\")]/parent::li/following-sibling::li[@class=\"product type-product post-34 status-publish instock product_cat-hoodies has-post-thumbnail sale featured shipping-taxable purchasable product-type-simple\"]/descendant::a[contains(text(), \"Add to cart\")]");
    static By viewCart=By.xpath("//a[@title='View cart']");
    static By productName=By.xpath(".//h2");
    static By productPrices=By.xpath("//span[contains(@class, 'woocommerce-Price-amount') and not(ancestor::del)]");
    List<Integer> price=new LinkedList<>();
    List<WebElement> allProducts=new LinkedList<>();
    LinkedList<String> productNamesList = new LinkedList<>();
    LinkedList<Double> productPricesList = new LinkedList<>();
    static HashMap<String,Double> products=new LinkedHashMap<>();

    public boolean checkProduct(WebDriver driver,String productName)
    {
        List<WebElement> products = driver.findElements(By.xpath("//li/descendant::h2[starts-with(text(),'"+productName+"')]"));
        boolean present = products.size() > 0;
        return present;
    }
    public List<Double> getprices(WebDriver driver)
    {

        allProducts=driver.findElements(productName);
        List<WebElement> priceListOfAllProducts =driver.findElements(productPrices);


        List<Double> prices = new ArrayList<>();
        for(WebElement product:allProducts) {
            String name = product.getText();
            productNamesList.add(name);
        }

        for(WebElement element:priceListOfAllProducts)
        {
            double price= Double.parseDouble(element.getText().replaceAll("£",""));
            prices.add(price);
        }
        for(int i=1;i<prices.size();i++)
        {
            productPricesList.add(prices.get(i));
        }
        for(int i=0;i<productPricesList.size();i++)
        {
            products.put(productNamesList.get(i),productPricesList.get(i));
        }
//        System.out.println(products);
        return productPricesList;
    }
    public void applyFilter(WebDriver driver)
    {
        WebElement filter=driver.findElement(filterField);
        filter.click();
        Select select=new Select(filter);
        select.selectByVisibleText("Sort by price: high to low");
    }

    public void selectProduct(WebDriver driver)
    {
        WebElement alert= driver.findElement(ad);
        alert.click();
        WebElement addHoodieToCart= driver.findElement(addToCart);
        addHoodieToCart.click();
    }

    public void clickViewCart(WebDriver driver)
    {
        WebElement viewCartButton=driver.findElement(viewCart);
        viewCartButton.click();
    }


    public void printProductWithSameprices(WebDriver driver)
    {
        HashMap<Integer,Integer> countOfPrice=new HashMap<>();
        for(int i :price)
        {
            if(countOfPrice.containsKey(i))
            {
                countOfPrice.put(i,countOfPrice.get(i)+1);
            }
            else
            {
                countOfPrice.put(i,1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : countOfPrice.entrySet())
        {
            if (entry.getValue() > 1)
            {
                int price = entry.getKey();
                System.out.println("Products with same price " + price + ":");
                for (Map.Entry<String, Double> productEntry : products.entrySet())
                {
                    if (productEntry.getValue() == price)
                    {
                        System.out.println(productEntry.getKey());
                    }
                }
                System.out.println();
            }
        }
    }


}
