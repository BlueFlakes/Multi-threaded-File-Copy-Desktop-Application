package mth.filecopier.service.filters;

import mth.filecopier.exceptions.UnexpectedFileDuplicateOccurredException;
import mth.filecopier.model.Resource;
import mth.filecopier.abstraction.Filter;

import java.io.File;

public class IgnoreDuplicatesFilter implements Filter<Resource> {
    @Override
    public void validate(Resource object) throws UnexpectedFileDuplicateOccurredException {
        File from = object.getSource();
        File dest = object.getDestination();

        if (from.exists() && !dest.exists()) {
            throw new UnexpectedFileDuplicateOccurredException();
        }
    }
}
