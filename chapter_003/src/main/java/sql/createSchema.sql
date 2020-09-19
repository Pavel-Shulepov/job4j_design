-- public.users definition
-- public."role" definition

-- Drop table

-- DROP TABLE public."role";

CREATE TABLE public."role" (
	role_id uuid NOT NULL,
	"name" varchar NOT NULL,
	description text NOT NULL,
	CONSTRAINT role_pk PRIMARY KEY (role_id)
);
COMMENT ON TABLE public."role" IS 'Роль';

-- public."rule" definition

-- Drop table

-- DROP TABLE public."rule";

CREATE TABLE public."rule" (
	rule_id uuid NOT NULL,
	"name" varchar NOT NULL,
	description text NOT NULL,
	CONSTRAINT rule_pk PRIMARY KEY (rule_id)
);
COMMENT ON TABLE public."rule" IS 'Правило роли';

-- public.rolesrules definition

-- Drop table

-- DROP TABLE public.rolesrules;

CREATE TABLE public.rolesrules (
	id uuid NOT NULL,
	role_id uuid NOT NULL,
	rule_id uuid NOT NULL,
	CONSTRAINT rolesrules_pk PRIMARY KEY (id)
);
COMMENT ON TABLE public.rolesrules IS 'Правила роли';


-- public.rolesrules foreign keys

ALTER TABLE public.rolesrules ADD CONSTRAINT rolesrules_fk FOREIGN KEY (role_id) REFERENCES role(role_id);
ALTER TABLE public.rolesrules ADD CONSTRAINT rolesrules_fk_1 FOREIGN KEY (rule_id) REFERENCES rule(rule_id);

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
	user_id uuid NOT NULL,
	user_name text NOT NULL, -- Имя пользователя
	role_id uuid NOT NULL,
	CONSTRAINT users_pk PRIMARY KEY (user_id)
);
COMMENT ON TABLE public.users IS 'Пользователи системы';

-- Column comments

COMMENT ON COLUMN public.users.user_name IS 'Имя пользователя';


-- public.users foreign keys

ALTER TABLE public.users ADD CONSTRAINT users_fk FOREIGN KEY (role_id) REFERENCES role(role_id);


-- public.category definition

-- Drop table

-- DROP TABLE public.category;

CREATE TABLE public.category (
	category_id uuid NOT NULL,
	description text NOT NULL,
	CONSTRAINT category_pk PRIMARY KEY (category_id)
);
COMMENT ON TABLE public.category IS 'Категория заявки';

-- public.request_state definition

-- Drop table

-- DROP TABLE public.request_state;

CREATE TABLE public.request_state (
	req_state_id uuid NOT NULL,
	description text NOT NULL,
	CONSTRAINT request_state_pk PRIMARY KEY (req_state_id)
);
COMMENT ON TABLE public.request_state IS 'Состояние заявки';

-- public.request definition

-- Drop table

-- DROP TABLE public.request;

CREATE TABLE public.request (
	request_id uuid NOT NULL,
	user_id uuid NOT NULL,
	description text NOT NULL,
	category_id uuid NOT NULL,
	req_state_id uuid NOT NULL,
	CONSTRAINT request_pk PRIMARY KEY (request_id)
);
COMMENT ON TABLE public.request IS 'Заявка';


-- public.request foreign keys

ALTER TABLE public.request ADD CONSTRAINT request_fk FOREIGN KEY (user_id) REFERENCES users(user_id);
ALTER TABLE public.request ADD CONSTRAINT request_fk_1 FOREIGN KEY (req_state_id) REFERENCES request_state(req_state_id);
ALTER TABLE public.request ADD CONSTRAINT request_fk_2 FOREIGN KEY (category_id) REFERENCES category(category_id);

-- public.files definition

-- Drop table

-- DROP TABLE public.files;

CREATE TABLE public.files (
	file_id uuid NOT NULL,
	url varchar NOT NULL,
	request_id uuid NOT NULL,
	CONSTRAINT files_pk PRIMARY KEY (file_id)
);
COMMENT ON TABLE public.files IS 'Файлы приложенные к заявке';


-- public.files foreign keys

ALTER TABLE public.files ADD CONSTRAINT files_fk FOREIGN KEY (request_id) REFERENCES request(request_id);

-- public."comment" definition

-- Drop table

-- DROP TABLE public."comment";

CREATE TABLE public."comment" (
	comment_id uuid NOT NULL,
	"content" text NOT NULL,
	request_id uuid NOT NULL,
	CONSTRAINT comment_pk PRIMARY KEY (comment_id)
);
COMMENT ON TABLE public."comment" IS 'Комментарий к заявке';


-- public."comment" foreign keys

ALTER TABLE public."comment" ADD CONSTRAINT comment_fk FOREIGN KEY (request_id) REFERENCES request(request_id);