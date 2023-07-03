
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
INSERT INTO universities ( name, deleted)
VALUES ('КНУ ім. Т. Шевченка', false),
       ('НТУ "КПІ"', false),
       ('ЛНУ ім. І. Франка', false),
       ('ХНУ ім. В. Н. Каразіна', false),
       ('ОНУ ім. І. І. Мечникова', false),
       ('НаУ "КМА"', false),
       ('ДНУ ім. О. Гончара', false),
       ('ХмНУ', false),
       ('ЗНУ', false),
       ( 'ІФНТУНГ', false);
INSERT INTO education_specializations (specialization, deleted)
VALUES ('Business Administration', false),
       ('Marketing', false),
       ('Finance', false),
       ('Psychology', false),
       ('Graphic Design', false);


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
INSERT INTO trainers (deleted, full_name)
VALUES
    (false, 'Thompson Davis'),
    (false, 'Lee Wilson'),
    (false, 'Ava Thompson'),
    (false, 'Davis Lee'),
    (false, 'Olivia Turner');
INSERT INTO mentors (deleted, full_name)
VALUES
    (false, 'John Smith'),
    (false, 'Emily Johnson'),
    (false, 'Michael Brown'),
    (false, 'Olivia Davis'),
    (false, 'William Wilson');


INSERT INTO contracts_status (deleted, status)
VALUES
    (false, 'Active'),
    (false, 'Cancelled'),
    (false, 'Paused'),
    (false, 'Completed');
INSERT INTO sessions_status (deleted, status)
VALUES
    (false, 'Active'),
    (false, 'Archieved');

INSERT INTO groups_status (deleted, status)
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
