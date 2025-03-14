Feature: Search Product

  Scenario Outline: Search Product
    Given user is on landing page
    When user click on Products button
    Then all products page is displayed
    When user enter "<product_name>" on search box and click search button
    Then "SEARCHED PRODUCTS" text is displayed
    And all the products with "<product_name>" are visible


    Examples:
      | product_name |
      | Saree        |



