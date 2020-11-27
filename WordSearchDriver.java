/*
 * Name: Conor McNelis
 * ID: 19277849
 * I am a student who has transferred into this course from LM121, I have been accepted fully into
 * the course but I may not be fully integrated into the system. I have been told to tell you that
 * grading may have to be manual in my case
 * 
 */
import java.util.Arrays;
import java.util.*;
public class WordSearchDriver
{
    public static void main(String[] args)
    {
        ArrayList<String>puzzleWords = new ArrayList<String>();
        puzzleWords.add("WATER");
        puzzleWords.add("BLOOD");
        puzzleWords.add("FLAMINGO");
        puzzleWords.add("ALPAHABET");
        puzzleWords.add("BULLET");
        puzzleWords.add("GOBBLE");
        
        WordSearchPuzzle puzzle1 = new WordSearchPuzzle(puzzleWords);
        puzzle1.showWordSearchPuzzle(false);
        System.out.println("\nGETTING PUZZLE AS STRING\n"+puzzle1.getPuzzleAsString());
        
        WordSearchPuzzle puzzle2 = new WordSearchPuzzle("BNCwords.txt", 20, 4, 10);
        puzzle2.showWordSearchPuzzle(false);
        
        WordSearchPuzzle puzzle3 = new WordSearchPuzzle("BasicEnglish.txt", 5, 3, 6);
        puzzle2.showWordSearchPuzzle(false);
    }
}