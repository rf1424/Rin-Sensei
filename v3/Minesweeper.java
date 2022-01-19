import java.util.*;

public class Minesweeper {
  String[][] squares;
  int[][] squareVals;
  int rows, columns, mines;
  int playState;
  final String letters = "ABCDEFGHIJKLMNOPQRSTUVXYZ!@#$%^&*() ";

  public Minesweeper(int r, int c, int m) {
    playState = 0;
    rows = r;
    columns = c;
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
    while(mines != m) {
      int rI = (int)(Math.random()*(rows))+1;
      int cI = (int)(Math.random()*(columns))+1;
      if (squareVals[rI][cI] != -1) {
        squareVals[rI][cI] = -1;
        mines ++;
      }
    }
    for(int i=1; i<rows+1; i++) {
      for (int j=1; j<columns+1; j++) {
        if (squareVals[i][j] != -1) {
          for (int p=i-1; p<i+2; p++) {
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
        if (!(isRevealed(i, j))) {
          ctr++;
        }
      }
    }
    if (ctr == mines) {
      playState = 1;
    }
  }

  public void processInput(String e) {
    int cCor = letters.indexOf(e.substring(0, 1))+1;
    int rCor = Integer.parseInt(e.substring(1));
    if (squareVals[rCor][cCor] != -1) {
      reveal(rCor, cCor);
    } else {
      playState = -1;
    }
  }

  public void reveal(int cCor, int rCor) {
    if (squareVals[cCor][rCor] != -1) {
      if (!(isRevealed(cCor, rCor))) {
          squares[cCor][rCor] = Integer.toString(squareVals[cCor][rCor]);
      }
      if (squareVals[cCor][rCor] == 0) {
        for (int p = cCor-1; p<cCor+2; p++) {
          for (int q=rCor-1; q<rCor+2; q++) {
    	      if (p != rCor || q != cCor) {
    		if (!(isRevealed(p,q))) reveal(p,q);
    	      }
    	  }
        }
      }
    }
  }

  public boolean isRevealed(int cCor, int rCor) {
    return !(squares[cCor][rCor].equals("█") || squares[cCor][rCor].equals("⚑"));
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
    Minesweeper you;
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

    if (r > 0 && c > 0 && r <= 99 && c <= 35 && m >= 0 && m <= r*c) {
      you = new Minesweeper(r, c, m);
    } else {
      you = new Minesweeper(10, 10, 20);
    }


    while(you.playState == 0) {
      you.printBoard();
      System.out.print("your move: ");
      input = response.nextLine();
      try {
        you.processInput(input);
      } catch (Exception e) {

      }
      you.checkWin();
    }




  }
}

// in case we need: ⚑
