CREATE TABLE category
(
    id          bigint NOT NULL,
    created_at  datetime,
    modified_at datetime,
    state       smallint,
    name        varchar(255),
    description varchar(255),
    CONSTRAINT pk_category PRIMARY KEY (id)
)
    GO

CREATE TABLE product
(
    id          bigint    NOT NULL,
    created_at  datetime,
    modified_at datetime,
    state       smallint,
    name        varchar(255),
    description varchar(255),
    image_url   varchar(255),
    price       float(53) NOT NULL,
    is_prime    bit,
    category_id bigint,
    CONSTRAINT pk_product PRIMARY KEY (id)
)
    GO

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id)
    GO