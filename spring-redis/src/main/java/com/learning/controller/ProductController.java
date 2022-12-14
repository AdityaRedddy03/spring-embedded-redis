package com.learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.Product;
import com.learning.repository.ProductRepository;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@PostMapping
	public Product save(@RequestBody Product product) {
		return productRepository.save(product);
	}
	
	@GetMapping
	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	@GetMapping("/{id}")
	@Cacheable(key="#id" , value ="Product" , unless = "#result.price > 1000 ") // Cache element where price lessthan 1000
	public Product findProduct(@PathVariable int id) {
		return productRepository.findProductById(id);
	}
	
	@DeleteMapping("/{id}")
	@CacheEvict(key="#id" , value ="Product")
	public String remove(@PathVariable int id) {
		return productRepository.deleteProduct(id);
	}

}
