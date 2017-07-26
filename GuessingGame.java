//15 Sep 2016
//J.JIANG & K.GONG
//Guessing game

import java.util.*;

public class GuessingGame {
    Scanner inputScanner = new Scanner(System.in);
    Random randomGenerator = new Random();

    public void run() {
        //TODO: IMPLEMENTATION
      int number = randomGenerator.nextInt(100);
      System.out.println("Start guessing!");
      int GuessNum[];
      String line[][];
      int i,j,k=0;
      
      GuessNum = new int[7];
      for(i=0; i<7; i++){
        GuessNum[i] = inputScanner.nextInt();
        if(GuessNum[i] < number){
          System.out.println("higher");
        }else if(GuessNum[i] > number){
          System.out.println("lower");
        }else if(GuessNum[i] == number){
          System.out.println("Good guess! You won.");
          System.out.println((i+1)+" guesses:");
          k=i+1;
          i=10;
        }
      }
      
      if(i == 7){
      System.out.println("No more guesses, you lost.");
      System.out.println("7 guesses:");
      k=i;;
      }
      
      line = new String[k][100];
      for(i=0; i<line.length; i++){
        for(j=0; j<100; j++){
          line[i][j] = ".";
        }
        line[i][number]="|";
        line[i][GuessNum[i]]="X";
        for(j=0; j<100; j++){
          System.out.print(line[i][j]);
        }
        System.out.println();
      }
   
        //END TODO: IMPLEMENTATION
    }

    public static void main(String[] args) {
        (new GuessingGame()).run();
    }
}