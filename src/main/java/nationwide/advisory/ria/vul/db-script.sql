CREATE TABLE Employee(
    ID NUMBER GENERATED BY DEFAULT AS IDENTITY,
    NAME VARCHAR2(30) NOT NULL,
    EMAIL VARCHAR2(30) NOT NULL,
    ADDRESS VARCHAR2(30) NOT NULL,
    TELEPHONE VARCHAR2(10) NOT NULL,
    PRIMARY KEY(id)
);


select * from employee