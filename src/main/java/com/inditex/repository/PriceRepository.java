package com.inditex.repository;
import org.springframework.data.repository.CrudRepository;
import com.inditex.model.Price;

import java.util.Date;
import java.util.List;

public interface PriceRepository extends CrudRepository<Price, Integer> {

    List<Price> findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(int brandId, int productId, Date date1,Date date2);
}
