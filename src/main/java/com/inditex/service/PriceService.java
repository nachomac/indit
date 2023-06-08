package com.inditex.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.inditex.model.Price;
import com.inditex.repository.PriceRepository;
//defining the business logic
@Service
public class PriceService {

    @Autowired
    PriceRepository priceRepository;

    public List<Price> getAllPrice() {
        List<Price> prices = new ArrayList<Price>();
        priceRepository.findAll().forEach(price -> prices.add(price));
        return prices;
    }

    public List<Price> getPriceBySeveral(int brandId, int productId, Date dateAppl) {

        return priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(brandId,productId,dateAppl,dateAppl);
    }

    public void saveOrUpdate(Price price) {

        priceRepository.save(price);
    }

    public void delete(int id) {

        priceRepository.deleteById(id);
    }
}