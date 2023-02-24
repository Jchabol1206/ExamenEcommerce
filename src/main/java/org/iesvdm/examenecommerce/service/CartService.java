package org.iesvdm.examenecommerce.service;

import org.iesvdm.examenecommerce.domain.Cart_Item;
import org.iesvdm.examenecommerce.domain.Product;
import org.iesvdm.examenecommerce.exception.CartNotFoundException;
import org.iesvdm.examenecommerce.exception.ProductNotFoundException;
import org.iesvdm.examenecommerce.repository.CartRepository;
import org.iesvdm.examenecommerce.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class CartService {


    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository){
        this.cartRepository= cartRepository;
    }

    public Cart_Item save(Cart_Item cartItem){
        return this.cartRepository.save(cartItem);
    }


    public Cart_Item one(Long id) {
        return this.cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException(id));
    }
    public void delete(Long id) {
        this.cartRepository.findById(id).map(p -> {this.cartRepository.delete(p);
                    return p;})
                .orElseThrow(() -> new CartNotFoundException(id));
    }
}
