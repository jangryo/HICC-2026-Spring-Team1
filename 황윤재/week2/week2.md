# 서블릿 (Servlet) 기초 정리

## 1. Hello 서블릿

### 서블릿이란?

- 자바를 사용해 웹 요청/응답을 처리하는 서버 사이드 컴포넌트
- `@WebServlet` 어노테이션으로 URL 패턴 매핑
- `@ServletComponentScan`을 메인 클래스에 붙여야 서블릿이 스캔됨

### 기본 구조

java

`@ServletComponentScan
@SpringBootApplication
public class ServletApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServletApplication.class, args);
    }
}`

> `@ServletComponentScan` : 해당 패키지 하위의 서블릿, 필터, 리스너를 자동 등록
>

---

## 2. HttpServletRequest

### 기본 사용법 & Header 조회

- `request.getMethod()` → HTTP 메서드 조회
- `request.getRequestURI()` → URI 조회
- `request.getHeaderNames()` → 전체 헤더 목록
- `request.getHeader("헤더명")` → 특정 헤더 값

### HTTP 요청 메시지 바디 조회

### ✅ GET - 쿼리 파라미터

- URL : `/url?username=kim&age=20`
- `request.getParameter("username")` → 단일 파라미터
- `request.getParameterValues("username")` → 복수 파라미터
- `request.getParameterNames()` → 전체 파라미터 목록

### ✅ POST - HTML Form

- `Content-Type: application/x-www-form-urlencoded`
- 쿼리 파라미터와 **동일한 방식**으로 조회 가능
- `request.getParameter("username")` 그대로 사용

### ✅ HTTP API - Message Body

- `Content-Type: application/json`
- `request.getInputStream()` 으로 바이트 스트림 직접 읽기
- `ObjectMapper.readValue()` 로 JSON → 자바 객체 변환

java

`HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);`

---

## 3. HttpServletResponse

### 기본 사용법 & Header 설정

java

`// 상태 코드
response.setStatus(HttpServletResponse.SC_OK); // 200

// 헤더 설정
response.setHeader("Content-type", "text/plain;charset=utf-8");
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("my-header", "hello"); // 커스텀 헤더`

### 편의 메서드

java

`// Content 설정
response.setContentType("text/plain");
response.setCharacterEncoding("utf-8");

// 쿠키 설정
Cookie cookie = new Cookie("myCookie", "good");
cookie.setMaxAge(600); // 600초
response.addCookie(cookie);

// 리다이렉트
response.setStatus(HttpServletResponse.SC_FOUND); // 302
response.setHeader("Location", "/basic/hello-form.html");`

### HTTP 응답 바디 조회

### ✅ HTML 응답

- `Content-Type: text/html;charset=utf-8`
- `response.getWriter().println("<html>...</html>")`

### ✅ JSON 응답

java

`@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("kim");
        helloData.setAge(20);

        // 객체 → JSON 문자열 변환
        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);
    }
}
```

| 항목 | 내용 |
|---|---|
| Content-Type | `application/json` |
| 직렬화 | `objectMapper.writeValueAsString(객체)` |
| 역직렬화 | `objectMapper.readValue(문자열, 클래스)` |

> ⚠️ `application/json`은 스펙상 utf-8을 기본으로 사용하므로 `charset=utf-8` 은 의미 없는 파라미터지만 일부 레거시 클라이언트 대응 목적으로 명시하기도 함

---

## 4. 전체 흐름 요약
```
클라이언트 요청
↓
@WebServlet 매핑
↓
service() 실행
↓
HttpServletRequest  →  요청 파라미터 / 바디 읽기
HttpServletResponse →  상태코드 / 헤더 / 바디 쓰기
↓
클라이언트 응답`