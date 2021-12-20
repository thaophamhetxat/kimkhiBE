package cuahang.kimkhi_be.controller;

import cuahang.kimkhi_be.model.Buyer;
import cuahang.kimkhi_be.service.impl.IBuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/kimkhi/buyers")
@CrossOrigin("*")
public class BuyerController {
    @Autowired
    IBuyerService iBuyerService;

    @PostMapping
    public ResponseEntity<Buyer> add(@RequestBody Buyer buyer) {
        return ResponseEntity.ok(iBuyerService.addBuyer(buyer));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(iBuyerService.getBuyerByAll());
    }

    @GetMapping("/{id}")
    public Buyer getBuyer(@PathVariable("id") Long id) {
        return iBuyerService.getBuyerById(id).get();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Buyer> updateBuyer(@PathVariable long id, @RequestBody Buyer buyer) {
        Optional productOptional = Optional.ofNullable(iBuyerService.getBuyerById(id));
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(iBuyerService.updateBuyer(buyer), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBuyer(@PathVariable("id") Long id) {
        iBuyerService.deleteBuyer(id);
    }
}
