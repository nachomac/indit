-- Crear el índice para la tabla PRICE
CREATE INDEX IF NOT EXISTS idx_price_brand_product_date
ON PRICE (brand_id, product_id, start_date, end_date);

-- Inserción de datos en la tabla PRICE
INSERT INTO PRICE (price_list, brand_id, start_date, end_date, product_id, priority, price, curr)
VALUES
(1, 1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 35455, 0, 35.50, 'EUR'),
(2, 1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 35455, 1, 25.45, 'EUR'),
(3, 1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 35455, 1, 30.50, 'EUR'),
(4, 1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 35455, 1, 38.95, 'EUR');
