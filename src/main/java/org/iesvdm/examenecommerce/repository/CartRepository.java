package org.iesvdm.examenecommerce.repository;

import org.iesvdm.examenecommerce.domain.Cart_Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart_Item, Long> {
}
