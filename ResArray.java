package com.company;

public class ResArray {

    String[] array;
    int nrOfElements;

    public ResArray(){
        array = new String[10];
        nrOfElements = 0;
    }

    public void add (String string) {

        if(nrOfElements < array.length){
            array[nrOfElements] = string;
            nrOfElements++;

        } else {

            String[] newArray = new String[nrOfElements*2];

            for(int i = 0; i <array.length; i++) {
                newArray[i] = array[i];
            }

            array = newArray;

            array[nrOfElements] = string;
            nrOfElements++;


        }

    }

    public String toString() {

        StringBuilder out = new StringBuilder();
        out.append("[");

        for(int i = 0; i<nrOfElements; i++) {
            out.append(array[i] + ",");
        }

        out.setLength(out.length() - 1);
        out.append("]");

        return out.toString();
    }


}
