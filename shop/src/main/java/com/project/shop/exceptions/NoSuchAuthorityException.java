package com.project.shop.exceptions;

import javax.management.RuntimeErrorException;

public class NoSuchAuthorityException extends RuntimeException {

    public NoSuchAuthorityException() {
        super("찾을 수 없는 권한입니다.");
    }
}
