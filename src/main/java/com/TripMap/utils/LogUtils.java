package com.TripMap.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {
    private static final Logger systemLogger = LoggerFactory.getLogger("SystemLogger");
    private static final Logger accessLogger = LoggerFactory.getLogger("AccessLogger");
    private static final Logger errorLogger = LoggerFactory.getLogger("ErrorLogger");

    // 系统日志
    public static void logSystem(String message) {
        systemLogger.info(message);
    }

    // 访问日志
    public static void logAccess(String message) {
        accessLogger.info(message);
    }

    // 错误日志
    public static void logError(String message, Throwable e) {
        errorLogger.error(message, e);
    }
} 