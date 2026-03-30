강의 소개

스프링 MVC

가장 인기 있는 웹 프레임워크

백엔드 웹 - 공부량이 너무 방대하고 많음

HTTP기반 지식이 있어야 깊이 있는 학습이 가능

Java는 20년 간 수많은 추상화와 개선이 이루어졌기 때문에 최근에 입문한 개발자들은 이에 대한 역사와 왜 이렇게 되었는지에 대해 알지 못함

Spring MVC는 백엔드 실무 개발에 필요한 모든 기능을 지원(수많은 기능)

- 기본 구조를 확실히 이해하는 것이 중요함

20년 전으로 돌아가서 Spring MVC의 탄생을 시작으로 코딩하면서 배워갈 예정

MVC 프레임워크를 직접 코드로 개발

스프링 MVC 1편 - 백엔드 웹 개발 핵심 기술 (2편은 활용 기술)

스프링 핵심 원리/HTTP

→ 웹 애플리케이션 이해, 서블릿, JSP, MVC패턴, MVC 프레임워크

→ 스프링MVC - 웹 개발, 기본 기능, 핵심 구조로 쌓아감

기초부터 완성까지

- 웹 - HTTP 기반

모든 것이 HTTP

→ HTML, TEXT, IMAGE, 음성, 영상, 파일, JSON, XML(API) 등의 모든 형태의 데이터 전송 가능

- 웹 서버(Web Server)

HTTP 기반으로 동작하는 서버

정적 리소스 제공 : 서버들이 이미 준비된 파일(html,css,js,이미지,영상)을 제공

NGINX, APACHE

- 웹 애플리케이션 서버(WAS - Web Application Server)

HTTP 기반으로 동작함

웹 서버의 대부분 기능 포함(정적 리소스)

프로그램 코드를 실행해서 애플리케이션 로직 수행

→ 동적 HTML, HTTP API(REST API) - JSON

→ 서블릿, JSP, 스프링 MVC

톰캣(Tomcat), Jetty, Undertow

둘의 경계가 모호해질 수 있음

웹 시스템 구성 - WAS,DB

정적 리소스, 애플리케이션 로직 모두 제공 가능

WAS가 너무 많은 역할을 담당, 과부하 우려

가장 비싼 애플리케이션 로직이 정적 리소스 때문에 수행이 어려울 수 있음

WAS 장애 시 오류 화면도 노출 불가능

웹 시스템 구성 - WEB, WAS, DB

사용자 화면에서 정적 리소스는 Web Server, 애플리케이션 로직은 WAS를 거쳐 DB에 도달

정적 리소스가 많이 사용되면 Web 서버 증설, 애플리케이션 리소스가 많이 사용되면 WAS 증설

WAS 장애 시 Web 서버가 오류 화면 제공 가능

- 서블릿

HTML Form 데이터 전송

Post 전송 - 요청 HTTP 메시지

순서:

서버 TCP/IP 대기, 소켓 연결

HTTP 요청 메시지를 파싱해서 읽기

POST 방식, /save URL 인지

Content Type 확인

HTTP 메시지 바디 내용 피싱

저장 프로세스 실행

---

비즈니스 로직 실행

- 데이터베이스에 저장 요청

→ 의미있는 비즈니스 로직 (이거 제외하면 서블릿에 다 자동화)

---

HTTP 응답 메시지 생성 시작

HTTP 시작 라인 생성

Header 생성

메시지 바디에 HTML, 생성에서 입력

TCP/IP에 응답 전달, 소켓 종료

```jsx
@WebServlet(name = "helloServlet", urlPatterns = "hello")
public class HelloServlet extends HttpServlet {
		@Override
		protected void service(HttpServletRequest request, HttpServletResponse response){
		//애플리케이션 로직
		}
}
```

urlPatterns(/hello)의 URL이 호출되면 서블릿 코드가 실행

HTTP 요청 정보를 편리하게 사용할 수 있는 HttpServletRequest

HTTP 응답 정보를 편리하게 사용할 수 있는 HttpServletResponse

개발자는 HTTP 스펙을 매우 편리하게 사용

- 서블릿 컨테이너

서블릿 객체를 자동으로 생성, 초기화, 호출, 종료하는 생명주기 관리

싱글톤으로 관리 - 객체를 하나만 생성해두고 관리

멀티 쓰레드로 동시 요청을 해결

애플리케이션 코드를 하나하나 순차적으로 실행하는 쓰레드

동시처리가 필요하면 쓰레드를 추가로 생성

생성비용이 매우 비쌈 (컨텍스트 스위칭 비용 발생)

쓰레드 풀 - 톰캣(최대 200개)

- HTML, HTTP API, CSR, SSR

API는 Data를 전달(JSON 형식)

UI 화면이 필요하면 클라이언트가 별도 처리

3가지 고민사항

- 정적 리소스
- HTML 페이지 (동적으로 제공)
- HTTP API

를 제공하는 방식에 대해 고민해야됨

SSR- 서버 사이드 렌더링

서버에서 최종 HTML을 생성, 주로 정적인 화면

JSP, Thymeleaf

CSR - 클라이언트 사이드 렌더링

HTML 결과를 JS를 사용하여 웹 브라우저에서 동적으로 생성해서 적용

React

- 자바 백엔드 웹 기술 역사

서블릿 - 1997(HTML 생성이 어려움)

JSP - 1999(HTML 생성은 쉽지만, 비즈니스 로직까지 너무 많은 역할 담당)

조합하여 MVC 패턴 사용 2000년대 초—

현재 -

어노테이션 기반의 스프링 MVC 등장

@Controller

Spring Boot의 등장

과거에는 WAS를 서버에 직접 설치 후 소스는 War 파일로 만들어서 WAS에 배포

Spring Boot(서버를 내장)- Jar에 WAS포함 - 빌드 배포 단순화

Web Servlet - Spring MVC

Web Reactive - Spring WebFlux

뷰 템플릿

JSP - 속도 느림, 기능 부족 (권장 X)

타임리프 - 내추럴 템플릿, 스프링 MVC와 강력한 기능 통합, 최선의 선택