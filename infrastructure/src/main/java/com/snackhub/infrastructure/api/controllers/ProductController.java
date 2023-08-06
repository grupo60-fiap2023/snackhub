package com.snackhub.infrastructure.api.controllers;

import com.snackhub.application.product.ProductOutput;
import com.snackhub.application.product.create.CreateProductCommand;
import com.snackhub.application.product.create.CreateProductUseCase;
import com.snackhub.application.product.delete.DeleteProductUseCase;
import com.snackhub.application.product.retrieve.FindAllProductsUseCase;
import com.snackhub.application.product.retrieve.FindProductsByCategoryUseCase;
import com.snackhub.application.product.update.UpdateProductCommand;
import com.snackhub.application.product.update.UpdateProductUseCase;
import com.snackhub.domain.exceptions.DomainException;
import com.snackhub.domain.product.Category;
import com.snackhub.infrastructure.api.ProductAPI;
import com.snackhub.infrastructure.product.models.ProductRequest;
import com.snackhub.infrastructure.product.models.ProductResponse;
import com.snackhub.infrastructure.product.presenters.ProductApiPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class ProductController implements ProductAPI {

    private final CreateProductUseCase createProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final FindProductsByCategoryUseCase findProductByCategory;
    private final FindAllProductsUseCase findAllProductsUseCase;

    public ProductController(
            final CreateProductUseCase createProductUseCase,
            final UpdateProductUseCase updateProductUseCase,
            final DeleteProductUseCase deleteProductUseCase,
            final FindProductsByCategoryUseCase findProductByCategor,
            final FindAllProductsUseCase findAllProductsUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.findProductByCategory = findProductByCategor;
        this.findAllProductsUseCase = findAllProductsUseCase;
    }

    @Override
    public ResponseEntity<?> createProduct(ProductRequest request) {
        ProductResponse response;
        try{
            CreateProductCommand command = CreateProductCommand.with(request.name(), request.price(), request.description(), request.category());
            ProductOutput output = this.createProductUseCase.execute(command);
            response = ProductApiPresenter.present(output);
        }catch (DomainException e){
            return ResponseEntity.unprocessableEntity().body(e.getErrors());
        }

        return ResponseEntity.created(URI.create("/products" + response.id())).body(response);
    }

    @Override
    public ResponseEntity<?> updateById(String id, ProductRequest request) {
        ProductResponse response;
        try{
            UpdateProductCommand command = UpdateProductCommand.with(id, request.name(), request.price(), request.description(), request.category());
            ProductOutput productOutput = this.updateProductUseCase.execute(command);
            response = ProductApiPresenter.present(productOutput);
        }catch (DomainException e){
            return ResponseEntity.unprocessableEntity().body(e.getErrors());
        }

        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<Void> deleteById(String id) {
        this.deleteProductUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<ProductResponse>> listProductByCategory(Category category) {
        return ResponseEntity.ok().body(this.findProductByCategory.execute(category).stream().map(ProductApiPresenter::present).toList());
    }

    @Override
    public ResponseEntity<List<ProductResponse>> listAllProducts() {
        return ResponseEntity.ok().body(this.findAllProductsUseCase.execute().stream().map(ProductApiPresenter::present).toList());
    }
}
