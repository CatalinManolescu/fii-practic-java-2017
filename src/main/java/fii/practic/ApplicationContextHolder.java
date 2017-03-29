package fii.practic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Util class for accessing the Spring {@code ApplicationContext} .
 */
@Component
public class ApplicationContextHolder {
    private static ApplicationContext context;

    @Autowired
    public void setContext(ApplicationContext context) {
        ApplicationContextHolder.context = context;
    }

    public static <T> T getBean(Class<T> definition) {
        return ApplicationContextHolder.context.getBean(definition);
    }
}
