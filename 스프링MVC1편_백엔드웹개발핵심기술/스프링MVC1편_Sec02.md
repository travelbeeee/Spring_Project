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

### 2) HttpServletRequest

HTTP 요청 메시지를 개발자가 직접 파싱해서 사용해도 되지만, 서블릿 개발자가 HTTP 요청 메시지를 편리하게 사용할 수 있도록 개발자 대신에 HTTP 요청 메시지를 파싱해준다. 그리고 그 결과를 `HttpServletRequest`객체에 담아서 제공한다.

##### -임시저장소기능

`HttpServletRequest` 는 HTTP 요청이 시작부터 끝날 때 까지 유지되는 임시 저장소 기능도 한다.

- request.setAttribute(name, value);
- request.getAttribute(name);

으로 객체에 값을 저장하고 꺼내올 수 있다.

##### -세션 관리 기능

- request.getSession(create: true)

<br>

WAS가 지원해주는 `HttpServletRequest`는 HTTP Request 메시지를 이용할 수 있는 정말 다양한 메소드들을 지원해준다.

```java
//start line 정보
    private void printStartLine(HttpServletRequest request) {
        System.out.println("--- REQUEST-LINE - start ---");
        System.out.println("request.getMethod() = " + request.getMethod()); //GET
        System.out.println("request.getProtocal() = " + request.getProtocol()); // HTTP/1.1
        System.out.println("request.getScheme() = " + request.getScheme()); //http

        // http://localhost:8080/request-header
        System.out.println("request.getRequestURL() = " + request.getRequestURL());

        // /request-test
        System.out.println("request.getRequestURI() = " + request.getRequestURI());

        //username=hi
        System.out.println("request.getQueryString() = " + request.getQueryString());
        System.out.println("request.isSecure() = " + request.isSecure()); //https 사용 유무
        System.out.println("--- REQUEST-LINE - end ---");
        System.out.println();
    }

    //Header 모든 정보
    private void printHeaders(HttpServletRequest request) {
        System.out.println("--- Headers - start ---");
        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> System.out.println(headerName + ": " + request.getHeader(headerName)));
        System.out.println("--- Headers - end ---");
        System.out.println();
    }

    //Header 편리한 조회
    private void printHeaderUtils(HttpServletRequest request) {
        System.out.println("--- Header 편의 조회 start ---");
        System.out.println("[Host 편의 조회]");
        System.out.println("request.getServerName() = " +
                request.getServerName()); //Host 헤더
        System.out.println("request.getServerPort() = " +
                request.getServerPort()); //Host 헤더
        System.out.println();
        System.out.println("[Accept-Language 편의 조회]");
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " +
                        locale));
        System.out.println("request.getLocale() = " + request.getLocale());
        System.out.println();
        System.out.println("[cookie 편의 조회]");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        System.out.println();
        System.out.println("[Content 편의 조회]");
        System.out.println("request.getContentType() = " +
                request.getContentType());
        System.out.println("request.getContentLength() = " +
                request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " +
                request.getCharacterEncoding());
        System.out.println("--- Header 편의 조회 end ---");
        System.out.println();
    }

    //기타 정보
    private void printEtc(HttpServletRequest request) {
        System.out.println("--- 기타 조회 start ---");
        System.out.println("[Remote 정보]");
        System.out.println("request.getRemoteHost() = " +
                request.getRemoteHost()); //
        System.out.println("request.getRemoteAddr() = " +
                request.getRemoteAddr()); //
        System.out.println("request.getRemotePort() = " +
                request.getRemotePort()); //
        System.out.println();
        System.out.println("[Local 정보]");
        System.out.println("request.getLocalName() = " +
                request.getLocalName()); //
        System.out.println("request.getLocalAddr() = " +
                request.getLocalAddr()); //
        System.out.println("request.getLocalPort() = " +
                request.getLocalPort()); //
        System.out.println("--- 기타 조회 end ---");
        System.out.println();
    }
```

<br>

HTTP 요청 메시지를 통해 클라이언트에서 서버로 데이터를 전달하는 방법

- GET - 쿼리 파라미터

  : GET 방식은 보통 HTTP Message Body 보다는 URL 쿼리 파라미터에 데이터를 포함해서 전달한다.

- POST - HTML Form

  : HTML Form을 이용한 Post 방식은 메시지 바디에 쿼리 파라미터 형식으로 전달한다. --> content-type: application/x-www-form-urlencoded

- HTTP Message body

  : HTTP API 에서 주료 사용하고,  데이터 형식은 최근에는 JSON 을 주로 사용한다.

<br>

**HTTP 요청 데이터 - GET 쿼리 파라미터로 값 넘기기**

```java
/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?username=hello&age=20&username=hello2
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[전체 파라미터 조회] - start");
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + "=" + request.getParameter(paramName)));
        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        System.out.println("[단일 파라미터 조회] - start");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println("[단일 파라미터 조회] - end");
		System.out.println();
        System.out.println("[이름이 같은 복수 파라미터 조회] - start");
        String[] usernames = request.getParameterValues("username");
        for (String s : usernames) {
            System.out.println("username = " + s);
        }
        System.out.println("[이름이 같은 복수 파라미터 조회] - end");
    }
}
```

```
[ 출력 ]
[전체 파라미터 조회] - start
username=hello
age=20
[전체 파라미터 조회] - end

[단일 파라미터 조회] - start
username = hello
age = 20
[단일 파라미터 조회] - end

[이름이 같은 복수 파라미터 조회] - start
username = hello
username = hello2
[이름이 같은 복수 파라미터 조회] - end
```

- request.getParameterNames() 메소드를 통해서 Parameter 값이 아니라 Parameter 의 Key를 다 받아올 수 있다.
- request.getParameter("parameterName") 메소드는 `parameterName`에 해당하는 파라미터 값을 받아온다. 이때, `parameterName`이 중복되어있으면 하나만 가져오므로 주의하자.
- request.getParameterValues("parameterName") 메소드는 `parameterName`에 해당하는 파라미터 값을 다 받아온다. 중복되어있을 때 사용하면 된다.

<br>

**HTTP 요청 데이터 -POST HTML FORM**

HTML Form 을 사용해서 파라미터를 넘기면 웹 브라우저가 HTTP Request 메시지를 아래 그림과 같이 만든다. 이때도, GET 메소드에서 쿼리 파라미터로 넘긴 것과 동일한 메소드를 사용해서 파라미터를 받을 수 	있다.

![springmvc_sec02_01](https://user-images.githubusercontent.com/59816811/115517533-2f8c2180-a2c2-11eb-80a7-98a78a87d621.png)

> 클라이언트 입장에서는 GET 메소드의 쿼리 파라미터로 보내는 방식이랑 HTML Form 형식으로 POST 메소드로 보내는 방식이 서로 다르나 서버 입장에서는 파라미터를 받아오는 방식이 똑같음!

<br>

**HTTP 요청 데이터 - API 메시지 바디**

