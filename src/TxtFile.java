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
    private String[][] dataValuesMatrix = new String[rows][columns];


    @Override
    public void Import(String inputFileLocation){
        //System.out.println("Choose the data file for encryption");
        //String fileLocation = scanner.nextLine();

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(inputFileLocation)));
            String fields = scanner.nextLine();
            String[] fieldsToSeparate = fields.split("\t");
            for (String field : fieldsToSeparate) {
                this.dataFields.add(field);
            }

            while(scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] dataValuesString = input.split("\t");
                for(String dataValue : dataValuesString){
                    this.dataValues.add(dataValue);
                }
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.rows = this.dataValues.size() / this.dataFields.size();
        this.columns = this.dataFields.size();
        PopulateDataValuesMatrix();

    }


    private void PopulateDataValuesMatrix(){
        int counter = 0;
        String[][] dataMatrix = new String[rows][columns];
        for (int i = 0; i < this.rows; i++){
            for (int j = 0; j < this.columns; j++){
                dataMatrix[i][j] = this.dataValues.get(counter);
                counter++;
            }
        }
        this.dataValuesMatrix = dataMatrix;
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                System.out.println(this.dataValuesMatrix[i][j]);
            }
        }
    }


    @Override
    public void Export(String outputFileDestination){

    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public ArrayList<String> getDataFields() {
        return dataFields;
    }

    public ArrayList<String> getDataValues() {
        return dataValues;
    }



}
