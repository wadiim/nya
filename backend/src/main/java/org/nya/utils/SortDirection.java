package org.nya.utils;

import org.nya.exceptions.InvalidSortDirectionException;

public enum SortDirection {
    ASCENDING,
    DESCENDING;

    public static SortDirection from(
            String label
    ) throws InvalidSortDirectionException {
        if (label.toLowerCase().startsWith("asc")) {
            return SortDirection.ASCENDING;
        } else if (label.toLowerCase().startsWith("desc")) {
            return SortDirection.DESCENDING;
        } else {
            throw new InvalidSortDirectionException(
                    "Invalid sort direction: " + label
            );
        }
    }
}
