package com.project.shop.exceptions;

public class NoSuchMemberException extends RuntimeException{
    public NoSuchMemberException() {
        super("찾을 수 없는 회원입니다.");
    }
}
