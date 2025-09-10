Feature: Ticket Booking
  As a logged-in user,
  I want to book tickets for a movie,
  So that I can reserve my seat.

  Scenario: Successful ticket booking
    Given the user is logged in
    When the user selects a movie and showtime
    And chooses seats
    And proceeds to payment
    And completes the payment
    Then the booking confirmation page is displayed with ticket details

  Scenario: Booking fails with invalid payment details
    Given the user is logged in
    When the user selects a movie and showtime
    And chooses seats
    And proceeds to payment
    And enters invalid payment details
    Then the payment fails and an error message is displayed
