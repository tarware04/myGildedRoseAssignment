Feature: Gilded Rose quality
  I want to know if the quality is updated properly

  Scenario: Checking items
    Given The items as item with sellin and quality:
      | item                        |  sellin     | quality     |
      | +5 Dexterity Vest           |    10       | 20          |
      | Aged Brie                   |    2        | 0           |
      | Sulfuras, Hand of Ragnaros  |    0        | 80          |
      | Conjured Mana Cake          |    3        | 6           |
    When I update the quality
    Then I should get item updated response as:
      | item                        |  sellin     | quality    |
      | +5 Dexterity Vest           |    9        | 19         |
      | Aged Brie                   |    1        | 1          |
      | Sulfuras, Hand of Ragnaros  |    0        | 80         |
      | Conjured Mana Cake          |    2        | 5          |

