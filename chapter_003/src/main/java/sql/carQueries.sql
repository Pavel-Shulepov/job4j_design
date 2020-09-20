--1. Вывести список всех машин и все привязанные к ним детали.

select c.id, c."name", b."name", e."name", t."name" from car c
	join engine e on e.engine_id = c.engine_id
	join body b on b.body_id = c.body_id
	join transmission t on t.transmission_id = c.transmission_id

--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.

select * from (
	select e."name", it.table_name as detail from car c
		right join engine e on e.engine_id = c.engine_id
		join information_schema.tables it on it.table_name = 'engine'
		where c.id is null
	union
	select b."name", it.table_name as detail from car c
		right join body b on b.body_id = c.body_id
		join information_schema.tables it on it.table_name = 'body'
		where c.id is null
	union
	select t."name", it.table_name as detail from car c
		right join transmission t on t.transmission_id = c.transmission_id
		join information_schema.tables it on it.table_name = 'transmission'
		where c.id is null
) as s order by s.detail