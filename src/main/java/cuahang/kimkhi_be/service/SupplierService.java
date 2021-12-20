package cuahang.kimkhi_be.service;

import cuahang.kimkhi_be.model.Supplier;
import cuahang.kimkhi_be.repository.ISupplierRepo;
import cuahang.kimkhi_be.service.impl.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SupplierService implements ISupplierService {
    @Autowired
    ISupplierRepo iSupplierRepo;

    @Override
    public Supplier addSupplier(Supplier supplier) {
        return iSupplierRepo.save(supplier);
    }

    @Override
    public Supplier updateSupplier(Supplier supplier) {
        return iSupplierRepo.save(supplier);
    }

    @Override
    public Optional<Supplier> getSupplierById(Long id) {
        return iSupplierRepo.findById(id);
    }

    @Override
    public Iterable<Supplier> getSupplierByAll() {
        return iSupplierRepo.findAll();
    }

    @Override
    public void deleteSupplier(Long id) {
        iSupplierRepo.deleteById(id);
    }
}
