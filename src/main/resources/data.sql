INSERT INTO Organization (id, version, name, full_name, inn, kpp, address)
VALUES (1, 0, 'Gazprom', 'OOO Gazprom', 123456789, 123454321, 'st. Lenina 1');

INSERT INTO Organization (id, version, name, full_name, inn, kpp, address)
VALUES (2, 0, 'Lukoil', 'OOO Lukoil', 987654321, 123456765, 'st. Lenina 2');

INSERT INTO Organization (id, version, name, full_name, inn, kpp, address, phone, is_active)
VALUES (3, 0, 'Rosneft', 'OOO Rosneft', 111222333, 333222111, 'st. Lenina 3', 89603332221, false);

INSERT INTO Doc_Type (doc_code, version, name) VALUES (21, 0, 'Passport of a citizen RF');

INSERT INTO Doc_Type (doc_code, version, name) VALUES (7, 0, 'military ID');

INSERT INTO Doc_Type (doc_code, version, name) VALUES (10, 0, 'Passport of a foreign citizen');

INSERT INTO Country (citizenship_code, version, citizenship_name)
VALUES (643, 0, 'Russian Federation');

INSERT INTO Country (citizenship_code, version, citizenship_name)
VALUES (380, 0, 'Ukraine');

INSERT INTO Country (citizenship_code, version, citizenship_name)
VALUES (375, 0, 'Belarus');

INSERT INTO Office (id, version, name, address, org_id) VALUES (1, 0, 'Gaz_Penza', 'st. Moscow 1', 1);

INSERT INTO Office (id, version, name, address, org_id) VALUES (2, 0, 'Gaz_Saratov', 'st. Moscow 2', 1);

INSERT INTO Office (id, version, name, address, phone, is_active, org_id)
VALUES (3, 0, 'Lukoil_Saratov', 'st. Moscow 3', 89272223311, false, 2);

INSERT INTO Employee (id, version, first_name, post, office_id, country_id) VALUES (1, 0, 'Kozlov', 'worker', 1, 643);

INSERT INTO Employee (id, version, first_name, post, office_id, country_id) VALUES (2, 0, 'Sergeev', 'manager', 2, 380);

INSERT INTO Employee (id, version, first_name, second_name, middle_name, post, phone, is_identified,
office_id, country_id) VALUES (3, 0, 'Tarkov', 'Andrey', 'Sergeevich', 'administrator', 89608887722, false, 1, 375);

INSERT INTO Document (employee_id, version, name, number, doc_date, doc_type_id)
VALUES (1, 0, 'Passport', 1122334455, '2019-09-11', 21);

INSERT INTO Document (employee_id, version, name, number, doc_date, doc_type_id)
VALUES (2, 0, 'ticket', 7788234455, '2019-09-12', 7);

INSERT INTO Document (employee_id, version, name, number, doc_date, doc_type_id)
VALUES (3, 0, 'Passport', 1122337788, '2019-09-13', 21);