package com.example.warehouse.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.warehouse.model.Product;
import com.example.warehouse.repository.ProductRepository;


@Service



public class ProductService {
	
	/*
	 Implement the business logic for the ProductService operations in this class
	 Make sure to add required annotations
	 */

	@Autowired
	
	private ProductRepository productRepository;
	
	
	//to post all the Product details
	//created->201
	//badRequest->400
	
	public Object postProduct(Product product) {
		if(productRepository.existsBySku(product.getSku())) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
		try {
			Product newprod = new Product(product.getName(),product.getDescription(),product.getVendor(),product.getPrice(),product.getStock(),product.getCurrency(),product.getImage_url(),product.getSku());
			productRepository.save(newprod);
			return new ResponseEntity<Object>(newprod,HttpStatus.CREATED);
			}
		catch(Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
		}
	
	
	//to get all the Product details
	//ok->200
	//badRequest->400
	
	public Object getProduct() {
		List<Product> products = productRepository.findAll();
		if(products.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		return ResponseEntity.ok(products);
		}
	
	
	//to get the product with the value(pathVariable)
	//ok()->200
	//badRequest()->400
	
	public ResponseEntity<Object> getSimilarVendor(String value) {
		List<Product> similarproducts = productRepository.findByVendor(value);
		if(similarproducts.isEmpty()) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
		else {
			return new ResponseEntity<Object>(similarproducts,HttpStatus.OK);
			}
		}
	
	
	//to update the Product with id as pathVariable and Product as object in RequestBody
	//ok->200
	//badRequest->400
	
	public ResponseEntity<Object> updateProduct(int id, Product product) {
		Optional<Product> expr = productRepository.findById(id);
		if(!expr.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			Product pr = expr.get();
			pr.setPrice(product.getPrice());
			pr.setStock(product.getStock());
			return new ResponseEntity<>(productRepository.save(pr),HttpStatus.OK);
			}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
	
	
	// to delete the product by using id as PathVariable
	//ok->200
	//badRequest->400
	
	public ResponseEntity<Object> deleteProductById(int id) {
		if(!productRepository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		try {
			productRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
			}
		catch(Exception e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
}