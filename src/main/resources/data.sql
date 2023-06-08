-- INSERT INTO personal_cards ( password, surname, name, second_name, date_of_birth, id_code, passport_data, link_to_crm)
-- VALUES
--     ('password1', 'Surname1', 'Name1', 'SecondName1', '2000-01-01', 'IDCODE1', 'PassportData1', 'LinkToCRM1'),
--     ('password2', 'Surname2', 'Name2', 'SecondName2', '2000-01-02', 'IDCODE2', 'PassportData2', 'LinkToCRM2'),
--     ('password3', 'Surname3', 'Name3', 'SecondName3', '2000-01-03', 'IDCODE3', 'PassportData3', 'LinkToCRM3'),
--     ( 'password10', 'Surname10', 'Name10', 'SecondName10', '2000-01-10', 'IDCODE10', 'PassportData10', 'LinkToCRM10');

INSERT INTO roles (role, deleted)
VALUES ('Super Admin', false),
       ('Client', false),
       ('Operations manager', false),
       ('Coordinator', false),
       ('Head of sales', false),
       ('Sales manager', false),
       ('Administrator', false),
       ('Trainer', false),
       ('Mentor', false);
INSERT INTO universities (id, name, deleted)
VALUES (1, 'КНУ ім. Т. Шевченка', false),
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
VALUES ('Business Administration', false),
       ('Marketing', false),
       ('Finance', false),
       ('Psychology', false),
       ('Graphic Design', false);

INSERT INTO email_lists (email,id_code)
VALUES ('test@gmail.com', 'IDCDE3'),
       ('example@gmail.com', 'IDCDE4'),
       ('user@example.com', 'IDCDE5'),
       ('info@example.com', 'IDCDE6'),
       ('contact@gmail.com', 'IDCDE7');
INSERT INTO professions (name, deleted)
VALUES
    ('Столяр', false),
    ('Електрик', false),
    ('Маляр', false),
    ('Кухар', false),
    ('Сантехнік', false);
INSERT INTO programs (program, program_hours, deleted)
VALUES
    ('Front end', 275, false),
    ('Back end', 315, false),
    ('Full Stack', 300, false),
    ('Mobile App Development', 260, false),
    ('Data Science', 325, false);
INSERT INTO legal_entities (iban, bank, deleted, id_code, legal_address, legal_entity_name, represented_by, statute_entity)
VALUES
    ('UA123456789012345678', 'Bank of America', 0, 'ID123456789', '123 Main Street, City, State', 'Company A', 'John Doe', 'Statute A'),
    ('UA987654321098765432', 'JP Morgan Chase', 0, 'ID987654321', '456 Elm Street, City, State', 'Company B', 'Jane Smith', 'Statute B'),
    ('UA456789012345678901', 'HSBC Bank', 1, 'ID456789012', '789 Oak Street, City, State', 'Company C', 'Mark Johnson', 'Statute C'),
    ('UA543210987654321098', 'Citibank', 0, 'ID543210987', '321 Pine Street, City, State', 'Company D', 'Sarah Williams', 'Statute D'),
    ('UA901234567890123456', 'Wells Fargo', 0, 'ID901234567', '654 Maple Street, City, State', 'Company E', 'Michael Brown', 'Statute E');
INSERT INTO responsible_managers (deleted, full_name)
VALUES
    (false, 'John Doe'),
    (false, 'Jane Smith'),
    (false, 'Michael Johnson'),
    (false, 'Sarah Williams'),
    (false, 'Robert Brown');
INSERT INTO coordinators (deleted, full_name)
VALUES
    (false, 'Emily Davis'),
    (false, 'Daniel Wilson'),
    (false, 'Olivia Thompson'),
    (false, 'Matthew Lee'),
    (false, 'Ava Turner');
INSERT INTO groups (deleted, group_name, start_date)
VALUES
    (false, 'pe-26', '2023-01-01'),
    (false, 'pe-27', '2023-02-15'),
    (false, 'pe-28', '2023-03-10'),
    (false, 'pe-21', '2023-04-20'),
    (false, 'pe-22', '2023-05-05');
INSERT INTO contracts_status (deleted, status)
VALUES
    (false, 'Active'),
    (false, 'Cancelled'),
    (false, 'Paused'),
    (false, 'Completed');


INSERT INTO payment_methods (deleted, method)
VALUES
    (false, 'Credit Card'),
    (false, 'Bank Transfer'),
    (false, 'PayPal'),
    (false, 'Cash'),
    (false, 'Cryptocurrency');
