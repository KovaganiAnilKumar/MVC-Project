package com.Sathya.mvc.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.Sathya.mvc.Repository.ProductRepository;
import com.Sathya.mvc.entity.ProductEntity;
import com.Sathya.mvc.model.ProductModel;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	public void saveProductData(ProductModel productModel)
	{
		double price=productModel.getProPrice();
		double dPrice=0.0;
		String category=productModel.getProCategory();
		switch(category)
		{
		case "Mobile":
			dPrice=0.1*price;
			break;
		case "Laptop":
			dPrice=0.15*price;
			break;
		case "Printer":
			dPrice=0.2*price;
			break;
		case "Camera":
			dPrice=0.25*price;
			break;
		}
		ProductEntity productEntity=new ProductEntity();
		productEntity.setProName(productModel.getProName());
		productEntity.setProBrand(productModel.getProBrand());
		productEntity.setProPrice(productModel.getProPrice());
		productEntity.setProCategory(productModel.getProCategory());
		productEntity.setProDescription(productModel.getProDescription());
		
		productEntity.setDisPrice(dPrice);
		productEntity.setCreatedAt(LocalDate.now());
		productEntity.setCreatedBy(System.getProperty("user.name"));
		
		productRepository.save(productEntity);
		
		}
	public List<ProductEntity> getAllProducts()
	{
		List<ProductEntity> products=productRepository.findAll();
		return products;
	}
	
	public void deleteProduct(Long proId) {
		productRepository.deleteById(proId);	
		
	}
	
	

	
	
	

    public ProductModel getProductById(Long proId) {		
		ProductEntity productEntity= productRepository.findById(proId).get();
		
	
	   ProductModel productModel=new ProductModel();
	   
	   productModel.setProName(productEntity.getProName());
	   productModel.setProBrand(productEntity.getProBrand());
	   productModel.setProPrice(productEntity.getProPrice());
	   productModel.setProCategory(productEntity.getProCategory());
	   productModel.setProDescription(productEntity.getProDescription());
	   
	   return productModel;
	 }
	
	
	
	
	
	
	
	
	public void updateProduct(Long proId, ProductModel productModel) {
     
        ProductEntity existingProduct = productRepository.findById(proId).get();

        double price=productModel.getProPrice();
		double dPrice=0.0;
		String category=productModel.getProCategory();
		switch(category)
		{
		case "Mobile":
			dPrice=0.1*price;
			break;
		case "Laptop":
			dPrice=0.15*price;
			break;
		case "Printer":
			dPrice=0.2*price;
			break;
		case "Camera":
			dPrice=0.25*price;
			break;
		}
        
        existingProduct.setProName(productModel.getProName());
        existingProduct.setProBrand(productModel.getProBrand());
        existingProduct.setProPrice(productModel.getProPrice());
        existingProduct.setProDescription(productModel.getProDescription());
        existingProduct.setProCategory(productModel.getProCategory());
        existingProduct.setDisPrice(dPrice);
    
        productRepository.save(existingProduct);
    }


	
	public Optional<ProductEntity> getProduct(Long proId)
	{
		Optional<ProductEntity>product = productRepository.findById(proId);
		return product;
	}
	
	 }

	
			

