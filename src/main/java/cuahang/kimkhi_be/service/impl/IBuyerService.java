package cuahang.kimkhi_be.service.impl;

import cuahang.kimkhi_be.model.Buyer;

import java.util.Optional;

public interface IBuyerService {
    public Buyer addBuyer(Buyer buyer);

    public Buyer updateBuyer(Buyer buyer);

    public Optional<Buyer> getBuyerById(Long id);

    public Iterable<Buyer> getBuyerByAll();

    public void deleteBuyer(Long id);
}
