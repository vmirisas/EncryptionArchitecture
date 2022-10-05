import java.util.Scanner;

public class FilePicker {

    Scanner scanner = new Scanner(System.in);
    private String fileLocation;
    private String fileExtension;

    public String getFileLocation() {
        return fileLocation;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void FileChooser(){
        System.out.println("Choose the data file for encryption");
        fileLocation = scanner.nextLine();
        if (fileLocation != null){
            int dotPosition = fileLocation.lastIndexOf('.');
            fileExtension = fileLocation.substring(dotPosition+1);
        } else {
            System.out.println("This file dosnt exist or is not a .txt file");
            System.exit(0);
        }



        //System.out.println(fileExtension);




    }

}
