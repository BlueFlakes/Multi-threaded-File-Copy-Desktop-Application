package mth.filecopier.service.filters;

import mth.filecopier.exceptions.InvalidChoiceException;

public enum FileFilterOptions {
    NO_FILTER,
    IGNORE_DUPLICATES_FILTER;

    public static FileFilterOptions getOptionByIdentity(String identity)
            throws InvalidChoiceException {

        if (identity.equals("NO")) {
            return IGNORE_DUPLICATES_FILTER;

        } else if (identity.equals("YES")) {
            return NO_FILTER;
        }

        throw new InvalidChoiceException("Invalid identity: " + identity);
    }
}
