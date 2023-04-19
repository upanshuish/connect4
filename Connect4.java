import java.util.HashMap;
import java.util.Scanner;

public class Connect4 {

    static int c1=0;
    static int c2=0;
    public static void board(int [][]grid){
        System.out.println("1 | 2 | 3 | 4 | 5 | 6 | 7 |");
        System.out.println("--|---|---|---|---|---|---|");
        for(int i=0; i<6; i++){
            for(int j=0; j<7; j++){
                System.out.print(grid[i][j]+" | ");
            } 
            System.out.println();         
        }
    }
    static Scanner sc=new Scanner(System.in);
    public static void p1(int [][]grid){
        System.out.print("P1: ");
        int column=sc.nextInt();
        for(int i=5; i>=0; i--){
            if(grid[i][column-1]==0){
                grid[i][column-1]=1; c1++;
                break;
            }
            else if(grid[0][column-1]!=0) p1(grid);
        }
    }
    
    public static void p2(int [][]grid){
        System.out.print("P2: ");
        int column=sc.nextInt();
        for(int i=5; i>=0; i--){
            if(grid[i][column-1]==0){
                grid[i][column-1]=2; c2++;
                break;
            }
            else if(grid[0][column-1]!=0) p2(grid);
        }
    }

    public static boolean checkWin(int sum){
        if(sum==4){
            System.out.println("\n****P1 WON****");
            return true;
        }
        if(sum==-4){
            System.out.println("\n****P2 WON****");
            return true;
        }
        return false;
    }

    static HashMap<Integer, Integer> map=new HashMap<>();
    public static boolean checkHV(int [][]grid){
        map.put(2,-1);
        map.put(1,1);
        map.put(0, 0);
        boolean b=false;
        for(int i=5; i>=0; i--){
            int j=6, l=6;
            int sum=0;
            while(j>=0){
                if(j>=l-3){
                    sum=sum+map.get(grid[i][j]);
                    if(checkWin(sum)){
                        b=true;
                        break;
                    }
                    j--;
                }
                else{
                    l--; j=j+3; sum=0;
                }
            }
            if(b) break;
        }
        for(int i=6; i>=0; i--){
            int j=5, l=5;
            int sum=0;
            while(j>=0){
                if(j>=l-3){
                    sum=sum+map.get(grid[j][i]);
                    if(checkWin(sum)){
                        b=true;
                        break;
                    }
                    j--;
                }
                else{
                    j=j+3; l--; sum=0;
                }
            }
            if(b) break;
        }
        return b;
    }
    
    public static boolean checkDiagonally(int [][]grid){
        boolean b=false;
        map.put(2,-1);
        map.put(1,1);
        map.put(0, 0);
        for(int i=3; i<=6; i++){
            int j=5;
            int sum=0;
            int k=i, l=i;
            while(j>=0 && k>=0){
                if(k>=l-3){
                    sum=sum+map.get(grid[j][k]);
                    if(checkWin(sum)){
                        b=true;
                        break;
                    }          
                    j--; k--;
                }
                else{
                    k=k+3; j=j+3; l--; sum=0;
                }
            }
            if(b) break;
        }
        for(int i=4; i>=3; i--){
            int j=6;
            int k=i, l=i;
            int sum=0;
            while(k>=0){
                if(k>=l-3){
                    sum=sum+map.get(grid[k][j]);
                    if(checkWin(sum)){
                        b=true;
                        break;
                    }           
                    j--; k--;
                }
                else{
                    k=k+3; j=j+3; l--; sum=0;
                }
            }
            if(b) break;
        }
        for(int i=3; i>=0; i--){
            int k=i, l=i;
            int j=5;
            int sum=0;
            while(j>=0 && k<=6){
                if(k<=l+3){
                    sum=sum+map.get(grid[j][k]);
                    if(checkWin(sum)){
                        b=true;
                        break;
                    } 
                    j--; k++;
                }
                else{
                    k=k-3; j=j+3; l++; sum=0;
                }
            }
            if(b) break;
        }
        for(int i=4; i>=3; i--){
            int j=0;
            int k=i, l=i;
            int sum=0;
            while(k>=0){
                if(k>=l-3){
                    sum=sum+map.get(grid[k][j]);
                    if(checkWin(sum)){
                        b=true;
                        break;
                    } 
                    j++; k--;
                }
                else{
                    k=k+3; j=j-3; l--; sum=0;        
                }
            }
            if(b) break;
        }
        return b;
    }
    
    public static void main(String[] args) {
        System.out.println("\n:::::::::::::::::::::::::::::::::::::::::::::::::::::::Connect4:::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
        
        int [][]grid=new int [6][7];

        for(int i=1; i<=42; i++){

            p1(grid);
            board(grid);
            if(checkHV(grid)) break;
            if(checkDiagonally(grid)) break;
            System.out.println();
            p2(grid);
            board(grid);
            if(checkHV(grid)) break;
            if(checkDiagonally(grid)) break;
            System.out.println();

        }
        
        System.out.println(">> Score of P1: "+c1);
        System.out.println(">> Score of P2: "+c2);
        System.out.println();

        sc.close();

    }
}
