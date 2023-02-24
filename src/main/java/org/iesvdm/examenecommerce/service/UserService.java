package org.iesvdm.examenecommerce.service;

import org.iesvdm.examenecommerce.domain.Product;
import org.iesvdm.examenecommerce.domain.User;
import org.iesvdm.examenecommerce.repository.ProductRepository;
import org.iesvdm.examenecommerce.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository= userRepository;
    }



    public List<User> all(){
        return this.userRepository.findAll();
    }
}
