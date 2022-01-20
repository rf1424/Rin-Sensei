import java.util.*;

public class Minesweeper {
  String[][] squares;
  int[][] squareVals;
  int rows, columns, mines;
  int playState;
  int playMode;
  int svR, svC;
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
  public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
  public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
  final String letters = "ABCDEFGHIJKLMNOPQRSTUVXYZ!@#$%^&*(){}|:<>?[]\',. ";


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

  public void processInputI(String e){
    int cCor = letters.indexOf(e.substring(0, 1)) + 1;
    int rCor;
    boolean wFlag = e.substring(e.length()-1).toLowerCase().equals("f");
    if (wFlag) {
      rCor = Integer.parseInt(e.substring(1, e.length()-1));
    } else {
      rCor = (Integer.parseInt(e.substring(1)));
    }
    if (squareVals[rCor][cCor] != -1 && !wFlag) {
      reveal(rCor, cCor);
    } else if (wFlag) {
      if (squares[rCor][cCor].equals("█")) {
        squares[rCor][cCor] = "⚑";
      } else if (squares[rCor][cCor].equals("⚑")){
        squares[rCor][cCor] = "█";
      } else {}
    } else {
      playState = -1;
    }
  }

  public void processInputC(String input) {
    for (int i=0; i < input.length(); i++) {
      String e = String.valueOf(input.charAt(i));
      if (e.toLowerCase().equals("w")) {
        if (svR > 1) {
          select(svR-1, svC);
        } else {}
      } else if (e.toLowerCase().equals("a")) {
        if (svC > 1) {
          select(svR, svC-1);
        } else {}
      } else if (e.toLowerCase().equals("s")) {
        if (svR < rows) {
          select(svR+1, svC);
        } else {}
      } else if (e.toLowerCase().equals("d")) {
        if (svC < columns) {
          select(svR, svC+1);
        }
      } else if (e.toLowerCase().equals("e")) {
        if (!(squareVals[svR][svC] == -1)) {
          reveal(svR, svC);
        } else {
          playState = -1;
        }
      } else if (e.toLowerCase().equals("f")) {
        if (squares[svR][svC].equals("█")) {
          squares[svR][svC] = "⚑";
        } else if (squares[svR][svC].equals("⚑")){
          squares[svR][svC] = "█";
        }
      }
    }
  }

  public void select(int rCor, int cCor) {
    svR = rCor;
    svC = cCor;
  }

  public void printBoardC() {
    for (int i=1; i<svR; i++) {
      for (int j=1; j<columns+1; j++) {
        System.out.print("|" + squares[i][j]);
      }
      System.out.println("|");
    }
    for (int i=1; i<svC; i++) {
      System.out.print("|" + squares[svR][i]);
    }
    System.out.print(ANSI_RED + "|");
    System.out.print(ANSI_CYAN_BACKGROUND + squares[svR][svC] + ANSI_RESET);
    System.out.print(ANSI_RED + "|" + ANSI_RESET);
    for (int i=svC+1; i<columns+1; i++) {
      System.out.print(squares[svR][i]+ "|");
    }
    System.out.println();
    for (int i=svR+1; i<rows+1; i++) {
      for (int j=1; j<columns+1; j++) {
        System.out.print("|" + squares[i][j]);
      }
      System.out.println("|");
    }
  }

  public void printBoardI() {
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

  public void printBoard(String bkgcolor) {
    for (int i=1; i<rows+1; i++) {
      for (int j=1; j<columns+1; j++) {
        if (!(isRevealed(i, j))) reveal(i, j);
      }
    }
    for (int i=1; i<rows+1; i++) {
      for (int j=1; j<columns+1; j++) {
        if (squareVals[i][j] != -1) {
          System.out.print(bkgcolor + "|" + squares[i][j] );
        } else {
          System.out.print(bkgcolor + "|☼");
        }
      }
      System.out.println("|" + ANSI_RESET);
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
    return !(squares[rCor][cCor].equals("█") || squares[rCor][cCor].equals("⚑"));
  }

  public void lose() {
    printBoard(ANSI_RED_BACKGROUND);
    System.out.println("bad");
  }

  public void checkWin() {
    int mCtr = 0;
    for (int i=1; i<rows+1; i++) {
      for (int j=1; j<columns+1; j++) {
        if (squares[i][j].equals("█") || squares[i][j].equals("⚑")) {
          mCtr++;
        }
      }
    }
    if (mCtr == mines) {
      playState = 1;
    }
  }

  public void win() {
    printBoard(ANSI_YELLOW_BACKGROUND);
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
      m = (int)(Math.random()*(r*c/3.0));
    }
    System.out.println();


    //game start
    Minesweeper you;

    if (r >= 1 && c >= 1 && r <= 99 && c <=49 && m >= 0 && m <= r*c) {
      you = new Minesweeper(r, c, m);
    } else {
      System.out.println("there was a problem with your input. you'll get the default board"); //smth like that
      you = new Minesweeper(10, 10, 20);
    }

    you.svC = 1;
    you.svR = 1;

    System.out.print("do you want to use your arrow keys? (y/n): ");
    input = response.nextLine();
    if (input.toLowerCase().equals("y")) {
      you.playMode = 0;
    } else if (input.toLowerCase().equals("n")) {
      you.playMode = 1;
    } else {
      System.out.println("i'll take that as a no");
      you.playMode = 1;
    }

    if (you.playMode == 1) {
      while(you.playState == 0) {
        you.printBoardI();
        System.out.print("your move:");
        input = response.nextLine();
        try {
          you.processInputI(input);
        } catch (Exception e) {
          System.out.println("Invalid move:" +
                           "Your move should be an alaphabet/symbol\n" +
                           "followed by a number on the board. Ex: A1");
        }
        if (you.playState == 0) {
          you.checkWin();
        } else {}
      }
    } else {
      while(you.playState == 0) {
        you.printBoardC();
        System.out.print("your move:");
        input = response.nextLine();
        try {
          you.processInputC(input);
        } catch (Exception e) {

        }
        if (you.playState == 0) {
          you.checkWin();
        } else {}
      }
    }


    if (you.playState == 1) {
      you.win(); //!!
    } else {
      you.lose(); //smh
    }


  }
}

// in case we need: ⚑
