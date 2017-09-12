/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursionpractice;

/**
 *
 * @author 14038690
 */

public class RecursionPractice {
    /**
     * @param args the command line arguments
     * pre: n >= 0
     * post: the value returned is y^n
     */
    public static double power(double y, int n){
        // pre: n >= 0
        // post: the value returned is y^n
        if(n>0){
            return y * power(y, n-1); // recursive case
        }
        
        else if(n==0){ //base case
            return 1;
        }
        return 0;
    }
   
    public static int chessBoardSquares(int n){
        if(n==1){ return 1;}
        else return chessBoardSquares(n-1)+n-1+n-1+1;
    }
    
    public static boolean arrayMatch(int n, int[] a, int[] b) {
	assert 0 <= n && n <= a.length && 0 <= n && n <= b.length;
        if(n==0){return true;}
        if(n>0){
            if(a[n-1]==b[n-1]){
                return(arrayMatch(n-1,a,b));
            }
        }
        return false;
    }
    
    public static int triangleNumber(int n){
        assert n >= 1;
        if(n==1){
            return 1;
        }
        else return n + triangleNumber(n-1);
    }
    
    public static void printFirstN(String[] a, int n) {
		assert 0 <= n && n <= a.length;
        if(n==1){
            System.out.println(a[n-1]);
        }
        else if(n>1){
            System.out.println(a[n-1]);
            printFirstN(a,n-1);
        }
    }
    
    public static void main(String[] args) {

        ////////////////// Below is print out code for Exercise 2 ///////////////////

        System.out.println("2 to the power 5 is " + power(2, 5));
        System.out.println("4 to the power 0 is " + power(4, 0));
        System.out.println("0 to the power 6 is " + power(0, 6));
        System.out.println("-7 to the power 3 is " + power(-7, 3));
        System.out.println("0.5 to the power 2 is " + power(0.5, 2) + "\n");
        
        ////////////////// Below is print out code for Exercise 3 ///////////////////

        for (int i = 1; i <= 8; i++) {
            System.out.println("chessBoardSquares(i) for i=" + i
                    + " is " + chessBoardSquares(i));
        }
        System.out.println("\n");
        
        ////////////////// Below is print out code for Exercise 4 ///////////////////

        int[] a = {1, 2, 3, 4, 5, 6};
        int[] b = {1, 2, 4, 8, 16};

        System.out.println("a: " + a[0] + "," + a[1] + "," + a[2] + "," + a[3] + "," + a[4] + "," + a[5]);
        System.out.println("b: " + b[0] + "," + b[1] + "," + b[2] + "," + b[3] + "," + b[4] + "\n");


        for (int i = 1; i <= 5; i++) {
            System.out.println("arrayMatch(i,a,b) for i=" + i + " is " + arrayMatch(i, a, b));
        }
        System.out.println("\n");
        
        ////////////////// Below is print out code for Exercise 5 //////////////
        for (int i = 1; i <= 5; i++) {
            System.out.println("triangleNumber(" + i + ") is "
                    + triangleNumber(i));
        }
        
        ////////////////// Below is print out code for Exercise 6 //////////////
        String[] as = {"Red", "Yellow", "Pink", "Green", "Orange"};
        for (int i = 1; i < as.length; i++) {
            System.out.println("Printing first " + i);
            printFirstN(as, i);
        }   
        

    }
}
    