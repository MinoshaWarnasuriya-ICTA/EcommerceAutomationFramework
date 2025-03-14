Feature: Scroll window up and down

  Background:
    Given user is on landing page

    Scenario: Scroll up with arrow button
      When user scroll down page to bottom
      Then subscription text is visible
      When user click on up arrow button to move upward
      Then page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen

Scenario: Scroll up without arrow button
  When user scroll down page to bottom
  Then subscription text is visible
  When user scroll up page to top
  Then page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen



