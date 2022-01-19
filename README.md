# Team Rin-Sensei

## Roster
* Rin Fukuoka
* Julia Kozak
* John Gupta-She


## Description
We plan on building a version of minesweeper that can be played with keyboard input in the terminal. As for the game, it consists of a board of hidden squares and randomly placed mines underneath some squares. The player can choose to select a square to reveal, upon which the board will either reveal that square by showing the total number of mines directly or diagonally adjacent to it, or the player loses because they selected a mine. The goal is to reveal the entire board without selecting a mine. Our version will have the player select their board size, and difficulty and/or number of mines. We plan on either having number/letter labels along the rows and columns that will allow the player to enter the index of the square they’d like to reveal, or having a cursor functionality that selects and highlights (prints in a different color) one square that can be revealed, which the player can control with WASD input. Also, since the original game includes the option of flagging a square that the player believes is a mine, we’d like to include an input option for that.
    The board will consist of two 2D (String and int) arrays: one that is generated with bombs and is filled with the classic minesweeper numbers, and another that indicates the revealed or unrevealed value to the player (for printing purposes). The functionality of revealing a big area of squares with no bombs around them will also be transferred over to our terminal version.

## How-To-Launch
To launch and play minesweeper, run the Woo.java file. The Minesweeper.java file will contain all of the necessary methods that Woo.java will use. The player can submit an integer value for the number of rows and columns in the grid as well as the amount of mines that will populate the grid. Then, to reveal squares, the player can input a letter (or symbol) followed by a number that represents the column and row of the square to be revealed.
