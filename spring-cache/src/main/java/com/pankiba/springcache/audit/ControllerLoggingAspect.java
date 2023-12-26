package com.pankiba.springcache.audit;

import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class ControllerLoggingAspect {

	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	public void controllerPointCut() {
		// point cut for rest controller layer
	}
	
	@Around("controllerPointCut()")
	public Object restLayerLogging(ProceedingJoinPoint proceedingJoinPoint) {
		
		String className = proceedingJoinPoint.getSignature().getDeclaringType().getName();
		String methodName = proceedingJoinPoint.getSignature().getName();
		
		StopWatch stopWatch = StopWatch.createStarted();
		Object returnValue = null;
		
		try {
			returnValue = proceedingJoinPoint.proceed();
			stopWatch.stop();
			
			log.info("method "+methodName+" from class "+className+" executed successfully in (HH:mm:ss.SSS) : "+stopWatch);
		}
		catch(Throwable throwable) {
			log.error("error while execution");
		}
		
		return returnValue;
	}
	
}
