CREATE TABLE IF NOT EXISTS user (
    user_id varchar(36) NOT NULL PRIMARY KEY,
    name varchar(200) NOT NULL,
    cpf varchar(11) NOT NULL,
    email varchar(100) NOT NULL,
    password varchar(50) NOT NULL,
    income double NOT NULL,
    UNIQUE KEY UK_cpf (cpf),
    UNIQUE KEY UK_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE IF NOT EXISTS spent (
    spent_id varchar(36) NOT NULL PRIMARY KEY,
    value double NOT NULL,
    spent_date timestamp NOT NULL,
    description varchar(255) NOT NULL,
    user_id varchar(36) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;