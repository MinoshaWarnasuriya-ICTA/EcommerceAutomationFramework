Feature: Place Order

  Background:
    Given user is on landing page

  Scenario Outline: Register While Checkout
    Given user is on products page
    When user add "<first_product>","<second_product>","<third_product>" to cart
    And user click on cart icon
    Then cart page is displayed
    When user click proceed to checkout and click register or login
    Then user is on login page
    When user enter sign up <name> and <email>
    And user click on sign up button
    And user fill account information with <title>,<password>,<date>,<month>,<year>
    And user fill address information with <firstName>,<lastName>,<company>,<address1>,<address2>,<country>,<state>,<city>,<zipcode>,<mobile>
    And user click on create account button
    Then 'ACCOUNT CREATED!' text is visible
    When user clicks on continue button
    And welcome text with <firstName> is visible
    When user click on cart icon
    And click proceed to checkout
    And user enter message and click on place order button
    And user enter payment details and click confirm order button
#    Then "<success_message>" is displayed
    When user click on delete account
    Then ACCOUNT DELETED! text is displayed
    Examples:
      | first_product | second_product     | third_product        | name | email         | title | password       | date | month    | year | firstName | lastName    | company | address1 | address2        | country | state  | city  | zipcode | mobile | success_message                          |
      | Men Tshirt    | Lace Top For Women | Frozen Tops For Kids | Mino | mds@yahoo.com | Mrs   | mino@yahoo.com | 20   | December | 1990 | Mino      | Warnasuriya | ICTA    | No:90    | St.Marry street | India   | Gujrat | Delhi | 009     | 09876  | Your order has been placed successfully! |

  Scenario Outline: Register Before Checkout
    Given user is on login page
    When user enter sign up <name> and <email>
    And user click on sign up button
    And user fill account information with <title>,<password>,<date>,<month>,<year>
    And user fill address information with <firstName>,<lastName>,<company>,<address1>,<address2>,<country>,<state>,<city>,<zipcode>,<mobile>
    And user click on create account button
    Then 'ACCOUNT CREATED!' text is visible
    When user clicks on continue button
    Then welcome text with <firstName> is visible
    When user click on Products button
    And user add "<product>" to cart
    And user click on cart icon
    Then cart page is displayed
    When user click on proceed to checkout
    And user enter message and click on place order button
    And user enter payment details and click confirm order button
#    Then "<success_message>" is displayed
    When user click on delete account
    Then ACCOUNT DELETED! text is displayed
    Examples:
      | product    | name | email         | title | password       | date | month    | year | firstName | lastName    | company | address1 | address2        | country | state  | city  | zipcode | mobile | success_message                          |
      | Men Tshirt | Mino | mds@gmail.com | Mrs   | mino@yahoo.com | 20   | December | 1990 | Mino      | Warnasuriya | ICTA    | No:90    | St.Marry street | India   | Gujrat | Delhi | 009     | 09876  | Your order has been placed successfully! |

  Scenario Outline: Login Before Checkout
    Given user is on login page
    When user login with <email> and <password>
    Then welcome text with <firstName> is visible
    When user click on Products button
    And user add "<product>" to cart
    And user click on cart icon
    Then cart page is displayed
    When user click on proceed to checkout
    And user enter message and click on place order button
    And user enter payment details and click confirm order button
#  Then "<success_message>" is displayed
    When user click on delete account
    Then ACCOUNT DELETED! text is displayed
    Examples:
      | email              | password    | firstName | product                          | success_message |
      | rajithaw@yahoo.com | rajitha@123 | Rajitha   | Printed Off Shoulder Top - White | xxx             |

  Scenario Outline: Download Invoice after purchase order
    Given user is on products page
    When user add "<product>" to cart
    And user click on cart icon
    Then cart page is displayed
    When user click proceed to checkout and click register or login
    And user login with <email> and <password>
    Then welcome text with <firstName> is visible
    When user click on cart icon
    And user click on proceed to checkout
    And user enter message and click on place order button
    And user enter payment details and click confirm order button
    And user click on download invoice
    Then invoice is downloaded successfully
    Examples:
      | product                          | email            | password  | firstName |
      | Printed Off Shoulder Top - White | hannam@gmail.com | hanna@123 | Hanna     |










