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
        squareVals[i][j] = 9;
        squares[i][j] = "@_@";
      }
    }
    for (int i=1; i<rows+1; i++) {
      for (int j=1; j<columns+1; j++) {
        squares[i][j] = "█";
        squareVals[i][j] = 0;
      }
    }
    while(mines != m) {
      int rIndex = (int)(Math.random()*(rows)) + 1;
      int cIndex = (int)(Math.random()*(columns)) + 1;
      if (squareVals[rIndex][cIndex] != -1) {
        squareVals[rIndex][cIndex] = -1;
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

  public void processInput(String e){
    int cCor = letters.indexOf(e.substring(0, 1)) + 1;
    int rCor = (Integer.parseInt(e.substring(1)));
    if (squareVals[rCor][cCor] != -1) {
      reveal(rCor, cCor);
    } else {
      playState = -1;
    }
  }

  public void reveal(int rCor, int cCor) {
    if (squareVals[rCor][cCor] != -1) {
      if (squareVals[rCor][cCor] != 0) {
        squares[rCor][cCor] = Integer.toString(squareVals[rCor][cCor]);
      } else {
        squares[rCor][cCor] = " ";
        for (int i=rCor-1; i<rCor+2; i++) {
          for (int j=cCor-1; j<cCor+2; j++) {
            if (i != rCor || j != cCor) {
              if (!(isRevealed(i,j))) reveal(i, j);
            }
          }
        }
      }
    }
  }

  public boolean isRevealed(int rCor, int cCor) {
    return !(squares[rCor][cCor].equals("█"));
  }

  public void printBoard() {
    System.out.print("   ");
    for (int i=0; i<columns; i++) {
      System.out.print(" " + letters.substring(i, i+1));
    }
    System.out.println();
    for (int i=1; i<rows+1; i++) {
      System.out.print(i);
      if (Integer.toString(i).length() > 1) {
        System.out.print(" ");
      } else {
        System.out.print("  ");
      }
      for (int j=1; j<columns+1; j++) {
        System.out.print("|" + squares[i][j] );
      }
      System.out.println("|");
    }
  }

  public void lose() {
    System.out.println("bad");
  }

  public void checkWin() {
    int mCtr = 0;
    for (int i=1; i<rows+1; i++) {
      for (int j=1; j<columns+1; j++) {
        if (squares[i][j].equals("█")) {
          mCtr++;
        }
      }
    }
    if (mCtr == mines) {
      playState = 1;
    }
  }

  public void win() {
    printBoard();
    System.out.println("YOU WON GOOD JOB");
  }

  public static void main(String[] args) {
    Scanner response = new Scanner(System.in);
    int r, c, m;

    System.out.println("welcome to minesweeper");
    System.out.println();


    System.out.print("choose length: ");
    String input = response.nextLine();
    try {
      r = Integer.parseInt(input);
    } catch (Exception e) {
      r = 10;
    }

    System.out.print("choose width: ");
    input = response.nextLine();
    try {
      c = Integer.parseInt(input);
    } catch (Exception e) {
      c = 10;
    }

    System.out.print("number of mines: ");
    input = response.nextLine();
    try {
      m = Integer.parseInt(input);
    } catch (Exception e) {
      m = 20;
    }
    System.out.println();

    //game start
    Minesweeper you;

    if (r >= 1 && c >= 1 && m >= 0 && m <= r*c) {
      you = new Minesweeper(r, c, m);
    } else {
      you = new Minesweeper(10, 10, 20);
    }


    while(you.playState == 0) {
      you.printBoard();
      System.out.print("your move:");
      input = response.nextLine();
      try {
        you.processInput(input);
      } catch (Exception e) {

      }
      you.checkWin();
    }

    if (you.playState == 1) {
      you.win(); //!!
    } else {
      you.lose(); //smh
    }

    //you.printBoard();


  }
}

// in case we need: ⚑
