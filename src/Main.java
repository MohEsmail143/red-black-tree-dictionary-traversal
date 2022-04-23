import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main
{
    // colors for console
    public static final String ANSI_WHITE = "\u001B[39m"; //WHITE Color
    public static final String ANSI_RED = "\u001B[31m"; // Red color
    public static final String ANSI_RESET = "\u001B[0m"; // Reset the color each time
    public static final String ANSI_BLUE = "\u001B[34m"; //Blue color

    public static void main(String[] args)
    {
        RBTree rbt = loadDictionary();
        Scanner scanner = new Scanner(System.in);

        System.out.println(ANSI_WHITE + "***** Welcome to The Dictionary *****" + ANSI_RESET);

        int n = 1;
        while (n == 1 || n == 2 || n == 3 || n == 4)
        {
            printMenu();
            n = scanner.nextInt();
            switch (n)
            {
                case 1:
                    insertWord(rbt);
                    break;
                case 2:
                    lookupWord(rbt);
                    break;
                case 3:
                    printDictionarySize(rbt);
                    break;
                case 4:
                    printTreeHeight(rbt);
                    break;
            }
        }
        goodbyePrintTerminal();
    }

    static RBTree loadDictionary()
    {
        RBTree scannedTree = new RBTree();
        try (BufferedReader br = new BufferedReader(new FileReader("src\\dict.txt")))
        {
            String str;

            while ((str = br.readLine()) != null)
            {
                scannedTree.insert(str);
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return scannedTree;
    }

    static void printMenu()
    {
        System.out.print(ANSI_BLUE);
        System.out.println("1- Insert");
        System.out.println("2- Search");
        System.out.println("3- Dictionary Size  ");
        System.out.println("4- Tree Height  ");
        System.out.println();
        System.out.println("Press" + ANSI_BLUE + " any number" + ANSI_RESET + " to" + ANSI_RED + " EXIT");
        System.out.print(ANSI_RESET + ">> ");
    }

    static void insertWord(RBTree rbt)
    {
        System.out.println("Please Enter the Keyword for Insertion");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        if (rbt.search(s))
        {
            System.out.println(ANSI_RED + "Word already in the dictionary!");
        } else
        {
            rbt.insert(s);
            System.out.println("Keyword " + ANSI_BLUE + s + ANSI_RESET + " is inserted successfully");
            printTreeHeight(rbt);
            printDictionarySize(rbt);
        }
        System.out.println();
    }

    static void lookupWord(RBTree rbt)
    {
        System.out.println("Please Enter the keyword for Searching");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String result = rbt.search(s) ? "Found" : "Not Found";
        String color = result.equals("Found") ? ANSI_BLUE : ANSI_RED;
        System.out.println("Keyword " + ANSI_BLUE + s + ANSI_RESET + " is " + color + result + ANSI_RESET);
        System.out.println();
    }

    static void printDictionarySize(RBTree rbt)
    {
        System.out.println("Dictionary Size equals = " + ANSI_BLUE + rbt.getSize() + ANSI_RESET);
    }

    static void printTreeHeight(RBTree rbt)
    {
        int height = rbt.getHeight(rbt.getRoot()) - 1;
        System.out.println("Height of the Red black tree = " + ANSI_BLUE + height + ANSI_RESET);
    }

    static void goodbyePrintTerminal()
    {
        System.out.println(ANSI_WHITE + "************* Thanks for Using The Dictionary *************" + ANSI_RESET);
    }
}
