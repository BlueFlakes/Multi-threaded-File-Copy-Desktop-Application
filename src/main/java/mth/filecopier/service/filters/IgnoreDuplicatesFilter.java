package mth.filecopier.service.filters;

import mth.filecopier.model.Resource;
import mth.filecopier.abstraction.Filter;

import java.io.File;

public class IgnoreDuplicatesFilter implements Filter<Resource> {
    @Override
    public boolean validate(Resource object) {
        File from = object.getSource();
        File dest = object.getDestination();

        return from.exists() && !dest.exists();
    }
}
