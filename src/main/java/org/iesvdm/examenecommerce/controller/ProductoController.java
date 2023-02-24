package org.iesvdm.examenecommerce.controller;


import lombok.extern.slf4j.Slf4j;
import org.iesvdm.examenecommerce.domain.Product;
import org.iesvdm.examenecommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/productos")
public class ProductoController {


    private final ProductService productService;

    public ProductoController(ProductService productService){
        this.productService=productService;
    }

    @GetMapping(value = {"", "/"}, params={"!buscar", "!ordenar", "!pagina", "!tamanio" })
    public List<Product> all(){
        log.info("Accediendo a todos los productos");
        return this.productService.all();
    }

    @GetMapping(value = {"","/"}, params = {"!buscar", "!ordenar"})
    public ResponseEntity<Map<String, Object>> all(@RequestParam(value = "pagina", defaultValue = "0")
                                                   int pagina, @RequestParam(value = "tamanio", defaultValue  ="3")
                                                   int tamanio) {
        log.info("Accediendo a todas las películas");

        Map<String, Object> responseAll = this.productService.all(pagina, tamanio);
        return ResponseEntity.ok(responseAll);
    }
    @GetMapping(value = {"","/"})
    public ResponseEntity<Map<String, Object>> all(@RequestParam(value = "pagina", defaultValue = "0")
                                                   int pagina, @RequestParam(value = "tamanio", defaultValue  ="3")
                                                   int tamanio, @RequestParam(value ="buscar") Optional<String> buscar, @RequestParam(value = "ordenar")
                                                   Optional<String> ordenar) {
        log.info("Accediendo a todas las películas");

        Map<String, Object> responseAll = this.productService.all(pagina, tamanio, buscar, ordenar);
        return ResponseEntity.ok(responseAll);
    }

    @PostMapping({"","/"})
    public Product newProduct(@RequestBody Product product){
        return this.productService.save(product);
    }
    @GetMapping("/{id}")
    public Product one(@PathVariable("id") Long id){
        return this.productService.one(id);
    }
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return this.productService.replace(id, product);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        this.productService.delete(id);
    }








}
