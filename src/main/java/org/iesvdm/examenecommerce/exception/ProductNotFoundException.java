package org.iesvdm.examenecommerce.exception;

public class ProductNotFoundException extends RuntimeException{


    public ProductNotFoundException(Long id) {
        super("Not found product with id: " + id);
    }
}
