package com.ohgiraffers.exception;

/* 사용자 정의 예외 클래스 */
public class MemberRegistException extends Exception{

    public MemberRegistException(String msg) {
        super(msg);
    }
}
