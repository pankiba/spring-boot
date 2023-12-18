# Spring Boot Internals

In-depth understanding of spring boot's internal working

## 
The class that implements the Servletcontainerinitializer interface is passed in the jar package through the meta-inf\services\ A file named Javax.servlet.ServletContainerInitializer is identified later in the container when the servlet container is started.

Springservletcontainerinitializer Java doc JAR Services API ServiceLoader.load(Class) method detecting the spring-web module ' s META-INF/services/javax.servlet.ServletContainerInitializer Service Pro Vider configuration file. See the JAR Services API documentation as well as sections 8.2.4 of the Servlet 3.0 Final Draft specification fo R complete details.

@HandlesTypes:

This annotation was used to declare an array of application classes which was passed to a javax.servlet.ServletContainerInitializer .

Springservletcontainerinitializer.onstartup (webappinitializerclasses, ServletContext); Java Doc:

Delegate the ServletContext WebApplicationInitializer implementations present on the application classpath.

Because This class declares @ HandlesTypes(WebApplicationInitializer.class) , Servlet 3.0+ containers would automatically scan the classpath for implementations of S Pring ' s interface and provide the set of all such types to the parameter of this WebApplicationInitializer webAppInitializerClasses method.

If No WebApplicationInitializer implementations is found on the Classpath, this method is effectively a no-op. An info-level log message would be issued notifying the user that the have ServletContainerInitializer indeed been invoked but that no WebApplicationInitializer imple Mentations were found.

Assuming that one or more WebApplicationInitializer types is detected, they would be instantiated (and sorted if the @ @Order annotatio n is present or the Ordered interface have been implemented). Then the WebApplicationInitializer.onStartup(ServletContext) method is invoked on each instance, delegating the such so each ServletContext instance may register and Conf Igure servlets such as Spring ' s DispatcherServlet , listeners such as Spring ' s ContextLoaderListener , or any other Servlet API componentry such as Fil Ters.

configures the pluggable nature of the runtime/shared library by using the @ handlestypes annotation.

Servletcontainerinitializer and @handlestypes

## 