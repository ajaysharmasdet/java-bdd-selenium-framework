Feature: Login functionality

  Scenario: Valid user logs in successfully
    Given the user is on the login page
    When the user logs in with username "standard_user" and password "secret_sauce"
    Then the products page should be displayed

  Scenario: Locked out user should see error message
    Given the user is on the login page
    When the user logs in with username "locked_out_user" and password "secret_sauce"
    Then an error message "Epic sadface: Sorry, this user has been locked out." should be displayed

  Scenario: Invalid password should show error message
    Given the user is on the login page
    When the user logs in with username "standard_user" and password "wrong_password"
    Then an error message "Epic sadface: Username and password do not match any user in this service" should be displayed

  Scenario: User logs out successfully
    Given the user logs in with valid username and password
    When the user logs out
    Then the user should be redirected to the login page