import java.util.Scanner;

import javax.swing.JOptionPane;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
class PersonManager{
    /**
     * Yeah it's the main yk
     * @param args
     */
    public static void main(String [] args){
        System.out.println("Welcome to the PersonDataManager");
        System.out.println("--------------------------------");
        System.out.println("Please select an option:\n");

        /**
         * This is the scanner for taking in input values from the user
         */
        Scanner s1 = new Scanner(System.in);
        char c;
        PersonDataManager obj = new PersonDataManager();
        // this integer is for checking if the csv file has already been read from in order to prevent corruption of the session
        int already_built = 0;
        
        // strings to take values from
        String n,g;
        String a;
        String h,w;
        char c1;
        loop:
        while(true){
            System.out.println("---------------------------------");
            System.out.println("------- Input a Character -------");
            System.out.println("(I) - Import from File\n(A) - Add Person\n(R) - Remove Person\n(G) - Get Info on Person\n(P) - Print Table\n(S) - Save to File\n(Q) - Quit\n");
            System.out.println("---------------------------------");
            // this is the character that the user input is stored in
            c = s1.next().charAt(0);
            switch(c){
                /**
                 * case I, will build initially, and once built, it will set the flag to not ever rebuild in that one session
                 */
                case ('I'): 
                            if(already_built == 0){
                                obj.buildFromFile("biostats.csv");
                                already_built = 1;
                                break;
                            }
                            if(already_built == 1){
                                System.out.println("The data has already been built.");
                                break;
                            }
                            break;
                case ('i'): 
                            if(already_built == 0){
                                obj.buildFromFile("biostats.csv");
                                already_built = 1;
                                break;
                            }
                            if(already_built == 1){
                                System.out.println("The data has already been built.");
                                break;
                            }
                            break;
                /**
                 * This will ask the user for 5 inputs, the name, age, gender, height, and weight. It will take that value and then add it to the array
                 * if there is a duplicate, it will throw a PersonAlreadyExistsException
                 */
                case ('a'): 
                            System.out.println("Please enter the name of the person: ");
                            n = "\"" + (s1.next()) + "\"";
                            System.out.println("Please enter the age: ");
                            a = (s1.next());
                            System.out.println("Please enter the gender (M or F): ");
                            c1 = s1.next().charAt(0);
                            /**
                             * Since the csv is formatted with quotes around the Strings, this is to keep that format
                             */
                            if(c1 == 'M' || c1 == 'm'){
                                g = ("\"M\"");
                            }
                            else if (c1 == 'F' || c1 == 'f'){
                                g =("\"F\"");
                            }
                            else{
                                g = ("\"?\"");
                            }
                            System.out.println("Please enter the height (in inches): ");
                            h = (s1.next());
                            System.out.println("Please enter the weight (in lbs): ");
                            w = (s1.next());
                            try{
                                obj.addPerson(new Person(n,g,a,h,w));
                            }
                            catch(PersonAlreadyExistsException e){
                                System.out.println("Person already exists.");
                            }
                            catch(NumberFormatException e){
                                System.out.println("Error - Incorrect Input!");
                            }
                            break;
                case ('A'):
                            System.out.println("Please enter the name of the person: ");
                            n = "\"" + (s1.next()) + "\"";
                            System.out.println("Please enter the age: ");
                            a = (s1.next());
                            System.out.println("Please enter the gender (M or F): ");
                            c1 = s1.next().charAt(0);
                            if(c1 == 'M' || c1 == 'm'){
                                g = ("\"M\"");
                            }
                            else if (c1 == 'F' || c1 == 'f'){
                                g =("\"F\"");
                            }
                            else{
                                g = ("\"?\"");
                            }
                            System.out.println("Please enter the height (in inches): ");
                            h = (s1.next());
                            System.out.println("Please enter the weight (in lbs): ");
                            w = (s1.next());
                            try{
                                obj.addPerson(new Person(n,g,a,h,w));
                            }
                            catch(PersonAlreadyExistsException e){
                                System.out.println("Person already exists.");
                            }
                            catch(NumberFormatException e){
                                System.out.println("Error - Incorrect Input!");
                            }
                            break;
                /**
                 * Calls the remove person function based on the name a user inputs
                 */
                case ('R'):
                            System.out.println("Please enter the name of the person: ");
                            try{
                                obj.removePerson("\"" + s1.next() + "\"");
                            }
                            catch(PersonDoesNotExistException e){
                                System.out.println("Person does not exist in the list");
                            }
                            break;
                case ('r'):
                            System.out.println("Please enter the name of the person: ");
                            try{
                                obj.removePerson("\"" + s1.next() + "\"");
                            }
                            catch(PersonDoesNotExistException e){
                                System.out.println("Person does not exist in the list");
                            }
                            break;
                /**
                 * searches and prints out a String is the person searching is found
                 */
                case ('G'):
                            System.out.println("Please enter the name of the person to find: ");
                            try{
                                obj.getPerson("\"" + s1.next() + "\"");
                            }
                            catch(Exception e){
                                System.out.println("Person does not exist in the list");
                            }
                            break;
                case ('g'): 
                            System.out.println("Please enter the name of the person to find: ");
                            try{
                                obj.getPerson("\"" + s1.next() + "\"");
                            }
                            catch(Exception e){
                                System.out.println("Person does not exist in the list");
                            }
                            break;
                /**
                 * Loops through entire array of Person's and prints them out in a format
                 */
                case ('P'):
                            for(int i = 0; i < obj.num_persons; i++){
                                System.out.println(obj.people[i].getName() + ", " + obj.people[i].getGender() + ", " + obj.people[i].getAge() + ", " + obj.people[i].getHeight() + ", " + obj.people[i].getWeight());
                            }
                            break;
                case ('p'):
                            for(int i = 0; i < obj.num_persons; i++){
                                System.out.println(obj.people[i].getName() + ", " + obj.people[i].getGender() + ", " + obj.people[i].getAge() + ", " + obj.people[i].getHeight() + ", " + obj.people[i].getWeight());
                            }
                            break;
                /**
                 * Calls the saveToFile() function to overwrite the csv file.
                 */
                case ('S'):
                            saveToFile(obj, "biostats.csv");
                            break;
                case ('s'):
                            saveToFile(obj, "biostats.csv");
                            break;
                /**
                 * Exits the while(true) loop
                 */
                case ('Q'):
                            break loop;
                case ('q'):
                            break loop;
            }
        }
        s1.close();
    }

    /**
     * saves Person array to csv file.
     * Takes is the PersonDataManager object created storing the data, and the file path name
     * @param p
     * @param file_name
     */
    public static void saveToFile(PersonDataManager p, String file_name){
        try{
            /**
             * Using FileWriter, BufferedWritter, and PrintWriter, it can take it the entire line of text and print it to a file
             */
            FileWriter fw = new FileWriter(file_name, false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println("\"Name\",     \"Sex\", \"Age\", \"Height (in)\", \"Weight (lbs)\"");
            for(int i = 0; i < p.num_persons; i++){
                pw.println(p.people[i].getName() + ",     " + p.people[i].getGender() + ", " + p.people[i].getAge() + ", " + p.people[i].getHeight() + ", " + p.people[i].getWeight());
            }
            pw.flush();
            pw.close();
            System.out.println("File Saved");

        }
        catch(Exception e){
            System.out.println("File Error: File not Saved");
        }
    }

}