package com.mcgovern.logger;

/**
 * Created by grantmcgovern on 3/23/15.
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.SQLException;

public class LoggingHandler {
    private static Logger databaseLogger = LogManager.getLogger("com.mcgovern.loggenerator.databaseLogger");

    public static void databaseLogger(SQLException error ) {
        databaseLogger.error(error);
    }
}
