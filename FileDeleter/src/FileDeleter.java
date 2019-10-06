import java.io.File;
import java.util.Scanner;

public class FileDeleter {
    public static String DIRECTORY;
    public static String EXTENSION;

    public static void deleteFile(File file) {
        String fileName = file.getName();
        String temp;
        if(fileName.length()>=EXTENSION.length()) {
            temp = fileName.substring(fileName.length() - EXTENSION.length());
            if(temp.equals(EXTENSION)) {
                file.delete();
                System.out.println(file + " is deleted!");
            }
        }
    }

    public static void traverse(File directory) {

        File[] files = new File(directory.getPath()).listFiles();
        for (File file: files) {
            if(file.isDirectory())
                traverse(file);
            else if (file.isFile()) {
                deleteFile(file);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Application Started !!!");
        System.out.println("First input Directory, then Extension(separated by space)...");

        DIRECTORY = args[0];
        EXTENSION = args[1];

        if(EXTENSION.charAt(0) != '.') {
            System.out.println("Extension needs to be started with a .(dot), like .txt, .html, .mp4");
            System.out.println("Give a proper extension...");
            return;
        }
        System.out.println("Directory found: " + DIRECTORY);
        System.out.println("Extension found: " + EXTENSION);
        System.out.println("Do you want to continue? If not type 1, else 2 to continue");

        Scanner scanner = new Scanner(System.in);
        int type = scanner.nextInt();
        if(type == 2) {
            File[] files = new File(DIRECTORY).listFiles();
            for (File file : files) {
                if (file.isDirectory())
                    traverse(file);
                else if (file.isFile()) {
                    deleteFile(file);
                }
            }
        }
        System.out.println("Application Closed !!!");
    }
}
