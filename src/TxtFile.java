import Interfaces.FileManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TxtFile implements FileManager {

    Scanner scanner = new Scanner(System.in);
    ArrayList<String> dataFields = new ArrayList<>();
    ArrayList<String> dataValues = new ArrayList<>();



    @Override
    public void Import(){
        System.out.println("Choose the data file for encryption");
        String fileLocation = scanner.nextLine();

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(fileLocation)));
            String fields = scanner.nextLine();
            String[] fieldsToSeparate = fields.split("\t");
            for (String field : fieldsToSeparate) {
                dataFields.add(field);
            }

            while(scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] dataValuesString = input.split("\t");
                for(String dataValue : dataValuesString){
                    dataValues.add(dataValue);
                }
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    int counter = 0;

    public String[][] DataValuesMatrixConstructor(int rows, int columns){
        String[][] dataValuesMatrix = new String[rows][columns];

        for(int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                dataValuesMatrix[i][j] = dataValues.get(counter);
                counter++;
            }
        }
        return dataValuesMatrix;
    }

    @Override
    public void Export(){

    }
}
