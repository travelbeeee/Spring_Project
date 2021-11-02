package hello.proxy.decorator;

import hello.proxy.decorator.code.Component;
import hello.proxy.decorator.code.DecoratorPatternClient;
import hello.proxy.decorator.code.RealComponent;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class DecoratorPatternTest {

    @Test
    void noDecorat(){
        Component component = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(component);
        client.execute();
    }
}
