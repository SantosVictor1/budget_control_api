CREATE TABLE IF NOT EXISTS user (
    user_id varchar(36) NOT NULL PRIMARY KEY,
    name varchar(200) NOT NULL,
    cpf varchar(11) NOT NULL,
    email varchar(100) NOT NULL,
    password varchar(50) NOT NULL,
    income double NOT NULL,
    UNIQUE KEY UK_cpf (cpf),
    UNIQUE KEY UK_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;