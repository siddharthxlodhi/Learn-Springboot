package com.sid.sdj2.controllers;

import com.sid.sdj2.Entity.Category;
import com.sid.sdj2.Entity.Product;
import com.sid.sdj2.repo.CategoryRepo;
import com.sid.sdj2.repo.ProductRepo;
import com.sid.sdj2.services.ProductService;
import com.sid.sdj2.specification.ProductSpecification;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Tag(name = "Product APIs")
@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductService productService;

    @PostMapping("/save/{cid}")
    public ResponseEntity<String> addProduct(@PathVariable String cid, @RequestBody Product product) {

        Optional<Category> category = categoryRepo.findById(cid);
        if (category.isPresent()) {
            product.getCategories().add(category.get());
            category.get().getProducts().add(product);
            categoryRepo.save(category.get());
            return new ResponseEntity<>("Product saved to Category:" + cid, HttpStatus.OK);
        }
        return new ResponseEntity<>("categoryNotExist", HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/getAll/{cid}")
    public ResponseEntity<?> getAllProducts(@PathVariable String cid) {
        Optional<Category> category = categoryRepo.findById(cid);
        if (category.isPresent()) {
            List<Product> products = category.get().getProducts();
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        return new ResponseEntity<>("CategoryNotExist for id:" + cid, HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/allProduct")
    public ResponseEntity<List<Product>> findAllProducts() {
        List<Product> productList = productRepo.findAll();
        log.info("ProductSize:" + productList.size());
        return new ResponseEntity<>(productList, HttpStatus.OK);

    }


    //GETTING SORTED DATA
    @GetMapping("/sort/{field}")
    public ResponseEntity<List<Product>> findAllProductsBySorting(@PathVariable String field, @RequestParam(name = "enum") Sort.Direction directionEnum) {
        List<Product> productsWithSorting = productService.findProductsWithSorting(directionEnum, field);
        log.info("ProductSize:" + productsWithSorting.size());
        return new ResponseEntity<>(productsWithSorting, HttpStatus.OK);

    }

    //GETTING PAGED DATA
    @GetMapping("/pagination/{pageSize}/{offset}")
    public ResponseEntity<Page<Product>> findProductsByPaging(@PathVariable int pageSize, @PathVariable int offset) {
        Page<Product> productsWithPaging = productService.findProductsWithPaging(pageSize, offset);
        log.info("ProductSize:" + productsWithPaging.getSize());
        return new ResponseEntity<>(productsWithPaging, HttpStatus.OK);
    }

    //Sorting&Paging
    @GetMapping("/pagination/{pageSize}/{offset}/{field}")
    public ResponseEntity<Page<Product>> findProductsByPaging(@PathVariable int pageSize, @PathVariable int offset, @RequestParam(name = "enum") Sort.Direction direction, @PathVariable String field) {
        Page<Product> productsWithPagingSorting = productService.findProductsWithSoringPaging(pageSize, offset, direction, field);
        log.info("ProductSize:" + productsWithPagingSorting.getSize());
        return new ResponseEntity<>(productsWithPagingSorting, HttpStatus.OK);
    }

    @GetMapping("/specification/{pageSize}/{offset}/{greater}/{lesser}")
    public ResponseEntity<Page<Product>> findProductsByPagingSpecification(@PathVariable int pageSize, @PathVariable int offset, @PathVariable double greater, @PathVariable double lesser) {
        PageRequest pageRequest = PageRequest.of(offset, pageSize);
        Specification<Product> productSpecification = Specification.where(ProductSpecification.priceGreaterThan(greater)).and(ProductSpecification.priceLessThan(lesser));
        Page<Product> productPage = productRepo.findAll(productSpecification, pageRequest);
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }

}
