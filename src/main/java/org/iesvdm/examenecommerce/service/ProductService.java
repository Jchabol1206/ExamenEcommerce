package org.iesvdm.examenecommerce.service;


import org.iesvdm.examenecommerce.domain.Product;
import org.iesvdm.examenecommerce.exception.ProductNotFoundException;
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
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository= productRepository;
    }



    public List<Product> all(){
        return this.productRepository.findAll();
    }
    public Map<String, Object> all(int pagina, int tamanio) {
        PageRequest paginado = PageRequest.of(pagina, tamanio, Sort.by("id").ascending());

        Page<Product> pageAll = this.productRepository.findAll(paginado);

        Map<String, Object> response = new HashMap<>();

        response.put("products", pageAll.getContent());
        response.put("currentPage", pageAll.getNumber());
        response.put("totalItems", pageAll.getTotalElements());
        response.put("totalPages", pageAll.getTotalPages());

        return response;
    }
    public Map<String, Object> all(int pagina, int tamanio, Optional<String> buscarOptional, Optional<String> ordenarOptional) {
        Pageable paginado=PageRequest.of(pagina, tamanio, Sort.by("id").ascending());

        if((ordenarOptional.isPresent() && ordenarOptional.get().equals("desc") )){
            paginado= PageRequest.of(pagina, tamanio, Sort.by("id").descending()) ;
        }


        Page<Product> pageAll = this.productRepository.findAll(paginado);

        if(buscarOptional.isPresent()){
            System.out.println("////////////////////////////////////////////");
            System.out.println(this.productRepository.findByNameContainingIgnoreCase(buscarOptional.get(), paginado).toString());
            pageAll= this.productRepository.findByNameContainingIgnoreCase(buscarOptional.get(), paginado);
        }

        Map<String, Object> response = new HashMap<>();

        response.put("products", pageAll.getContent());
        response.put("currentPage", pageAll.getNumber());
        response.put("totalItems", pageAll.getTotalElements());
        response.put("totalPages", pageAll.getTotalPages());

        return response;
    }


    public Product save(Product product){
        return this.productRepository.save(product);
    }

    public Product replace(Long id, Product product){
        return this.productRepository.findById(id).map( p -> (id.equals(product.getId())  ?
                        this.productRepository.save(product) : null))
                .orElseThrow(() -> new ProductNotFoundException(id)
        );
    }

    public Product one(Long id) {
        return this.productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
    public void delete(Long id) {
        this.productRepository.findById(id).map(p -> {this.productRepository.delete(p);
                    return p;})
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}
