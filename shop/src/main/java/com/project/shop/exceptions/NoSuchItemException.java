package com.project.shop.exceptions;

public class NoSuchItemException extends RuntimeException{
    public NoSuchItemException() {
        super("찾을 수 없는 아이템입니다.");
    }
}
