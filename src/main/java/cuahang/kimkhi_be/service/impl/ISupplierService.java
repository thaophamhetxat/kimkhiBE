package cuahang.kimkhi_be.service.impl;


import cuahang.kimkhi_be.model.Supplier;

import java.util.Optional;

public interface ISupplierService {
    public Supplier addSupplier(Supplier supplier);

    public Supplier updateSupplier(Supplier supplier);

    public Optional<Supplier> getSupplierById(Long id);

    public Iterable<Supplier> getSupplierByAll();

    public void deleteSupplier(Long id);
}
