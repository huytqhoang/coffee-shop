
INSERT INTO shop (id, address, closing_time, contact_details, latitude, longitude, name, opening_time)
Select 1, 'Ho Chi Minh', '21:00:00', '0908500500', 123.0, 321.0, 'Shop 1', '09:00:00'
WHERE NOT EXISTS (
    SELECT 1 FROM shop
    WHERE id = 1
);
INSERT INTO queue (id, max_size, shop_id) Select 1, 3, 1 WHERE NOT EXISTS (SELECT 1 FROM queue WHERE id = 1);
INSERT INTO queue (id, max_size, shop_id) Select 2, 3, 1 WHERE NOT EXISTS (SELECT 1 FROM queue WHERE id = 2);
INSERT INTO queue (id, max_size, shop_id) Select 3, 3, 1 WHERE NOT EXISTS (SELECT 1 FROM queue WHERE id = 3);
INSERT INTO coffee_menu (id, description, name, price, shop_id) Select 1, 'Bitter', 'Black Coffee', 9.0, 1 WHERE NOT EXISTS (SELECT 1 FROM coffee_menu WHERE id = 1);
INSERT INTO coffee_menu (id, description, name, price, shop_id) Select 2, 'Rich', 'Latte', 11.0, 1 WHERE NOT EXISTS (SELECT 1 FROM coffee_menu WHERE id = 2);
INSERT INTO "user" (id, password, role, username, shop_id_operator, shop_id_owner) Select 1, '$2a$06$y25S3T0NiGHvWjiVtuCnPOGlMOjH.lbOgOrN0XY.nMfcvHzwLgCyi', 'OWNER', 'owner_1', NULL, 1 WHERE NOT EXISTS (SELECT 1 FROM "user" WHERE username = 'owner_1');
INSERT INTO "user" (id, password, role, username, shop_id_operator, shop_id_owner) Select 2, '$2a$06$u9obLCz80cpCQa7CmezkuuMSGDeSEFAqDwSUhB/fVDmSLTNUY5nO.', 'CUSTOMER', 'customer_1', NULL, NULL WHERE NOT EXISTS (SELECT 1 FROM "user" WHERE username = 'customer_1');
