--1. Написать запрос получение всех продуктов с типом "СЫР"
select * from product p
	join "type" t on p.type_id = t.id
	where t."name" like 'СЫР'

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select * from product p where p."name" like '%мороженное%'

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select * from product p where Extract(MONTH from p.expired_date) - Extract(MONTH from pg_catalog.now()) = 1

--4. Написать запрос, который выводит самый дорогой продукт.
SELECT * FROM product p
WHERE price = (
   SELECT MAX (price)
   FROM product p2
);

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT
	type_id,
	t."name",
	COUNT (type_id)
FROM
	product p
JOIN "type" t ON t.id = p.type_id
GROUP BY
	type_id, t."name" ;

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select * from product p
join "type" t on t.id = p.type_id
where t."name" like 'СЫР' or t."name" like 'МОЛОКО'

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
select * from (
	SELECT
	type_id,
	t."name",
	count (type_id) as cnt
	FROM
		product p
	JOIN "type" t ON t.id = p.type_id
	GROUP BY
		type_id, t."name"
) as c where c.cnt < 10;

--8. Вывести все продукты и их тип.
select p.id, p."name", t."name" as product_type, p.price, p.expired_date from product p
join "type" t on t.id = p.type_id