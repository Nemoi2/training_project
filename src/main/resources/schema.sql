CREATE TABLE IF NOT EXISTS Organization(
  id               INTEGER               COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
  version          INTEGER NOT NULL      COMMENT 'Служебное поле hibernate',
  name             VARCHAR(25) NOT NULL  COMMENT 'Имя организации',
  full_name        VARCHAR(50) NOT NULL  COMMENT 'Полное имя организации',
  inn              BIGINT NOT NULL       COMMENT 'Идентификационный номер налогоплательщика',
  kpp              BIGINT NOT NULL       COMMENT 'Код причины постановки на учёт',
  address          VARCHAR(50) NOT NULL  COMMENT 'Адрес',
  phone            VARCHAR(15)           COMMENT 'Телефон',
  is_active        BIT NULL DEFAULT 1    COMMENT 'Активна ли органиация?'
 );
COMMENT ON TABLE Organization IS 'Организация';

CREATE TABLE IF NOT EXISTS Office(
  id               INTEGER               COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
  version          INTEGER NOT NULL      COMMENT 'Служебное поле hibernate',
  name             VARCHAR(25) NOT NULL  COMMENT 'Имя офиса',
  address          VARCHAR(50) NOT NULL  COMMENT 'Адрес'
  phone            VARCHAR(15)           COMMENT 'Телефон',
  is_active        BIT NULL DEFAULT 1    COMMENT 'Активен ли офис?',

  FOREIGN KEY (org_Id) REFERENCES Organization(id) COMMENT 'Внешний ключ на таблицу Организация'
);
COMMENT ON TABLE Office IS 'Офис';

CREATE TABLE IF NOT EXISTS Doc_Type(
  id               INTEGER               COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
  version          INTEGER NOT NULL      COMMENT 'Служебное поле hibernate',
  name             VARCHAR(25) NOT NULL  COMMENT 'Имя документа',
  doc_code         BIGINT                COMMENT 'Код документа'
 );
COMMENT ON TABLE Doc_Type IS 'Тип документа';

CREATE TABLE IF NOT EXISTS Country(
  id               INTEGER               COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
  version          INTEGER NOT NULL      COMMENT 'Служебное поле hibernate',
  citizenship_name VARCHAR(25) NOT NULL  COMMENT 'Имя страны гражданина',
  citizenship_code INTEGER               COMMENT 'Код страны гражданина'
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
  is_identified    BIT NULL DEFAULT 1    COMMENT 'Идентифицирован?',

  FOREIGN KEY (office_Id)   REFERENCES Office(id)   COMMENT 'Внешний ключ на таблицу офис',
  FOREIGN KEY (country_Id)  REFERENCES Country(id)  COMMENT 'Внешний ключ на таблицу страны'
 );
COMMENT ON TABLE Employee IS 'Сотрудники';

CREATE TABLE IF NOT EXISTS Document(
  id               INTEGER               COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
  version          INTEGER NOT NULL      COMMENT 'Служебное поле hibernate',
  number           BIGINT NOT NULL       COMMENT 'Уникальный номер документа',
  doc_date         DATE                  COMMENT 'Дата создания',

  FOREIGN KEY (employee_Id) REFERENCES Employee(id) COMMENT 'Внешний ключ на таблицу сотрудники',
  FOREIGN KEY (doc_type_Id) REFERENCES Doc_Type(id) COMMENT 'Внешний ключ на таблицу тип документа'
 );
COMMENT ON TABLE Document IS 'Документы';

CREATE INDEX IX_Office_Organization_Id ON Office (org_Id);
CREATE INDEX IX_Employee_Office_Id     ON Employee (office_Id);
CREATE INDEX IX_Document_Employee_Id   ON Document (employee_Id);
CREATE INDEX IX_Doc_Type_Document_Id   ON Doc_Type (document_Id);
CREATE INDEX IX_Country_Employee_Id    ON Country (employee_Id);