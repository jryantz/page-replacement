/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jy.pagereplacement;

/**
 *
 * @author jonathanyantz
 */

public class Page {
    
    public Page() {
        
    }//end Page

    public void fifo(int frameSize, String refString) {
        
        //set up the frame
        Frame frame = new Frame(frameSize);
        String[] split = refString.split(" ");
        
        //put each number into the frame
        for(String piece:split) {
            frame.fifoAdd(Integer.parseInt(piece));
        }

        System.out.print(frame.faults() + " faults\n");
    }//end fifo

    public void lru(int frameSize, String refString) {
        
        //set up the frame
        Frame frame = new Frame(frameSize);
        String[] split = refString.split(" ");
        
        //put each number into the frame
        for(String piece:split) {
            frame.lruAdd(Integer.parseInt(piece));
        }

        System.out.print(frame.faults() + " faults\n");
    }//end lru

    public void second(int frameSize, String refString) {
        
        //set up the frame
        Frame frame = new Frame(frameSize);
        String[] split = refString.split(" ");
        
        //put each number into the frame
        for(String piece:split) {
            frame.secondAdd(Integer.parseInt(piece));
        }
        
        System.out.print(frame.faults() + " faults\n");
    }//end second

    public void optimal(int frameSize, String refString) {
        
        //set up the frame
        Frame frame = new Frame(frameSize);
        String[] split = refString.split(" ");
        
        //put each number into the frame
        for(int i = 0; i < split.length; i++) {
            frame.optimalAdd(Integer.parseInt(split[i]), split, i);
        }
        
        System.out.print(frame.faults() + " faults\n");
    }//end optimal
    
    public void random(int frameSize, String refString) {
        
        //set up the frame
        Frame frame = new Frame(frameSize);
        String[] split = refString.split(" ");
        
        //put each number into the frame
        for(String piece:split) {
            frame.randomAdd(Integer.parseInt(piece));
        }
        
        System.out.print(frame.faults() + " faults\n");
    }//end second
}//end class Page
