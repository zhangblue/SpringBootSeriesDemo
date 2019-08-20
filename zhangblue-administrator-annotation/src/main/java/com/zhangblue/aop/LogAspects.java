package com.zhangblue.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAspects {

	/**
	 * 抽取公共的切入点表达式
	 */
	@Pointcut(value = "execution(public int com.zhangblue.aop.MathCaluclator.div(int,int))")
	public void porinCut() {
	}

	@Before(value = "com.zhangblue.aop.LogAspects.porinCut()")
	public void logStart(JoinPoint joinPoint) {
		System.out.println(joinPoint.getSignature().getName() + "方法运行.....参数列表为：{" + Arrays.asList(joinPoint.getArgs()) + "}");
	}

	@After(value = "com.zhangblue.aop.LogAspects.porinCut()")
	public void logEnd(JoinPoint joinPoint) {
		System.out.println(joinPoint.getSignature().getName() + "方法结束.....");
	}

	@AfterReturning(value = "porinCut()", returning = "result")
	public void logReturn(JoinPoint joinPoint, Object result) {
		System.out.println(joinPoint.getSignature().getName() + "除法正常返回.....运行结果：{" + result + "}");
	}

	@AfterThrowing(value = "porinCut()", throwing = "exc")
	public void logException(JoinPoint joinPoint, Exception exc) {
		System.out.println(joinPoint.getSignature().getName() + "方法运行异常.....异常信息：{" + exc + "}");
	}
}
