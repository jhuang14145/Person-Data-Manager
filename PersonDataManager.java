import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.util.Arrays;
//import java.security.cert.Extension;
//import java.util.Scanner;

class PersonDataManager{
    // array for all the person objects
    // the total amount of Persons in the people array
    Person [] people = new Person[1000];
    int num_persons = 0;

    /**
     * Takes in two things, the String that holds the file to be opened and throws argument excpetion if incorrect
     * @param location
     * @throws IllegalArgumentException
     */
    public void buildFromFile(String location) throws IllegalArgumentException{
        // the entire line read form the csv
        String line = "";
        // the token to separate and used to read and split each element
        String splitBy = ",";
        System.out.println("Importing data...");
        /**
         * These variables are for the BufferedReader object, which is something I used to properly read each line by line in the csv file
         * It helps be divide each segment by a defined separator, in this case it would be the comma.
         */
        /**
         * count people is temporary variable used to count total amoutn of people to be written initally and updates num_people which is the global variable
         * for counting how many people are in the array at a given time
         */
        int count_people = 0;
        /**
         * in order for formatting, since the first line in the array is used to tell the reader what each value in the csv is for,
         * i kept this in order to prevent taking in the titles for each element in the csv file
         */
        int initial = 1;
        try{
            // create and object to run through and read the entire single line of the csv
            BufferedReader br = new BufferedReader(new FileReader(location));
            // run through the entire file and add it.

            /**
             * This runs until you reach null, which would come from reaching the end of the csv file
             */
            while((line = br.readLine()) != null){
                try{

                    /**
                     * The split() function is what does the splycing of the entire line of the csv, it then divides it by the defines seaparator (the comma), and returns
                     * an array of strings that are read from the csv.
                     */
                    String [] one_person = line.split(splitBy);
                    /**
                     * initial title checker and skipper
                     */
                    if(initial == 1){
                        initial = 0;
                        continue;
                    }
                    // int age_temp = Integer.parseInt(one_person[2].trim());
                    // System.out.println(age_temp);
                    /**
                     * just text to show reader each person begin read
                     */
                    System.out.println("Reading Person " + (count_people+1) + "...");
                    // for(int i = 0; i < one_person.length; i++){
                    //     System.out.print(one_person[i] + ", ");
                    // }
                    /**
                     *  
                     * Adds each new person read in to the people[] array
                     * 
                     * */ 
                    people[count_people] = new Person(one_person[0].trim(), one_person[1].trim(), one_person[2].trim(), one_person[3].trim(), one_person[4].trim());
                    System.out.println(people[count_people].toString());
                    count_people++;
                }
                catch(NullPointerException e){
                    // pretty much garanteed to reach null for end of file, so this is to prevent that. it will always reach null and this is to stop erroa dn kicking
                    // out of the program
                    //System.out.println("error ptr");
                }
            System.out.println("Importing complete");
            }
            try{
                /**
                 * close the bufferedreader object
                 */
                br.close();
            }
            catch(IOException e){
                System.out.println("Enable to close file");
            }
        }
        catch(IllegalArgumentException e){
            System.out.println("Error: Incorrect format detected");
            e.printStackTrace();
        }
        catch(FileNotFoundException e){
            System.out.println("File does not exist.");
        }
        catch(Exception e){
            //System.out.println("error with reading file");
        }
        /**
         * this is what updates the total number of people in the array counter. very important to be used for fast iterations instead of 
         * having to loop through the entire array(could go up to thousands)
         */
        num_persons = count_people;
    }


    /**
     * Takes in a new Person objec to be stored
     * @param newPerson
     * @throws PersonAlreadyExistsException
     */
    public void addPerson(Person newPerson) throws PersonAlreadyExistsException{
        System.out.println("Adding person to list...");
        /**
         * This checks if the people array is at maxcapacity or not. If it is, then you would create a new array of Persons and then set it to the new list
         */
        if(num_persons == people.length){
            Person [] people_new = new Person[100000];
            for(int i = 0; i < num_persons; i++){
                people_new[i] = people[i];
            }
            people = people_new;
        }
        // checks if there is a person with the same data
        for(int i = 0; i < num_persons; i++){
            try{
                /**
                 * This is for if there is a copy with exact same stats
                 */
                if((people[i].getAge() == newPerson.getAge()) && (people[i].getGender().compareTo(newPerson.getGender()) == 0) && (people[i].getHeight() == newPerson.getHeight()) && (people[i].getName().compareTo(newPerson.getName()) == 0) && (people[i].getWeight() == newPerson.getWeight())){
                    System.out.println("Copy found");
                    throw new PersonAlreadyExistsException("Error: Person already found in database");
                }

            }
            catch(PersonAlreadyExistsException e){
                System.out.println("Duplicate person found...\n");
                System.out.println("Cannot add due to duplicate");
                return;
            }
        }
        int cit1,cit2;
        //int h = 1;
        // try{
        //     if(Integer.parseInt(newPerson.getName()))
        // }
        // catch(Exception e){

        // }



        /**
         * This is for comparing each character in each name to make sure that it is imputted in the proper alphabetical order
         */
        for(int j = 0; j < num_persons; j++){
            /**
             * comparing characters
             */
            cit1 = (int)people[j].getName().charAt(1);
            cit2 = (int)newPerson.getName().charAt(1);
            // if it's the same, move on
            if(cit1 == cit2){
                continue;
            }
            else if(cit1-cit2 > 0){
                for(int k = num_persons; k > j; k--){
                    people[k] = people[k-1];
                }
                people[j] = newPerson;
                num_persons++;
                return;
            }
        }
        people[num_persons] = newPerson;
        num_persons++;
        //Arrays.sort(people);
        System.out.println("Sucessfully added person to the list");
    }

    /**
     * returns a string of the entire person for lookup
     * @param name
     * @throws PersonDoesNotExistException
     */
    public void getPerson(String name) throws PersonDoesNotExistException{
        try{
            // loops through comparing names until you reach the end or the person is found
            for(int i = 0; i < num_persons; i++){
                if(people[i].getName().compareTo(name) == 0){
                    System.out.println(people[i].getName() + " is a " + people[i].getAge() + " year old " + people[i].getGender() + ", who is " + (int)(people[i].getHeight())/12 + " feet " + (int)(people[i].getHeight())%12 +" inches tall and weights " + people[i].getWeight() + " pounds.");
                    return;
                }
            }
        throw new PersonDoesNotExistException("Error: The person is not in the list");
        }
        catch(PersonDoesNotExistException e){
            System.out.println("The person you are trying to find does not exist in the list\n");
        }
    }

    /**
     * removes the person by looking up the name
     * @param name
     * @throws PersonDoesNotExistException
     */
    public void removePerson(String name) throws PersonDoesNotExistException{
        for(int i = 0; i < num_persons; i++){
            if(people[i].getName().compareTo(name) == 0){
                for(int j = i; j < num_persons-1; j++){
                    people[j] = people[j+1];
                    /**
                     * This operation above shifts the entire array down and decrements the index pointer from the last Person in the array
                     */
                }
                num_persons--;
                System.out.println("Person successfully removed");
                return;
            }
        }
        throw new PersonDoesNotExistException("Error");
    }

    /**
     * just prints out each person in the array
     */
    public void printTable(){
        for(int i = 0; i < num_persons; i++){
            System.out.println(people[i].toString());
        }
    }
}

