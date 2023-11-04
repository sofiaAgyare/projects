import structure5.*;
import java.util.Scanner;
import java.util.Random;
/**
 * A FrequencyTable stores a set of characters each of which has
 * an associated integer frequency
 */
public class FrequencyTable {

protected int sum;
protected Hashtable<Character, Integer> freq; 
protected Random random = new Random();

/** Construct an empty FrequencyTable */
  public FrequencyTable() {
    freq = new Hashtable<Character, Integer>();
    sum = 0;

  }

  /** add(char ch)
   * If ch is in the FrequencyTable, increment its associated frequency
   * Otherwise add ch to FrequencyTable with frequency 1
   * @param ch is the String to add to the FrequencyTable
   */
  public void add(char ch) {
    if (freq.containsKey(ch)){
      freq.put(ch, freq.get(ch)+1);
    }else{
      freq.put(ch, 1);
    }
    sum++;
  }

  /** Selects a character from this FrequencyTable with probabality equal to its relative frequency.
   * @return a character from the FrequencyTable
   */
  public char choose() {
    int num = random.nextInt(sum);
    int cumFreq = 0;
    for (Character charac : freq.keySet()){
        if (num < cumFreq + freq.get(charac)){
          return charac;
        }else{
          cumFreq += freq.get(charac);
        }
      }
      return 'e';
    }

  /** Produce a string representation of the FrequencyTable 
   * @return a String representing the FrequencyTable
   */
  public String toString() {
    return freq.toString();
  }

  // Use main to test your FrequencyTable class
  /*public static void main(String[] args) {
    
  }
*/
}

