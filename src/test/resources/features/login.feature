Feature: Login

  Scenario: Successful login with valid credentials
    Given the user is on the login page
    When the user logs in with valid credentials
    Then the products page should be displayed
