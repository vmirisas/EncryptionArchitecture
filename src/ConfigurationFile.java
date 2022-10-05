import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ConfigurationFile {

    private String configureFileLocation = "";
    private ArrayList<String> configuredFields = new ArrayList<>();

    private ArrayList<Integer> sameFieldIdexes = new ArrayList<>();



    public void ConfigurationFileChoice(String configureFileLocation){
        //System.out.println("Choose the configuration file");
        //BufferedReader br = new BufferedReader(new InputStreamReader(configureFileLocation));

//        try {
//            configureFileLocation = br.readLine();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        try {
            Scanner configurationReader = new Scanner(new BufferedReader(new FileReader(configureFileLocation)));
            String fieldsToConfigureString = configurationReader.nextLine();
            String[] fieldsToSeparateFromConfigureFile = fieldsToConfigureString.split("\t");
            //System.out.println(Arrays.toString(fieldsToSeparateFromConfigureFile));
            for (String field : fieldsToSeparateFromConfigureFile) {
                this.configuredFields.add(field);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public ArrayList<Integer> SameFieldIndex(ArrayList dataFields, ArrayList configuredFields){
        ArrayList<Integer> sameFieldIndex = new ArrayList<>();
        for(int i = 0; i < dataFields.size(); i++){
            for (int j = 0; j < this.configuredFields.size(); j++){
                if(dataFields.get(i).equals(configuredFields.get(j))){
                    sameFieldIndex.add(dataFields.indexOf(dataFields.get(i)));
                }
            }
        }
        return sameFieldIndex;
    }

    public ArrayList<String> getConfiguredFields() {
        return configuredFields;
    }


}
