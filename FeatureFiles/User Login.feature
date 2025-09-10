Feature: User Login
  As a registered user,
  I want to log in to my account,
  So that I can access personalized features.

  Scenario: Login with valid credentials
    Given the user has a registered account
    When the user clicks 'Sign In'
    And enters a valid mobile number or email and password
    And clicks on the 'Login' button
    Then the user is logged in and redirected to the homepage or dashboard

  Scenario: Login with invalid credentials
    Given the user is on the login page
    When the user enters an invalid mobile number or email or password
    And clicks on the 'Login' button
    Then an error message is displayed indicating invalid credentials
