INSERT INTO personal_cards ( password, surname, name, second_name, date_of_birth, id_code, passport_data, link_to_crm)
VALUES
    ('password1', 'Surname1', 'Name1', 'SecondName1', '2000-01-01', 'IDCODE1', 'PassportData1', 'LinkToCRM1'),
    ('password2', 'Surname2', 'Name2', 'SecondName2', '2000-01-02', 'IDCODE2', 'PassportData2', 'LinkToCRM2'),
    ('password3', 'Surname3', 'Name3', 'SecondName3', '2000-01-03', 'IDCODE3', 'PassportData3', 'LinkToCRM3'),
    ( 'password10', 'Surname10', 'Name10', 'SecondName10', '2000-01-10', 'IDCODE10', 'PassportData10', 'LinkToCRM10');

INSERT INTO roles ( role, deleted)
VALUES
    ( 'Super Admin', false),
    ( 'Client', false),
    ( 'Operations manager', false),
    ( 'Coordinator', false),
    ( 'Head of sales', false),
    ( 'Sales manager', false),
    ( 'Administrator', false),
    ( 'Trainer', false),
    ( 'Mentor', false);
INSERT INTO universities (id, name, deleted)
VALUES
    (1, 'КНУ ім. Т. Шевченка', false),
    (2, 'НТУ "КПІ"', false),
    (3, 'ЛНУ ім. І. Франка', false),
    (4, 'ХНУ ім. В. Н. Каразіна', false),
    (5, 'ОНУ ім. І. І. Мечникова', false),
    (6, 'НаУ "КМА"', false),
    (7, 'ДНУ ім. О. Гончара', false),
    (8, 'ХмНУ', false),
    (9, 'ЗНУ', false),
    (10, 'ІФНТУНГ', false);
INSERT INTO education_specializations (specialization, deleted)
VALUES
    ('Business Administration', false),
    ('Marketing', false),
    ('Finance', false),
    ('Psychology', false),
    ('Graphic Design', false);

