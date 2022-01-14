import java.util.*;

public class Minesweeper {
  String[][] squares;
  int[][] squareVals;
  int length, width;
  final String letters = "ABCDEFGHIJKLMNOPQRSTUVXYZ ";

  public Minesweeper() {
    length = 9;
    width = 7;
    squares = new String[length][width];
    squareVals = new int[length][width];
    for (int i=0; i<length; i++) {
      for (int j=0; j<width; j++) {
        squares[i][j] = "#";
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
    for (int i=0; i<length; i++) {
      System.out.print(i+1);
      System.out.print(" ");
      for (int j=0; j<width; j++) {
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
