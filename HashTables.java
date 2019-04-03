import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.lang.Math.*;
 
 

public class HashTables{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("**** Hash Table Test ****\n\n");
        System.out.println("Enter the Amount of Size : ");
        int table_size = scan.nextInt();

        Scanner input_solution = new Scanner(System.in);
        System.out.println("Enter Collision Resolution (Chaining, Quadratic or Linear) :");
        String col_solution = input_solution.nextLine();

        if(col_solution.equals("Chaining")){
            ChainingHashTable ht = new ChainingHashTable(table_size);

            if(ht.isPrime(table_size)){
            Scanner num_keys_input = new Scanner(System.in);
            System.out.println("Enter Number of Keys to Search :");
            int no_keys = num_keys_input.nextInt();

            //prompt the use rto enter the file that contains the data
            Scanner file_input = new Scanner(System.in);
            System.out.println("Enter File to Search Data on :");
            String filename = file_input.nextLine();
            filename = filename.trim();

            ht.BuildHashTable(filename);
            System.out.println("There are 0 Number of Probes.");
            ht.Searchkeys(no_keys);
            }
        }
        else if(col_solution.equals("Quadratic")){
            QuadraticProbingHashTable lpht = new QuadraticProbingHashTable(table_size);
        
            if(lpht.isPrime(table_size)){
                Scanner num_keys_input = new Scanner(System.in);
                System.out.println("Enter Number of Keys to Search");
                int no_keys = num_keys_input.nextInt();
    
                //prompt the use rto enter the file that contains the data
                Scanner file_input = new Scanner(System.in);
                System.out.println("Enter File to Search Data on :");
                String filename = file_input.nextLine();
                filename = filename.trim();
                lpht.BuildHashTable(filename);
                //lpht.printHashTable();
                lpht.Searchkeys(no_keys);
            }
        }
        else{
            LinearProbingHashTable lpht = new LinearProbingHashTable(table_size);
            if(lpht.isPrime(table_size)){
            
                Scanner num_keys_input = new Scanner(System.in);
                System.out.println("Enter Number of Keys to Search : ");
                int no_keys = num_keys_input.nextInt();
        
                //prompt the use rto enter the file that contains the data
                Scanner file_input = new Scanner(System.in);
                System.out.println("Enter File to Search Data on :");
                String filename = file_input.nextLine();
                filename = filename.trim();
                lpht.BuildHashTable(filename);
            
                lpht.Searchkeys(no_keys);
            }
        }
    
    
    }
}
