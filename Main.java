package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    /*
        LAB 3 HIGH GRADE ASSIGNMENT

        BinarySearchST, BST, and Queue classes are Sedgewick&Wayne's implementations
        ResArray is an Object that stores a self resizing String array.

        This program first builds a data structure which allows for easy retrieval of the k:th most common words as well as the k:th to n:th most common words.
        -The first algorithm used in the process is the BST, in which we put the words from the text file as keys and calculate their occurrence count with subsequent insertions.
        -Then we use a BinarySearchST (bsST) to store the elements. We transfer the data from the BST by iterating through it, and placing all the elements in the new bsST
         The keywords used int the bsST are occurrences and the values stored are a custom object based on a self resizing String array.
         This is so we can store words that have the same number of of occurrences with the same keyword. When the bsST is filled, we count the number of unique occurrence counts for the words.
         When we want to find the k:th most common word(s), we call the valueAt(i) method which will return the String array at the position i in the value array in the bsST.
         The K:th most common element will be k:th element from the end of the value array in the bsST, because it is sorted. Because an array access at an index in an array has constant
         time complexity, this data structure satisfies the requirements from the assignment description.

         USER GUIDE:
         When the program runs, the data structure will be constructed. When texts starts to be printed to stdOut,
         input 1 to choose to find the k:th most common element.
         Input 2 to choose to find the k:th to n:th most common element.
         Input 3 to choose to close out the program. It will continue to run indefinitely unless explicitly terminated

     */


    public static void main(String[] args) {

        //read the text file leipzig1m.txt with scanner sc
        Scanner sc = null;

        try {
            sc = new Scanner(new File("leipzig1m.txt"));
        } catch(FileNotFoundException e) {
            System.out.println("file leipzig1m.txt was not found");
            System.exit(0);
        }



        //create a Binary Search Tree with keys as Strings and values as Integers

        BST<String, Integer> tree = new BST<>();

        //read the word from the text file.place each word as a key in the BST.
        //The value accompanying each keyword is the words number of occurrences in the text

        while(sc.hasNext()) {

            String word = sc.next();

            if(tree.contains(word)){
                int count = tree.get(word);
                count++;
                tree.put(word,count);
            } else {
                tree.put(word, 1);
            }

        }

        //create a sorted array Search Table where the keys are the occurrences of the words, and the values are self-readjusting String arrays.
        //If two or more words occur the same amount of times, we store them under the same keyword (occurrences) in the self-readjusting String array.


            BinarySearchST<Integer, ResArray> st = new BinarySearchST<Integer, ResArray>();

        for(String word: tree.keys()) {

            int occurrences = tree.get(word);

            if(st.contains(occurrences)){

                ResArray theWords = st.get(occurrences);
                theWords.add(word);

                st.put(occurrences, theWords );
            } else {

                ResArray theWords = new ResArray();
                theWords.add(word);
                st.put(occurrences, theWords );

            }

        }

        //iterate through the whole Search Table array and count the number of different keys (occurrences of words)

        int nrOfIndexes = 0;

        for(int i: st.keys()){

            nrOfIndexes++;

        }


        while(true) {

            System.out.println("\n********************************************************");
            System.out.println("Choose what mode you want!\n");
            System.out.println("To find the k:th most common word:           press 1");
            System.out.println("To find the k:th to n:th most common words:  press 2");
            System.out.println("To exit the program:                         press 3");
            System.out.println("********************************************************\n");

            Scanner input = new Scanner(System.in);

            int in = input.nextInt();

            if (in == 1) {
                System.out.println("Input a number between 1 and " + (nrOfIndexes - 1));


                int num = input.nextInt();

                if (num >= 1 && num < (nrOfIndexes)) {

                    //find the words at a chosen position with method valueAt() which returns the value stored in a slot the the BinarySearchST's value array.
                    //Because of this, this method has constant time complexity.

                    ResArray newResArray = st.valueAt(nrOfIndexes-num);
                    System.out.println("The " + num + " most common words are: \n" + newResArray.toString());
                    continue;

                } else {
                    System.out.println("input value out of bounds, restarting selection");
                    continue;
                }

            } else if(in == 2) {

                System.out.println("What position should we start at? (from 1 to " + (nrOfIndexes - 1) + ")");

                int num = input.nextInt();

                if (num >= 1 && num < (nrOfIndexes)) {
                    System.out.println("What position should we stop at? (from " + num + " to " + (nrOfIndexes - 1) + ")");

                    int num2 = input.nextInt();

                    if(num2 >= num && num2 < nrOfIndexes){

                        int i = 0;

                        while(num <= num2){

                            //find the words at a chosen position with method valueAt() which returns the value stored in a slot the the BinarySearchST's value array.
                            //Because of this, this method has constant time complexity.

                            ResArray newResArray = st.valueAt(nrOfIndexes-(num+i));
                            System.out.println("The " + num + ":th most common words are: " + newResArray.toString());
                            num++;
                        }


                    } else {
                        System.out.println("input value out of bounds, restarting selection");
                        continue;

                    }


                } else {
                    System.out.println("input value out of bounds, restarting selection");
                    continue;
                }

            } else if(in == 3) {

                System.out.println("exiting program!");
                break;
            } else{

                System.out.println("input value out of bounds, restarting selection");
                continue;

            }
        }







    }
}
