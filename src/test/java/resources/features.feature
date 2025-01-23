Feature: place an order for hoodie

  @Regression
  Scenario Outline: click the shop tab and view the products

    Given Open the website
    When click the Shop tab
    Then verify if you are navigated to Shop page

    When I select the filter price high to low
    Then check if the products are sorted accordingly
    And Check if the product Hoodie is available

    When select the Hoodie product and add it to the cart
    And click the view cart button
    And in the cart page add quantity "<expectedquantity>" if needed
    Then check whether the cart and the price is updated "<actualprice>","<expectedquantity>"

    When click proceed to check out
    And login with the registered account to proceed "<username>", "<password>"
    Then validate whether your billing details are correct "<firstname>","<lastname>","<country>","<address>","<city>","<state>","<pincode>","<phone>","<email>"
    And verify your order details are correct "<expectedquantity>","<subtotal>","<total>"

    When click cash on delivery option and click place order
    Then verify the final order details "<paymentMethod>","<total>","<email>"

    When move to MyAccount tab and move to orders
    Then Check if the order id  for the ordered product is available

    Examples:
      | expectedquantity | actualprice | username              | password       | firstname | lastname | country | address                       | city       | state      | pincode | phone      | email                 | subtotal | total | paymentMethod    |
      | 2                | 42          | swethad2021@gmail.com | #swe@1234.2024 | swetha    | D        | India   | 103,Kothari nagar,singanallur | coimbatore | Tamil Nadu | 641005  | 8220854350 | swethad2021@gmail.com | 84       | 93.95 | Cash on delivery |






