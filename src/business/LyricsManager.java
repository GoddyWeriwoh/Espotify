package business;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class LyricsManager extends Thread{
    private String title;
    private String author;
    private String currentLyrics;

    public void run() {
        try {
            //PUBLIC API:
            //https://api.lyrics.ovh/v1/<Artist>/<SongName>
            author = author.replaceAll(" ", "_");
            title = title.replaceAll(" ", "_");
            URL link = new URL("https://api.lyrics.ovh/v1/" + author +"/"+ title);
            System.out.println(link);

            HttpURLConnection conn = (HttpURLConnection) link.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();
            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                String inline = "";
                Scanner scanner = new Scanner(link.openStream());

                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }
                scanner.close();

                //Using the JSON simple library parse the string into a json object
                JsonParser parse = new JsonParser();
                JsonObject data_obj = (JsonObject) parse.parse(inline);

                //Get the required object from the above created object
                String lyrics = data_obj.get("lyrics").getAsString();

                //Get the required data using its key
                currentLyrics = lyrics;
                System.out.println(lyrics);
            }
        } catch  (Exception e) {
            e.printStackTrace();
        }
    }

    public void setInfo(String title, String author){
        this.author = author;
        this.title = title;
    }

    public String getCurrentLyrics() {
        return currentLyrics;
    }
}
