Feature: Showtimes Display
  As a user,
  I want to see available showtimes for a movie,
  So that I can choose a suitable time to watch.

  Scenario: Display available showtimes
    Given the movie is available
    When the user goes to the movie details page
    Then a list of available showtimes is displayed
