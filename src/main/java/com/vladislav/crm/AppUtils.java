package com.vladislav.crm;

import java.util.Optional;

public class AppUtils {
    private AppUtils() {
    }

    public static String getMessage(Throwable t) {
        final String localizedMessage = t.getLocalizedMessage();
        if (localizedMessage == null || "".equals(localizedMessage)) {
            final String message = t.getMessage();
            return Optional.ofNullable(message).orElse("");
        }
        return localizedMessage;
    }
}
