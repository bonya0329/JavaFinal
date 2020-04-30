insert into t_categories(id,name) values (1,'Accessories');
insert into t_categories(id,name) values (2,'Beauty');
insert into t_categories(id,name) values (3,'Decor');
insert into t_categories(id,name) values (4,'Kids Toy');

insert into t_products(content, image_path, name, price, category_id) values ('<p>hello</p>','product_images/1_sweets/1_kitkat.jpg','Quartz Belt Watch', 250, 1);
insert into t_products(content, image_path, name, price, category_id) values ('<p>hello</p>','product_images/3_Beauty/2_Women Freshwash.jpg','Women Freshwash', 1500, 2);
insert into t_products(content, image_path, name, price, category_id) values ('<p>hello</p>','product_images/4_Decor/3_Room Flash Light.jpg','Room Flash Light', 2500, 3);