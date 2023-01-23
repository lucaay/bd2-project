import net.minidev.json.JSONArray;
import net.minidev.json.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class LocationData {

    JSONArray getStateData(){
        try {

            URL url = new URL("https://roloca.coldfuse.io/judete");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int response = conn.getResponseCode();

            if (response != 200) {
                throw new RuntimeException("HttpResponseCode: " + response);
            } else {

                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                //Close the scanner
                scanner.close();

                //Using the JSON simple library parse the string into a json object
                JSONParser parse = new JSONParser();
                JSONArray data_arr = (JSONArray) parse.parse(inline);


//                System.out.println(data_arr);
                return data_arr;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    JSONArray getCityData(String stateInitials){
        try {

            URL url = new URL("https://roloca.coldfuse.io/orase/" + stateInitials);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int response = conn.getResponseCode();

            if (response != 200) {
                throw new RuntimeException("HttpResponseCode: " + response);
            } else {

                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                //Close the scanner
                scanner.close();

                //Using the JSON simple library parse the string into a json object
                JSONParser parse = new JSONParser();
                JSONArray data_arr = (JSONArray) parse.parse(inline);


//                System.out.println(data_arr);
                return data_arr;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
