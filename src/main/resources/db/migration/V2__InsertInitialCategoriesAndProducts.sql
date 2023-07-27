INSERT INTO category(name) VALUES
('Produtos de Limpeza'),
('Bebidas'),
('Salgadinhos'),
('Chás e Cafes'),
('Produtos de Limpeza');

commit;


INSERT INTO product(name,unit_of_measurement,price,category_id) VALUES
('Vinagre branco','l',8.5,1),
('Cerveja Heineken','ml',11.0,2);

commit;