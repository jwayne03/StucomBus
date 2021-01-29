package utils;

import exception.DatabaseException;

public interface DatabaseConnector {
    void connect() throws DatabaseException;
    void disconnect();
}
