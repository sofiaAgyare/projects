import structure5.*;
import java.util.Scanner;
import java.util.Random;
import java.util.Iterator;



public class WordGen {
    /**
     * the amount of letters that will be randomly generated
     */
    
    public static final int OUTPUT_LENGTH = 500;
    /**
     * the table for each prefix
     */
    protected Table t;

    /**
     * constructor for a new word generator
     */
    public WordGen(){
        t = new Table();
    }

    /**
     * 
     * @return the table containing the prefizes and their frequences
     */
    public Table getTable(){
        return t;
    }

    /**
     * will populate the table of frequencies and prefixes
     * @param text is the input
     * @param k the prefix size 
     */
    public void populate(String text, int k) {
        text = text + text.substring(0, k+1);
        //System.out.println(text);
        for (int i = 0; i < text.length()- k; i++){
            t.add(text.substring(i, i+k), text.charAt(i+k));

        }
    }

    /**
     * generates random text
     * @param text the input seed from which the text is generted
     * @param k the prefix size
     * @return the randomly generated string
     */
    public String generate(String text, int k){
        String fresh = text.substring(0, k);
        for (int i = 0; i < OUTPUT_LENGTH; i++){
            fresh += t.choose(fresh.substring(i, i+k));
        }
        return fresh;
    }

    /**
     * inputs the text
     * @param args prefix size K
     */
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]); 
        Scanner in = new Scanner(System.in);
        StringBuffer textBuffer = new StringBuffer();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            textBuffer.append(line);
            textBuffer.append("\n");
        }
        String text = textBuffer.toString();
        // 'text' now contains the full contents of the input.

        WordGen w = new WordGen();
        w.populate(text, k);
        //System.out.println(w.getTable());
        System.out.println(w.generate(text, k));
    }
 
}
