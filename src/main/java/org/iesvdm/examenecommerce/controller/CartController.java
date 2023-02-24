package org.iesvdm.examenecommerce.controller;


import lombok.extern.slf4j.Slf4j;
import org.iesvdm.examenecommerce.domain.Cart_Item;
import org.iesvdm.examenecommerce.domain.Product;
import org.iesvdm.examenecommerce.domain.User;
import org.iesvdm.examenecommerce.service.CartService;
import org.iesvdm.examenecommerce.service.ProductService;
import org.iesvdm.examenecommerce.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/carrito")
public class CartController {

    private final UserService userService;

    private final CartService cartService;

    public CartController(UserService userService, CartService cartService){
        this.userService=userService;
        this.cartService=cartService;
    }
    /* el metodo all era para probar como me devolvia en json el usuario */
    @GetMapping(value = {"", "/"})
    public List<User> all(){
        log.info("Accediendo a todos los productos");
        return this.userService.all();
    }


    @PostMapping({"","/"})
    public Cart_Item newProduct(@RequestBody Cart_Item product){
        return this.cartService.save(product);
    }


    /*
    @GetMapping("/{idUsu}/")
    public Product one(@PathVariable("id") Long id){
        return this.productService.one(id);
    }
    */





}
