package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CheckOutPage
{
    static By checkOutTitle=By.className("entry-title");
    static By billingFirstNameField=By.id("billing_first_name");
    static By billingLastNameField=By.id("billing_last_name");
    static By countryField=By.id("select2-billing_country-container");
    static By streetfield=By.id("billing_address_1");
    static By cityField=By.id("billing_city");
    static By stateField=By.id("select2-billing_state-container");
    static By pinCodeField=By.id("billing_postcode");
    static By mobileField=By.id("billing_phone");
    static By emailField=By.id("billing_email");
    static By quantity=By.className("product-quantity");
    static By subTotalField=By.xpath("//tr[@class=\"cart-subtotal\"]/descendant::bdi");
    static By totalField=By.xpath("//tr[@class=\"order-total\"]/descendant::bdi");

    public List<String> getDetails(WebDriver driver)
    {
        List<String> details=new ArrayList<>();
        WebElement firstName= driver.findElement(billingFirstNameField);
        String firstNameValue= firstName.getAttribute("value");
        details.add(firstNameValue);
        WebElement lastName= driver.findElement(billingLastNameField);
        String lastNameValue= lastName.getAttribute("value");
        details.add(lastNameValue);
        WebElement country=driver.findElement(countryField);
        String countryValue=country.getText();
        details.add(countryValue);
        WebElement street=driver.findElement(streetfield);
        String streetValue=street.getDomAttribute("value");
        details.add(streetValue);
        WebElement city=driver.findElement(cityField);
        String cityValue=city.getAttribute("value");
        details.add(cityValue);
        WebElement state=driver.findElement(stateField);
        String stateValue=state.getText();
        details.add(stateValue);
        WebElement pinCode=driver.findElement(pinCodeField);
        String pinCodeValue=String.valueOf(pinCode.getAttribute("value"));
        details.add(pinCodeValue);
        WebElement mobile= driver.findElement(mobileField);
        String mobileValue=String.valueOf(mobile.getAttribute("value"));
        details.add(mobileValue);
        WebElement email= driver.findElement(emailField);
        String emailValue=email.getAttribute("value");
        details.add(emailValue);


        return details;
    }

    public List<String> getorderDetails(WebDriver driver)
    {
        List<String> order=new ArrayList<>();
        WebElement quantityProvided=driver.findElement(quantity);
        order.add(quantityProvided.getText().replaceAll("× ",""));
        WebElement subtotal=driver.findElement(subTotalField);
        order.add(subtotal.getText().replaceAll(".00","").replaceAll("£",""));
        WebElement total=driver.findElement(totalField);
        order.add(total.getText().replaceAll("£",""));

        return order;
    }
}
