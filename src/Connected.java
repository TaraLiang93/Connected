import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tara on 1/17/18.
 */
public class Connected {
    public static void main(String [] args){
        try{
            if(args.length<3){
                throw new IndexOutOfBoundsException();
            }
            BufferedReader f = new BufferedReader(new FileReader(args[0]));
            Map<String, City> cityList = new HashMap<String, City>(); //a list of all the city in this world

            String line;
            while((line = f.readLine())!= null){
                addCitiesnConnection(cityList,line);
            }
            //all connection are made here
            if((cityList.containsKey(args[1])==false)||(cityList.containsKey(args[2]))==false){
                //if the orgin or dest does not exist in the city list return no and end program
                System.out.println("No");
                System.exit(1);
            }
            if(args[1].equalsIgnoreCase(args[2])){
                //origin and dest are the same city
                System.out.println("Yes");
                System.exit(1);
            }

            ArrayList<String> visited = new ArrayList<String>();
            visited.add(args[1]);
            City origin = cityList.get(args[1]);
            if(origin.getConnection().containsKey(args[2])){
                System.out.println("Yes");
                System.exit(1);
            }

                System.out.println(visited.toString());
        }catch(IOException e){//no such file
            System.out.println("No");
            e.printStackTrace();
        }catch(ArrayIndexOutOfBoundsException e){//not enough input args
            System.out.println("Missing input args\nNo");
            e.printStackTrace();
        }

    }

    public static void addCitiesnConnection(Map<String, City> cityList, String input){
        try{
            String[] cities = input.trim().split("\\s*,\\s*");
            if(cities.length>2){ //if there is a mistake in tokenizing the line
                throw new errorLoadingCity(input);
            }
            City temp;
            if(cityList.containsKey(cities[0]) == false){
                // city in the input is not in the list of existing cities.
                // Create the obj for city and add to list
                //if the city exist in the list then don't add to list
                temp = new City(cities[0]);
                cityList.put(temp.getCityName(), temp);
            }
            if(cityList.containsKey(cities[1]) == false){
                //sec city in the line is not in the list of existing cities.
                // Create the obj for city and add to list
                //if the city exist in the list then don't add to list
                temp = new City(cities[1]);
                cityList.put(temp.getCityName(), temp);
            }
            //the two cities from input is in the list create the connection between them
            addConnection(cityList, cities);

        }catch(errorLoadingCity e){
            System.out.println(e.getMessage());
        }
    }

    public static void addConnection(Map<String, City> cityList, String[] cities){
        cityList.get(cities[0]).addConnection(cityList.get(cities[1]));//get the first city and add the connection to the sec city from input
        cityList.get(cities[1]).addConnection(cityList.get(cities[0]));
    }
}
