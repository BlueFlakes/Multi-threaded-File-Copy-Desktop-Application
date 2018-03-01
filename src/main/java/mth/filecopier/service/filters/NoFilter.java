package mth.filecopier.service.filters;

import mth.filecopier.model.Resource;
import mth.filecopier.abstraction.Filter;

public class NoFilter implements Filter<Resource> {

    @Override
    public boolean validate(Resource object) {
        return true;
    }
}
