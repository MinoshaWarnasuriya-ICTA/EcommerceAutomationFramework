Feature: View Category and Brand Products

  Background:
    Given user is on landing page

  Scenario Outline: View Category Products
    When user click on "<category>" on left side bar
    And user click on "<subcategory>" under the "<category>"
    Then category page is displayed with "<category_title>"
    Examples:
      | category | subcategory   | category_title       |
      | Kids     | Tops & Shirts | Kids > Tops & Shirts |

  Scenario Outline: View Brand Products
    Given user is on products page
    Then brands are visible on the left side bar
    When user select a "<brand>"
    Then "<brand_title>" and brand products are displayed
    Examples:
      | brand  | brand_title             |
      | Madame | BRAND - MADAME PRODUCTS |

