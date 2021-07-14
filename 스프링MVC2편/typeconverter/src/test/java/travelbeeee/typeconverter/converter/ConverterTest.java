package travelbeeee.typeconverter.converter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import travelbeeee.typeconverter.type.IpPort;

public class ConverterTest {

    @Test
    void stringToInteger(){
        //given
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Assertions.assertThat(converter.convert("10")).isEqualTo(10);
    }

    @Test
    void integerToString(){
        //given
        IntegerToStringConverter converter = new IntegerToStringConverter();
        Assertions.assertThat(converter.convert(10)).isEqualTo("10");
    }

    @Test
    void stringToIpPort(){
        //given
        IpPortToStringConverter converter = new IpPortToStringConverter();
        String result = converter.convert(new IpPort("127.0.0.1", 8080));
        Assertions.assertThat(result).isEqualTo("127.0.0.1:8080");
    }

    @Test
    void ipPortToString(){
        //given
        StringToIpPortConverter converter = new StringToIpPortConverter();
        IpPort result = converter.convert("127.0.0.1:8080");
        Assertions.assertThat(result).isEqualTo(new IpPort("127.0.0.1", 8080));
        //then
    }
}
