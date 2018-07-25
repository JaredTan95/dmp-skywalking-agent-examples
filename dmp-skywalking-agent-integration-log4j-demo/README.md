# 日志组件集成

- 使用 maven 和 gradle 依赖相应的工具包:
```xml
<dependency>
    <groupId>org.apache.skywalking</groupId>
    <artifactId>apm-toolkit-logback-1.x</artifactId>
    <version>${skywalking.version}</version>
    <!-- <version>5.0.0-beta2</version>-->
</dependency>
```
- 配置logback.xml
```text
......
<appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
      <encoder class="ch.qos.logback.core.encoder.LayoutWrappingEncoder">
          <layout class="org.apache.skywalking.apm.toolkit.log.logback.v1.x.TraceIdPatternLogbackLayout">
              <Pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%tid] [%thread] %-5level %logger{36} -%msg%n</Pattern>
          </layout>
      </encoder>
</appender>
......
```
使用-javaagent参数激活sky-walking的探针, 如果当前上下文中存在traceid，logback将输出traceId。否则将输出TID: N/A.
- 效果

```text
2018-07-25 17:44:58.842 [TID:N/A] [main] INFO  o.s.b.c.e.t.TomcatEmbeddedServletContainer -Tomcat started on port(s): 8001 (http)
2018-07-25 17:44:58.852 [TID:N/A] [main] INFO  i.d.n.s.l.AgentLog4jDemoApplication -Started AgentLog4jDemoApplication in 4.03 seconds (JVM running for 12.204)
2018-07-25 17:45:10.226 [TID:39.58.15325119102050001] [http-nio-8001-exec-1] INFO  o.a.c.c.C.[Tomcat].[localhost].[/] -Initializing Spring FrameworkServlet 'dispatcherServlet'
2018-07-25 17:45:10.226 [TID:39.58.15325119102050001] [http-nio-8001-exec-1] INFO  o.s.web.servlet.DispatcherServlet -FrameworkServlet 'dispatcherServlet': initialization started
2018-07-25 17:45:10.247 [TID:39.58.15325119102050001] [http-nio-8001-exec-1] INFO  o.s.web.servlet.DispatcherServlet -FrameworkServlet 'dispatcherServlet': initialization completed in 21 ms
2018-07-25 17:45:10.314 [TID:39.58.15325119102050001] [http-nio-8001-exec-1] INFO  i.d.n.sw.log4j.HelloWorldController -words:dddddd
```
