package com.inditex.service;

import com.inditex.model.Price;
import com.inditex.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @InjectMocks
    private PriceService priceService;

    @Mock
    private PriceRepository priceRepository;

    private SimpleDateFormat sdf;
    private Date applicationDate1, applicationDate2, applicationDate3, applicationDate4, applicationDate5;

    @BeforeEach
    void setUp() throws Exception {
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Initialize application dates for testing
        applicationDate1 = sdf.parse("2025-03-14 10:00:00");
        applicationDate2 = sdf.parse("2025-03-14 16:00:00");
        applicationDate3 = sdf.parse("2025-03-14 21:00:00");
        applicationDate4 = sdf.parse("2025-03-15 10:00:00");
        applicationDate5 = sdf.parse("2025-03-16 21:00:00");
    }

    @Test
    @DisplayName("""
    petición a las 10:00 del día 14 
    del producto 35455 para la brand 1
    """)
    void testGetApplicablePrice1() {
        // Given
        Price mockPrice = new Price();
        mockPrice.setProductId(35455);
        mockPrice.setBrandId(1);
        mockPrice.setPrice(35.50);
        mockPrice.setStartDate(applicationDate1);
        mockPrice.setEndDate(applicationDate1);

        List<Price> priceList = List.of(mockPrice);
        when(priceRepository.findApplicablePrice(1, 35455, applicationDate1)).thenReturn(priceList);

        // When
        Optional<Price> result = priceService.getApplicablePrice(1, 35455, applicationDate1);

        // Then
        assertTrue(result.isPresent());
        assertEquals(35.50, result.get().getPrice());
        assertEquals(35455, result.get().getProductId());
        assertEquals(1, result.get().getBrandId());
    }

    @Test
    @DisplayName("""
    petición a las 16:00 del día 14 
    del producto 35455 para la brand 1
    """)
    void testGetApplicablePrice2() {
        // Given
        Price mockPrice = new Price();
        mockPrice.setProductId(35455);
        mockPrice.setBrandId(1);
        mockPrice.setPrice(40.00);
        mockPrice.setStartDate(applicationDate2);
        mockPrice.setEndDate(applicationDate2);

        List<Price> priceList = List.of(mockPrice);
        when(priceRepository.findApplicablePrice(1, 35455, applicationDate2)).thenReturn(priceList);

        // When
        Optional<Price> result = priceService.getApplicablePrice(1, 35455, applicationDate2);

        // Then
        assertTrue(result.isPresent());
        assertEquals(40.00, result.get().getPrice());
        assertEquals(35455, result.get().getProductId());
        assertEquals(1, result.get().getBrandId());
    }

    @Test
    @DisplayName("""
    petición a las 21:00 del día 14 
    del producto 35455 para la brand 1
    """)
    void testGetApplicablePrice3() {
        // Given
        Price mockPrice = new Price();
        mockPrice.setProductId(35455);
        mockPrice.setBrandId(1);
        mockPrice.setPrice(45.00);
        mockPrice.setStartDate(applicationDate3);
        mockPrice.setEndDate(applicationDate3);

        List<Price> priceList = List.of(mockPrice);
        when(priceRepository.findApplicablePrice(1, 35455, applicationDate3)).thenReturn(priceList);

        // When
        Optional<Price> result = priceService.getApplicablePrice(1, 35455, applicationDate3);

        // Then
        assertTrue(result.isPresent());
        assertEquals(45.00, result.get().getPrice());
        assertEquals(35455, result.get().getProductId());
        assertEquals(1, result.get().getBrandId());
    }

    @Test
    @DisplayName("""
    petición a las 10:00 del día 15 
    del producto 35455 para la brand 1
    """)
    void testGetApplicablePrice4() {
        // Given
        Price mockPrice = new Price();
        mockPrice.setProductId(35455);
        mockPrice.setBrandId(1);
        mockPrice.setPrice(50.00);
        mockPrice.setStartDate(applicationDate4);
        mockPrice.setEndDate(applicationDate4);

        List<Price> priceList = List.of(mockPrice);
        when(priceRepository.findApplicablePrice(1, 35455, applicationDate4)).thenReturn(priceList);

        // When
        Optional<Price> result = priceService.getApplicablePrice(1, 35455, applicationDate4);

        // Then
        assertTrue(result.isPresent());
        assertEquals(50.00, result.get().getPrice());
        assertEquals(35455, result.get().getProductId());
        assertEquals(1, result.get().getBrandId());
    }

    @Test
    @DisplayName("""
    petición a las 21:00 del día 16 
    del producto 35455 para la brand 1
    """)
    void testGetApplicablePrice5() {
        // Given
        Price mockPrice = new Price();
        mockPrice.setProductId(35455);
        mockPrice.setBrandId(1);
        mockPrice.setPrice(55.00);
        mockPrice.setStartDate(applicationDate5);
        mockPrice.setEndDate(applicationDate5);

        List<Price> priceList = List.of(mockPrice);
        when(priceRepository.findApplicablePrice(1, 35455, applicationDate5)).thenReturn(priceList);

        // When
        Optional<Price> result = priceService.getApplicablePrice(1, 35455, applicationDate5);

        // Then
        assertTrue(result.isPresent());
        assertEquals(55.00, result.get().getPrice());
        assertEquals(35455, result.get().getProductId());
        assertEquals(1, result.get().getBrandId());
    }

    @Test
    void testGetApplicablePriceNotFound() {
        // Given
        when(priceRepository.findApplicablePrice(1, 35455, applicationDate5)).thenReturn(List.of());

        // When
        Optional<Price> result = priceService.getApplicablePrice(1, 35455, applicationDate5);

        // Then
        assertFalse(result.isPresent());
    }
}
