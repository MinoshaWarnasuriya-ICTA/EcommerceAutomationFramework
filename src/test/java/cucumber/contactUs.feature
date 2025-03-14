Feature: Contact Us Form


  Scenario Outline: Fill Contact Us Form
    Given user is on landing page
    When user click on Contact Us button
    Then Get In Touch is visible
    When user enter contact us details <name>,<email>,<subject>,<message>
    And user upload <file>
    And user click submit button
    And user accept alert
    Then success message is visible
    Examples:
      | name  | email           | subject   | message                     | file                       |
      | Scott | scott@gmail.com | Hi there! | I'd like to get a quotation | F:/Udemy/API/students.json |



