package mth.filecopier.service.filters;

import mth.filecopier.model.Resource;
import mth.filecopier.abstraction.Filter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class FileFilterFactory {

    public static Filter<Resource> createFileFiter(FileFilterOptions deliveredType) {

        if (deliveredType == FileFilterOptions.IGNORE_DUPLICATES_FILTER) {
            return new IgnoreDuplicatesFilter();
        }

        if (deliveredType == FileFilterOptions.NO_FILTER) {
            return new NoFilter();
        }

        throw new IllegalStateException("Invalid parameter occured: " + deliveredType);
    }
}
