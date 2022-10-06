import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> dataFields;
        //ArrayList<String> dataValues;
        ArrayList<String> configuredFields;

        boolean quit = false;

        while (!quit){
            System.out.println("Choose the the type of the files you will work with");
            System.out.println("1 : .txt " + "\n" + "2 : .csv"+ "\n" + "3 : .json");

            int dataTypeChoice = Integer.parseInt(scanner.nextLine());
            switch (dataTypeChoice){
                case 1:
                    System.out.println("Choose the data file for encryption");
                    String fileLocation = scanner.nextLine();
                    TxtFile file = new TxtFile();
                    file.Import(fileLocation);
                    dataFields = file.getDataFields();

                    System.out.println("Choose the configuration file");
                    String configurationFileLocation = scanner.nextLine();
                    file.ConfigurationFileChoice(configurationFileLocation);

                    configuredFields = file.getConfiguredFields();
                    file.SameFieldIndex(dataFields, configuredFields);

                    boolean quit2 = false;
                    boolean quit3 = false;
                    while (!quit2){
                        System.out.println("Encryption Methods" + "\n"
                                + "1 : AES" + "\n"
                                + "2 : RSA" + "\n"
                                + "3 : DES");
                        int pickEncryptionMethod = Integer.parseInt(scanner.nextLine());
                        switch(pickEncryptionMethod){
                            case 1:
                                System.out.println("Type the 16bit secret key needed for the encryption");
                                String secretKey = scanner.nextLine();
                                file.setSecretKey(secretKey);
                                file.encrypt();

                                System.out.println("Set the name of the encrypted file");
                                String destinationPath = scanner.nextLine();
                                String[][] encryptedValuesToWrite = file.getDataEncryptedValuesMatrix();
                                file.Export(destinationPath, encryptedValuesToWrite);
                                quit2 = true;
                                break;
                            case 2:
                                System.out.println("This encryption method is not implemented yet. Choose another method.");
                                break;
                            case 3:
                                System.out.println("This encryption method is not implemented yet. Choose another method.");
                                break;
                            default:
                                System.out.println("Chose a valid encryption method from the available above");
                                break;
                        }

                        while(!quit3) {
                            System.out.println("Would you like to decrypt the file;" + "\n" +
                                    "1 : Yes" + "\n" +
                                    "2 : No");
                            int decryptChoice = Integer.parseInt(scanner.nextLine());
                            switch (decryptChoice){
                                case 1:
                                    System.out.println("Set the name of the decrypted file");
                                    String decryptionDestinationPath = scanner.nextLine();
                                    file.decrypt();
                                    String[][] decryptedValuesToWrite = file.getDataDecryptedValuesMatrix();
                                    file.Export(decryptionDestinationPath, decryptedValuesToWrite);
                                    quit3 = true;
                                    break;
                                case 2:
                                    System.out.println("No decrypted file made");
                                    quit3 = true;
                                    break;
                                default:
                                    System.out.println("Would you like to decrypt the file, yes or no;");
                                    break;
                            }
                        }
                    }
                    quit = true;
                    break;
                case 2:
                    System.out.println("Not implemented yet");
                    break;
                case 3:
                    System.out.println("Not implemented yet");
                    break;
                default:
                    System.out.println("Choose a valid datatype from the available above");
            }

        }

    }

}