-- public.body definition

-- Drop table

-- DROP TABLE public.body;

CREATE TABLE public.body (
	body_id int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
	"name" varchar NOT NULL,
	CONSTRAINT body_pk PRIMARY KEY (body_id)
);

-- public.engine definition

-- Drop table

-- DROP TABLE public.engine;

CREATE TABLE public.engine (
	engine_id int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
	"name" varchar NOT NULL,
	CONSTRAINT engine_pk PRIMARY KEY (engine_id)
);

-- public.transmission definition

-- Drop table

-- DROP TABLE public.transmission;

CREATE TABLE public.transmission (
	transmission_id int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
	"name" varchar NOT NULL,
	CONSTRAINT transmission_pk PRIMARY KEY (transmission_id)
);

-- public.car definition

-- Drop table

-- DROP TABLE public.car;

CREATE TABLE public.car (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
	"name" varchar NOT NULL,
	body_id int8 NOT NULL,
	engine_id int8 NOT NULL,
	transmission_id int8 NOT NULL,
	CONSTRAINT car_pk PRIMARY KEY (id)
);


-- public.car foreign keys

ALTER TABLE public.car ADD CONSTRAINT car_fk FOREIGN KEY (body_id) REFERENCES body(body_id);
ALTER TABLE public.car ADD CONSTRAINT car_fk_1 FOREIGN KEY (engine_id) REFERENCES engine(engine_id);
ALTER TABLE public.car ADD CONSTRAINT car_fk_2 FOREIGN KEY (transmission_id) REFERENCES transmission(transmission_id);

INSERT INTO public.body ("name") VALUES
('седан')
,('лифтбэк')
,('хэчбэк')
,('универсал')
,('купе');

INSERT INTO public.engine ("name") VALUES
('1.4 бензин')
,('1.6 бензин')
,('2.0 бензин')
,('2.0 дизель')
,('1.5 турбо-дизель');

INSERT INTO public.transmission ("name") VALUES
('механика 5 ступеней')
,('механика 6 ступеней')
,('автомат 4 ступени')
,('автомат 7 степеней')
,('роботизированный автомат')
,('вариатор');

INSERT INTO public.car ("name",body_id,engine_id,transmission_id) VALUES
('Kia Ceed',3,1,1)
,('VW Polo',1,2,1);