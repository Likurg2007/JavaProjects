package org.dpolianskyi.epam.delivery.beans;

import org.apache.log4j.Logger;

public class LogBean {

    private static Logger log = null;

    private LogBean() {
    }

    public static Logger getLogger() {
        if (log == null) {
            log = Logger.getRootLogger();
        }
        return log;
    }
}