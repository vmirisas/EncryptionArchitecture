import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        boolean quit = false;

        while (!quit){
            System.out.println("Choose the the type of the files you will work with");
            System.out.println("1 : .txt " + "\n" + "2 : .csv"+ "\n" + "3 : .json");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    quit = true;
                    break;
                case 2:
                    System.out.println("Not implemented yet");
                    break;
                case 3:
                    System.out.println("Not implemented yet");
                    break;
            }

        }
        System.out.println("Choose the data file for encryption");
        String fileLocation = scanner.nextLine();
        TxtFile file = new TxtFile();
        file.Import(fileLocation);

        System.out.println("Choose the configuration file");
        String configurationFileLocation = scanner.nextLine();
//        ConfigurationFile configurationFile = new ConfigurationFile();
//        configurationFile.ConfigurationFileChoice(configurationFileLocation);
        file.ConfigurationFileChoice(configurationFileLocation);
        System.out.println(file.getConfiguredFields());
        System.out.println(file.getSameIndexArray());


    }

}