# 使用SkyWalking手动追踪API

- 使用 maven 和 gradle 依赖相应的工具包:
```xml
 <dependency>
      <groupId>org.apache.skywalking</groupId>
      <artifactId>apm-toolkit-trace</artifactId>
      <version>${skywalking.version}</version>
      <!-- <version>5.0.0-beta2</version>-->
   </dependency>
```
- 在程序任何地方均可使用 TraceContext.traceId() API来获取traceId。
```text
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
······

public void hello(){
    String traceId = TraceContext.traceId();
    System.out.printlm("traceId:"+traceId);
}
```
- 自定义Tag:在需要定义tag的方法中通过@Trace注解将该方法加入到追踪链中
```text
    @Trace
    @GetMapping("/sleep")
    public void sleep() throws InterruptedException {
        log.info("{}", TraceContext.traceId());
        ActiveSpan.tag("sleep trace","sleeping...");
        Thread.sleep(6000l);
    }
```

- 运行：
**vm options:** 
```text
-javaagent:/Users/tanjian/Daocloud-gitlab/DMP/sky/github-source/incubator-skywalking/skywalking-agent/skywalking-agent.jar -Dskywalking.collector.servers=localhost:10800 -Dskywalking.agent.application_code=dmp-sw-trace-activation-demo
```
