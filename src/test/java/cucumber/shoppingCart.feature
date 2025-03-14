Feature: Shopping cart

  Background:
    Given user is on landing page

  Scenario: Add products to cart
    Given user click on Products button
    When user add first and second product to cart
    And user click on cart icon
    Then first and second products are displayed in the cart
    And their prices, quantity and total price

  Scenario Outline: Verify product quantity in cart
    Given user click on Products button
    When user click on View Product of "<product>"
    Then Product detail Page is visible
    When user increase quantity by "<quantity>"
    And user add product to cart
    And user click on cart icon
    Then "<product>" is displayed in cart page with exact "<quantity>"
    Examples:
      | product         | quantity |
      | Fancy Green Top | 4        |

  Scenario Outline: Remove Products from Cart
    Given user is on products page
    When user add "<firstProd>","<secondProd>","<thirdProd>" to cart
    And user click on cart icon
    Then cart page is displayed
    When user remove "<product>" from the cart
    Then "<product>" is not visible in the cart
    Examples:
      | firstProd                     | secondProd                    | thirdProd                          | product                       |
      | Cotton Mull Embroidered Dress | Pure Cotton Neon Green Tshirt | Cotton Silk Hand Block Print Saree | Pure Cotton Neon Green Tshirt |


  Scenario Outline: Search Products and Verify Cart After Login
    Given user click on Products button
    Then all products page is displayed
    When user enter "<product_name>" on search box and click search button
    Then "SEARCHED PRODUCTS" text is displayed
    And all the products with "<product_name>" are visible
    When user add all search products to cart
    And user click on cart icon
    Then all added products are available in the cart
    When user is on login page
    When user login with <email> and <password>
    And user click on cart icon
    Then all added products are available in the cart
    Examples:
      | product_name | email            | password  |
      | Saree       | hannam@gmail.com | hanna@123 |

  Scenario Outline: Add to cart from Recommended items
    Given user scroll down to recommended items
    Then recommended items are visible
    When user add the "<recommended_product>" to cart
    And user click on view cart link
    Then "<recommended_product>" is displayed in the cart
    Examples:
      | recommended_product |
      | Men Tshirt          |




