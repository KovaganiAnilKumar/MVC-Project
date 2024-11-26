package com.Sathya.mvc.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Sathya.mvc.entity.ProductEntity;
import com.Sathya.mvc.model.ProductModel;
import com.Sathya.mvc.service.ProductService;

import jakarta.validation.Valid;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	//get the productform 
	@GetMapping("/productform")
	public String getProductForm(Model model)
	{
		//creating a object
		ProductModel productModel=new ProductModel();
		
		productModel.setProBrand("Sathya");
		//add the empty object to the form 
		model.addAttribute("productModel",productModel);
		model.addAttribute("page","productform");
		return "index";
	}
	
	//
	@PostMapping("/saveProduct")
	public String saveProductForm(@Valid ProductModel productModel,BindingResult bindingResult, Model model)
	{
		if(bindingResult.hasErrors())
		{
			model.addAttribute("productModel",productModel);
			return "add-product";
		}
		
		
		productService.saveProductData(productModel);
		return "success";
				
	}
	@GetMapping("/getProducts")
	public String getProducts(Model model)
	{
		List<ProductEntity> products=productService.getAllProducts();
		model.addAttribute("products",products);
		model.addAttribute("page","getProducts");
		return "index";
	}
	
	@GetMapping("/deleteProduct")
	public String deleteProduct(@RequestParam("id") Long proId) {
	    productService.deleteProduct(proId);
	    return "redirect:/getProducts"; 
	}
		@GetMapping("/editProduct")
	public String showEditForm(@RequestParam("id") Long proId, Model model)
	{
		
		ProductModel productModel=productService.getProductById(proId);
		model.addAttribute("productModel", productModel);
		model.addAttribute("categories",Arrays.asList("Mobile", "Camera", "Printer", "Laptop"));
		model.addAttribute("proId", proId);
		
		return "productform";
	}
	
	
		@PostMapping("/update")
		public String updateProduct(@ModelAttribute ProductModel productModel, 
									@RequestParam Long proId) {
			productService.updateProduct(proId, productModel);

			return "redirect:/getProducts"; 
		}
	
	
		 @GetMapping("/about")
		    public String aboutus(Model model) {
		     model.addAttribute("page","about");
		        return "index";
		    }
		 
		@GetMapping("/contact")
		public String contactus(Model model) {
			model.addAttribute("page","contact");
			return "index";
		}
		
		
		
		//search
		@GetMapping("/getproduct") 
		public String getById(Model model)
		{
			ProductModel productModel = new ProductModel();
			model.addAttribute("proId", productModel );
			model.addAttribute("page","getproduct");
			return "index";
		}
	 @PostMapping("/searchProduct")
	    public String searchProduct(@RequestParam Long id, Model model) {
		        Optional<ProductEntity> productOpt = productService.getProduct(id); // Retrieve the product
		        if (productOpt.isPresent()) {
		            model.addAttribute("product", productOpt.get());   // Add product to the model if found
		            return "display"; 
		        } else {
		            model.addAttribute("error", "Product not found with ID: " + id); // Show an error message if not found
		            return "searchbyid";
		        }
		 }	
	 
	 //Get the index form.
	 @GetMapping("/")
	 public String getHomePage()
	 {
		 //returning view name.
		 return "index";
	 }
	 
	 
	 @GetMapping("/home")
	 public String getImage(Model model)
	 {
		 model.addAttribute("page","home");
		 return "index";
	 }
}
		


