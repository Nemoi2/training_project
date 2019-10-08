package bellintegrator.training.advice;

import bellintegrator.training.view.ErrorView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class ExceptionHandlerController {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(Exception.class)
    public ErrorView unhandledException(Exception e) {
        logger.error("Unexpected error", e);

        if (e.getMessage().startsWith("JSON")) {
            return new ErrorView("Неправильно введены данные");
        }
        return new ErrorView("Ошибка Сервера: " + e.getMessage());
    }
}
