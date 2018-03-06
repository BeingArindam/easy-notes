package com.example.learning.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;


@Aspect
public class LogAspect {
	
	@Before("execution(* com.example.learning.*.*.*(..))")
	public void allAdvice(JoinPoint joinPoint){
		ThreadLocal<String> threadLocal = new ThreadLocal<>();
		String string = threadLocal.get();
		System.out.println(string+": Dao method getter called "+joinPoint.toLongString());
	}
}
