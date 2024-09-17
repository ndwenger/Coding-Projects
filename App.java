import java.util.Scanner;
import java.io.*;

public class App {
    private static final int GRID_SIZE = 9;
    public static String rowB = "";
    public static Scanner sc;
    private static String[][] board = {
        {"0", "0", "0", "0", "0", "0", "0", "0", "0"},
        {"0", "0", "0", "0", "0", "0", "0", "0", "0"},
        {"0", "0", "0", "0", "0", "0", "0", "0", "0"},
        {"0", "0", "0", "0", "0", "0", "0", "0", "0"},
        {"0", "0", "0", "0", "0", "0", "0", "0", "0"},
        {"0", "0", "0", "0", "0", "0", "0", "0", "0"},
        {"0", "0", "0", "0", "0", "0", "0", "0", "0"},
        {"0", "0", "0", "0", "0", "0", "0", "0", "0"},
        {"0", "0", "0", "0", "0", "0", "0", "0", "0"},
    };
    
    //private static Scanner scan = new Scanner(System.in);

    public static void buildBoard(){

        for(int i = 0; i < GRID_SIZE; i++){
            rowB = sc.nextLine();
            for(int j = 0; j < GRID_SIZE; j++){
                board[i][j] = rowB.substring(j, j + 1);
            }
            printBoard(board);
        }
    }

    public static int toInt(String number){
        int num = 0;
        String tempnum = "";
        String k = "";
        for(int i = 0; i < number.length(); i++){
            if(number.equals("0")){
                num = 0;
            } else if(number.equals("1")){
                num = 1;
            }else if(number.equals("2")){
                num = 2;
            }else if(number.equals("3")){
                num = 3;
            }else if(number.equals("4")){
                num = 4;
            }else if(number.equals("5")){
                num = 5;
            }else if(number.equals("6")){
                num = 6;
            }else if(number.equals("7")){
                num = 7;
            }else if(number.equals("8")){
                num = 8;
            }else if(number.equals("9")){
                num = 9;
            }else{
                System.out.print("Can't find number");
            }
            
            k = String.valueOf(tempnum) + String.valueOf(num);
            tempnum = k;

        }
        int kint = Integer.valueOf(k);
        return kint;
    }
    public static String toString(int number){
        int temprow = 0;
        int row = number;
        String str = "";
        for(int j = 0; j < GRID_SIZE; j++){
            if(j == 0){
                temprow = row / 100000000;
            }else if(j == 1){
                temprow = row / 10000000;
                temprow %= 10;
            }else if(j == 2){
                temprow = row / 1000000;
                temprow %= 10;
            }else if(j == 3){
                temprow = row / 100000;
                temprow %= 10;
            }else if(j == 4){
                temprow = row / 10000;
                temprow %= 10;
            }else if(j == 5){
                temprow = row / 1000;
                temprow %= 10;
            }else if(j == 6){
                temprow = row / 100;
                temprow %= 10;
            }else if(j == 7){
                temprow = row / 10;
                temprow %= 10;
            }else if(j == 8){
                temprow = row;
                temprow %= 10;
            }
        }
        if(temprow == 0){
            str += "0";
        }else if(temprow == 1){
            str += "1";
        }else if(temprow == 2){
            str += "2";
        }else if(temprow == 3){
            str += "3";
        }else if(temprow == 4){
            str += "4";
        }else if(temprow == 5){
            str += "5";
        }else if(temprow == 6){
            str += "6";
        }else if(temprow == 7){
            str += "7";
        }else if(temprow == 8){
            str += "8";
        }else{
            str += "9";
        }

        return str;
    }
    public static void main(String[] args) throws IOException{

        /*int[][] b = {
            {7, 0, 2, 0, 5, 0, 6, 0, 0},
            {0, 0, 0, 0, 0, 3, 0, 0, 0},
            {1, 0, 0, 0, 0, 9, 5, 0, 0},
            {8, 0, 0, 0, 0, 0, 0, 9, 0},
            {0, 4, 3, 0, 0, 0, 7, 5, 0},
            {0, 9, 0, 0, 0, 0, 0, 0, 8},
            {0, 0, 9, 7, 0, 0, 0, 0, 5},
            {0, 0, 0, 2, 0, 0, 0, 0, 0},
            {0, 0, 7, 0, 4, 0, 2, 0, 3}

        };*/

        
        try {
            
            sc = new Scanner(new File("testSudoku.txt"));
            buildBoard();
            System.out.println("File Found");
           
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } 
    
        System.out.println("Input a Valid Sudoku Puzzle(By Row)");

        System.out.println("Puzzle Scanned");

        printBoard(board);
        if(solveBoard(board)){
            System.out.println("Solved Successfully");
        }else{
            System.out.println("Cannot Solve");
        }
        printBoard(board);
    }
    
    public App(String[][] board){
        //int[][] board;
        /*for(int i = 0; i < GRID_SIZE; i++){
            for(int j = 0; j < GRID_SIZE; j++){
                board[i][j] = rowB.substring(j, j + 1);
            }
        }*/
    }

    private static boolean isNumberInRow(String[][] board, String number, int row){
        for(int i = 0; i < GRID_SIZE; i++){
            if(board[row][i].equals(number)){
                return true;
            }
        }
        return false;
    }
    private static boolean isNumberInColumn(String[][] board, String number, int column){
        for(int i = 0; i < GRID_SIZE; i++){
            if(board[i][column].equals(number)){
                return true;
            }
        }
        return false;
    }
    private static boolean isNumberInBox(String[][] board, String number, int row, int column){
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;
        for(int i = localBoxRow; i < localBoxRow + 3; i++){
            for(int j = localBoxColumn; j < localBoxColumn + 3; j++){
                if(board[i][j].equals(number)){
                    return true;
                }
            }
        }
        return false;
    }
    private static boolean isValidPlacement(String[][] board, String number, int column, int row){
        return !isNumberInRow(board, number, row) && !isNumberInColumn(board, number, column) && !isNumberInBox(board, number, row, column);
    }
    private static boolean solveBoard(String[][] board) {
        for(int row = 0; row < GRID_SIZE; row++){
            for(int column = 0; column < GRID_SIZE; column++){
                if(board[row][column].equals("0")){
                    for(int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++){
                        String numberToTrystr = toString(numberToTry);
                        if(isValidPlacement(board, numberToTrystr, column, row)){
                            board[row][column] = numberToTrystr;

                            if(solveBoard(board)){
                                return true;
                            }else{
                                board[row][column] = "0";
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static void printBoard(String[][] board) {
        for(int row = 0; row < GRID_SIZE; row++){
            if(row % 3 == 0 && row != 0){
                System.out.println("-----------");
            }
            for(int column = 0; column < GRID_SIZE; column++){
                if(column % 3 == 0 && column != 0){
                    System.out.print("|");
                }
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }
}

