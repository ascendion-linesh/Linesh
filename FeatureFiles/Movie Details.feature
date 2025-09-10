Feature: Movie Details
  As a user,
  I want to view movie details,
  So that I can learn more about movies before booking.

  Scenario: View movie details page
    Given the movie is available
    When the user searches for a movie
    And clicks on the movie from the results
    Then the movie details page is displayed with synopsis, cast, showtimes, etc.
