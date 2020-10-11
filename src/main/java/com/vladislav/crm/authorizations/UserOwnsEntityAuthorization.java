package com.vladislav.crm.authorizations;

public interface UserOwnsEntityAuthorization {
    boolean hasAuthorization(Long entityId);
}
