CREATE TABLE IF NOT EXISTS Organization(
  id               INTEGER               COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
  version          INTEGER NOT NULL      COMMENT 'Служебное поле hibernate',
  name             VARCHAR(25) NOT NULL  COMMENT 'Имя организации',
  full_name        VARCHAR(50) NOT NULL  COMMENT 'Полное имя организации',
  inn              VARCHAR(15) NOT NULL  COMMENT 'Идентификационный номер налогоплательщика',
  kpp              VARCHAR(15) NOT NULL  COMMENT 'Код причины постановки на учёт',
  address          VARCHAR(50) NOT NULL  COMMENT 'Адрес',
  phone            VARCHAR(15)           COMMENT 'Телефон',
  is_active        BOOLEAN DEFAULT TRUE  COMMENT 'Активна ли органиация?'
 );
COMMENT ON TABLE Organization IS 'Организация';

CREATE TABLE IF NOT EXISTS Office(
  id               INTEGER               COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
  version          INTEGER NOT NULL      COMMENT 'Служебное поле hibernate',
  name             VARCHAR(25)           COMMENT 'Имя офиса',
  address          VARCHAR(50)           COMMENT 'Адрес',
  phone            VARCHAR(15)           COMMENT 'Телефон',
  is_active        BOOLEAN DEFAULT TRUE  COMMENT 'Активен ли офис?',
  org_id           INTEGER NOT NULL      COMMENT 'Внешний ключ на организацию'
);
COMMENT ON TABLE Office IS 'Офис';

CREATE TABLE IF NOT EXISTS Doc_Type(
  doc_code         INTEGER               COMMENT 'Уникальный идентификатор, Код документа' PRIMARY KEY,
  version          INTEGER NOT NULL      COMMENT 'Служебное поле hibernate',
  name             VARCHAR(40) NOT NULL  COMMENT 'Имя документа'
 );
COMMENT ON TABLE Doc_Type IS 'Тип документа';

CREATE TABLE IF NOT EXISTS Country(
  citizenship_code INTEGER               COMMENT 'Уникальный идентификатор, код страны' PRIMARY KEY,
  version          INTEGER NOT NULL      COMMENT 'Служебное поле hibernate',
  citizenship_name VARCHAR(25)           COMMENT 'Имя страны гражданина'
 );
COMMENT ON TABLE Country IS 'Страны';

CREATE TABLE IF NOT EXISTS Employee(
  id               INTEGER               COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
  version          INTEGER NOT NULL      COMMENT 'Служебное поле hibernate',
  first_name       VARCHAR(25) NOT NULL  COMMENT 'Фамилия сотрудника',
  second_name      VARCHAR(25)           COMMENT 'Имя сотрудника',
  middle_name      VARCHAR(25)           COMMENT 'Отчество сотрудника',
  post             VARCHAR(25) NOT NULL  COMMENT 'Должность сотрудника',
  phone            VARCHAR(15)           COMMENT 'Телефон',
  is_identified    BOOLEAN DEFAULT TRUE  COMMENT 'Идентифицирован?',
  office_id        INTEGER NOT NULL      COMMENT 'Внешний ключ на офис',
  country_id       INTEGER               COMMENT 'Внешний ключ на страны'
 );
COMMENT ON TABLE Employee IS 'Сотрудники';

CREATE TABLE IF NOT EXISTS Document(
  employee_id      INTEGER NOT NULL      COMMENT 'Уникальный идентификатор' PRIMARY KEY,
  version          INTEGER NOT NULL      COMMENT 'Служебное поле hibernate',
  name             VARCHAR(40)           COMMENT 'Имя документа',
  number           VARCHAR(15)           COMMENT 'Уникальный номер документа',
  doc_date         DATE                  COMMENT 'Дата создания',
  doc_type_id      INTEGER               COMMENT 'Внешний ключ на страны'
 );
COMMENT ON TABLE Document IS 'Документы';

CREATE INDEX IX_Office_Organization_Id ON Office (org_id);
ALTER TABLE Office ADD  FOREIGN KEY (org_id) REFERENCES Organization(id);

CREATE INDEX IX_Employee_Office_Id     ON Employee (office_id);
ALTER TABLE Employee ADD FOREIGN KEY (office_id)   REFERENCES Office(id);

CREATE INDEX IX_Document_Employee_Id   ON Document (employee_id);
ALTER TABLE Document ADD FOREIGN KEY (employee_id) REFERENCES Employee(id);

CREATE INDEX IX_Document_Doc_Type_Id   ON Document (doc_type_id);
ALTER TABLE Document ADD FOREIGN KEY (doc_type_id) REFERENCES Doc_Type(doc_code);

CREATE INDEX IX_Employee_Country_Id    ON Employee (country_id);
ALTER TABLE Employee ADD FOREIGN KEY (country_id)  REFERENCES Country(citizenship_code);