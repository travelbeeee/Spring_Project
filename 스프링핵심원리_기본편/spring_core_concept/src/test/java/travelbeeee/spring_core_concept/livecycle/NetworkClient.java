package travelbeeee.spring_core_concept.livecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {

    private String url;

    public NetworkClient() {
        System.out.println("[NetworkClient.NetworkClient] url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작 시 호출
    public void connect() {
        System.out.println("connect = " + url);
    }

    // 서비스 중에 호출
    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    // 서비스 종료 시 호출
    public void disconnect() {
    }

    /**
     * V1) 스프링 인터페이스를 이용한 초기화, 소멸 메서드
     * - 단점 : 스프링에 종속적이고 과거 코드 (메서드명 등 수정도 불가능)
     */

    /**
     * InitializingBean > 오버라이드메서드
     * - 빈 생성 후에 초기화 메서드
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("[NetworkClient.afterPropertiesSet]");
        connect();
    }

    /**
     * DisposableBean > 오버라이드메서드
     * - 빈 소멸 전에 호출되는 메서드
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("[NetworkClient.destroy]");
        disconnect();
    }

    /**
     * V2) 빈 설정을 이용한 방식
     * - @Bean(initMethod = "init", destroyMethod = "close") ( 빈 주입 시, 설정으로 명시한다 )
     * - 장점
     *   - 스프링 빈인 NetWorkClient 에는 스프링 관련된 코드들이 없음.
     *   - 코드를 고칠 수 없는 외부 라이브러리에도 지정 가능
     * - 단점
     *   - @Bean(destroyMethod="(inferred)") 로 디폴트가 잡혀 있음
     *     => close, shutdown 라는 이름의 메서드가 있다면 자동으로 호출해준다.
     *     ==> 종료 메서드를 호출하지 않아야 된다면 destroyMethod="" 로 따로 지정해줘야한다.
     */
    public void init() throws Exception {
        System.out.println("[NetworkClient.init]");
        connect();
    }

    public void close() throws Exception {
        System.out.println("[NetworkClient.close]");
        disconnect();
    }

    /**
     * V3) 자바진영에서 지원하는 애노테이션 사용하기
     * - javax.annotation.postconstruct, predestroy => 자바 표준!
     * - 권장하는 방법
     */
    @PostConstruct
    public void init2() throws Exception {
        System.out.println("[NetworkClient.init2]");
        connect();
    }

    @PreDestroy
    public void close2() throws Exception {
        System.out.println("[NetworkClient.close2]");
        disconnect();
    }
}
