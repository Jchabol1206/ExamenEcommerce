package org.iesvdm.examenecommerce.exception;

public class CartNotFoundException extends RuntimeException{

    public CartNotFoundException(Long id) {
        super("Not found cart with id: " + id);
    }
}
