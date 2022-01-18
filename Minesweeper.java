import java.util.*;

public class Minesweeper {
  String[][] squares;
  int[][] squareVals;
  int length, width;
  final String letters = "ABCDEFGHIJKLMNOPQRSTUVXYZ ";

  public Minesweeper() {
    length = 9;
    width = 7;
    squares = new String[length+2][width+2];
    squareVals = new int[length+2][width+2];
    for (int i=1; i<length+1; i++) {
      for (int j=1; j<width+1; j++) {
        squares[i][j] = "█";
        squareVals[i][j] = 0;
      }
    }
  }

  public void printBoard() {
    System.out.print("   ");
    for (int i=0; i<width; i++) {
      System.out.print(letters.substring(i, i+1) + " ");
    }
    System.out.println();
    for (int i=1; i<length+1; i++) {
      System.out.print(i);
      System.out.print(" ");
      for (int j=1; j<width+1; j++) {
        System.out.print("|" + squares[i][j]);
      }
      System.out.println("|");
    }
  }

  public static void main(String[] args) {
    Minesweeper me = new Minesweeper();
    Scanner response = new Scanner(System.in);

    System.out.println("welcome to minesweeper");
    System.out.println();

    me.printBoard();

    //System.out.println("choose length");



  }
}

// in case we need: ⚑
