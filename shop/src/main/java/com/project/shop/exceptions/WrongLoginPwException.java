package com.project.shop.exceptions;

public class WrongLoginPwException extends RuntimeException{

    public WrongLoginPwException() {
        super("아이디 또는 비밀번호가 잘못되었습니다.");

    }
}
