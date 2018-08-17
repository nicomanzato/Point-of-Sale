package com.nicolas.pos.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.nicolas.pos.model.Product;

public class GenerateProduct {
	
	public static Product generateRandomProduct() {
		
		List<String> nameOfProducts = new ArrayList<String>();
		List<String> nameOfSides = new ArrayList<String>();
		List<Float> priceOfProducts = new ArrayList<Float>();

		
		nameOfProducts.add("Pizza");
		nameOfProducts.add("Burger");
		nameOfProducts.add("Salad");
		nameOfProducts.add("HotDog");
		
		nameOfSides.add(" with Fries");
		nameOfSides.add(" with Coke");
		nameOfSides.add(" with Chili");
		
		priceOfProducts.add(5f);
		priceOfProducts.add(10f);

		 Random rand = new Random();
		 
		 String name = nameOfProducts.get(rand.nextInt(nameOfProducts.size())) + nameOfSides.get(rand.nextInt(nameOfSides.size()));
		 Float price = priceOfProducts.get(rand.nextInt(priceOfProducts.size()));
		
		return new Product(name, price);
		
	}

}
