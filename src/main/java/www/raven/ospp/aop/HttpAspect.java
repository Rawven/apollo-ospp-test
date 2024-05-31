package www.raven.ospp.aop;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * logging aspect
 *
 * @author 刘家辉
 * @date 2023/11/22
 */
@Aspect
@Component
@Slf4j
public class HttpAspect {

    /**
     * 定义切点表达式,指定通知功能被应用的范围
     */

    @Pointcut("execution(public * www.raven.ospp.controller.*.*(..))")
    public void webLog() {
    }

    /**
     * 通知包裹了目标方法，在目标方法调用之前和之后执行自定义的行为
     * ProceedingJoinPoint切入点可以获取切入点方法上的名字、参数、注解和对象
     **/
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        //获取当前请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        log.info("----HTTP 收到请求 url:{},调用者:{}", request.getRequestURL().toString(), request.getRemoteUser());
        Object result = joinPoint.proceed();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        long endTime = System.currentTimeMillis();
        log.info("----HTTP 被调用方法 method:{} ", request.getMethod());
        log.info("----HTTP 返回值:{} --总耗时:{}毫秒", result, (int) (endTime - startTime));
        return result;
    }

}
