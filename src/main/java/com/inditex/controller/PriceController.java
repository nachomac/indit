package com.inditex.controller;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import com.inditex.model.Price;
import com.inditex.service.PriceService;

@RestController
public class PriceController {
    @Autowired
    PriceService priceService;
    //creating a get mapping that retrieves all the Prices detail from the database
    @GetMapping("/price")
    private List<Price> getAllPrice() {

        return priceService.getAllPrice();
    }

    @GetMapping("/prices")
    private List<Price> getPrices(    @RequestParam(required=false) int brandId,
                               @RequestParam(required=false) int productId,
                                      @RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd-HH.mm.ss") Date dateAppl) {

        return priceService.getPriceBySeveral(brandId,productId,dateAppl);
    }

    //creating a delete mapping that deletes a specific Price
    @DeleteMapping("/price/{id}")
    private void deletePrice(@PathVariable("id") int id) {

        priceService.delete(id);
    }
    //creating post mapping that post the Price detail in the database
    @PostMapping("/price")
    private int savePrice(@RequestBody Price price) {
        priceService.saveOrUpdate(price);
        return price.getPriceList();
    }
}
