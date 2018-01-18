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

            String line = f.readLine();
            if(line == null){
                //empty file
                System.out.println("No");
                System.exit(1);
            }
            addCitiesnConnection(cityList,line);
            while((line = f.readLine())!= null){//keep reading untill the end
                addCitiesnConnection(cityList,line);
            }
            //all connections are made by this point
            if((cityList.containsKey(args[1])==false)||(cityList.containsKey(args[2]))==false){
                //if the orgin or dest city does not exist in the city list return no and end program
                System.out.println("No");
                System.exit(0);
            }
            if(args[1].equalsIgnoreCase(args[2])){
                //origin and dest are the same city
                System.out.println("Yes");
                System.exit(0);
            }

            ArrayList<String> visited = new ArrayList<String>();//record the list of visited cities
            City origin = cityList.get(args[1].trim().toLowerCase());
            City dest = cityList.get(args[2].trim().toLowerCase());

            searchConnection(origin, dest, visited);
            //by the time the code reach here there are no direct connection between two cities, and return no
            System.out.println("No");
            System.exit(0);

        }catch(IOException e){//no such file
            System.out.println("No");
            e.printStackTrace();
            System.exit(1);
        }catch(ArrayIndexOutOfBoundsException e){//not enough input args
            System.out.println("Missing input args\nNo");
            e.printStackTrace();            
            System.exit(1);
        }

    }

    public static void addCitiesnConnection(Map<String, City> cityList, String input){
        try{
            String[] cities = input.trim().toLowerCase().split("\\s*,\\s*");// remove all space and make all letter into lowercase
            if(cities.length!=2){ //if there extra cities or missing cities
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
                temp = new City(cities[1]);
                cityList.put(temp.getCityName(), temp);
            }
            //the two cities from input is in the list create the connection between them
            addConnection(cityList, cities);

        }catch(errorLoadingCity e){
            System.out.println(e.getMessage());
            System.out.println("No");
            System.exit(1);

        }
    }

    public static void addConnection(Map<String, City> cityList, String[] cities){
        cityList.get(cities[0]).addConnection(cityList.get(cities[1]));//get the first city and add the connection to the sec city from input
        cityList.get(cities[1]).addConnection(cityList.get(cities[0]));
    }

    public static void searchConnection(City ptr, City dest, ArrayList<String> v){
        v.add(ptr.getCityName());//mark current city as visited
        if(ptr.getConnection().containsKey(dest.getCityName())){
            //one of the connected city is the dest search ends in success
            System.out.println("Yes");
            System.exit(0);
        }
        for(City adj : ptr.getConnection().values()){
            if(v.contains(adj.getCityName())==false){
                searchConnection(adj, dest, v);
            }
        }


    }
}
