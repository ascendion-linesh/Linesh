Feature: Homepage Display
  As a user,
  I want to access the homepage,
  So that I can view banners, search bar, and navigation menu.

  Scenario: Homepage loads successfully
    Given the user navigates to https://in.bookmyshow.com/
    Then the homepage is displayed with banners, search bar, and navigation menu
