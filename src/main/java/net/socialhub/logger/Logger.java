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
        DEBUG(INFO, WARN, ERROR);

        private List<LogLevel> levels;

        LogLevel(LogLevel... levels) {
            this.levels = Arrays.asList(levels);
        }

        public boolean isLogTarget(LogLevel contextLevel) {
            return (this == contextLevel) || levels.contains(contextLevel);
        }
    }

    abstract public void debug(String message, Throwable th);

    abstract public void info(String message, Throwable th);

    abstract public void warn(String message, Throwable th);

    abstract public void error(String message, Throwable th);

    abstract public void setLogLevel(LogLevel logLevel);

    abstract public LogLevel getLogLevel();


    //<editor-fold desc="// Getter&Setter">
    public static LoggerFactory getLoggerFactory() {
        return LOGGER_FACTORY;
    }

    public static void setLoggerFactory(LoggerFactory loggerFactory) {
        Logger.LOGGER_FACTORY = loggerFactory;
    }
    //</editor-fold>

    //<editor-fold desc="// Other interfaces">
    public void debug(String message) {
        debug(message, null);
    }

    public void debug(Throwable th) {
        debug("", th);
    }

    public void info(String message) {
        info(message, null);
    }

    public void info(Throwable th) {
        info("", th);
    }

    public void warn(String message) {
        warn(message, null);
    }

    public void warn(Throwable th) {
        warn("", th);
    }

    public void error(String message) {
        error(message, null);
    }

    public void error(Throwable th) {
        error("", th);
    }
    //</editor-fold>
}
