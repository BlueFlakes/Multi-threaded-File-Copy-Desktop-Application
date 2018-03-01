package mth.filecopier.model;

import java.io.File;

/**
 * Hello world!
 *
 */
public class Resource {
    private File source;
    private File destination;

    public Resource(File source, File destination) {
        this.source = source;
        this.destination = destination;
    }

    public File getSource( ) {
        return source;
    }

    public File getDestination( ) {
        return destination;
    }
}