package com.chen.myblog.Interceptor;

import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

//配置拦截器
@ControllerAdvice
public class interceptor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)  //拦截处理exception的异常
    public ModelAndView exceptinterecpt(HttpServletRequest request,Exception e) throws Exception {

        logger.error("Request URL:{},Exception:{}",request.getRequestURL(),e);

        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
            throw e;

        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("URL",request.getRequestURL());
        modelAndView.addObject("exception",e);
        modelAndView.setViewName("error/error");

        return modelAndView;


    }
}
