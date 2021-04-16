# 스프링MVC1편_Sec01

### 1) 웹 서버

- HTTP 기반으로 동작
- 정적 리소스 제공
- 정적파일 HTML, CSS, JS, 이미지 영상

--> HTTP 요청이 오면 정적 파일들을 응답해주는 서버

ex) NGINX, APACHE

<br>

### 2) 웹 애플리케이션 서버 (WAS, Web Application Server)

- HTTP 기반으로 동작
- 웹 서버 기능 포함
- 프로그램 코드를 실행해서 애플리케이션 로직 수행 가능
  - 동적 HTML, HTTP API(JSON)
  - 서블릿, JSP, 스프링 MVC

ex) Tomcat, Jetty, Undertow

--> 웹 서버는 정적 리소스, WAS는 애플리케이션 로직 이라는 차이가 있음!

--> 현재는 웹 서버도 프로그램을 실행하는 기능을 포함하기도 해서 둘의 경계가 모호함.

<br>

### 3) 웹 시스템 구성

그러면 웹 시스템을 어떻게 구성하면 좋을까??

WAS 가 어차피 웹 서버의 기능도 할 수 있으니까 WAS와 DB로만 구성하면 될까??

![springmvc_sec01](https://user-images.githubusercontent.com/59816811/114989175-2110c500-9ed2-11eb-8a5d-a714e7a302a7.png)

WAS가 정적 리소스도 제공하고, 애플리케이션 로직도 실행이 가능하지만 이렇게 하면 WAS가 너무 많은 역할을 담당해 서버 과부하에 걸릴 수 있다. 또, 가장 비싼 애플리케이션 로직이 정적 리소스 때문에 수행이 어려울 수도 있고, WAS 장애시 오류 화면도 노출이 불가능하다.

![springmvc_sec01_02](https://user-images.githubusercontent.com/59816811/114989373-54535400-9ed2-11eb-8d7c-67c2fa8e8e1c.png)

따라서, 보통은 Web_Server와 WAS 를 모두 이용해서 웹 시스템을 구성한다.

이렇게 구성하면 정적 리소스를 제공하는 웹 서버는 잘 죽지 않으므로 WAS 가 죽었을 때도 에러 페이지 등 대처가 가능하다.

<br>

### 4) 서블릿

웹 브라우저가 HTTP 메시지를 생성해서 넘겨주면 그걸 우리는 어떻게 처리할 수 있을까??

우리가 WAS를 다 구현해야된다고 하면 아래와 같은 모든 작업들을 다 해줘야한다.

<img src="https://user-images.githubusercontent.com/59816811/114991009-15be9900-9ed4-11eb-9ecf-40b7d13e05c1.png" alt="springmvc_sec01_03" style="zoom:67%;" />

우리가 집중해야되는 부분은 `비즈니스 로직` 인데 비즈니스 로직 외에도 신경써야되는 부분이 굉장히 많아진다!

서버와 연결해야되고, HTTP 요청 메시지 파싱해야되고, HTTP 응답 메시지도 다 직접 생성해야되고 할 일이 너무 많아진다.

--> 서블릿 등장!!

--> 서블릿은 `비즈니스 로직 실행` 을 제외한 모든 부분의 일들을 해준다.

<br>

```java
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse reponse){
        // 애플리케이션 로직
    }
}
```

`/hello` 라는 URL 경로가 요청되면 HelloServlet 이 동작해서 `HttpServletRequest`, `HttpServletResponse` 객체를 만들어서 원하는 정보들을 다 넣어주고, 우리가 HTTP 스펙을 편리하게 사용할 수 있게 도와준다.

![springmvc_sec01_04](https://user-images.githubusercontent.com/59816811/114991680-d3e22280-9ed4-11eb-8f12-180f42122ad2.png)

> 서블릿 컨테이너는 서블릿을 관리해주는 WAS를 의미함. 

<br>

서블릿 객체는 `싱글톤`으로 관리된다. Request, Response 객체는 고객마다 다른 정보를 가지고 있으므로 새롭게 생성하지만, 서블릿 객체는 한 번 생성해놓고 싱글톤으로 관리가 된다. 따라서, 공유 변수를 사용하면 안되고, 서블릿 컨테이너가 종료 시에 함께 종료된다. 

<br>

### 5) 동시 요청(Multi Thread)

