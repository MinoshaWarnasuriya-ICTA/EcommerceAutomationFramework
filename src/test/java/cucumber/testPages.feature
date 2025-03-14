Feature: Test Page Visibility

  Background:
    Given user is on landing page

  Scenario: Verify Test Cases Page
    When user click on Test Cases button
    Then test cases page is displayed

  Scenario Outline: Verify All Products and Product Detail Page
    When user click on Products button
    Then all products page is displayed
    And product list is visible
    When user click on View Product of first product
    Then Product detail Page is visible
    And  <product name>, <category>, <price>, <availability>, <condition>, <brand> are visible
    Examples:
      | product name | category               | price | availability | condition | brand |
      | Blue Top     | Women > Tops | 500   | In Stock     | New       | Polo  |

