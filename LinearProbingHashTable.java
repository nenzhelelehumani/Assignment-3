/**
 *   Java Program to implement Linear Probing Hash Table
 * @author SanFundry modifified by @author Humani Nenzhelele
 **/ 
 
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.lang.Math.*;
 
 
/** Class LinearProbingHashTable **/
public class LinearProbingHashTable
{
    private int currentSize, maxSize;       
    private Integer[] keys;   
    private String[] vals;
    public ArrayList<String> keys_arr;
    public ArrayList<Integer> no_probes_arr;
    //public  ArrayList<String> colors = new ArrayList<String>();
 
    /** Constructor **/
    public LinearProbingHashTable(int capacity) 
    {
        currentSize = 0;
        maxSize = capacity;
        keys = new Integer[maxSize];
        vals = new String[maxSize];
    }  
 
    /**
     * Function to clear hash table
     * @param 
     * @return Nothing.
     * @exception 
     * @see 
     */

    public void makeEmpty()
    {
        currentSize = 0;
        keys = new Integer[maxSize];
        vals = new String[maxSize];
    }
 
    /** Function to get size of hash table
     * @param 
     * @return int
     * @exception 
     * @see 
     */
    public int getSize() 
    {
        return currentSize;
    }
 
    /** Function to check if hash table is full
     * @param 
     * @return boolean
     * @exception 
     * @see 
    */
    public boolean isFull() 
    {
        return currentSize == maxSize;
    }
 
    /** Function to check if hash table is empty
     * @param 
     * @return boolean
     * @exception 
     * @see 
    */
    public boolean isEmpty() 
    {
        return getSize() == 0;
    }
 
    /** Fucntion to check if hash table contains a key
     * @param String key, takes a key to check if it exist on the hash table
     * @return boolean
     * @exception
     * @see 
    */
    public boolean contains(String key) 
    {
        return get(key) !=  null;
    }
 
    /** Functiont to get hash code of a given key
     * @param String key, takes string to hash and returns its hash code
     * @return int 
     * @exception
     * @see
    */
    private int hash(String key) 
    {
        return key.hashCode() % maxSize;
    }    
 
    /** Function to insert key
     * @param String key, takes a string to hash and insert on the hash table
     * @return Nothing.
     * @exception
     * @see 
    */
    public void insert(String key) //takes a string now not a number
    {        
        String val = key;
        int tmp = Math.abs(hash(key));
        int i = tmp;
        do
        {
            if (keys[i] == null)
            {
                keys[i] = i;
                vals[i] = val;
                currentSize++;
                return;
            }
            if (keys[i].equals(key)) 
            { 
                vals[i] = val;
                //System.out.println("I am reached");
                return; 
            }            
            i = (i + 1) % maxSize;
            no_probes_arr.add(1);
        } while (i != tmp);    
    }
 
    /** Function to get value for a given key
     * @param args Unused.
     * @return String key that was searched
     * @exception
     * @see 
    */
    public String get(String key) 
    {
        int i = Math.abs(hash(key));
        while (keys[i] != null)
        {
            if (keys[i].equals(key))
                return vals[i];
            i = (i + 1) % maxSize;
        }            
        return null;
    }
 
       
 
    /** Function to print HashTable
     * @param 
     * @return Nothing.
     * @exception
     * @see
    */
    public void printHashTable()
    {
        System.out.println("\nHash Table: ");
        for (int i = 0; i < maxSize; i++)
            if (keys[i] != null)
                System.out.println(keys[i] +" "+ vals[i]);
        System.out.println();
        if(isFull()){
            System.out.println("The Table is Full");
        }
    }

    /** Function checks if a number is a prime number
     * @param number takes a number to check if its a prime number or not
     * @return boolean 
     * @exception IOException On input error.
     * @see IOException
    */
    public boolean isPrime(int n){
        int i,m=0,flag=0;      
        m=n/2;      
        if(n==0||n==1){  
         System.out.println(n+" is not Prime Number.\nEnter Table size that is a Prime Number");   
         return false;   
        }else{  
         for(i=2;i<=m;i++){      
          if(n%i==0){      
           System.out.println(n+" is not Prime Number.\nEnter Table size that is a Prime Number");     
           flag=1;
           return false;            
          }      
         }      
         if(flag==0)  { return true; }else{return false;}
    }
}
    /** This method build the hash table with a data from the file given as parameters
     * @param filename takes the name of the file to build table from
     * @return Nothing.
     * @exception IOException Error occured .
     * @see IOException
    */
    public void BuildHashTable(String filename){
        System.out.println("Insertion Performace");
        Double load_factor = 0.0; //the load factor is initially 0 when the Table is empty
        no_probes_arr = new ArrayList<Integer>();
        try{
            filename = filename.trim();
            File y = new File(filename);
            Scanner datafile = new Scanner(y);
            int count = 0;
            String[] parts;
            while(count<500) { //control how many keys you wanna insert
                String str = datafile.next();
                if(!(count==0)){
                    parts = str.split(",");
                    String strdata = parts[0]+","+parts[1]+","+parts[3];
                    insert(strdata);
                }
                ++count;
                }    
                datafile.close();
                Double d = (double)maxSize;
                load_factor = currentSize/d; //This value is to be printed
                System.out.println("Load Factor: "+load_factor);
                System.out.println("There are "+no_probes_arr.size()+" Number of Probes in Total.");
            }
        catch (Exception e) {
            System.out.println("Error Occured. Try Again!");
        }
    }

    /** This method searches for random number of keys on the hash table
     * @param no_keys number of keys to search from the table
     * @return Nothing.
     * @exception none
     * @see 
    */
    public void Searchkeys(int no_keys){
        System.out.println("Searching Performance");
        keys_arr = new ArrayList<String>();
        int ca = 0; //countavailables, which counts for each value found in the evals array
        for (String num : vals) { //copies the values of the vals array to this array so that it can choose random values
            if(num!=null){
                keys_arr.add(num);
                ++ca;
            }
         }
        int no_probes = 0;
        int longest_probes = 0;
        for(int x=0;x<no_keys;x++){ //selecting the random keys according to the given number of keys to search
            Random rand = new Random();
            int n = rand.nextInt(currentSize); //this n is any random number in the size of the keys_array so they won't be picked up in any order
            int currentprobe = 0; //to calculate the number of probes for each key 
            if(n>=0){
                String key = keys_arr.get(n);
                int i = Math.abs(hash(key));
                while (keys[i] != null)
                {
                    if (keys[i].equals(key)){
                        return;
                    }
                    i = (i + 1) % maxSize;
                    ++no_probes;
                    ++currentprobe;
                }            
                //return;
        }
        if(currentprobe>=longest_probes){
            longest_probes = currentprobe;
        }
    }
    System.out.println("There are "+no_probes+" number of probes in total when searching "+no_keys+" keys.");
    System.out.println("Average probes: "+no_probes/no_keys);
    System.out.println("Longest probe sequence is: "+longest_probes);
    }
}
 