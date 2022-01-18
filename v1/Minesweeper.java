import java.util.*;

public class Minesweeper {
  String[][] squares;
  int[][] squareVals;
  int rows, columns, mines;
  final String letters = "ABCDEFGHIJKLMNOPQRSTUVXYZ!@#$%^&*() ";

  public Minesweeper() {

  }

  public Minesweeper(int r, int c, int m) {
    this();
    rows = r;
    columns = c;
    squares = new String[rows+2][columns+2];
    squareVals = new int[rows+2][columns+2];
    for (int i=0; i<rows+2; i++) {
      for (int j=0; j<columns+2; j++) {
        squares[i][j] = "sad extreme";
        squaresVals[i][j] = 9;
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
      for (j=1; j<columns+1; j++) {
        if (squareVals[i][j] != -1) {
          for (int p = i-1; p<i+2; p++) {
            
          }
        }
      }
    }

  }

  public void printBoard() {
    System.out.print("   ");
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
    int input = response.nextInt();
    r = input;
    System.out.println();

    System.out.print("choose columns: ");
    input = response.nextInt();
    c = input;

    System.out.print("choose mines: ");
    input = response.nextInt();
    m = input;

    me = new Minesweeper(r, c, m);

    me.printBoard();




  }
}

// in case we need: ⚑
