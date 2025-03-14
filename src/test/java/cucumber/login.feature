Feature: Login

  Background:
    Given user is on landing page

  Scenario Outline: Login with valid credentials
    Given user is on login page
    When user login with <email> and <password>
    Then Logged in as <username> is visible
    Examples:
      | email           | password | username |
      | scott@gmail.com | scot@123 | Scott    |

  Scenario Outline: Login with invalid credentials
    Given user is on login page
    When user login with <email> and <password>
    Then "Your email or password is incorrect!" message is displayed
    Examples:
      | email             | password |
      | minosha@gmail.com | minos@12 |

  Scenario Outline: User Logout
    Given user is logged in with <email> and <password>
    When user clicks on logout button
    Then login page is displayed
    Examples:
      | email           | password |
      | scott@gmail.com | scot@123 |



