package com.ecommerce.back;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.ecommerce.back.security.entities.User;
import com.ecommerce.back.entities.Product;
import com.ecommerce.back.entities.ShoppingCart;
import com.ecommerce.back.security.repositories.UserRepository;
import com.ecommerce.back.repositories.ProductRepository;
import com.ecommerce.back.repositories.ShoppingCartRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ShoppingCartTest {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private UserRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
	@Rollback(false)
    public void testCreateShoppingCart() {

        // Crear un objeto Client
        User client = new User("Juann1","juanperez@gmail.com", "password");

        // Persistir el objeto Client en la base de datos
        clientRepository.save(client);

        // Crear un objeto Product
        Product product = new Product("Celular Samsung para Shopping", 7000000, "Celular smartphone de excelentes caracteristicas", "Celulares", "url-image");

        // Persistir el objeto Product en la base de datos
        productRepository.save(product);

        // Crear un objeto ShoppingCart
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setClient(client);
        shoppingCart.setProduct(product);
        shoppingCart.setAmount(3);

        // Persistir el objeto ShoppingCart en la base de datos
        shoppingCartRepository.save(shoppingCart);

        // Verificar que el objeto ShoppingCart fue persistido correctamente
        List<ShoppingCart> shoppingCarts = shoppingCartRepository.findByClient(client);
        assertThat(shoppingCarts.size()).isEqualTo(1);
        ShoppingCart persistedShoppingCart = shoppingCarts.get(0);
        assertThat(persistedShoppingCart.getClient()).isEqualTo(client);
        assertThat(persistedShoppingCart.getProduct()).isEqualTo(product);
        assertThat(persistedShoppingCart.getAmount()).isEqualTo(2);
    }
}