package datacompression;

/**
 *
 * @author test
 */

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class TreeTester
{
    private String prefixCode;
    private String encodedMessage;
    private Scanner input;

    public static void main(String args[])
    {
        try
        {
            new TreeTester();
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e);
        }
    }

    public TreeTester() throws FileNotFoundException
    {
        prefixCode = "";
        encodedMessage = "";
        int choice = -1;
        String fileName = "";

        System.out.println(
                           "0. 2 cities excerpt\n"
                         + "1. abracadabra!\n"
                         + "2. aesop's fables\n"
                         + "3. the constitution\n"
                         + "4. MLK's dream\n"
                         + "5. lil women\n"
                         + "6. ascii art of mona lis\n"
                         + "7. lyrics from \"pearl jam's\" discography\n"
                         + "8. the digits of pi\n"
                         + "9. yabba dabba do!");

        while(choice < 0 || choice > 9)
        {
            input = new Scanner(System.in);
            choice = input.nextInt();
            if(choice < 0 || choice > 9)
            {
                System.out.println("Enter a number between and including 0 to 9");
            }
        }

        switch(choice)
        {
            case 0:
                fileName = "2cities";
                break;
            case 1:
                fileName = "abra";
                break;
            case 2:
                fileName = "aesop";
                break;
            case 3:
                fileName = "constitution";
                break;
            case 4:
                fileName = "dream";
                break;
            case 5:
                fileName = "lilwomen";
                break;
            case 6:
                fileName = "monalisa";
                break;
            case 7:
                fileName = "pearl_jam";
                break;
            case 8:
                fileName = "pi";
                break;
            case 9:
                fileName = "yabba";
                break;
        }

        input = new Scanner(
                new BufferedReader(
                new FileReader("src/datacompression/testdata/" + fileName + ".pre")));
        input.useDelimiter("");

        String temp = input.nextLine();
        while(temp.contains("*"))
        {
            prefixCode += temp + "\n";
            temp = input.nextLine();
        }

        encodedMessage += temp;
        while(input.hasNext())
        {
            encodedMessage += temp + "\n";
            temp = input.nextLine();
        }
        PrefixTree tree = new PrefixTree(prefixCode);
        tree.traverse();
        tree.uncompress(encodedMessage);
    }
}