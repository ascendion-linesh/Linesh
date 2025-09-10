Feature: Booking History
  As a user,
  I want to view my booking history,
  So that I can see my previous bookings.

  Scenario: View booking history
    Given the user has previous bookings
    When the user logs in
    And goes to the 'My Bookings' section
    Then a list of previous bookings is displayed
