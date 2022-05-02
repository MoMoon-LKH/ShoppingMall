package com.project.shop.exceptions;

public class NotEnoughItemException extends RuntimeException {

    public NotEnoughItemException() {
        super("주문한 상품의 재고가 부족합니다.");
    }
}
