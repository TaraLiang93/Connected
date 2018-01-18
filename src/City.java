import java.util.HashMap;
import java.util.Map;

/**
 * Created by tara on 1/17/18.
 */
public class City {
    public String cityName;
    public Map<String, City> connection = new HashMap<String, City>();

    public City(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public Map<String, City> getConnection() {
        return connection;
    }

    public void addConnection(City city){
        if(this.connection.containsValue(city) == false) {
            this.connection.put(city.getCityName(),city);
        }
    }

    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                ", dict=" + connection.keySet()+
                '}';
    }
}
