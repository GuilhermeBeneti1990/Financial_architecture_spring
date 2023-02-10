CREATE TABLE diary_limit (
    id SERIAL NOT NULL,
    agency INT NOT NULL,
    account INT NOT NULL,
    date_transaction DATE NOT NULL,
    value_transaction DECIMAL(8, 2),
    PRIMARY KEY (id)
);