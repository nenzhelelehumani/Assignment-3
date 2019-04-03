/*
 *    Java Program to Implement Hash Tables Chaining with List Heads
 */ 
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.lang.Math.*;
 
/* Class LinkedHashEntry */
class LinkedHashEntry 
{
    String key;
    String value;
    LinkedHashEntry next;
 
    /* Constructor */
    LinkedHashEntry(String key, String value) 
    {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}
 
/* Class HashTable */
public class ChainingHashTable
{
    private int TABLE_SIZE;
    private int size; 
    private LinkedHashEntry[] table;
    public ArrayList<String> keys_arr;
    private String[] vals; //array to store every item that is in the table

 
     /* Constructor */
    public ChainingHashTable(int ts) 
    {
        size = 0;
        TABLE_SIZE = ts;
        table = new LinkedHashEntry[TABLE_SIZE];
        vals = new String[TABLE_SIZE+size]; //making the array with eacj value of the tble enough for any value
        for (int i = 0; i < TABLE_SIZE; i++)
            table[i] = null;
    } 
    /** Function to get size of hash table
     * @param 
     * @return int
     * @exception 
     * @see 
     */
    public int getSize()
    {
        return size;
    }
    /** method makes a table empty
     * @param 
     * @return boolean
     * @exception 
     * @see 
    */
    public void makeEmpty()
    {
        for (int i = 0; i < TABLE_SIZE; i++)
            table[i] = null;
    }
    /* Function to get value of a key */
    public void get(String key) 
    {
        int hash = (myhash( key ) % TABLE_SIZE);
        if (table[hash] == null)
            return;
        else 
        {
            LinkedHashEntry entry = table[hash];
            while (entry != null && !entry.key.equals(key))
                entry = entry.next;
            if (entry == null)
                return;
            else
                return;
        }
    }

    /** Function to insert key
     * @param String key, takes a string to hash and insert on the hash table
     * @return Nothing.
     * @exception
     * @see 
    */
    public void insert(String key) 
    {
        int count_vals = 0; //this is the count to values in the vals array
        String value = key;
        int hash = (myhash( key ) % TABLE_SIZE);
        if (table[hash] == null){
            table[hash] = new LinkedHashEntry(key, value);
            size++;
        }
        else 
        {
            LinkedHashEntry entry = table[hash];
            while (entry.next != null && !entry.key.equals(key))
                entry = entry.next;
            if (entry.key.equals(key)){
                entry.value = value;
                vals[count_vals] = key;
            }
            else{
                entry.next = new LinkedHashEntry(key, value);
            }
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
         System.out.println(n+" is not prime number.\nEnter Table size that is a prime number");   
         return false;   
        }else{  
         for(i=2;i<=m;i++){      
          if(n%i==0){      
           System.out.println(n+" is not prime number.\nEnter Table size that is a prime number");     
           flag=1;
           return false;            
          }      
         }      
         if(flag==0)  { return true; }else{return false;}
    }
}
 

    /** Functiont to get hash code of a given key
     * @param String key, takes string to hash and returns its hash code
     * @return int 
     * @exception
     * @see
    */
    private int myhash(String x )
    {
        int hashVal = x.hashCode( );
        hashVal %= TABLE_SIZE;
        if (hashVal < 0)
            hashVal += TABLE_SIZE;
        return hashVal;
    }
    /** Function to print HashTable
     * @param 
     * @return Nothing.
     * @exception
     * @see
    */
    public void printHashTable()
    {
        for (int i = 0; i < TABLE_SIZE; i++)
        {
            System.out.print("\nBox "+ (i + 1) +" : ");
            LinkedHashEntry entry = table[i];
            while (entry != null)
            {
                System.out.print(entry.value +" | ");
                entry = entry.next;
            }            
        }
    }

    /** This method build the hash table with a data from the file given as parameters
     * @param filename takes the name of the file to build table from
     * @return Nothing.
     * @exception IOException Error occured .
     * @see IOException
    */
    public void BuildHashTable(String filename){
        Double load_factor = 0.0; //the load factor is initially 0 when the Table is empty
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
                Double d = (double)TABLE_SIZE;
                load_factor = size/d; //This value is to be printed
                System.out.println("Load Factor: "+load_factor);
            }
        catch (Exception e) {
            System.out.println("Error Occured !");
        }
    }

    /** This method searches for random number of keys on the hash table
     * @param no_keys number of keys to search from the table
     * @return Nothing.
     * @exception none
     * @see 
    */
    public void Searchkeys(int no_keys){
        keys_arr = new ArrayList<String>();
        int ca = 0; //countavailables, which counts for each value found in the evals array
        for (String num : vals) { //copies the values of the vals array to this array so that it can choose random values
            if(num!=null){
                keys_arr.add(num);
                ++ca;
            }
         }
        for(int x=0;x<no_keys;x++){ //selecting the random keys according to the given number of keys to search
            Random rand = new Random();
            int n = rand.nextInt(size); //this n is any random number in the size of the keys_array so they won't be picked up in any order
            if(size<n && n>=0){
                String key = keys_arr.get(n);
                int i = Math.abs(myhash(key));
                if (table[i] == null)
                return;
                else 
                {
                    LinkedHashEntry entry = table[i];
                    while (entry != null && !entry.key.equals(key))
                        entry = entry.next;
                    if (entry == null)
                        return;
                    else
                        return;
                }
        }
    }
    }
}
 
/* Class HashTablesChainingListHeadsTest */
