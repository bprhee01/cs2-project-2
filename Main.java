//Ben Rhee
//bpr210000

/*
 * The main function first handles obtaining an input file containing driver info
 * if not found, main throws an exception
 * If found, the function while iterate through the files and add drivers to a linked list as well as calculate area
 * Once completeted, main will print out every driver
 * 
 * The next part of main will obtain an input file containing command info
 * If not found, main will throw an exception
 * If found, main will handle each case
 * CASES
 *sort by (area/name) in (ascending/descending order) 
 *find driver by name
 *find driver by area
 * 
 * OTHER FUNCTIONS MADE
 * addDriver(String inputString[], LinkedList<Driver> driverList) => void
 *  This function will parse the input string, first find the name of the driver
 *  Next, the function will calculate area based on the given formula. 
 *  If the first and last coordinate don't match, the function will exit and the driver wont be added
 *  A new node is now created with the Driver as the payload. 
 *  The node is now added to the LinkedList
 * 
 * getStringArray(String s) => string array
 *  This function will parse through the input line given from the file, and remove any characters that are unnecessary, 
 *      as well seperate inputs within each line
 * isAlphaNumericHyphenApostrophe(String str) => boolean
 *  Returns true if the input string only contains alphanumneric values, hypehens, and apostrophes (REGES expression)
 * isValidInteger(String str) => boolean
 *  Returns true if the input string is a valid integer (REGEX expression)
 */

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import LinkedList.Node;
import LinkedList.LinkedList;
import Driver.Driver;

public class Main {

    public static void main(String[] args){

        //intiializing Driver Linked List
        LinkedList<Driver> driverList = new LinkedList();
        int numDrivers = 0;

        // Requesting driver info from user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file name: " );
        String fileName = scanner.nextLine();

        try {

            //Part 1, Adding Driver Info to the List and Printing results
            Scanner inputFile = new Scanner(new File(fileName));
            while(inputFile.hasNext()){
                String driverInputString[] = getStringArray(inputFile.nextLine());
                addDriver(driverInputString, driverList);
                numDrivers += 1;
            }

            //Pring the results from processing the drivers
            String driverListString = driverList.toString();
            System.out.println(driverListString);
            inputFile.close();

            //Part 2, Executing instruction commands given by the user
            System.out.print("Enter file name: " );
            String fileNameTwo = scanner.nextLine();
            Scanner commandInputFile = new Scanner(new File(fileNameTwo));

            //Iterate through each command
            while (commandInputFile.hasNext()) {
                String commandString[] = getStringArray(commandInputFile.nextLine());

                //command for sorting
                if (commandString[0].equals("sort")){
    
                    //case where command isnt valid
                    if(commandString.length != 3) {
                        continue;
                    }
            
                    //sorting criteria
                    if (commandString[1].equals("area")) {
                        Driver.comparisonVar = "area";
                    }
                    else if(commandString[1].equals("driver")) {
                        Driver.comparisonVar = "name";
                    }
                    else{
                        continue;
                    }
                    driverList.sort();

                    //sorting direction
                    if  (commandString[2].equals("asc")) {
                        String driverListStringAsc = driverList.toString();
                        System.out.println(driverListStringAsc);
                    }
                    else if (commandString[2].equals("des")) {
                        StringBuilder sb = new StringBuilder();
                        Node<Driver> current = driverList.getHead();
                        while (current != null) {
                            sb.insert(0,current.getPayload().toString());
                            if (current.getNext() != null){
                                sb.insert(0,System.lineSeparator());
                            }else{
                                sb.append(System.lineSeparator());
                            }
                            current = current.getNext();
                        }
                        System.out.println(sb.toString());
                        
                    }
                    //invalid direction
                    else {
                        continue;
                    }
                    
                }

                //command asking for a specifc area
                else if (isValidInteger(commandString[0])) {
                    double targetArea = Double.parseDouble(commandString[0]) ;
                    Node<Driver> curr = driverList.getHead().getNext();
                    while(curr !=  null & curr != driverList.getTail()){
                        double currArea = curr.getPayload().getArea();
                        if (currArea == targetArea){
                            System.out.println(curr.getPayload().toString());
                            break;
                        }
                        else{
                            curr = curr.getNext();
                        }
                        System.out.println(commandString[0] + " not found");
                        System.out.println();
                    }
                }

                //command asking for a name
                else if (isAlphaNumericHyphenApostrophe(commandString[0])) {
                    String targetName = commandString[0];
                    Node<Driver> curr = driverList.getHead().getNext();
                    while(curr !=  null & curr != driverList.getTail()){
                        Driver driver = curr.getPayload();
                        String currName = driver.getName();
                        if (currName.equals(targetName)){
                            System.out.println(curr.getPayload().toString());
                            System.out.println();
                            break;
                        }
                        else{
                            curr = curr.getNext();
                        }
                        System.out.println(targetName + " not found");
                        System.out.println();
                    }
                }

            }
        }
        // Case where input file not found
        catch(FileNotFoundException e){
            System.err.println("File not found: " + fileName);

        }


    }

    public static void addDriver(String inputString[], LinkedList<Driver> driverList){
        try {
            // Construct the name of the driver and set the index of the first coordinate
            int firstCoordIdx = 0;
            String name = "";
            while (isAlphaNumericHyphenApostrophe(inputString[firstCoordIdx]) & firstCoordIdx < inputString.length){
                name += inputString[firstCoordIdx];
                firstCoordIdx += 1;
            }
    
            if(firstCoordIdx+1 >= inputString.length){
                return;
            }
    
            //setting area
            double area = 0.0;
            int firstX = Integer.parseInt(inputString[firstCoordIdx]);
            int firstY = Integer.parseInt(inputString[firstCoordIdx+1]);
            int prevX = firstX;
            int prevY = firstY;
            for(int i = firstCoordIdx+2; i < inputString.length; i += 2){
                int x = Integer.parseInt(inputString[i]);
                int y  = Integer.parseInt(inputString[i + 1]);
                area += ((prevX +x)) * (y - prevY);
                prevX = x;
                prevY = y;
    
            }
            area = Math.abs(area) / 2;

            //if the first and last coordinates dont match, this row is invalid
            if (firstX != prevX || firstY != prevY){
              return;
            }
    
            //setting driver
            Driver driver = new Driver(name);
            driver.setArea(area);
    
            //Setting Node
            Node<Driver> driverNode = new Node(driver);
            //Adding driver to Linked List
            driverList.add(driverNode);
            
        } catch (NumberFormatException e) {
            return; // Parsing failed, not a valid integer
        }

    }

    //Function to generate string from inputFile that's clean (fixes edge cases)
    public static String[] getStringArray(String s){
        return s.split("[,\\s]");
    }
    public static boolean isAlphaNumericHyphenApostrophe(String str) {
        // Define a regular expression pattern that matches alphanumeric, hyphen, or apostrophe
        Pattern pattern = Pattern.compile("^[a-zA-Z\\-']+$");
        Matcher matcher = pattern.matcher(str);

        // Check if the entire string matches the pattern
        return matcher.matches();
    }
    public static boolean isValidInteger(String str) {
        try {
            // Attempt to parse the string as an integer
            Integer.parseInt(str);
            return true; // If successful, it's a valid integer
        } catch (NumberFormatException e) {
            return false; // Parsing failed, not a valid integer
        }
    }




}