Feature: login test

  Scenario: Login success on saucedemo
    Given Open web url "https://www.saucedemo.com/"
    And Input username "standard_user" Input password "secret_sauce"
    When Click Login button
    Then Should success login and redirected to homepage

  @valid
  Scenario Outline: Login success on saucedemo using scenario outline
    Given Open web url "https://www.saucedemo.com/"
    And Input username "<username>" Input password "<password>"
    When Click Login button
    Then Should success login and redirected to homepage

    Examples: 
      | username      | password     |
      | standard_user | secret_sauce |

  @invalid
  Scenario Outline: Login failed on saucedemo using scenario outline
    Given Open web url "https://www.saucedemo.com/"
    And Input username "<username>" Input password "<password>"
    When Click Login button
    Then Failed login and showing message

    Examples: 
      | username      | password |
      | standard_user | xx       |
