package cuahang.kimkhi_be.controller;

import cuahang.kimkhi_be.model.Supplier;
import cuahang.kimkhi_be.service.impl.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/kimkhi/suppliers/")
@CrossOrigin("*")
public class SupplierController {
    @Autowired
    ISupplierService iSupplierService;

    @PostMapping
    public ResponseEntity<Supplier> add(@RequestBody Supplier supplier) {
        return ResponseEntity.ok(iSupplierService.addSupplier(supplier));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(iSupplierService.getSupplierByAll());
    }

    @GetMapping("/{id}")
    public Supplier getSupplier(@PathVariable("id") Long id) {
        return iSupplierService.getSupplierById(id).get();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable long id, @RequestBody Supplier supplier) {
        Optional productOptional = Optional.ofNullable(iSupplierService.getSupplierById(id));
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(iSupplierService.updateSupplier(supplier), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteSupplier(@PathVariable("id") Long id) {
        iSupplierService.deleteSupplier(id);
    }
}
