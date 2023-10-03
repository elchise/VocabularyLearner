/*
BST Generic Project
April 2023
Ella Chi

Class: VocabEntry 
creates VocabEntry objects to reperesent vocabulary words to study
words are stored in nodes for the bst to represent the deck of vocab words

*/

import java.lang.*;

public class VocabEntry implements Comparable<VocabEntry> {
    private String word, definition;
    private int timesStudied, timesCorrect;
    
    public VocabEntry(String w, String d){
        this(w, d, 0, 0);
    }
    
    public VocabEntry(String w, String d, int c, int t){
        word = w;
        definition = d;
        timesStudied = t;
        timesCorrect = c;
    }
    
    //return vocab word
    public String getWord(){
        return word;
    }
    
    //return definition of word
    public String getDef(){
        return definition;
    }
    
    //set the definition of the word
    public void setDef(String d){
        definition = d;
    }
    
    //set the word
    public void setWord(String w){
        word = w;
    }
    
//    public void setCorrect(int c){
//        timesCorrect = c;
//    }
//    
//    public void setTotal(int t){
//        timesStudied = t;
//    }
    
    //returns the number of times this word has been correct
    public int getCorrect(){
        return timesCorrect;
    }
    
    //return total amount the word has been studied
    public int getTotal(){
        return timesStudied;
    }
    
    //return the average score of the word as a percentage
    public int percent(){
        //return 0 if the word hasn't been studied at all
        if(timesStudied == 0){
            return 0;
        }
        
        return (int)(100*(double)timesCorrect/timesStudied);
    }
    
    //overrides Comparable's compareTo method by comparing the average scores/percentages of this and another vocab entry
    public int compareTo(VocabEntry n){
        return (int)(this.percent() - n.percent());
    }
    
    //increments number of times word has been correct
    public void updateCorrect(){
        timesCorrect++;
    }
    
    //increments total times word has been studied
    public void updateTotal(){
        timesStudied++;
    }
    
    //return a string to represent vocab entry
    public String toString(){
        return word;
    }
}
