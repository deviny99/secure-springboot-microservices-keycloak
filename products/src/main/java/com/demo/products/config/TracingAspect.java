package com.demo.products.config;

import com.demo.products.models.Product;
import com.demo.products.models.Provider;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TracingAspect {

    @Autowired
    private Tracer tracer;


    @Around("execution(* com.demo.products.proxies.ProviderProxy.*(..))" )
    public Object aroundAdvice(ProceedingJoinPoint jp) throws Throwable {

        String class_name = jp.getTarget().getClass().getSimpleName();
        String method_name = jp.getSignature().getName();
        Span span = tracer.buildSpan(class_name + "." + method_name).withTag("class", class_name)
                    .withTag("method", method_name).start();
        ResponseEntity<Product> result = (ResponseEntity<Product>) jp.proceed();
        span.setTag("http.status_code",result.getStatusCodeValue());
        span.finish();
        return result;
    }





}
