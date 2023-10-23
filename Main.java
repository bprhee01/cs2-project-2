//Ben Rhee
//bpr210000
// Still need pseudocode
/*
 * The main function first handles obtaining an input file containing driver info
 * if not found, main throw an exception
 * If found, the function while iterativelt add drivers to a linked list as well as calculate area
 * Once completeted, main will print out every driver
 * 
 * The next part of main will obtain an input file containing cmd info
 * If not found, main will throw an exception
 * If found, main will handle each case
 */

import java.io.*;
import java.util.*;


import LinkedList.Node;
import LinkedList.LinkedList;
import Driver.Driver;

public class Main {

    public static void main(String[] args){

        //intiializing data structures for coordinates, driver, and indiced based off rubric
        LinkedList driverList = new LinkedList();
        int numDrivers = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file name: " );
        String fileName = scanner.nextLine();

        try {
            Scanner inputFile = new Scanner(new File(fileName));
            while(inputFile.hasNext()){
                String driverInputString[] = getStringArray(inputFile.nextLine());
                addDriver(driverInputString, driverList);
                numDrivers += 1;
            }
            inputFile.close();
            scanner.close();
        }
        // Case where input file not found
        catch(FileNotFoundException e){
            System.err.println("File not found: " + fileName);
        }

        //The code for the second input file
        Scanner scannerTwo = new Scanner(System.in);
        System.out.print("Enter file name: " );
        String fileNameTwo = scanner.nextLine();
        try {
            Scanner inputFile = new Scanner(new File(fileNameTwo));
            while(inputFile.hasNext()){
                String commandString[] = getStringArray(inputFile.nextLine());
                if (commandString[0] == "sort"){
                    if(commandString.length != 3){
                        continue;
                    }
                    driverList.sort();
                    if(commandString[1] == "area"){
                        if (commandString[2] == "asc"){
                            Node curr = driverList.getHead();
                            while (curr !=  null){
                                System.out.println(curr.getPayload().toString());
                                curr = curr.getNext();
                            }
                        }
                        else if(commandString[2] == "des"){
                            Node curr = driverList.getTail();
                            while (curr !=  null){
                                System.out.println(curr.getPayload().toString());
                                curr = curr.getPrevious();
                            }
                        }
                    }
                    else if(commandString[1] == "driver"){
                        if (commandString[2] == "asc"){
                            Node curr = driverList.getHead();
                            while (curr !=  null){
                                System.out.println(curr.getPayload().toString());
                                curr = curr.getNext();
                            }
                        }
                        else if(commandString[2] == "des"){
                            Node curr = driverList.getTail();
                            while (curr !=  null){
                                System.out.println(curr.getPayload().toString());
                                curr = curr.getPrevious();
                            }
                        }
                    }
                    
                }
                else if(commandString[0].charAt(0) >= 65){
                    //check for name
                    String name = "";
                    for (int i = 0; i < commandString.length; i += 1){
                        name += commandString[i];
                    }
                    Node curr = driverList.getHead();
                    while(curr !=  null){
                        String currName = curr.getPayload().getName();
                        if (currName == name){
                            System.out.println(curr.getPayload().toString());
                            System.out.println();
                            break;
                        }
                        else{
                            curr = curr.getNext();
                        }
                        System.out.println(name + " not found");
                        System.out.println();
                    }
                }
                else{
                   //search the number
                   double area = Double.parseDouble(commandString[0]) ;
                   Node curr = driverList.getHead();
                    while(curr !=  null){
                        double currArea = curr.getPayload().getArea();
                        if (currArea == area){
                            System.out.println(curr.getPayload().toString());
                            System.out.println();
                            break;
                        }
                        else{
                            curr = curr.getNext();
                        }
                        System.out.println(area + " not found");
                        System.out.println();
                    }
    
                }
                System.out.println();
            }

            inputFile.close();
            scanner.close();
        }
        catch(FileNotFoundException e){
            System.err.println("Second File not found: " + fileName);
        }
    }

    public static void addDriver(String inputString[], LinkedList driverList){
        int firstCoordIdx = 0;
        String name = "";
        int numCoords = 0;
        while (inputString[firstCoordIdx].charAt(0) >= 65){
            name += inputString[firstCoordIdx];
            firstCoordIdx += 1;
        }
        //setting area
        double area = 0.0;
        double firstX = Double.parseDouble(inputString[firstCoordIdx]);
        double firstY = Double.parseDouble(inputString[firstCoordIdx+1]);
        double prevX = firstX;
        double prevY = firstY;
        for(int i = firstCoordIdx+2; i < inputString.length; i += 2){
            double x = Double.parseDouble(inputString[i]);
            double y  = Double.parseDouble(inputString[i + 1]);
            area += ((prevX +x)) * (y - prevY);
            prevX = x;
            prevY = y;

        }
        area = Math.abs(area) / 2;
        if (firstX != prevX || firstY != prevY){
          return;
        }

        //setting driver
        Driver driver = new Driver(name);
        driver.setArea(area);
        String printString = name + "\t" + String.format("%.2f", area);
        System.out.println(printString);
        //Setting Node
        Node driverNode = new Node(driver);
        //Adding driver to Linked List
        driverList.add(driverNode);

    }

    //Function to generate string from inputFile that's clean (fixes edge cases)
    public static String[] getStringArray(String s){
        return s.split("[,\\s]");
    }



}