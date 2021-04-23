package com.example.demo.reposities;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.Product;

public interface productRepository extends CrudRepository<Product, Long> {
	

}

