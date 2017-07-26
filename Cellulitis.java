/**
 * Cellulitis
 * by <<J. JIANG>>
 * and <<K. GONG>>
 */
import java.util.*;
public class Cellulitis {
    boolean[] currentGeneration;
    boolean[] nextGeneration;
    Scanner input = new Scanner(System.in);
    String rule;
    int G;
    int L; 
    
    public void readGeneral() {
        //TODO read the general input (up to the initial configuration)
      rule=input.next();
      L=input.nextInt();
      G=input.nextInt();
      currentGeneration = new boolean[L+2];
      nextGeneration = new boolean[L+2];
      for(int i=0; i<L+2; i++){
        currentGeneration[i] = false;
      }
        //END TODO
    }


    public void readInitial() {
        //TODO read the initial configuration (build the first currentGeneration)
     String Initial = input.next();
     if(Initial.equals("init_start")){
       while(input.hasNextInt()){
         int init=input.nextInt();
         if (init<=L&&init>0){
         currentGeneration[init] = true;
         }
       }
     }
        //END TODO
    }


    public void readRules() {
        //TODO read the ruleset (only implement in case of an universal automaton)

        //END TODO
    }

    boolean newCellValueByA(int k) {
        // TODO return the value {true, false} of cell number k
        // for the next generation according to the rules of A
      if(currentGeneration[k] == true){
        if((currentGeneration[k-1]==true&&currentGeneration[k+1]==false)||(currentGeneration[k-1]==false&&currentGeneration[k+1]==true)){
          return true;
        }else{
          return false;
        }
      }else{
        if(currentGeneration[k-1]==false&&currentGeneration[k+1]==false){
          return false;
        }else{
          return true;
        }
      }
        //END TODO
    }

    boolean newCellValueByB(int k) {
        // TODO return the value {true, false} of cell number k
        // for the next generation according to the rules of B
      if(currentGeneration[k] == true){
        if(currentGeneration[k+1]==false){
          return true;
        }else{
          return false;
        }
      }else{
        if((currentGeneration[k-1]==true&&currentGeneration[k+1]==false)||(currentGeneration[k-1]==false&&currentGeneration[k+1]==true)){
          return true;
        }else{
          return false;
        }
      }
        //END TODO
    }

    boolean newCellValueByRules(int k){
        // TODO return the value {true, false} of cell number k
        // for the next generation according to the universal automatons rules

        return false;

        //END TODO
    }


    public void draw() {
        //TODO draw the current state of the automaton; in other words: print currentGeneration
      for(int i=1; i<L+1; i++){
        if(currentGeneration[i]==true){
        System.out.print("*");
        }else{
          System.out.print(" ");
        }
      }  
      System.out.println();
        //END TODO
    }


    public void computeNextGeneration() {
        //TODO compute the nextGeneration and update the currentGeneration.
      if(rule.equals("A")){
        for(int i=1; i<L+1;i++){
          nextGeneration[i]=newCellValueByA(i);
        }
      }else{
        for(int i=1; i<L+1;i++){
          nextGeneration[i]=newCellValueByB(i);
        }
      }
      for(int i=1;i<L+1;i++){
        currentGeneration[i]=nextGeneration[i];
      }
        //END TODO
    }

    public void run() {
        //TODO implement the procedure for the assignment
      readGeneral();
      readInitial();
      draw();
      for(int i=1; i<G;i++){
        computeNextGeneration();
        draw();
      }
        //END TODO
    }

    //TODO more methods if nescessairy

    //END TODO


    public static void main(String[] args) {
        (new Cellulitis()).run();
    }
}