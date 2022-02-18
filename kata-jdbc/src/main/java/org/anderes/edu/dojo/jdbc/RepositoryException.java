package org.anderes.edu.dojo.jdbc;

import java.sql.SQLException;

public class RepositoryException extends Exception {

    private static final long serialVersionUID = 1L;

    public RepositoryException(String message, SQLException e) {
        super(message, e);
    }
}
