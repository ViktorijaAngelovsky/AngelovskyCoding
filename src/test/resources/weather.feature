Feature: Check coordinates of City

  Scenario: Lat and Lon check
    Given country: uk
    And city: London

    When we are request weather data

    Then lon is -0.13
    And lat is 51.51

#    And id is 300
#    And main is Drizzle
#    And description is light intensity drizzle
#    And icon is 09d

    And weather data:
      | id          | 300                    |
      | main        | Drizzle                |
      | description | ight intensity drizzle |
      | icon        | 09d                    |

    And base is stations

    And temp is 280.32
    And pressure is 1012
    And humidity is 81
    And temp_min is 279.15
    And temp_max is 281.15

    And visibility is 10000

    And speed is 4.1
    And deg is 80

    And all_clouds is 90

    And dt is 1485789600

    And type is 1
    And id is 5091
    And message is 0.0103
    And country is GB
    And sunrise is 1485762037
    And sunset is 1485794875


    And other_id is 2643743

    And name is London

    And cod is 200

