public class Driver implements Comparable<Driver> {
    private String name;
    private double area;
    private static String comparisonVar = "name"; 

    // Constructors
    public Driver() {
        this.name = "";
        this.area = 0.0;

    }
    public Driver(String name){
        this.name = name;
        this.area = 0.0;
    }

    // Accessors
    public String getName() {
        return name;
    }
    public double getArea(){
        return area;
    }

    // Mutators
    public void setName(String name){
        this.name = name;
    }
    public void setArea(double area){
        this.area = area;
    }

    // CompareTo 
    public int compareTo(Driver other) {
        if (comparisonVar.equals("name")) {
            return this.name.compareTo(other.name);
        } else {
            return Double.compare(this.area, other.area);
        }
    }

    // toString method
    public String toString(){
        return "Driver: [Name: " + name + ", Area: " + area + "]";
    }
}