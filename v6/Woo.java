import java.util.*;

public class Woo{
  
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


  }//end of main

}//end of class
