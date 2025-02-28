package com.ohgiraffers.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/* @ControllerAdvice :
*  Spring에서 예외 처리를 담당하는 어노테이션으로, 전역(global-scope) 예외 처리를 담당하게 된다.
*  즉, 여러개의 컨트롤러에서 발생하는 예외를 일괄적으로 처리할 수 있다.
*  따라서 해당 어노테이션을 사용하면 모든 컨트롤러에서 발생하는 예외를 한 곳에서 처리할 수 있어
*  중복되는 코드를 줄여준다.
* */
@ControllerAdvice
public class GlobalExceptionHandler {

    /* 위에서 발생시킨 NPE 예외 타입(빌트인)을 핸들링할 메서드 */
    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException e) {

        System.out.println("Global 레벨의 NPE 처리기 동작 시작!");

        return "error/nullPointer";
    }


    /* 위에서 발생시킨 MemberRegistException 예외 타입(커스텀)을 핸들링할 메서드 */
    @ExceptionHandler(MemberRegistException.class)
    public String userCustomizedExceptionHandler(Model model, MemberRegistException e) {

        System.out.println("Global 레벨의 MemberRegistException 처리기 동작 시작!");
        model.addAttribute("e", e);

        return "error/memberRegist";
    }


    /* 상위 타입인 Exception 타입을 사용하면 구체적으로 작성되지 않은 기타 타입의 에러가 발생하더라도
    *  공통적으로 처리 가능하기 때문에 일반적으로 default 예외 처리 용도로 많이 사용된다.
    * */
    @ExceptionHandler(Exception.class)
    public String defaultExceptionHandler(Model model, Exception e) {

        System.out.println("Global 레벨의 기타 등등 공통 에외처리기 동작 시작!");
        model.addAttribute("e", e);

        return "error/default";
    }
}
