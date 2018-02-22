package net.socialhub.logger;

/**
 * Standard Out Logger
 * 標準出力
 */
public class StdOutLogger extends Logger {

    public static class StdOutLoggerFactory implements LoggerFactory {
        private StdOutLogger SINGLETON = new StdOutLogger();

        @Override
        public Logger getLogger(Class<?> clazz) {
            return SINGLETON;
        }
    }

    private Logger.LogLevel logLevel = LogLevel.DEBUG;

    @Override
    public void debug(String message, Throwable th) {
        print(message, th, LogLevel.DEBUG);
    }

    @Override
    public void info(String message, Throwable th) {
        print(message, th, LogLevel.INFO);
    }

    @Override
    public void warn(String message, Throwable th) {
        print(message, th, LogLevel.WARN);
    }

    @Override
    public void error(String message, Throwable th) {
        print(message, th, LogLevel.ERROR);
    }

    private void print(String message, Throwable th, LogLevel logLevel) {
        if (this.logLevel.isLogTarget(logLevel)) {
            System.out.print("[" + logLevel.name() + "]");

            if (message != null && !message.isEmpty()) {
                System.out.println(message);
            }

            if (th != null) {
                th.printStackTrace();
            }
        }
    }

    //<editor-fold desc="// Getter&Setter">
    @Override
    public LogLevel getLogLevel() {
        return logLevel;
    }

    @Override
    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }
    //</editor-fold>
}
