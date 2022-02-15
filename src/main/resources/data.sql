INSERT INTO users(id, email, password, username) VALUES ( '4b0a9782-5466-48fe-9bfe-df10cc3159ec','admin@email.com', '$2a$12$8wvEzU9At/HGOnIxIGjnYOlo/5yWoAImw3QZokvahCzjuiUpFay6C', 'admin' );
INSERT INTO users(id, email, password, username) VALUES ( '092afba2-218b-4504-bf78-fd738ef62832','default@email.com', '$2a$10$9dxopSV6AwxiyTd8S1lGoeYlmI07vxRe0DPfCo2IQA/DzBfauRXQu', 'default' );

INSERT INTO owner(id, cpf, name) VALUES ( '1784fe90-be61-4162-8298-6454b0f66978', '111.222.333-44', 'Anderson');
INSERT INTO owner(id, cpf, name) VALUES ( 'b55ea1c3-b537-48c4-ac37-ace7be65e39e', '333.222.111-00', 'Gabriela');

INSERT INTO dog(id, age, breed, name, owner_id) VALUES ( '5410b580-91ff-432d-94c0-b5e9d4ad0c1e', 1, 'DACHSHUND', 'Pedro Jorge','1784fe90-be61-4162-8298-6454b0f66978' );
INSERT INTO dog(id, age, breed, name, owner_id) VALUES ( '7e5fff78-2d6f-422b-8ace-c2e4186fb6b6', 3, 'DACHSHUND', 'Chokito','1784fe90-be61-4162-8298-6454b0f66978' );
INSERT INTO dog(id, age, breed, name, owner_id) VALUES ( 'de2bc2fb-46e3-423a-b5c3-0ddf66e8011f', 10, 'BEAGLE', 'Julie','b55ea1c3-b537-48c4-ac37-ace7be65e39e' );