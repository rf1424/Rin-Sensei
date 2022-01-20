import java.util.*;

public class Woo{

  public static void main(String[] args) {
    Scanner response = new Scanner(System.in);
    String input;
    int r, c, m;
    Minesweeper you;

    System.out.println("welcome to minesweeper");
    System.out.println();

    //choose board
    System.out.println("Default Board: Enter 1");
    System.out.println("Choose your own Board: Enter 2");
    input = response.nextLine();
    if (!(input.equals("2"))) {
      //set to default board
      you = new Minesweeper(10, 10, 20);
      System.out.println();
      System.out.println("set to default board: 10x10");
    } else {
      //choose your own board
      System.out.print("choose length: ");
      input = response.nextLine();
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



      if (r >= 1 && c >= 1 && m >= 0 && m <= r*c) {
        you = new Minesweeper(r, c, m);
      } else {
        you = new Minesweeper(10, 10, 20);
        System.out.println();
        System.out.println("set to default board: 10x10");
      }
    }

    //game start
    while(you.playState == 0) {
      you.printBoard();
      System.out.println();
      System.out.print("your move:");
      input = response.nextLine();
      try {
        you.processInput(input);
      } catch (Exception e) {
        System.out.println("Invalid move:" +
                           "Your move should be an alaphabet/symbol\n" +
                           "followed by a number on the board. Ex: A1");
      }
      you.checkWin();
    }

    if (you.playState == 1) {
      you.win(); //!!
    } else {
      you.lose(); //smh
    }

    //you.printBoard();
  }//end of main

}//end of class
