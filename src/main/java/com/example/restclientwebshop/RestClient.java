package com.example.restclientwebshop;

import com.example.restclientwebshop.ShopObjects.Category;
import com.example.restclientwebshop.ShopObjects.Product;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class RestClient {


    WebClient client = WebClient.create("http://localhost:8080/rs/");


    public List<Product> getAllProducts() {

        Flux<Product> flux = client
                .get()
                .uri("allProducts")
                .retrieve()
                .bodyToFlux(Product.class);

        return(flux.collectList().block());


    }


    public List<Product> getProductsByCategory(Category value) {
        Flux<Product> flux = client
                .get()
                .uri("category/"+value)
                .retrieve()
                .bodyToFlux(Product.class);

        return(flux.collectList().block());

    }
    public List<Product> searchProduct(String search){
        Flux<Product> flux = client
                .get()
                .uri("search/"+search)
                .retrieve()
                .bodyToFlux(Product.class);

        return(flux.collectList().block());

    }

    public String deleteProduct(int id){
        Mono<String> mono = client
                .delete()
                .uri("delete/"+id)
                .retrieve()
                .bodyToMono(String.class);
        return mono.block();
    }
}
