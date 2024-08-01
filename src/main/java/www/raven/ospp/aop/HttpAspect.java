package www.raven.ospp.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Aspect
@Component
@Slf4j
public class HttpAspect {

  @Pointcut("execution(public * www.raven.ospp.controller.*.*(..))")
  public void webLog() {
  }

  @Around("webLog()")
  public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
    long startTime = System.currentTimeMillis();
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    assert attributes != null;
    HttpServletRequest request = attributes.getRequest();
    log.info("----HTTP 收到请求 url:{},调用者:{}", request.getRequestURL().toString(),
        request.getRemoteUser());
    Object result = joinPoint.proceed();
    long endTime = System.currentTimeMillis();
    log.info("----HTTP 被调用方法 method:{} ", request.getMethod());
    log.info("----HTTP 返回值:{} --总耗时:{}毫秒", result, (int) (endTime - startTime));
    return result;
  }

}
