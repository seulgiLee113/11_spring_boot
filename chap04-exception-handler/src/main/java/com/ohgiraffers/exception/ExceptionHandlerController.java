package com.ohgiraffers.exception;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionHandlerController {

    @GetMapping("controller-null")
    public String nullPointerExceptionTest() {

        String str = null;
        str.charAt(0);      // 이 부분에서 강제로 NPE가 트리거링 됨!

        return "/";     // 위에서 발생한 NPE 때문에 어차피 해당 핸들러 메서드는 정상 종료될 수 없음
    }

    @GetMapping("controller-user")
    public String userCustomExceptionTest() throws MemberRegistException {

        // 이 부분에서 강제로 사용자 정의 예외인 MemberRegistException이 트리거링 됨!
        if(true) {
            throw new MemberRegistException("아무튼 인터넷 무슨 무슨 법으로 인해 당신은 회원 가입을 할 수 없습니다.");
        }

        return "/";     // 위에서 발생한 NPE 때문에 어차피 해당 핸들러 메서드는 정상 종료될 수 없음
    }


    /* 위에서 발생시킨 NPE 예외 타입(빌트인)을 핸들링할 메서드 */
    // public String nullPointerExceptionTest() 메서드 동작이 막혀서 여기로 넘어감
    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException e) {

        System.out.println("Controller 레벨의 NPE 처리기 동작 시작!");

        return "error/nullPointer";
    }


    /* 위에서 발생시킨 MemberRegistException 예외 타입(커스텀)을 핸들링할 메서드 */
    @ExceptionHandler(MemberRegistException.class)
    public String userCustomizedExceptionHandler(Model model, MemberRegistException e) {

        System.out.println("Controller 레벨의 MemberRegistException 처리기 동작 시작!");
        model.addAttribute("e", e);

        return "error/memberRegist";
    }

}
