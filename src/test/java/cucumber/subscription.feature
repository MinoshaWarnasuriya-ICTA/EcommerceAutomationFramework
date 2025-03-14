Feature: Subscription

  Background:
    Given user is on landing page

  Scenario Outline: Subscription on Home Page
    When user scroll down to footer
    Then Subscription text is displayed
    When user enter "<email>" and click arrow button
    Then "<success_message>" is displayed on footer
    Examples:
      | email         | success_message                        |
      | msd@gmail.com | You have been successfully subscribed! |

  Scenario Outline: Subscription on Cart Page
    Given user is on Cart Page
    When user scroll down to footer
    Then Subscription text is displayed
    When user enter "<email>" and click arrow button
    Then "<success_message>" is displayed on footer
    Examples:
      | email         | success_message                        |
      | xyz@gmail.com | You have been successfully subscribed! |