Feature: Reset functionality on login page of Application

  Scenario Outline: Verification of reset button with different credentials
    Given Open the Edge and launch the application
    When I enter the Username "<username>" and Password "<password>"
    Then I click on the Reset button to clear the credentials

    Examples:
      | username | password  |
      | User1    | password1 |
      | User2    | password2 |
