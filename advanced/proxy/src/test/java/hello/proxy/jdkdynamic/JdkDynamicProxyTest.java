package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA(){
        AInterface target = new AImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{AInterface.class}, handler);

        proxy.call(); // TimeInvocationHandler 의 Invoke()가 실행되는데 call 메소드로 정보가 넘어가서 실행!
        log.info("targetClass = {}", target.getClass());
        log.info("targetClass = {}", proxy.getClass());
    }

    @Test
    void dynamicB(){
        BInterface target = new BImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        BInterface proxy = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(), new Class[]{BInterface.class}, handler);

        proxy.call(); // TimeInvocationHandler 의 Invoke()가 실행되는데 call 메소드로 정보가 넘어가서 실행!
        log.info("targetClass = {}", target.getClass());
        log.info("targetClass = {}", proxy.getClass());
    }
}
