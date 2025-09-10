Feature: Logout
  As a logged-in user,
  I want to log out,
  So that I can end my session securely.

  Scenario: Successful logout
    Given the user is logged in
    When the user clicks on the user profile
    And clicks 'Logout'
    Then the user is logged out and redirected to the homepage
