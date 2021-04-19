# 스프링MVC1편_Sec02

### 1) 서블릿

```java
@ServletComponentScan
@SpringBootApplication
public class ServletApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
	}
}
```

스프링이 @ServletComponentScan 애노테이션을 통해 서블릿을 하위 패키지의 서블릿을 자동 등록해준다.

```java
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
    }
}
```

@WebServlet 애노테이션을 통해 `/hello` URL 에 `helloServlet` 이라는 이름의 서블릿을 동작시킬 수 있다.

- name : 서블릿 이름
- urlPatterns : URL 매핑

HTTP 요청을 통해 매핑된 URL이 호출되면 서블릿 컨테이너는 해당 서블릿의 service 메소드를 실행하고, WAS가 HttpServletRequest 객체와 HttpServletResponse 객체를 만들어서 던져준다.

<br>

### 2) `HttpServletRequest`

HTTP 요청 메시지를 개발자가 직접 파싱해서 사용해도 되지만, 서블릿 개발자가 HTTP 요청 메시지를 편리하게 사용할 수 있도록 개발자 대신에 HTTP 요청 메시지를 파싱해준다. 그리고 그 결과를 `HttpServletRequest`객체에 담아서 제공한다.

##### -임시저장소기능

`HttpServletRequest` 는 HTTP 요청이 시작부터 끝날 때 까지 유지되는 임시 저장소 기능도 한다.

- request.setAttribute(name, value);
- request.getAttribute(name);

으로 객체에 값을 저장하고 꺼내올 수 있다.

##### -세션 관리 기능

- request.getSession(create: true)

