package com.inditex.controller;

import com.inditex.model.Price;
import com.inditex.service.PriceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceService priceService;  // Spring will automatically mock this for you

    @Test
    public void testGetPrice() throws Exception {
        // Arrange
        Price mockPrice = new Price();
        mockPrice.setPrice(25.45);
        mockPrice.setProductId(35455);
        mockPrice.setBrandId(1);

        // Parse the date string into Date objects using SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = sdf.parse("2020-06-14 00:00:00");
        //Date endDate = sdf.parse("2020-12-31 23:59:59");

        // Mock the service call to return a price for the given date
        when(priceService.getApplicablePrice(1, 35455, startDate))
                .thenReturn(Optional.of(mockPrice));

        // Act & Assert
        mockMvc.perform(get("/prices/apply")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("date", "2020-06-14 00:00:00")  // Test date within range
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());  // Expect HTTP 200 status
    }
}
