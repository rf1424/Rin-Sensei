# Team Rin-Sensei

## Roster
* Rin Fukuoka (Tape)
* Julia Kozak (Flopsy)
* John Gupta-She (Po)


## Description
- *Minesweeper Game Description*: The classic version of the game consists of a board of hidden squares and randomly placed mines underneath some squares. The player can choose to select a square to reveal (which they'd normally click), upon which the board will either reveal that square by showing the total number of mines directly or diagonally adjacent to it, or the player loses because they selected a mine. The goal is to reveal the entire board without selecting a mine. A player can also select (normally right-click) a square to flag it as a mine. 
- *Our Version*: This version has the same setup and goal, but it takes keyboard input from the terminal, which will allow the player to reveal a square. The player can first select the number of rows, columns, and mines, then the mode in which they want to play. We have two game modes:
  - *WASD*: One square of the board will be highlighted in red/blue, which the player can control with WASD and enter "e" or "f" to reveal or flag. 
  - *without WASD*: Column/row indices (numbers/letters) will be printed on the side of the board, and the player can input a certain code for the move they want to make.
- The board will consist of two 2D (String and int) arrays: one that is generated with bombs and is filled with the classic minesweeper numbers, and another that indicates the revealed or unrevealed value to the player (for printing purposes). The functionality of revealing a big area of squares with no bombs around them will also be transferred over to our terminal version.

## How-To-Launch
- To launch and play minesweeper, run the Woo.java file. The Minesweeper.java file will contain all of the necessary methods that Woo.java will use. The player can submit a value for the number of rows and columns in the grid as well as the amount of mines that will populate the grid (invalid inputs will produce a default board). To reveal squares in the non-WASD mode, the player can input a letter (or symbol) followed by a number that represents the column and row of the square to be revealed, and may include "f" to denote flagging. In the WASD mode, the player can input the characters of efwasd for controls, selecting, and flagging. 
- More detailed instructions are printed at the start of the game and in the selection of the game mode.
