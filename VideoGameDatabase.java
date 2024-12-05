/*
 * Written by Lance Kimani
 */
import java.util.Scanner;
import java.io.*;
import java.io.FileWriter;

public class VideoGameDatabase {
	public static void printChoices()
    {
        System.out.println("Look at these options below");
        System.out.println("--------------------------------------------------");
        System.out.println("Enter 1 to load the video game database");
        System.out.println("--------------------------------------------------");
        System.out.println("Enter 2 to search the database");
        System.out.println("--------------------------------------------------");
        System.out.println("Enter 3 to print current results to the console");
        System.out.println("--------------------------------------------------");
        System.out.println("Enter 4 to print current results to file");
        System.out.println("--------------------------------------------------");
        System.out.println("Enter 0 to quit");
        System.out.println("--------------------------------------------------");
    }

    public static void main(String[] args) {
        int check = 0;
      String readFile;
        Scanner key = new Scanner(System.in);
        	System.out.println("Welcome to the Video Game Database.");
      Node head = null; // Head loop
        Node current = null; // Current loop
        		int MAX_SIZE = 100000;
      Node[] searchResultsArray = new Node[MAX_SIZE];
        	int searchResultsCount = 0;
        while (check == 0)
        {
            printChoices();
          int answer = key.nextInt();
            key.nextLine();
            	if (answer == 1)
            {
                System.out.println("A file? What is the name of it?");
             readFile = key.nextLine();
                try {
                 Scanner fileScanner = new Scanner(new File(readFile)); 
                    head = new Node(readFile, readFile);
                 String temp = fileScanner.nextLine();
                    head.setGameName(temp.substring(0, temp.indexOf("\t")));
                    head.setConsole(temp.substring(temp.indexOf("\t") + 1)); // Increment index to skip '\t'
                 current = head;

                    while (fileScanner.hasNext())
                    {
                        temp = fileScanner.nextLine();
                        Node newNode = new Node(temp, temp);
                        newNode.setGameName(temp.substring(0, temp.indexOf("\t")));
                        newNode.setConsole(temp.substring(temp.indexOf("\t") + 1));
                        current.setNext(newNode);
                        current = newNode;
                    }     
                }
                catch (Exception e)
                {
                    System.out.println("Looks like the file name is invalid, please enter the correct file.");
                }
            }
            if (answer == 2) 
            {
                if (head != null)
                { 
                    System.out.println("Enter the name of the game you want to search for... (or '*' for all):");
                 String searchGame = key.nextLine();
                    System.out.println("Enter the name of the console you want to search for... (or '*' for all):");
                    Node temp = head;            
                 String searchConsole = key.nextLine();
                 	while (temp != null) 
                    {
                        if ((searchGame.equals("*") || temp.getGameName().toUpperCase().contains(searchGame.toUpperCase()))
                                && (searchConsole.equals("*") || temp.getConsole().toUpperCase().contains(searchConsole.toUpperCase()))) 
                        {
                            System.out.println(temp.getGameName() + " / " + temp.getConsole());  
                            searchResultsArray[searchResultsCount++] = new Node(temp.getGameName(), temp.getConsole()); 
                        }
                        temp = temp.getNext();
                    }
                }
                else
                    System.out.println("No results :|, check if the there is a spelling error.");
            }
            
            if(answer == 3)
            {
                System.out.println("Results of the search:");
                for (int i = 0; i < searchResultsCount; i++) 
                {
                    if(searchResultsArray[i]!=null)
                        System.out.println(searchResultsArray[i].getGameName() + " / " + searchResultsArray[i].getConsole());
                }
            }

            if(answer == 4)
            {
                if(searchResultsCount>0)
                {
                System.out.println("Enter the File name:");
                String newFile = key.nextLine();
                try
                {
                    FileWriter fileWriter = new FileWriter(newFile);
                    for(int i = 0; i < searchResultsCount; i++)
                    {
                        fileWriter.write(searchResultsArray[i].getGameName() + "\t" + searchResultsArray[i].getConsole()+"\n");
                    }
                    fileWriter.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
              }
            }
            else
                System.out.println("There are no search results to print, look for something to add to new file..");
            }

            if (answer == 0)
            {
                System.out.println("That's it??? Alrigh then, Have a good day :)");
                check = 1;
            }
        }
    }    
}

class Node {
    String console;
    String game;
    Node next;

    public Node(String aGameName, String aConsole) 
    {
        this.console = aConsole;
        this.game = aGameName;
    }

    void setConsole(String aConsole)
    {
        this.console = aConsole;
    }

    void setGameName(String aGameName)
    {
        this.game = aGameName;
    }

    void setNext(Node aNext)
    {
        this.next = aNext;
    }

    String getConsole()
    {
        return this.console;
    }

    String getGameName()
    {
        return this.game;
    }

    Node getNext()
    {
        return this.next;
    }  
}