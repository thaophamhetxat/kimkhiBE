package cuahang.kimkhi_be.controller;

import cuahang.kimkhi_be.dto.response.ResponseMessage;
import cuahang.kimkhi_be.model.Cart;
import cuahang.kimkhi_be.model.Product;
import cuahang.kimkhi_be.service.impl.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    //Xắp xếp theo tên,phân trang
    @GetMapping("/page")
    public ResponseEntity<?> pageProduct(@PageableDefault(sort = "nameProduct", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Product> productPage = iProductService.findAll(pageable);
        if (productPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }

    @GetMapping("/shop")
    public ResponseEntity<List<Product>> products(@RequestParam int index) {
        List<Product> products = iProductService.getAllProduct(index);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Product product) {
        //xét trừng lặp => return về message
        if (iProductService.existsByNameProduct(product.getNameProduct())) {
            return new ResponseEntity<>(new ResponseMessage("no_name_product"), HttpStatus.OK);
        }
        //avata null
        if (product.getImage() == null) {
            return new ResponseEntity<>(new ResponseMessage("no_image"), HttpStatus.OK);
        }
        iProductService.addProduct(product);
        return new ResponseEntity<>(new ResponseMessage("add_success"), HttpStatus.OK);
    }

    //lấy tất cả sản phẩm không phân trang
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
