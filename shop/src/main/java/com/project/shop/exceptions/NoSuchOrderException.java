package com.project.shop.exceptions;

public class NoSuchOrderException extends RuntimeException{

    public NoSuchOrderException() {
        super("해당 주문이 없습니다.");
    }
}
