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

    private int rows;
    private int columns;
    private int counter = 0;
    private String[][] dataValuesMatrix = new String[rows][columns];



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

            System.out.println(dataFields);

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

        rows = dataValues.size() / dataFields.size();
        columns = dataFields.size();

        //String[][] dataValuesMatrix = new String[rows][columns];
        for(int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                this.dataValuesMatrix[i][j] = dataValues.get(counter);
                counter++;
            }
        }

    }



    @Override
    public void Export(){

    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }





}
