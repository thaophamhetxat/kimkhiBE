package cuahang.kimkhi_be.controller;

import cuahang.kimkhi_be.model.Cart;
import cuahang.kimkhi_be.model.Product;
import cuahang.kimkhi_be.service.impl.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/kimkhi/sanphams/")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private IProductService iProductService;

////phân trang
//    @GetMapping("/shop")
//    public ResponseEntity<List<Product>> getAllVaccine(@RequestParam long index) {
//        Optional<Product> product = iProductService.getProductById(index);
//        if (product.isPresent()) {
//            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity(product, HttpStatus.OK);
//    }

    @GetMapping("/page1")
    public ResponseEntity<Page<Product>> products1(@RequestParam(defaultValue = "0") int page) {
        return new ResponseEntity<>(iProductService.findAll(PageRequest.of(page, 3)), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<List<Product>> products(@RequestParam int index) {
        List<Product> products = iProductService.getAllProduct(index);
        if (products.isEmpty()) {
            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(products,HttpStatus.OK);

        //        return new ResponseEntity<>(iProductService.findAll(PageRequest.of(page, 3)), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Product> add(@RequestBody Product product) {
        return ResponseEntity.ok(iProductService.addProduct(product));
    }

    @GetMapping
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(iProductService.getProductByAll());
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long id) {
        return iProductService.getProductById(id).get();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product product) {
        Optional productOptional = Optional.ofNullable(iProductService.getProductById(id));
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(iProductService.updateProduct(product), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        iProductService.deleteProduct(id);
    }



    @GetMapping("/add/{id}")
    public ResponseEntity<Product> addToCart(@PathVariable Long id, @ModelAttribute Cart cart) {
        Optional<Product> productOptional = iProductService.getProductById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
//        if (action.equals("show")) {
//            cart.addProduct(productOptional.get());
//            return new ResponseEntity<>(HttpStatus.OK);
////            return "redirect:/shopping-cart";
//        }




//        cart.addProduct(productOptional.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
