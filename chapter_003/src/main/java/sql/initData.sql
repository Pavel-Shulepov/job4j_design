INSERT INTO public."role" (role_id,"name",description) VALUES
('bede67c4-1b4a-4872-9418-2450be4ed1dc','Администратор','Администратор системы заявок')
,('dd756082-4788-4de4-a9d7-95e5192898a7','Пользователь','Пользователь системы заявок');

INSERT INTO public."rule" (rule_id,"name",description) VALUES
('854d1817-492a-4b9d-a618-3e670bf785ac','добавление','добавление заявки в систему')
,('7ca83b76-ef45-4b01-b560-5b015b8f5b31','удаление','удаление заявки из системы')
,('b383ae6d-fa5f-4926-8aa3-85cc5f6ef3ef','редактирование','редактирование заявки системы')
,('f5cff6a5-723e-4022-b670-3270079d90c2','просмотр','просмотр всех заявок системы');

INSERT INTO public.rolesrules (id,role_id,rule_id) VALUES
('e2754075-f768-4e09-94da-990909e08b7e','bede67c4-1b4a-4872-9418-2450be4ed1dc','854d1817-492a-4b9d-a618-3e670bf785ac')
,('1411c4e0-f22c-4334-9a7f-5cb3572c90a7','bede67c4-1b4a-4872-9418-2450be4ed1dc','7ca83b76-ef45-4b01-b560-5b015b8f5b31')
,('e49e7911-44a2-4519-9a5f-556d5ab3c4ff','bede67c4-1b4a-4872-9418-2450be4ed1dc','b383ae6d-fa5f-4926-8aa3-85cc5f6ef3ef')
,('901c8030-326e-4b60-a9d4-d829eba4bf78','bede67c4-1b4a-4872-9418-2450be4ed1dc','f5cff6a5-723e-4022-b670-3270079d90c2')
,('8afc33b3-6eaa-49b5-9e15-6e9b71a2a455','dd756082-4788-4de4-a9d7-95e5192898a7','854d1817-492a-4b9d-a618-3e670bf785ac')
,('0c63a8a2-437c-4962-bb51-9862210cdcf3','dd756082-4788-4de4-a9d7-95e5192898a7','b383ae6d-fa5f-4926-8aa3-85cc5f6ef3ef');

INSERT INTO public.users (user_id,user_name,role_id) VALUES
('9b4c6551-1122-4159-8bf3-7062e910ce60','Павел Шулепов','bede67c4-1b4a-4872-9418-2450be4ed1dc')
,('020e6a8d-737f-485e-bd21-8fd4ca690984','Ваня Иванов','dd756082-4788-4de4-a9d7-95e5192898a7');

INSERT INTO public.request_state (req_state_id,description) VALUES
('91cabac6-5f02-4822-96f2-4ba87cc0ae0e','принята')
,('33943ddb-1acf-41f4-a388-56f92be4db90','в работе')
,('0c1a0b4e-50c3-47fb-b981-6ba6488e6739','выполнена');

INSERT INTO public.category (category_id,description) VALUES
('ce720c4e-cacf-4654-ae76-9d313ed20685','гарантийный ремонт')
,('2eaf74ff-dd37-40ad-b837-9099e5c42174','платный ремонт');

INSERT INTO public.request (request_id,user_id,description,category_id,req_state_id) VALUES
('2cae9704-4f6e-4309-8609-77750ae664d0','020e6a8d-737f-485e-bd21-8fd4ca690984','Монитор гаснет через 5 минут работы и не включается','ce720c4e-cacf-4654-ae76-9d313ed20685','91cabac6-5f02-4822-96f2-4ba87cc0ae0e');

INSERT INTO public."comment" (comment_id,"content",request_id) VALUES
('2496e0a3-2f8d-4f2a-92ac-79cac9d1191c','Монитор на экспертизе, возможно попадание влаги\воды','2cae9704-4f6e-4309-8609-77750ae664d0');

INSERT INTO public.files (file_id,url,request_id) VALUES
('46fb3b09-4d39-4c8a-bd49-4749a951fafa','cloud.ru/46fb3b09-4d39-4c8a-bd49-4749a951fafa','2cae9704-4f6e-4309-8609-77750ae664d0');