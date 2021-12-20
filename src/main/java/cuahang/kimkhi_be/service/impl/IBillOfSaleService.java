package cuahang.kimkhi_be.service.impl;

import cuahang.kimkhi_be.model.BillOfSale;

import java.util.Optional;

public interface IBillOfSaleService {
    public BillOfSale addBillOfSale(BillOfSale billOfSale);

    public BillOfSale updateBillOfSale(BillOfSale billOfSale);

    public Optional<BillOfSale> getBillOfSaleById(Long id);

    public Iterable<BillOfSale> getBillOfSaleByAll(Long id);

    public void deleteBillOfSale(Long id);
}
