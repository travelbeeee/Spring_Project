package travelbeeee.typeconverter.converter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;
import travelbeeee.typeconverter.type.IpPort;

public class ConversionServiceTest {

    @Test
    void conversionService(){
        //given
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new StringToIntegerConverter());
        conversionService.addConverter(new IntegerToStringConverter());
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());

        //when
        Integer intRes = conversionService.convert("10", Integer.class);
        IpPort ipPortRes = conversionService.convert("127.0.0.1:8080", IpPort.class);

        //then
        Assertions.assertThat(intRes).isEqualTo(10);
        Assertions.assertThat(ipPortRes).isEqualTo(new IpPort("127.0.0.1", 8080));
    }
}
