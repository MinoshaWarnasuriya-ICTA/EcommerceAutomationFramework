Feature: User Registration

  Background:
    Given user is on landing page

  Scenario Outline: Register new user
    Given user is on login page
    When user enter sign up <name> and <email>
    And user click on sign up button
    Then ENTER ACCOUNT INFORMATION is displayed
    When user fill account information with <title>,<password>,<date>,<month>,<year>
    And user fill address information with <firstName>,<lastName>,<company>,<address1>,<address2>,<country>,<state>,<city>,<zipcode>,<mobile>
    And user click on create account button
    Then "ACCOUNT CREATED!" text is visible
    When user clicks on continue button
    Then welcome text with <firstName> is visible
    When user click on delete account
    Then ACCOUNT DELETED! text is displayed
    Examples:
      | name  | email            | title | password | date | month   | year | firstName | lastName | company | address1 | address2 | country | state     | city  | zipcode | mobile  |
      | Jenny | jenny1@gmail.com | Mrs   | jen@123  | 23   | January | 1994 | Jenny     | Winget   | StarTV  | No:123   | 6th Lane | India   | New Delhi | Delhi | 009     | 0467890 |

  Scenario Outline: Existing User Registration
    Given user is on login page
    When user enter sign up <name> and <email>
    And user click on sign up button
    Then "Email Address already exist!" error message is displayed
    Examples:
      | name  | email           |
      | Jenny | jenny@gmail.com |

