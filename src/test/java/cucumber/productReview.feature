Feature: Add Product Review

  Background:
    Given user is on landing page

  Scenario Outline: Add review on product
    Given user is on products page
    When user click on View Product of a "<product>"
    Then Write Your Review is visible
    When user enter "<name>","<email>" and "<review>" and click submit
    Then Thank you for your review message is displayed
    Examples:
      | product                    | name    | email              | review                      |
      | Regular Fit Straight Jeans | Rajitha | rajithaw@gmail.com | Highly reccomended product! |


