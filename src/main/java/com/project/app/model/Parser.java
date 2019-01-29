package com.project.app.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author      Dominika Kunc
 * @use        Class that converts *.csv file to an ArrayList of Word class objects.
 * @version     1.0
 */
public class Parser {

    /**
     *
     * @param filePath path of file to parse
     * @return ArrayList of parsed Word class objects
     */
    private  ArrayList<Word> parse(String filePath)
    {

        /**
         * ArrayList of parsed Word class objects
         */
        ArrayList<Word> parsed = new ArrayList<Word>();
        /**
         * FileReader used to reading from file
         */
        FileReader fr = null;
        /**
         * String, which stores currently read line of file
         */
        String line = "";

        try
        {
            fr = new FileReader(filePath);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error: opening file");
            System.exit(1);
        }


        BufferedReader br = new BufferedReader(fr);

        // reading from file line by line and aparsedding words to ArrayList
        try
        {
            line = br.readLine();
            while(line != null)
            {
                /**
                 * Array of Strings used to separate words from *.csv file line with ";" delimiter
                 */
                String[] separated = line.split(";");
                /**
                 * New object of Word class used while creating ArrayList of parsed words
                 */
                Word newWord = new Word(separated[0], separated[1], separated[2], Integer.parseInt(separated[3]), separated[4]);
                parsed.add(newWord);
                line = br.readLine();
            }
        }
        catch (IOException e)
        {
            System.out.println("Error: reading from file");
            System.exit(-1);
        }

        try
        {
            fr.close();
        }
        catch (IOException e)
        {
            System.out.println("Error: closing FileReader");
            System.exit(-1);
        }
        return parsed;
    }

    public static void main(String[] args){
        String filePath = "";
        if(args.length == 0)
        {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Please specify file path: ");
            filePath = scanner.nextLine();

        }
        else
        {
            filePath = args[0];
        }
        Parser p = new Parser();
        ArrayList<Word>  words  = p.parse(filePath);
        DictionaryAccessor wordAccesor;


        //add array of objects to dictionary
        wordAccesor = new DictionaryAccessor();
        wordAccesor.addArrayListOfWordsToJSON(words);




    }
}