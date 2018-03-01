package mth.filecopier.view;
import java.util.Scanner;

public class View {

    public static String askInputsource() {

        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter a file destination: ");

        String answer = reader.next();

        return answer;
    }

    public static String askInputDestination() {

        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter a source file: ");

        String answer = reader.next();

        return answer;
    }

    public static String askCopyOrOverWrite() {

        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("COPY - YES, OVERWRITE - NO ");

        String answer = reader.next().toUpperCase();

        return answer;
    }

}
