package mth.filecopier.service;

import mth.filecopier.abstraction.Filter;
import mth.filecopier.model.Resource;

import java.io.*;

public class Duplicator implements Runnable {

    private Resource resource;
    private Filter<Resource> filter;

    public Duplicator(Resource resource, Filter<Resource> filter) {
        this.resource = resource;
        this.filter = filter;
    }

    @Override
    public void run( ) {
        try {
            this.filter.validate(resource);
            copy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void copy( ) throws Exception {
        try (InputStream is = new FileInputStream(resource.getSource());
             OutputStream os = new FileOutputStream(resource.getDestination())) {

            byte[] buffer = new byte[8192];
            int length;

            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
    }
}
