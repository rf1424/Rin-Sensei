import java.util.*;

public class Woo{
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";

  public static void main(String[] args) {
    Scanner response = new Scanner(System.in);
    int r, c, m;
    String input;

    //display start page/description
    System.out.println();
    System.out.println("======================");
    System.out.println("WELCOME TO MINESWEEPER");
    System.out.println("======================");
    String desc =
    "The classic version of the game consists of a board of " +
    "hidden squares and randomly placed mines underneath some squares.\n" +
    "The player can choose to select a square to reveal," +
    " upon which the board will either reveal that square \n" +
    "by showing the total number of mines directly or diagonally " +
    "adjacent to it, or the player loses because they selected a mine. \n" +
    "The goal is to reveal the entire board without selecting a mine. " +
    "A player can also select a square to flag it as a mine.";
    String desc2 =
    "WASD(Arrow Keys): One square of the board will be highlighted in red/blue,\n" +
    "which the player can control with WASD and enter 'e' or 'f' to reveal or flag.\n" +
    "Without WASD: Column/row indices (alphabet/number) will be printed on the side\n" +
    "of the board, and the player can input a certain code for the move they want to make.";
    System.out.print(ANSI_BLUE);
    System.out.println(desc);
    System.out.println();
    System.out.println(desc2);
    System.out.println();
    System.out.print(ANSI_RESET);

    //choose board
    System.out.println("Choose your board size.");
    System.out.println();
    System.out.print("Do you want the default board? (y/n): ");
    input = response.nextLine();
    if (input.equals("y")) {
      //set to default board
      r = 10;
      c = 10;
      m = 16;
      System.out.println();
    }
    //set your own board
    else if (input.equals("n")) {

      System.out.print("Choose length: ");
      input = response.nextLine();
      try {
        r = Integer.parseInt(input);
      } catch (Exception e) {
        System.out.println("Invalid input. Length set to 10.");
        r = 10;
      }

      System.out.print("Choose width: ");
      input = response.nextLine();
      try {
        c = Integer.parseInt(input);
      } catch (Exception e) {
        System.out.println("Invalid input. Width set to 10.");
        c = 10;
      }

      System.out.print("Number of mines: ");
      input = response.nextLine();
      try {
        m = Integer.parseInt(input);
      } catch (Exception e) {
        System.out.println("Invalid input. Random number of mines.");
        m = (int)(Math.random()*(r*c/6.0));
      }
    System.out.println();
  } else {//neither y or n. Set to default board.
    r = 10;
    c = 10;
    m = 16;
    System.out.println("There was a problem with your input." +
                       " You'll get the default board.");
    System.out.println();
  }


    //make an instance of Minesweeper
    Minesweeper you;

    if (r >= 1 && c >= 1 && r <= 99 && c <=49 && m >= 0 && m <= r*c) {
      you = new Minesweeper(r, c, m);
    } else {
      System.out.println("There was a problem with your input." +
                         " You'll get the default board.");
      you = new Minesweeper(10, 10, 16);
    }


    you.svC = 1;
    you.svR = 1;

    System.out.print("Do you want to use your arrow keys? (y/n): ");
    input = response.nextLine();
    if (input.toLowerCase().equals("y")) {
      you.playMode = 0;
      System.out.println();
      you.printInstr(0);
    } else if (input.toLowerCase().equals("n")) {
      you.playMode = 1;
      System.out.println();
      you.printInstr(1);
    } else {
      System.out.println("I'll take that as a no");
      you.playMode = 1;
      you.printInstr(1);
    }

    if (you.playMode == 1) {
      while(you.playState == 0) {
        you.printBoardI();
        System.out.print("your move:");
        input = response.nextLine();
        try {
          you.processInputI(input);
        } catch (Exception e) {
            System.out.println("INVALID MOVE. Type 'p' for play guide.");
            System.out.println();
        }
        //print help guide
        if (input.equals("p")) {
          you.printInstr(1);
        }

        if (you.playState == 0) {
          you.checkWin();
        } else {}
      }
    } else {
      while(you.playState == 0) {
        you.printBoardC();
        System.out.println("type 'p' for play guide.");
        System.out.print("your move:");
        input = response.nextLine();

        //print play guide
        if (input.equals("p")){
          you.printInstr(0);
        }

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
