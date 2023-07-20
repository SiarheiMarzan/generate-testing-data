package logger;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;

public class Log {
    private org.apache.logging.log4j.Logger logger;

    public Log(Class<?> clazz) {
        logger = LogManager.getLogger(clazz);
    }

    public void INFO(String message) {
        logger.info(message);
    }

    public void STEP(String message) {
        Allure.step(message);
        logger.info(message);
    }

    public void ERROR(String message) {
        logger.error(message);
    }
}