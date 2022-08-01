delete
from TACO_CLOUD.TACO_INGREDIENT;
delete
from TACO_CLOUD.TACO;
delete
from TACO_CLOUD.TACO_ORDER;
delete
from TACO_CLOUD.INGREDIENT;
insert into TACO_CLOUD.INGREDIENT (name, type)
values ('Flour Tortilla', 'WRAP');
insert into TACO_CLOUD.INGREDIENT (name, type)
values ('Corn Tortilla', 'WRAP');
insert into TACO_CLOUD.INGREDIENT (name, type)
values ('Ground Beef', 'PROTEIN');
insert into TACO_CLOUD.INGREDIENT (name, type)
values ('Carnitas', 'PROTEIN');
insert into TACO_CLOUD.INGREDIENT (name, type)
values ('Diced Tomatoes', 'VEGGIES');
insert into TACO_CLOUD.INGREDIENT (name, type)
values ('Lettuce', 'VEGGIES');
insert into TACO_CLOUD.INGREDIENT (name, type)
values ('Cheddar', 'CHEESE');
insert into TACO_CLOUD.INGREDIENT (name, type)
values ('Monterrey Jack', 'CHEESE');
insert into TACO_CLOUD.INGREDIENT (name, type)
values ('Salsa', 'SAUCE');
insert into TACO_CLOUD.INGREDIENT (name, type)
values ('Sour Cream', 'SAUCE');