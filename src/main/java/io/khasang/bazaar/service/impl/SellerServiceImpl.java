package io.khasang.bazaar.service.impl;

import io.khasang.bazaar.dao.SellerDao;
import io.khasang.bazaar.entity.Seller;
import io.khasang.bazaar.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Zulfia Garifullina on 13.10.2017.
 */
@Service("sellerService")
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerDao sellerDao;

    @Override
    public Seller getById(Long id) {
        return sellerDao.getById(id);
    }

    @Override
    public List<Seller> getSellerByLogin(String login) {
        return sellerDao.getSellerByLogin(login);
    }

    @Override
    public Seller addSeller(Seller seller) {
        return sellerDao.add(seller);
    }

    @Override
    public Seller updateSeller(Seller seller) {
        return sellerDao.update(seller);
    }

    @Override
    public Seller deleteSeller(Long id) {
        Seller seller = getById(id);
        return sellerDao.delete(seller);
    }

    @Override
    public List<Seller> getList() {
        return sellerDao.getList();
    }

    @Override
    public Seller increaseSellerBalance(Long id, Integer amount) {
        Seller seller = getById(id);
        seller.setBalance(seller.getBalance() + amount);
        return sellerDao.update(seller);
    }
}