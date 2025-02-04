import java.util.ArrayList;

public class City {
    private int id;
    private double x_coord;
    private double y_coord;
    private static ArrayList<City> cityList = new ArrayList<>();

    public City(int id, double x_coord, double y_coord){
        this.id = id;
        this.x_coord = x_coord;
        this.y_coord = y_coord;
        cityList.add(this);
    }

    public City(String line){
        while (line.charAt(0) == ' ')
            line = line.substring(1, line.length());
        line = line.replace("   ", ",");
        line = line.replace("  ", ",");
        String[] splitted = line.split(",");
        this.id = Integer.parseInt(splitted[0]);
        this.x_coord = Double.parseDouble(splitted[1]);
        this.y_coord = Double.parseDouble(splitted[2]);
        cityList.add(this);
    }

    public static void printCityList(){
        for (City city : cityList) {
            System.out.println(city.id);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getX_coord() {
        return x_coord;
    }

    public void setX_coord(double x_coord) {
        this.x_coord = x_coord;
    }

    public double getY_coord() {
        return y_coord;
    }

    public void setY_coord(double y_coord) {
        this.y_coord = y_coord;
    }

    public static ArrayList<City> getCityList() {
        return cityList;
    }

    public static void setCityList(ArrayList<City> cityList) {
        City.cityList = cityList;
    }
}
