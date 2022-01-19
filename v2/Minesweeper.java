import java.util.*;

public class Minesweeper {
  String[][] squares;
  int[][] squareVals;
  int rows, columns, mines;
  int playState;
  final String letters = "ABCDEFGHIJKLMNOPQRSTUVXYZ!@#$%^&*() ";

  public Minesweeper() {

  }

  public Minesweeper(int r, int c, int m) {
    this();
    rows = r;
    columns = c;
    playState = 0;
    squares = new String[rows+2][columns+2];
    squareVals = new int[rows+2][columns+2];
    for (int i=0; i<rows+2; i++) {
      for (int j=0; j<columns+2; j++) {
        squares[i][j] = "sad extreme";
        squareVals[i][j] = 9;
      }
    }
    for (int i=1; i<rows+1; i++) {
      for (int j=1; j<columns+1; j++) {
        squares[i][j] = "█";
        squareVals[i][j] = 0;
      }
    }
    int ctr = 0;
    while(ctr != m) {
      int rI = (int)(Math.random()*(rows))+1;
      int cI = (int)(Math.random()*(columns))+1;
      if (squareVals[rI][cI] != -1) {
        squareVals[rI][cI] = -1;
        ctr ++;
      }
    }
    for (int i=1; i<rows+1; i++) {
      for (int j=1; j<columns+1; j++) {
        if (squareVals[i][j] != -1) {
          for (int p = i-1; p<i+2; p++) {
            for (int q=j-1; q<j+2; q++) {
	      if (p != i || q != j) {
	        if (squareVals[p][q] == -1) {
		  squareVals[i][j]++;
		}
	      }
	    }
          }
        }
      }
    }

  }

  public void checkWin(){
    int ctr = 0;
    for (int i=1; i<rows+1; i++) {
      for (int j=1; j<columns+1; j++) {
        if (isRevealed(i, j)) {
          ctr++;
        }
      }
    }
    if (ctr == mines) {
      playState = 1;
    }
  }

  public void processInput(String e) {
    try {
      int cCor = letters.indexOf(e.substring(0, 1))+1;
      int rCor = Integer.parseInt(e);
      reveal(cCor+1, rCor+1);
    } catch (Exception w) {

    }
  }

  public void reveal(int c, int r) {
    if (squareVals[c][r] != -1) {
      if (!(isRevealed(c, r))) {
          squares[c][r] = Integer.toString(squareVals[c][r]);
      }
      if (squareVals[c][r] == 0) {
              for (int p = c-1; p<c+2; p++) {
                for (int q=r-1; q<r+2; q++) {
    	      if (p != r || q != c) {
    	        if (squareVals[p][q] == 0) {
    		  reveal(p, q);
    		}
    	      }
    	    }
              }
      }
    } else {
      playState = -1;
    }
  }

  public boolean isRevealed(int c, int r) {
    return squares[c][r].equals("█") ||squares[c][r].equals("⚑");
  }

  public void printBoard() {
    System.out.print("    ");
    for (int i=0; i<columns; i++) {
      System.out.print(letters.substring(i, i+1) + " ");
    }
    System.out.println();
    for (int i=1; i<rows+1; i++) {
      System.out.print(i);
      System.out.print(" ");
      if (i <= 9) {
        System.out.print(" ");
      }
      for (int j=1; j<columns+1; j++) {
        System.out.print("|" + squares[i][j]);
      }
      System.out.println("|");
    }
  }

  public static void main(String[] args) {
    Minesweeper me;
    int r, c, m;
    Scanner response = new Scanner(System.in);

    System.out.println("welcome to minesweeper");
    System.out.println();

    System.out.print("choose rows: ");
    String input = response.nextLine();
    try {
      r = Integer.parseInt(input);
    } catch (Exception e) {
      r = 10;
    }
    System.out.println();

    System.out.print("choose columns: ");
    input = response.nextLine();
    try {
      c = Integer.parseInt(input);
    } catch (Exception e) {
      c = 10;
    }

    System.out.print("choose mines: ");
    input = response.nextLine();
    try {
      m = Integer.parseInt(input);
    } catch (Exception e) {
      m = 20;
    }

    me = new Minesweeper(r, c, m);


    while(me.playState == 0) {
      me.printBoard();
      System.out.print("your move: ");
      input = response.nextLine();
      me.processInput(input);
      me.checkWin();
    }




  }
}

// in case we need: ⚑
