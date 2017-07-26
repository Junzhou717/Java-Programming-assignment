import java.util.Scanner;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


class GameOfLife implements ActionListener{
  
  Cell[][] grid;
  String birthFilename = "birth.txt";
  Scanner sc;
  Timer timer;
  
    GameOfLife(){
    JFrame frame = new JFrame("Game Of Life");
    JPanel panel = new JPanel();
    timer=new Timer(500,this);
    
    JLabel extension = new JLabel("Extensions:   1, 3");
    extension.setBackground(Color.RED);
    extension.setOpaque(true);
    frame.add(extension,BorderLayout.NORTH);
    frame.add(panel);
    JPanel bpanel = new JPanel();
    JButton b1 = new JButton("Start");
    b1.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
    }
});
    JButton b2 = new JButton("End"); 
    b2.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.stop();
    }
});
    bpanel.add(b1);
    bpanel.add(b2);
    frame.add(bpanel,BorderLayout.SOUTH);
    
    readInitial();
    GridLayout layout = new GridLayout(grid.length-2,grid[0].length-2);
    panel.setLayout(layout);
    for(int i=1;i<grid.length-1;i++){
      for(int j=1;j<grid[0].length-1;j++){
        panel.add(grid[i][j]);
        grid[i][j].update();
      }
    }
    
      
    frame.setSize(300, 350);
    frame.setLocation(200, 200);
    frame.setVisible(true);
  }
  
  public void actionPerformed(ActionEvent e) {
    calculateNumNeighbors();
    nextGeneration();
    for(int i=1;i<grid.length-1;i++){
      for(int j=1;j<grid[0].length-1;j++){
        grid[i][j].update();
      }
    }
    
  }
    
  
  void calculateNumNeighbors(){
    for(int i=1;i<grid.length-1;i++){
      for(int j=1;j<grid[0].length-1;j++){
        int numN = 0;
        for(int k = i-1;k<i+2;k++){
          for(int l=j-1;l<j+2;l++){
            if(!(k==i&&l==j)){
              if(grid[k][l].isAlive()){
                numN++;
              }
            }
          }
        }
        grid[i][j].setNumNeighbors(numN);
      }
    }
    // calculates the number of living neighbors of each cell and sets numNeighbors; it will not update the dead/alive state of the cells.
  }
  
  void readInitial(){
    try{    
     Scanner sc = new Scanner(new File(birthFilename));
     int r = sc.nextInt();
     int c = sc.nextInt();
     grid = new Cell[r+2][c+2];
     for(int i=0;i<r+2;i++){
       for(int j=0;j<c+2;j++){
         grid[i][j] = new Cell();
       }
     }
     for(int i=1;i<r+1;i++){
       for(int j=1;j<c+1;j++){
         String s = sc.next();
         if(s.equals("*")){
           grid[i][j].setAlive(true);
         }
       }
     }
     
     }catch(FileNotFoundException e){
      System.out.println("Could not open file due to");
      System.out.println(e);
      int r = 15;
      int c = 15;
      grid = new Cell[r+2][c+2];
     for(int i=0;i<r+2;i++){
       for(int j=0;j<c+2;j++){
         grid[i][j] = new Cell();
       }
     }
     for(int m=1;m<9;m++){
           grid[8][m].setAlive(true);
         }
      
    }
    
   //creates the grid and reads the initial generation from the file with the name stored in cd birthFilename.
  }
  
  void nextGeneration(){
    for(int i=1;i<grid.length;i++){
      for(int j=1;j<grid[0].length;j++){
        if((grid[i][j].getNumNeighbors()<2) || (grid[i][j].getNumNeighbors()>3)){
          grid[i][j].setAlive(false);
        }else if(grid[i][j].getNumNeighbors()==3){
          grid[i][j].setAlive(true);
        }
      }
    }    
    //updates the grid to the next generation,using the values ofnum Neighbors in the cells.
  }
  
  
  public static void main(String[] args){
    new GameOfLife();
  }
  
}

class Cell extends JLabel{
  
  boolean alive = false;
  int numNeighbors;
  
  void setAlive(boolean state){
  this.alive = state;
    // changes the alive/dead state of the cell
  }
  
  boolean isAlive(){
    return this.alive;
    // returns the alive/dead state of the cell
  }
  
  void setNumNeighbors(int n){
    this.numNeighbors = n;
    //sets numNeighbors of the cell to n
  }
  
  int getNumNeighbors(){
    return this.numNeighbors;
  }
  
  void update(){
    if(alive==true){
      setBackground(Color.WHITE);      
    }else{
      Color DarkSlateBlue = new Color(72, 61 ,139);
      setBackground(DarkSlateBlue);
    }
    setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
    setOpaque(true);
    
    // takes the cell to next generation according to alive and numNeighbors
  }
  
}

