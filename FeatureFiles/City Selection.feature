Feature: City Selection
  As a user,
  I want to select my city,
  So that I can view relevant content for my location.

  Scenario: Select a city
    Given the user is on the homepage
    When the user clicks on the city selector at the top
    And chooses a city from the list
    Then the selected city is set and relevant content is displayed
