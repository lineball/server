# Game can be start when two players are set

## Narrative:
As a player  
I want to make init the game when second player enters the field  
So that second player can accept and game starts  

## Scenario 1: Game starts with two players
Given: new field is created  
And: first and second player enter the field  
When: first player (white) init game  
Then: second player got event and can start game  


## Scenario 2: Single player cannot start game
Given: new field is created  
And: first player enters the field  
When: first player (white) init game  
Then: an exception is thrown - Cannot init game without second player  

