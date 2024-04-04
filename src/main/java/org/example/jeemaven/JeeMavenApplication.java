package org.example.jeemaven;

import org.example.jeemaven.entities.Product;
import org.example.jeemaven.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class JeeMavenApplication implements CommandLineRunner {
	@Autowired
	private ProductRepository productRepository;
	public static void main(String[] args) {
		SpringApplication.run(JeeMavenApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		// Ajouter Produit
//		productRepository.save(new Product(1,"Comp",4300,3));
//		productRepository.save(new Product(2,"Comp",300,14));
//		productRepository.save(new Product(3,"Comp",430,44));
		productRepository.save(new Product(4,"MacBook",50000,2));
		// Consulter tous les produits
		List<Product> products = productRepository.findAll();
		products.forEach(product -> {
			System.out.println(product.toString());
		});
		//	Consulter un produit
		Product product1=productRepository.findById(Long.valueOf(1)).get();
		System.out.println(product1.getId());
		System.out.println(product1.getName());
		System.out.println(product1.getQuantity());

		//	Chercher des produits
		List<Product> ProductsWithC = productRepository.findByNameContains("C");
		ProductsWithC.forEach(product -> {
			System.out.println("Product list : "+ product);
		});
		// Methode 2 Search :
		List<Product> ProductsWithC2 = productRepository.search("%C%");
		//  Mettre à jour un produit
// Update a product (assuming the user provides the new product details)
		Long productIdToUpdate = 1L; // Replace with the actual product ID
		Product productToUpdate = productRepository.findById(productIdToUpdate).get();

// Modify product details (example: change name and price)
		productToUpdate.setName("Phone");
		productToUpdate.setPrice(5500);

		productRepository.save(productToUpdate);
		System.out.println("Product with ID " + productIdToUpdate + " updated successfully!");
		products.forEach(product -> {
			System.out.println(product.toString());
		});

		// supprimer un produit
		Long productIdToDelete = 4L;
		productRepository.deleteById(productIdToDelete);
		System.out.println("Product with ID " + productIdToDelete + " deleted successfully!");



	}

}
