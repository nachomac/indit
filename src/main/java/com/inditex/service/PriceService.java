package com.inditex.service;

import com.inditex.model.Price;
import com.inditex.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    public Optional<Price> getApplicablePrice(int brandId, int productId, Date applicationDate) {
        List<Price> prices = priceRepository.findApplicablePrice(brandId, productId, applicationDate);
        return prices.stream().findFirst(); // Se toma el precio con mayor prioridad
    }
}
