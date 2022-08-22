#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.

* 서블릿 컨테이너는 웹 애플리케이션의 상태를 관리하는 ServetContext 생성한다.

* ServeletContext가 초기화 되면 컨텍스트의  초기화 이벤트가 발생된다.

* 등록된 ServletContextListener의 콜백 메서드가 호출된다. 이 무제에서는 ContextLoaderListener의 contextInitialized() 메서드가 호출된다.

* jwp.sql 파일에서 SQL 문을 실행해 데이터 베이스 테이블을 초기화한다.

* 서블릿 컨테이너는 클라이언트로부터의 최초 요청시 (또는 컨테이너에 서블릿 인스턴스를 생성하도록 미리 설정을 한다면 최초 요청 전에) 
  DispatcherServletStartup 인스턴스를 생성한다.(생성자 호출)
  이에 대한 설정은 @WebServlet의 loadOnStartup 속성으로 설정할 수 있다.
  이 문제에서는 loadOnStartup 속성이 설정되어 있기 때문에 서블릿 컨테이너가 시작하는 시점에 인스턴스를 생성한다.
  
* DispatchServlet 인스턴스의 init() 메서드를 호출해 초기화 작업을 진행한다.

* init() 메서드 안에서 RequestMapping 객체를 생성한다.

* RequestMapping 인스턴스의 initMapping() 메서드를 호출한다. initMapping() 메서드에서는 요청 URL 과 Controller 인스턴스를 매핑시킨다.

#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.

* localhost:8080으로 접근하면 요청을 처리할 서블릿에 접근하기 전에 먼저 ResourceFilter와 CharacterEncodingFilter의 doFilter() 메서드가 실행된다.
  ResourceFilter의 경우 해당 요청이 정적 자원(Css, 자바 스크립트, 이미지) 요청이 아니기 때문에 서블릿으로 요청을 위힘한다.
  
* 요청 처리는 "/" 으로 매핑되어 있는 DispatchServlet 이므로 이 서블릿의 service() 메서드가 실행된다.

* service() 메서드는 요청받은 URL을 분석해 해당 Controller 객체를  RequestMapping에서 가져온다. 
  요청 URL은 "/" 이며, 이와 연결되어 있는 HomeController가 반환된다.

* service() 메서드는 HomeController의 execute() 메서드에게 작업을 위임한다.
  요청에 대한 실질적인 작업은 HomeController의 execute() 메서드가 실행한다. execute() 메서드의 반환 값은 ModelAndView 이다.
  
* service() 메서드는 반환 받은 ModelAndView 의 모델 데이터를 뷰의 render() 메서드에 전달한다. 이 요청에서 View는 JspView 이다. 
  JspView는 render()메서드로 전달된 모델 데이터를 home.jsp 에 전달해 HTML을 생성하고, 응답함으로써 작업을 끝낸다.

#### 7. next.web.qna package의 ShowController는 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
* 
