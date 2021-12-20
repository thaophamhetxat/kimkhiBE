package cuahang.kimkhi_be.service;

import cuahang.kimkhi_be.model.Buyer;
import cuahang.kimkhi_be.repository.IBuyerRepo;
import cuahang.kimkhi_be.service.impl.IBuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuyerService implements IBuyerService {
    @Autowired
    IBuyerRepo iBuyerRepo;

    @Override
    public Buyer addBuyer(Buyer buyer) {
        return iBuyerRepo.save(buyer);
    }

    @Override
    public Buyer updateBuyer(Buyer buyer) {
        return iBuyerRepo.save(buyer);
    }

    @Override
    public Optional<Buyer> getBuyerById(Long id) {
        return iBuyerRepo.findById(id);
    }

    @Override
    public Iterable<Buyer> getBuyerByAll() {
        return iBuyerRepo.findAll();
    }

    @Override
    public void deleteBuyer(Long id) {
        iBuyerRepo.deleteById(id);
    }
}
