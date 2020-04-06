package net.socialhub.logger;

import net.socialhub.logger.StdOutLogger.StdOutLoggerFactory;

import java.util.Arrays;
import java.util.List;

public abstract class Logger {

    private static LoggerFactory LOGGER_FACTORY = new StdOutLoggerFactory();

    public static Logger getLogger(Class<?> clazz) {
        return LOGGER_FACTORY.getLogger(clazz);
    }

    /**
     * LoggerFactory
     * TODO: Make Log4J LoggerFactory
     * TODO: Make Slf4J LoggerFactory
     */
    public interface LoggerFactory {
        Logger getLogger(Class<?> clazz);
    }

    /**
     * Logger LogLevel
     */
    public enum LogLevel {
        ERROR(),
        WARN(ERROR),
        INFO(WARN, ERROR),
        DEBUG(INFO, WARN, ERROR),
        TRACE(DEBUG, INFO, WARN, ERROR),
        ;

        private List<LogLevel> levels;

        LogLevel(LogLevel... levels) {
            this.levels = Arrays.asList(levels);
        }

        public boolean isLogTarget(LogLevel contextLevel) {
            return (this == contextLevel) || levels.contains(contextLevel);
        }
    }

    // ----------------- //
    // Trace
    // ----------------- //

    abstract public void trace(String message, Throwable th);

    abstract public void trace(String message, Object... args);

    // ----------------- //
    // Debug
    // ----------------- //

    abstract public void debug(String message, Throwable th);

    abstract public void debug(String message, Object... args);

    // ----------------- //
    // Info
    // ----------------- //

    abstract public void info(String message, Throwable th);

    abstract public void info(String message, Object... args);

    // ----------------- //
    // Warn
    // ----------------- //

    abstract public void warn(String message, Throwable th);

    abstract public void warn(String message, Object... args);

    // ----------------- //
    // Error
    // ----------------- //

    abstract public void error(String message, Throwable th);

    abstract public void error(String message, Object... args);

    // ----------------- //
    // Set Level
    // ----------------- //

    abstract public void setLogLevel(LogLevel logLevel);

    abstract public LogLevel getLogLevel();

    public boolean isTraceEnabled() {
        return getLogLevel().isLogTarget(LogLevel.TRACE);
    }

    public boolean isDebugEnabled() {
        return getLogLevel().isLogTarget(LogLevel.DEBUG);
    }

    public boolean isInfoEnabled() {
        return getLogLevel().isLogTarget(LogLevel.INFO);
    }

    public boolean isWarnEnabled() {
        return getLogLevel().isLogTarget(LogLevel.WARN);
    }

    public boolean isErrorEnabled() {
        return getLogLevel().isLogTarget(LogLevel.ERROR);
    }

    //<editor-fold desc="// Getter&Setter">
    public static LoggerFactory getLoggerFactory() {
        return LOGGER_FACTORY;
    }

    public static void setLoggerFactory(LoggerFactory loggerFactory) {
        Logger.LOGGER_FACTORY = loggerFactory;
    }
    //</editor-fold>

    //<editor-fold desc="// Other interfaces">
    public void trace(String message) {
        trace(message, (Throwable) null);
    }

    public void trace(Throwable th) {
        trace("", th);
    }

    public void debug(String message) {
        debug(message, (Throwable) null);
    }

    public void debug(Throwable th) {
        debug("", th);
    }

    public void info(String message) {
        info(message, (Throwable) null);
    }

    public void info(Throwable th) {
        info("", th);
    }

    public void warn(String message) {
        warn(message, (Throwable) null);
    }

    public void warn(Throwable th) {
        warn("", th);
    }

    public void error(String message) {
        error(message, (Throwable) null);
    }

    public void error(Throwable th) {
        error("", th);
    }
    //</editor-fold>
}
