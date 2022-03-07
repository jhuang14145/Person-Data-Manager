public class Person{
    /**
     * 
     * Person Object instance variables
     */
    int age;
    double height, weight;
    String name, gender;


    /**
     * 
     * @param n T
     * @param g
     * @param a
     * @param h
     * @param w
     * Parameters are for the default constructors when you do a new Person() object in line.
     */
    public Person(String n, String g, String a, String h, String w) throws NumberFormatException{
        //try{
            name = n;
            gender = g;
            age = Integer.parseInt(a);
            height = Double.parseDouble(h);
            weight = Double.parseDouble(w);
        //}
        // catch(NumberFormatException e){
        //     System.out.println("Error: Incorrect format");
        //     return;
        // }
    }

    /**
     * returns a string of all of the class variables
     */
    public String toString(){
        return (name + " " + gender + " " + age + " " + height + " " + weight);
    }

    /**
     * 
     * @param age
     * age setter
     */
    public void setAge(int age){
        this.age = age;
    }

    /**
     * Height setter
     * @param height
     */
    public void setHeight(double height){
        this.height = height;
    }

    /**
     * weight setter
     * @param weight
     */
    public void setWeight(double weight){
        this.weight = weight;
    }

    /**
     * name setter
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * gender setter
     * @param gender
     */
    public void setGender(String gender){
        this.gender = gender;
    }

    /**
     * returns the age
     * @return
     */
    public int getAge(){
        return age;
    }

    /**
     * returns the height
     * @return
     */
    public double getHeight(){
        return height;
    }

    /** returns the weight */
    public double getWeight(){
        return weight;
    }
    /**
     * returns the name
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     * returns the gender
     * @return
     */
    public String getGender(){
        return gender;
    }

}