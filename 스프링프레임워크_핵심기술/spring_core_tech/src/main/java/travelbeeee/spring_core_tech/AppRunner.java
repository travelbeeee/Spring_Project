package travelbeeee.spring_core_tech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Locale;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    ApplicationContext ctx;

    ResourceBundleMessageSource
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(Locale.getDefault());
        System.out.println(ctx.getMessage("greeting", new String[]{"travelbeeee"}, Locale.getDefault()));
        System.out.println(ctx.getMessage("greeting", new String[]{"travelbeeee"}, Locale.ENGLISH));
    }
}
