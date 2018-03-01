package mth.filecopier.view;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class FileCopierView {
    private static final Scanner in = new Scanner(System.in);

    private String getUserInput() {
        return in.nextLine().trim();
    }

    public String askInputSource() {
        System.out.println("Enter a file destination: ");
        return getUserInput();
    }

    public String askInputDestination() {
        System.out.println("Enter a source file: ");
        return getUserInput();
    }

    public String askCopyOrOverWrite() {
        System.out.println("COPY - YES, OVERWRITE - NO ");
        return getUserInput();
    }
}
