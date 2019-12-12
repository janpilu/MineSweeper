# MineSweeper
A JavaFX Minesweeper Game
## Gameplay
The goal of the game is to sucsessfuly disarm a Minefiled by activating all fileds that don't contain a mine
### Setup
Upon starting the game you are greeted by a small setup window, where you can configure your Gameboard
#### Size
Using a dropdownmenu you can select the gameboards size.
#### Difficulty
Using a dropdownmenu you can select the games difficulty.
* Easy: ~10% Mines
* Medium: ~20% Mines
* Hard: ~30% Mines
### Game
You now Proceed to clear the minefield by either activating or marking tiles
#### Activate
Done by clicking a tile using the left mousebutton.
Activating a tile displays its content.
* Mine
If the tile contains a mine, all tiles are activated and the mines are highlighted red, indicating your loss
* No mine
  * Neighbours
  The tile displays the amount of mines in neighboring Tiles
  * No neighbors
  Should the Tile neither have mines nor neighbors containing mines, all of the activated tiles neighboring tiles are also activated
#### Mark
Done by clicking a tile using the right mousebutton
* !
Marking a Tile for the first time marks it with an "!", signaling that this Tile contains a mine
* ?
Marking a Tile for the second time marks it with a "?", signaling that this Tile might contain a mine

Marking a Tile for the third time removes the "?"
