package bit.com.a.aop;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LogAop {

	@Around("within(bit.com.a.controller.*)")
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable {
		
		// 공통기능 적용 메소드 출력하는 메소드명
		String signatureStr = joinpoint.getSignature().toShortString();
		
		// 공통기능 시작
		try {// 핵심기능 실행
			Object obj = joinpoint.proceed();
			System.out.println("loggerAOP:" + signatureStr + " 메소드 호출하여 실행 " + new Date());
			return obj;
		}finally {// 핵심기능 종료 후
		}
	}
}





