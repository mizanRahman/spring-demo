create TABLE cards (
    id bigint(20) not null,
    created datetime,
    updated datetime,
    version bigint(20) not null,
    balance bigint(20),
    expiry_date date,
    pan varchar(19) not null,
    PRIMARY KEY (id)
);
CREATE UNIQUE INDEX UK_pan_card ON cards (pan ASC);


