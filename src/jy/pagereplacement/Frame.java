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
public class Frame /*extends ReplacementAlgorithm*/ {
    
    private int[][] frame;
    private int[] ref;
    private int size;
    private int fault;

    //sets up all variables and prepares them for use.
    public Frame(int frameSize) {
        frame = new int[frameSize][2];
        ref = new int[frameSize];
        size = 0;
        fault = 0;
    }//end Frame

    public void fifoAdd(int x) {
        
        //sets the current location in the frame to 0
        int location = 0;

        //checks if the number being entered is already in the frame
        //if not
        if(!contains(x)) {
            //check if the frame is full
            if(frame.length == size) {//frame is full
                //set the location to the item that has been in the frame the oldest
                location = oldest();
            } else {//has room
                //set the location to the next available slot
                location = size++;
            }

            //set the frame at the location found
            frame[location][0] = x;
            frame[location][1] = 0;
            fault++;
            
            //age everything in the list
            age(location);
        } else {
            //do nothing
        }
    }//end fifoAdd
    
    public void lruAdd(int x) {
        
        //sets the current location in the frame to 0
        int location = 0;
        
        //checks if the number being entered is already in the frame
        //if not
        if(!contains(x)) {
            //check if the frame is full
            if(frame.length == size) {//frame is full
                //set the location to the item that has been in the frame the oldest
                location = oldest();
            } else {
                //set the location to the next available slot
                location = size++;
            }
            
            //set the frame at the location found
            frame[location][0] = x;
            frame[location][1] = 0;
            fault++;
        } else {
            //if the number is there, set the age back to 0
            location = indexOf(x);
            frame[location][1] = 0;
        }
        
        age(location);
    }//end lruAdd
    
    public void secondAdd(int x) {
        
        //sets the current location in the frame to 0
        int location = 0;
        
        //checks if the number being entered is already in the frame
        //if not
        if(!contains(x)) {
            //check if the frame is full
            if(frame.length == size) {//frame is full
                //set the location to the item that has been in the frame the oldest
                int old = oldest();
                int times = 0;
                //if the location has a reference of 1, look for the next oldest item
                while(ref[old] == 1) {
                    if(times >= size) {
                        old = oldest();
                    }
                    times++;
                    ref[old] = 0;
                    old = nextOldest(old);
                }
                location = old;
            } else {
                //set the location to the next available slot
                location = size++;
            }
            
            //set the frame at the location found
            frame[location][0] = x;
            frame[location][1] = 0;
            ref[location] = 1;
            fault++;
            
            age(-1);
        } else {
            //if the number is in the frame, give it a second chance
            location = indexOf(x);
            ref[location] = 1;
        }
    }//end secondAdd
    
    public void optimalAdd(int x, String[] refStr, int loc) {
        
        //sets the current location in the frame to 0
        int location = 0;
        
        //checks if the number being entered is already in the frame
        //if not
        if(!contains(x)) {
            //check if the frame is full
            if(frame.length == size) {//frame is full
                //set the location to the item that has been in the frame the oldest
                location = oldest();
            } else {
                //set the location to the next available slot
                location = size++;
            }
            
            //set the frame at the location found
            frame[location][0] = x;
            frame[location][1] = 0;
            fault++;
        } else {
            //do nothing
        }
        
        //looks at each number in the frame, looks how far ahead it is until that number is used again
        for(int place = 0; place < size; place++) {
            for(int i = loc + 1; i < refStr.length; i++) {
                if(Integer.parseInt(refStr[i]) == elementAt(place, 0)) {
                    frame[place][1] = i;
                    break;
                } else {
                    frame[place][1] = 2000000000;
                }
            }
        }
    }//end optimalAdd
    
    public void randomAdd(int x) {
        //sets the current location in the frame to 0
        int location = 0;

        //checks if the number being entered is already in the frame
        //if not
        if(!contains(x)) {
            //check if the frame is full
            if(frame.length == size) {//frame is full
                //set the location to the item that has been in the frame the oldest
                location = 0 + (int)(Math.random() * (size - 0));
            } else {//has room
                //set the location to the next available slot
                location = size++;
            }

            //set the frame at the location found
            frame[location][0] = x;
            frame[location][1] = 0;
            fault++;
            
            //age everything in the list
            age(location);
        } else {
            //do nothing
        }
    }//end randomAdd
    
    private void age(int current) {
        
        //age everything up other than the number provided
        for (int i = 0; i < size; i++) {
            if (i != current) {
                frame[i][1]++;
            }
        }
    }//end age

    private int oldest() {
        
        //find the oldest aged item
        int oldestLoc = 0;

        for(int i = 0; i < size; i++) {
            if(frame[i][1] > elementAt(oldestLoc, 1)) {
                oldestLoc = i;
            }
        }

        return oldestLoc;
    }//end oldest
    
    private int nextOldest(int x) {
        
        //find the next oldest from the number provided
        int oldest = elementAt(x, 1);
        int oldestLoc = x;
        
        while(oldestLoc == x) {
            for(int i = 0; i < size; i++) {
                if(frame[i][1] == oldest) {
                    oldestLoc = i;
                    return oldestLoc;
                }
            }
            
            if(oldestLoc == x) {
                oldest--;
            }
        }
        
        return oldestLoc;
    }

    public boolean contains(int x) {
        
        //checks if a number is in the frame
        for (int i = 0; i < size; i++) {
            if (x == frame[i][0]) {
                return true;
            }
        }

        return false;
    }//end contains

    public int elementAt(int x, int depth) {
        
        //gets the item in a certain location
        if (x < 0 || x >= size) {
            return -1;
        } else {
            return frame[x][depth];
        }
    }//end elementAt

    public int indexOf(int x) {
        
        //gets the location of a number
        for (int i = 0; i < size; i++) {
            if (x == frame[i][0]) {
                return i;
            }
        }

        return -1;
    }//end indexOf

    public int size() {
        
        //returns the size
        return size;
    }//end size
    
    public int faults() {
        
        //returns the faults
        return fault;
    }//end faults
}//end class Frame
