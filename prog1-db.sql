--Drop list
drop table Receipts;
drop table Reimbursements;
drop table Employees;

drop sequence emp_id_seq;
drop sequence rem_id_seq;
drop sequence rec_id_seq;

--Creating Tables
Create Table Employees (
    employ_id Number(8) Primary Key,
    username Varchar2(30) Unique Not Null,
    password varchar2(30) Not Null,
    is_manager Number (1) Default 0,
    first_name Varchar2(15),
    last_name Varchar2(15),
    email Varchar2(25) Not Null
);
/
Create Table Reimbursements (
    reimbursements_id Number(8) Primary Key,
    employ_id Number(8) Not Null,
    manager_id Number(8),
    reimbursement Number(7,2) Default 0.00,
    approved Number(1) Default 0,
    Foreign Key (employ_id) References Employees(employ_id),
    Foreign Key (manager_id) References Employees(employ_id)
);
/
Create Table Receipts (
    receipts_id number(10) Primary Key,
    receipt Blob,
    reimbursements_id Number(8),
    Foreign Key(reimbursements_id) References Reimbursements(reimbursements_id)
);
/

--Creating sequences to set the ids automatically

--employee/manager
CREATE SEQUENCE emp_id_seq
    START WITH 1
    INCREMENT BY 1
;
/

--reimbursement
CREATE SEQUENCE rem_id_seq
    START WITH 1
    INCREMENT BY 1
;
/


--receipt
CREATE SEQUENCE rec_id_seq
    START WITH 1
    INCREMENT BY 1
;
/

--triggers to add ids to tables on insertion

--employee/manager
CREATE or Replace TRIGGER emp_id_trigger
BEFORE INSERT
ON Employees
FOR EACH ROW
BEGIN
    SELECT emp_id_seq.nextval INTO :new.employ_id
    FROM dual;
END;
/

--Reimbursements
CREATE or Replace TRIGGER rem_id_trigger
BEFORE INSERT
ON Reimbursements
FOR EACH ROW
BEGIN
    SELECT rem_id_seq.nextval INTO :new.reimbursements_id
    FROM dual;
END;
/

--receipt
CREATE or Replace TRIGGER rec_id_trigger
BEFORE INSERT
ON REceipts
FOR EACH ROW
BEGIN
    SELECT rec_id_seq.nextval INTO :new.receipts_id
    FROM dual;
END;
/

INSERT INTO employees(username, password, is_manager, first_name, last_name, email) Values ('TheRock', '123', 0, 'Dwayne', 'Johnson', 'mud@test.com');
commit;
