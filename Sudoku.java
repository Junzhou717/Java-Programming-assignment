// Sep 28th
// J. JIANG & K. GONG
// Sudoku


class Sudoku {
    private static final int SIZE = 9;     // size of the grid e.g. 9 -> 9x9
    private static final int DMAX = 9;     // max digit to be filled in e.g. 9
    private static final int BOXSIZE = 3;  // size of the boxes e.g. 3 -> 3x3
    int[][] grid = new int[][] {
        { 0, 6, 0,  0, 0, 1,  0, 9, 4 },
        { 3, 0, 0,  0, 0, 7,  1, 0, 0 },
        { 0, 0, 0,  0, 9, 0,  0, 0, 0 },
        { 7, 0, 6,  5, 0, 0,  2, 0, 9 },
        { 0, 3, 0,  0, 2, 0,  0, 6, 0 },
        { 9, 0, 2,  0, 0, 6,  3, 0, 1 },
        { 0, 0, 0,  0, 5, 0,  0, 0, 0 },
        { 0, 0, 7,  3, 0, 0,  0, 0, 2 },
        { 4, 1, 0,  7, 0, 0,  0, 8, 0 },
    };
    int[][] temp = new int[9][9]; 
      
    int[] LastEmpty = new int[2];
    int solutionnr = 0; //solution counter

    void run() {
        //TODO starts the solving process.
       solve();
       if(solutionnr==1){
         print();
       }else{
         System.out.println(solutionnr);
       }
      
        //END TODO
    }

    boolean givesConflict(int r, int  c, int d) {
        //TODO is there a conflict when we fill in d at position r,c?
      if(rowConflict(r,d)&&colConflict(c,d)&&boxConflict(r,c,d)){
        return true;
      }else{
        return false;
      }
        //END TODO

    }

    boolean rowConflict(int r, int d) {
        //TODO is there a conflict in row r when we fill in d?
       int m=0;
       for(int i=0; i<SIZE; i++){
         if(grid[r][i]==d){
           m++;
         }
       }
       if(m==0){
         return true;
       }else{
         return false;
       }
        //END TODO
    }

    boolean colConflict(int c, int d) {
        //TODO is there a conflict in column c when we fill in d?
       int m=0;
       for(int i=0; i<SIZE; i++){
         if(grid[i][c]==d){
           m++;
         }
       }
       if(m==0){
         return true;
       }else{
         return false;
       }
        //END TODO
    }

    boolean boxConflict(int rr, int cc, int d) {
        //TODO is there a conflict when we fill in d at position in the box of rr,cc?
        int m=0;
        int k=rr/3;
        int l=cc/3;
        for(int i=k*3;i<k*3+3;i++){
          for(int j=l*3;j<l*3+3;j++){
            if(grid[i][j]==d){
              m++;
            }
          }
        }
       if(m==0){
         return true;
       }else{
         return false;
       }

        //END TODO
    }


    int[] findEmptySquare() {
        //TODO return the next empty square (See assignment).
      for(int i=0;i<SIZE;i++){
        for(int j=0;j<SIZE;j++){
          if(grid[i][j]==0){
            LastEmpty[0]=i;
            LastEmpty[1]=j;
            return LastEmpty;
          }
        }
      }
      return null;
        //END TODO
    }

    void solve() {
        //TODO see (4)
        
        int[] a = findEmptySquare();
        if(a!=null){
         int r=a[0];
        int c=a[1];
        for(int i = 1; i <= DMAX; i++) {
            if(givesConflict(r, c, i)) {
                grid[r][c] = i;
                solve();
                grid[r][c] = 0;
            }
        }
        }else{
          solutionnr++;
          for(int m=0;m<9;m++){
            for(int n=0;n<9;n++){
              temp[m][n]=grid[m][n];
            }
          }
           
          
        }
          
     //END TODO
    }

    // print the grid, 0s are printed as spaces
    void print() {
        //TODO print the grid, printing spaces instead of 0s.
      System.out.print("+");
      for(int i = 0; i<17;i++){
        System.out.print("-");
      }
      System.out.println("+");
      for(int r = 0; r<SIZE; r++){
        if(r>0&&(r%BOXSIZE)==0){
          for(int i=0; i<19; i++){
            System.out.print("-");
          }
          System.out.println();
        }
        for(int c =0; c<SIZE; c++){
          if((c%BOXSIZE)==0){
            if(temp[r][c]!=0){
              System.out.print("|"+temp[r][c]);
            }else{
              System.out.print("|"+" ");
            }
          }else{
            if(temp[r][c]!=0){
              System.out.print(" "+temp[r][c]);
            }else{
              System.out.print(" "+" ");
            }
          }
        }
        System.out.println("|");
      }
      System.out.print("+");
      for(int i = 0; i<17;i++){
        System.out.print("-");
      }
      System.out.println("+");
        //END TODO
    }

    //TODO extra methods



    //END TODO


    public static void main(String[] args) {
        new Sudoku().run();
    }
}