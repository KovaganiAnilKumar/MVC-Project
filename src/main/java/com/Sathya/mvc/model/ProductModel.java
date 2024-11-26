package com.Sathya.mvc.model;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {
	
	@NotBlank(message = "Product name is required")
    private String proName;

    @NotBlank(message = "Product brand is required")
    private String proBrand;

    @DecimalMin(value = "0.0", inclusive = false, message = "Product price must be greater than zero")
    private double proPrice;

    @NotBlank(message = "Product description is required")
    private String proDescription;

    @NotBlank(message = "Product category is required")
    private String proCategory;
    
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getProBrand() {
		return proBrand;
	}
	public void setProBrand(String proBrand) {
		this.proBrand = proBrand;
	}
	public double getProPrice() {
		return proPrice;
	}
	public void setProPrice(double proPrice) {
		this.proPrice = proPrice;
	}
	public String getProDescription() {
		return proDescription;
	}
	public void setProDescription(String proDescription) {
		this.proDescription = proDescription;
	}
	public String getProCategory() {
		return proCategory;
	}
	public void setProCategory(String proCategory) {
		this.proCategory = proCategory;
	}
	

}
