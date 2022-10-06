import Interfaces.Encryptor;
import Interfaces.FileManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TxtFile implements FileManager, Encryptor {

    Scanner scanner = new Scanner(System.in);
    private ArrayList<String> dataFields = new ArrayList<>();
    private ArrayList<String> dataValues = new ArrayList<>();
    private ArrayList<String> configuredFields = new ArrayList<>();
    private ArrayList<Integer> sameFieldIndexArray = new ArrayList<>();
    private String secretKey;
    private int rows;
    private int columns;
    private String[][] dataValuesMatrix = new String[rows][columns];
    private String[][] dataEncryptedValuesMatrix = new String[rows][columns];
    private String[][] dataDecryptedValuesMatrix = new String[rows][columns];

    @Override
    public void Import(String inputFileLocation){

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

    public void ConfigurationFileChoice(String configureFileLocation){
        try {
            Scanner configurationReader = new Scanner(new BufferedReader(new FileReader(configureFileLocation)));
            String fieldsToConfigureString = configurationReader.nextLine();
            String[] fieldsToSeparateFromConfigureFile = fieldsToConfigureString.split("\t");
            for (String field : fieldsToSeparateFromConfigureFile) {
                this.configuredFields.add(field);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    }

    public void SameFieldIndex(ArrayList dataFields, ArrayList configuredFields){
        for(int i = 0; i < dataFields.size(); i++){
            for (int j = 0; j < configuredFields.size(); j++){
                if(dataFields.get(i).equals(configuredFields.get(j))){
                    this.sameFieldIndexArray.add(dataFields.indexOf(dataFields.get(i)));
                }
            }
        }
    }

    @Override
    public void setSecretKey(String key) {
        this.secretKey = key;
    }

    @Override
    public void encrypt() {
        String[][] encryptedDataMatrix = new String[rows][columns];
        for(int i = 0; i < this.rows; i++){
            for (int j = 0; j < this.columns; j++){
                for (int k = 0; k < this.sameFieldIndexArray.size(); k++){
                    if(this.sameFieldIndexArray.get(k) == j){
                        encryptedDataMatrix[i][j] =  AESEncryptor.encrypt(this.dataValuesMatrix[i][j], this.secretKey) ;
                        break;
                    } else {
                        encryptedDataMatrix[i][j] = this.dataValuesMatrix[i][j];
                    }

                }
            }
        }
        this.dataEncryptedValuesMatrix = encryptedDataMatrix;
    }

    @Override
    public void decrypt() {
        String[][] decryptedDataMatrix = new String[rows][columns];
        for(int i = 0; i < this.rows; i++){
            for (int j = 0; j < this.columns; j++){
                for (int k = 0; k < this.sameFieldIndexArray.size(); k++){
                    if(this.sameFieldIndexArray.get(k) == j){
                        decryptedDataMatrix[i][j] =  AESEncryptor.decrypt(this.dataEncryptedValuesMatrix[i][j], this.secretKey) ;
                        break;
                    } else {
                        decryptedDataMatrix[i][j] = this.dataEncryptedValuesMatrix[i][j];
                    }

                }
            }
        }
        this.dataDecryptedValuesMatrix = decryptedDataMatrix;
    }

    @Override
    public void Export(String outputFileDestination, String[][] dataMatrix){
        String fileSeparator = File.separator;
        String exportDirectoryPath = fileSeparator + "Users" + fileSeparator + "vmagkounis" + fileSeparator + "Desktop" + fileSeparator + "Java_training" + fileSeparator + "EncryptionArchitecture" + fileSeparator + "src" + fileSeparator + "Files";
        try (BufferedWriter cypheredFile = new BufferedWriter(new FileWriter(exportDirectoryPath + fileSeparator + outputFileDestination))){
            String dataFieldsRow = "";
            for (int i = 0; i < dataFields.size(); i++){
                if (i < dataFields.size()-1){
                    dataFieldsRow += dataFields.get(i) + "\t";
                } else {
                    dataFieldsRow += dataFields.get(i) + "\n";
                }
            }
            cypheredFile.write(dataFieldsRow);

            for (int i = 0; i < rows; i++){
                String exportedRow;
                for (int j = 0; j < columns; j++) {
                    if (j < columns - 1) {
                        exportedRow = dataMatrix[i][j] + "\t";
                    } else {
                        exportedRow = dataMatrix[i][j] + "\n";
                    }
                    cypheredFile.write(exportedRow);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getDataFields() {
        return dataFields;
    }

    public ArrayList<String> getConfiguredFields() {
        return configuredFields;
    }

    public String[][] getDataValuesMatrix() {
        return dataValuesMatrix;
    }

    public String[][] getDataEncryptedValuesMatrix() {
        return dataEncryptedValuesMatrix;
    }

}
