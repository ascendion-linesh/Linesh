Feature: Movie Search
  As a user,
  I want to search for movies,
  So that I can find movies I want to watch.

  Scenario: Search for a movie
    Given the user is on the homepage
    When the user enters a movie name in the search bar
    And clicks the search icon
    Then relevant search results are displayed

  Scenario: Search with no results
    Given the user is on the homepage
    When the user enters a random string in the search bar
    And clicks the search icon
    Then a message such as "No results found" is displayed
