/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jy.pagereplacement;

import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author jonathanyantz
 */

public class Main {

    public static void main(String[] args) {
        
        int frmsize;
        String refString;
        
        //get the frame - entered by the user
        System.out.println("Please enter a reference string with spaces between each number:");
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        refString = sc.nextLine();
        
        //get the frame size - entered by the user
        System.out.println("Please enter a frame size:");
        frmsize = Integer.parseInt(sc.nextLine());
        
        System.out.println("\nReference String: " + refString);
        System.out.println("Frame Size: " + frmsize + "\n");
        
        Page page = new Page();
        
        //start each algorithm
        System.out.print("FIFO: ");
        page.fifo(frmsize, refString);
        
        System.out.print("LRU: ");
        page.lru(frmsize, refString);
        
        System.out.print("Second Chance: ");
        page.second(frmsize, refString);
        
        System.out.print("Optimal: ");
        page.optimal(frmsize, refString);
        
        System.out.print("Random: ");
        page.random(frmsize, refString);
    }//end Main
}//end class Main
