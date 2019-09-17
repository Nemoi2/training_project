INSERT INTO Organization (id, version, name, full_name, inn, kpp, address)
VALUES (1, 0, 'Газпром', 'ООО Газпром', 123456789, 123454321, 'ул.Цюрупы, 16');

INSERT INTO Organization (id, version, name, full_name, inn, kpp, address)
VALUES (2, 0, 'Лукойл', 'ООО Лукойл', 987654321, 123456765, 'г.Москва ул.Ленина 1');

INSERT INTO Organization (id, version, name, full_name, inn, kpp, address, phone, is_active)
VALUES (3, 0, 'Роснефть', 'ООО Роснефть', 111222333, 333222111, 'ул.Цюрупы, 1', 89603332221, 0);

INSERT INTO Doc_Type (id, version, name, doc_code) VALUES (1, 0, 'Паспорт гражданина РФ', 21);

INSERT INTO Doc_Type (id, version, name, doc_code) VALUES (2, 0, 'Военный билет', 07);

INSERT INTO Doc_Type (id, version, name, doc_code) VALUES (3, 0, 'Паспорт иностранного гражданина', 10);

INSERT INTO Country (id, version, citizenship_name, citizenship_code)
VALUES (1, 0, 'Российская Федерация', 643);

INSERT INTO Country (id, version, citizenship_name, citizenship_code)
VALUES (2, 0, 'Украина', 380);

INSERT INTO Country (id, version, citizenship_name, citizenship_code)
VALUES (3, 0, 'Белоруссия', 375);

INSERT INTO Office (id, version, name, address, org_Id) VALUES (1, 0, 'ГАЗ_Пенза', 'ул.Московская, 2', 1);

INSERT INTO Office (id, version, name, address, org_Id) VALUES (2, 0, 'ГАЗ_Саратов', 'ул.Ленина, 5', 1);

INSERT INTO Office (id, version, name, address, phone, is_active, org_Id)
VALUES (3, 0, 'Лукойл_Саратов', 'ул.Ленина, 10', 89272223311, 0, 2);

INSERT INTO Employee (id, version, first_name, post, office_Id, country_Id) VALUES (1, 0, 'Kozlov', 'worker', 1, 1);

INSERT INTO Employee (id, version, first_name, post, office_Id, country_Id) VALUES (2, 0, 'Sergeev', 'manager', 2, 2);

INSERT INTO Employee (id, version, first_name, second_name, middle_name, post, phone, is_identified,
office_Id, country_Id) VALUES (3, 0, 'Tarkov', 'Andrey', 'Sergeevich', 'administrator', 89608887722, 0, 1, 3);

INSERT INTO Document (id, version, number, doc_date, employee_Id, doc_type_Id)
VALUES (1, 0, 1122334455, 2019-09-11, 1, 1);

INSERT INTO Document (id, version, number, doc_date, employee_Id, doc_type_Id)
VALUES (2, 0, 7788234455, 2019-09-12, 2, 3);

INSERT INTO Document (id, version, number, doc_date, employee_Id, doc_type_Id)
VALUES (3, 0, 1122337788, 2019-09-13, 3, 3);