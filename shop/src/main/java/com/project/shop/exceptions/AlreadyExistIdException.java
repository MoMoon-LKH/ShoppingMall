package com.project.shop.exceptions;

public class AlreadyExistIdException extends RuntimeException{
    public AlreadyExistIdException() {
        super("이미 존재하는 아이디입니다.");
    }
}
