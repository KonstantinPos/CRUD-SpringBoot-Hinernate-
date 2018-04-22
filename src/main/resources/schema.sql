 drop table  user;
 drop table   country;
 drop table  doc_type;
 drop table  office;
 drop table  organization;

CREATE TABLE IF NOT EXISTS organization (
    id BIGINT (20) NOT NULL AUTO_INCREMENT,
    version INT (11) NOT NULL,
    NAME VARCHAR (100) NOT NULL,
    full_name VARCHAR (150) NOT NULL,
    inn VARCHAR (12) NOT NULL,
    kpp VARCHAR (9) NOT NULL,
    ADDRESS VARCHAR (100) NOT NULL,
    phone VARCHAR (12) NOT NULL,
    is_active BIT (1) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS office(
    id BIGINT (20) NOT NULL AUTO_INCREMENT,
    version INT (11) NOT NULL,
    NAME VARCHAR (50) NOT NULL,
    ADDRESS VARCHAR (100) NOT NULL,
    phone VARCHAR (12) NOT NULL,
    is_active BIT NOT NULL,
    organization_id BIGINT (20) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT organization_id FOREIGN KEY (organization_id) REFERENCES organization (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE INDEX IX_organization_office_Id ON office (organization_id);

CREATE TABLE IF NOT EXISTS country(
    id BIGINT (20) NOT NULL AUTO_INCREMENT,
    version INTEGER NOT NULL,
    NAME VARCHAR (50) NOT NULL,
    code VARCHAR (3) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS doc_type(
    id BIGINT (20) NOT NULL AUTO_INCREMENT,
    version INTEGER NOT NULL,
    NAME VARCHAR (150) NOT NULL,
    code VARCHAR (3) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS USER(
    id BIGINT (20) NOT NULL AUTO_INCREMENT,
    version INT (11) NOT NULL,
    first_name VARCHAR (50) NOT NULL,
    second_name VARCHAR (50) NOT NULL,
    middle_name VARCHAR (50) NOT NULL,
    position VARCHAR (100) NOT NULL,
    phone VARCHAR (12) NOT NULL,
    doc_date DATE NOT NULL,
    is_identified BIT (1) NOT NULL,
    citizenship_country_id BIGINT (20) NOT NULL,
    doc_type_id BIGINT (20) NOT NULL,
    office_id BIGINT (20) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT office_id FOREIGN KEY (office_id) REFERENCES office (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT doc_type_id FOREIGN KEY (doc_type_id) REFERENCES doc_type (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT citizenship_country_id FOREIGN KEY (citizenship_country_id) REFERENCES country (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE INDEX IX_office_user_Id ON user (office_id);
CREATE INDEX IX_country_user_Id  ON user (citizenship_country_id);
CREATE INDEX IX_doc_type_user_Id ON user (doc_type_id);



