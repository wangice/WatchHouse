package com.ice.brother.house.app.web.aspect;

import com.ice.brother.house.Misc;
import com.ice.brother.house.app.web.core.Rsp;
import com.ice.brother.house.app.web.core.Rsp.RspErr;
import com.ice.brother.house.common.proto.OperateLog.WebOperateLog;
import com.ice.brother.house.mqproducer.KafkaProducerMessageHandler;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 切面,请求过程的拦截输出日志和异常的捕获
 *
 * @author:ice
 * @Date: 2018/7/10 20:07
 */
@Aspect
@Component
public class ControllerInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class);

  @Value("${log.topic}")
  private String topic;

  @Autowired
  private KafkaProducerMessageHandler messageHandler;

  @Pointcut("execution(* com.ice.brother.house.app.web.controller.*..*(..))")
  public void methodPointcut() {

  }

  @AfterThrowing(pointcut = "methodPointcut()", throwing = "e")
  public void doThrowingAfterMethodPointcut(JoinPoint jp, Throwable e) {
    logger.error("exception: {}", Misc.trace(e));
  }

  @Around("methodPointcut()")
  public Object aroundMethodPointcut(ProceedingJoinPoint pjp) {
    Object result;
    long startTime = System.currentTimeMillis();
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    String method = request.getMethod();
    String uri = request.getRequestURI();
    Map<String, String[]> parameterMap = request.getParameterMap();
    logger.info("request start,uri: {},method: {},req: {}", uri, method, parameterMap);
    long endTime;
    //
    WebOperateLog.Builder webOperateLog = WebOperateLog.newBuilder();
    webOperateLog.setRequestUrl(uri);
    webOperateLog.setModule("house-app-web");
    webOperateLog.setIp(request.getRemoteAddr());
    webOperateLog.setRealIp(request.getHeader("X-Real-IP") == null ? request.getRemoteAddr()
        : request.getHeader("X-Real-IP"));
    webOperateLog.setRequestMethod(
        request.getMethod() == null ? request.getRequestURI() : request.getMethod());
    //获取参数
    Map<String, String> paramMap = new HashMap<>();
    Enumeration<String> paramNames = request.getParameterNames();
    while (paramNames.hasMoreElements()) {
      String paramName = paramNames.nextElement();
      String valueStr = request.getParameter(paramName);
      paramMap.put(paramName, valueStr);
    }
    webOperateLog.putAllParams(paramMap);
    webOperateLog.setTime(new Date().getTime());
    //
    try {
      result = pjp.proceed();
      endTime = System.currentTimeMillis();
      logger.info("request end,elap: {}ms,uri: {},method: {},rsp: {}", (endTime - startTime), uri,
          method, Misc.obj2json(result));
      webOperateLog.setDuration(endTime - startTime);
      webOperateLog.setResponse(Misc.obj2json(result));
      messageHandler.send(topic, webOperateLog.build(), new Date().getTime());
    } catch (Throwable throwable) {
      endTime = System.currentTimeMillis();
      logger.error("exception: {}", Misc.trace(throwable));
      Rsp data = new Rsp();
      data.setErr(RspErr.ERR_SERVER_ERROR.getErrCode());
      data.setDesc(RspErr.ERR_SERVER_ERROR.getErrName());
      logger
          .debug("request end,elap: {}ms,uri: {},method: {},rsp: {},e: {}", (endTime - startTime),
              uri, method, Misc.obj2json(data), Misc.trace(throwable));
      webOperateLog.setDuration(endTime - startTime);
      webOperateLog.setResponse(Misc.obj2json(data));
      messageHandler.send(topic, webOperateLog.build(), new Date().getTime());
      return data;
    }
    return result;
  }
}
