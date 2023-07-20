@TestNG
@severity=normal
Feature: Unique Identification Number Generation

  Scenario: Generate 5 CNP values and verify them on the website http://testingchallenges.thetestingmap.org/challenge4.php.
    Given I am on the webpage
    When I submit 5 different valid CNP numbers one at a time
    Then I should see 'YOU HAVE DONE IT. Please enter your name.*' message
