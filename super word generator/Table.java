import structure5.*;
import java.util.Scanner;
import java.util.Random;
import java.util.Iterator;


/**
* A Table holds a collection of strings, each of which has anjavac
* associated FrequencyTable
*/
public class Table {

protected Hashtable<String, FrequencyTable> t; 
//k-length o fthe word
protected int k;
  /** Construct an empty table */
  public Table() {
    t = new Hashtable<String, FrequencyTable>();
  }

  /**
  * Updates the table as follows
  * If key already exists in the table, update its FrequencyTable
  * by adding value to it
  * Otherwise, create a FrequencyTable for key and add value to it
  * @param key is the desired k-letter sequence
  * @param value is the character to add to the FrequencyTable of key
  */
  public void add(String key, char value) {
    if (!t.containsKey(key)){
      FrequencyTable frequency_table = new FrequencyTable();
      t.put(key, frequency_table);
    }
    t.get(key).add(value);
  }

  /**
  * If key is in the table, return one of the characters from
  * its FrequencyTable with probability equal to its relative frequency
  * Otherwise, determine a reasonable value to return
  * @param key is the k-letter sequence whose frequencies we want to use
  * @return a character selected from the corresponding FrequencyTable
  */
  public char choose(String key) {
    if (t.containsKey(key)){
      return t.get(key).choose();
    }
    return 'e';
  }

  /** Produce a string representation of the Table 
  * @return a String representing this Table
  */
  public String toString() {
    String s = "{\n";
    Iterator<String> keys = t.keys();
    while (keys.hasNext()) {
      String key = keys.next();
      FrequencyTable freq = t.get(key);
      // replace any \n in the key with an escaped newline
      // so we can see it
      key = key.replace("\n", "\\n");
      s += "\"" + key + "\"," + freq.toString() + "\n";
    }
    return s + "}";
  }

  // Use main to test your Table class
  public static void main(String[] args) {
    //Table t = new Table();
   /* t.add("th" , 'e');
    t.add("th" , 'e');
    t.add("th" , 'e');
    t.add("th" , 'a');
    t.add("th" , 'a');
    t.add("th" , 'o');
    t.add("m" , 'a');
    t.add("m" , 'a');
    t.add("m" , 'a');
    t.add("m" , 'i');
    t.add("m" , 'i');
    t.add("m" , 'o');
    t.add("se" , 'n');
    t.add("se" , 'n');
    t.add("se" , 'e');
    t.add("se" , 'e');
    t.add("se" , 'e');
    t.add("se" , 't');
    System.out.println(t);
    System.out.println(t.choose("th"));
*/
  }

}
