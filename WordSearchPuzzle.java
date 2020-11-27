/*
 * Name: Conor McNelis
 * ID: 19277849
 * I am a student who has transferred into this course from LM121, I have been accepted fully into
 * the course but I may not be fully integrated into the system. I have been told to tell you that
 * grading may have to be manual in my case
 * 
 */
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;
import java.io.*;
import java.util.LinkedList;
public class WordSearchPuzzle {
    private char[][] puzzle;
    private ArrayList<String>puzzleWords;
    private String alphabet = ("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

    public WordSearchPuzzle(ArrayList<String> userSpecifiedWords) {
        puzzleWords = userSpecifiedWords;
        generateWordSearchPuzzle();
    }

    public WordSearchPuzzle(String wordFile, int wordCount,
    int shortest, int longest) {
        ArrayList<String> wordList1 = loadWordsFromFileRandom(wordFile, wordCount, longest, shortest);
        puzzleWords = wordList1;
        generateWordSearchPuzzle();
    }

    private static ArrayList<String> loadWordsFromFileRandom(String filename, int numberOfWords, int max, int min) {
        try {
            FileReader aFileReader = new FileReader(filename);
            BufferedReader aBufferReader = new BufferedReader(aFileReader);
            String aWord;
            int wordsRead = 0;
            LinkedList<String> chosenWords = new LinkedList<String>();
            aWord = aBufferReader.readLine() ;
            while (aWord != null) {
                wordsRead++;
                chosenWords.add(aWord.toUpperCase());
                aWord = aBufferReader.readLine() ;
            }
            aBufferReader.close();
            aFileReader.close();
            ArrayList<String> AllWords = new ArrayList<String>(chosenWords);
            ArrayList<String> Words = new ArrayList<String>();
            int wordNo = 0;
            while(wordNo<numberOfWords){
                String Word = AllWords.get((int) (Math.random()*(AllWords.size()-1)+1));
                if(Word.length()<=max &&  Word.length()>=min){
                    Words.add(Word);
                    wordNo++;
                }
            }
            return Words;
        }
        catch(IOException x) {
            return null ;
        }
    }

    public int puzzleSize(ArrayList<String> userSpecifiedWords) {
        int total = 0;
        int max = 0;
        for(int wordno = 0; wordno<userSpecifiedWords.size(); wordno++){
            String word = userSpecifiedWords.get(wordno); 
            total = total + word.length();
            if(word.length()>=max){
                max = word.length();
            }
        }
        max = max*2;
        if((max*max)<=(total*2)){
            max = max*2;
        }
        return max;
    }

    public ArrayList<String> getWordSearchList() {
        return new ArrayList(puzzleWords);
    }

    public char[][] getPuzzleAsGrid(){
        this.puzzle=puzzle;
        return puzzle;
    }

    public String getPuzzleAsString(){
        char[] arr = new char[puzzleSize(puzzleWords)];
        String s = "";
        for(int i = 0; i<puzzleSize(puzzleWords); i++){
            for(int j = 0; j< puzzleSize(puzzleWords); j++){
                arr[j] = puzzle[i][j];
            }
            s += String.valueOf(arr);
            s += '\n';
        }
        return s;
    }

    public List<Integer> findWord(String word){
        int row = 0;
        int col = 0;
        int sum = 0;
        int vertical = 0;
        List<Integer> coordinates = new ArrayList<Integer>();
        for (int i = 0; i<puzzle.length; i++){
            for (int j = 0; j<puzzle.length; j++){
                if(sum<=(word.length()-1)){
                    if(puzzle[i][j]==word.charAt(sum)){
                        sum = sum+1;
                    }
                    else{
                        sum = 0;
                    }
                }
                else if(sum==word.length()){
                    sum=0;
                    col=j-word.length()+1;
                    row=i+1;
                    vertical = 1;
                }

            }
        }
        for (int i = 0; i<puzzle.length; i++){
            for (int j = 0; j<puzzle.length; j++){
                if(sum<=(word.length()-1)){
                    if(puzzle[j][i]==word.charAt(sum)){
                        sum = sum+1;
                    }
                    else{
                        sum = 0;
                    }
                }
                else if(sum==word.length()){
                    sum=0;
                    row=j-word.length()+1;
                    col=i+1;
                    vertical = 2;
                }

            }
        }
        coordinates.add(row);
        coordinates.add(col);
        coordinates.add(vertical);
        return coordinates;
    }

    public void showWordSearchPuzzle(boolean hide){
        System.out.println("PUZZLE \n------\nThe puzzle with unused positions filled with random characters...");
        for(int i = 0; i<puzzle.length; i++)
        {
            for(int j = 0; j<puzzle[0].length; j++)
            {        
                System.out.print(puzzle[i][j]);
            }
            System.out.println();
        }
        System.out.println("The puzzle words in alphabetical order...\n");
        for(int j = 0; j<puzzleWords.size(); j++){
            System.out.print(puzzleWords.get(j));
            if(hide==false){
                List<Integer> coordinates = findWord(puzzleWords.get(j));
                String vertical = "";
                if(coordinates.get(2)==2){
                    vertical = " top to bottom";
                }
                else if(coordinates.get(2)==1){
                    vertical = " left to right";
                }
                int row = coordinates.get(0);
                int col = coordinates.get(1);
                System.out.println(" is in row number "+row+" and in column number "+col+""+vertical);
            }
            if(hide==true){
                System.out.println("");
            }
        }

    }

    public void generateWordSearchPuzzle(){
        int max = puzzleSize(puzzleWords);
        puzzle = new char[max][max];
        ArrayList<String> verticalWords = new ArrayList<String>();
        ArrayList<String> horizontalWords = new ArrayList<String>();
        int row = 0;
        int row2 = 1;
        int colV = 0;
        int colV2 = 1;
        for (int i = 0; i<puzzle.length; i++){
            for (int j = 0; j<puzzle[i].length; j++){
                puzzle[i][j] = '1';
            }
        }
        for(int wordno = 0; wordno<puzzleWords.size(); wordno++){
            String word = puzzleWords.get(wordno); 
            int letterno = 0;
            boolean vertical;
            row = 0;
            colV = 0;
            int StartPos = 0;
            int CoinFlip = (int) (Math.random()*(2)+1);
            if (CoinFlip == 2){
                while(colV!=colV2){
                    int rowV2 = 0;
                    int initialRowV2 = (int) (Math.random()*(puzzle.length-1-word.length()));
                    colV2 = (int) (Math.random()*(puzzle.length-1)+1);
                    int sum = 0;
                    for (rowV2 = initialRowV2; rowV2<(initialRowV2+word.length()); rowV2++){
                        if(puzzle[rowV2][colV2]=='1'){
                            sum = sum+1;
                        }
                        else{
                            sum = 0;
                        }
                    }
                    if(sum>=word.length()){
                        colV = colV2;
                        StartPos = initialRowV2;
                    }
                }
            }
            else if (CoinFlip == 1){
                while(row!=row2){
                    int col2 = 0;
                    int initialCol2 = (int) (Math.random()*(puzzle.length-1-word.length()));
                    row2 = (int) (Math.random()*(puzzle[0].length-1)+1);
                    int sum = 0;
                    for (col2 = initialCol2; col2<(initialCol2+word.length()); col2++){
                        if(puzzle[row2][col2]=='1'){
                            sum = sum+1;
                        }
                        else{
                            sum = 0;
                        }
                    }
                    if(sum>=word.length()){
                        row = row2;
                        StartPos = initialCol2;
                    }
                }
            }

            row2 = -1;
            colV2 = -1;

            for(int x = StartPos; x<puzzle.length; x++){
                if(letterno<word.length()){    
                    char letter = word.charAt(letterno);

                    if (CoinFlip == 1){
                        if(puzzle[row][x]=='1'){
                            puzzle[row][x] = letter;
                            letterno=letterno+1;
                        }
                    }
                    else if (CoinFlip == 2){
                        if(puzzle[x][colV]=='1'){
                            puzzle[x][colV] = letter;
                            letterno=letterno+1;
                        }
                    }

                }
            }
        }
        for (int i = 0; i<puzzle.length; i++){
            for (int j = 0; j<puzzle[i].length; j++){
                if(puzzle[i][j]=='1'){
                    puzzle[i][j] = alphabet.charAt((int) (Math.random()*(alphabet.length()-2)+1));
                }
            }
        }
    }
}
