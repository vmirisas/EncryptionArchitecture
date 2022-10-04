import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ConfigurationFile {

    String configureFileLocation = null;
    ArrayList<String> configuredFields = new ArrayList<>();

    public void ConfigurationFileChoice(){
        System.out.println("Choose the configuration file");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            configureFileLocation = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Scanner configurationReader = new Scanner(new BufferedReader(new FileReader(configureFileLocation)));
            String fieldsToConfigureString = configurationReader.nextLine();
            String[] fieldsToSeparateFromConfigureFile = fieldsToConfigureString.split("\t");
            for (String field : fieldsToSeparateFromConfigureFile) {
                configuredFields.add(field);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static ArrayList<Integer> SameFieldIndex(ArrayList dataFields, ArrayList configuredFields){
        ArrayList<Integer> sameFieldIndex = new ArrayList<>();
        for(int i = 0; i < dataFields.size(); i++){
            for (int j = 0; j < configuredFields.size(); j++){
                if(dataFields.get(i).equals(configuredFields.get(j))){
                    sameFieldIndex.add(dataFields.indexOf(dataFields.get(i)));
                }
            }
        }
        return sameFieldIndex;
    }






}
