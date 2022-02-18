package org.anderes.edu.dojo.rest.persistence;

import org.apache.commons.lang3.Validate;

public class RepositoryException extends Exception {

    private static final long serialVersionUID = 1L;
    private final Reason reason;
    
    public enum Reason { BUSINESS, TECHNICAL }

    public RepositoryException(final Reason reason, final Throwable exception) {
        super(exception);
        Validate.notNull(reason);
        this.reason = reason;
    }

    public RepositoryException(final Reason reason) {
        super();
        Validate.notNull(reason);
        this.reason = reason;
    }

    public Reason getReason() {
        return reason;
    }
    
}
