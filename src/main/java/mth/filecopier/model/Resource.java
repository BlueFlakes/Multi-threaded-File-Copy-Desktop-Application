package mth.filecopier.model;

import java.io.File;

/**
 * Hello world!
 *
 */
public class Resource {
    private File source;
    private File destination;

    public Resource(String sourcePath, String destination) {
        this.source = new File(sourcePath);
        this.destination = new File(sourcePath);
    }

    public File getSource( ) {
        return source;
    }

    public File getDestination( ) {
        return destination;
    }
}