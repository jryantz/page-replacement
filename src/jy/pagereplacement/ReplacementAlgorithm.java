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

public abstract class ReplacementAlgorithm {
    
    protected int pageFaultCount;
    protected int pageFrameCount;

    public ReplacementAlgorithm(int pageFrameCount) {
        if(pageFrameCount < 0) {
            throw new IllegalArgumentException();
        }
        
        this.pageFrameCount = pageFrameCount;
        pageFaultCount = 0;
    }//end ReplacementAlgorithm
    
    public int getPageFaultCount() {
        return pageFaultCount;
    }//end getPageFaultCount
    
    public abstract void insert(int pageNumber);
}//end class ReplacementAlgorithm
