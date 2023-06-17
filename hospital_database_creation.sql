create database if not exists hospital;

use hospital;

drop table if exists doctors;
create table doctors (
    doctor_id int not null auto_increment,
    doctor_name varchar(50) not null,
    doctor_photo BLOB,
    department_id int, 
    primary key (doctor_id)
);

insert into doctors values (DEFAULT, "Dr. Mark Alvey", NULL, 1);
insert into doctors values (DEFAULT, "Dr. Glen Curtis", NULL, 2);
insert into doctors values (DEFAULT, "Dr. Debbie Lane", NULL, 3);
insert into doctors values (DEFAULT, "Dr. Paul Wright", NULL, 4);
insert into doctors values (DEFAULT, "Dr. Sigi Braun", NULL, 5);
insert into doctors values (DEFAULT, "Dr. Wanda Hoffmann", NULL, 6);
insert into doctors values (DEFAULT, "Dr. Rajani Bala", NULL, 7);
insert into doctors values (DEFAULT, "Dr. Sunita Dhawan", NULL, 8);
insert into doctors values (DEFAULT, "Dr. Mizuno Naomi", NULL, 9);

drop table if exists patients;
create table patients (
    patient_id int not null auto_increment,
    patient_name varchar(50) not null,
    date_of_hospitalization date,
    date_of_discharge date,
    assigned_doctor_id int, 
    diagnosis_id int,
    treatment_course_id int,
    primary key (patient_id)
);

insert into patients values (DEFAULT, "Uwain Merrick", "2023-05-19", "2023-05-21", 1, 1, 1);
insert into patients values (DEFAULT, "Dominic Holland", "2023-05-20", "2023-05-22", 2, 2, 2);
insert into patients values (DEFAULT, "Melvin Mallin", "2023-05-21", "2023-05-23", 3, 3, 3);
insert into patients values (DEFAULT, "Aline Martel", "2023-05-22", "2023-05-24", 4, 4, 4);
insert into patients values (DEFAULT, "Raimond Lyon", "2023-05-23", "2023-05-25", 5, 5, 5);
insert into patients values (DEFAULT, "Bae Jin-Ae", "2023-05-24", "2023-05-26", 6, 6, 6);
insert into patients values (DEFAULT, "Nishihara Amaya", "2023-05-25", "2023-05-27", 7, 7, 7);
insert into patients values (DEFAULT, "Gogol Egorov", "2023-05-26", "2023-05-28", 8, 8, 8);
insert into patients values (DEFAULT, "Vitaly Gusev", "2023-05-27", "2023-05-29", 9, 9, 9);

drop table if exists departments;
create table departments (
    department_id int not null auto_increment,
    department_name varchar(50),
    primary key (department_id)
);

insert into departments values(DEFAULT, "Internal Medicine");
insert into departments values(DEFAULT, "Pediatrics");
insert into departments values(DEFAULT, "Cardiology");
insert into departments values(DEFAULT, "Gastroenterology");
insert into departments values(DEFAULT, "Gynecology");
insert into departments values(DEFAULT, "Neurology");
insert into departments values(DEFAULT, "Ophthalmology");
insert into departments values(DEFAULT, "Dermatology");
insert into departments values(DEFAULT, "Oncology");

drop table if exists diagnosis;
create table diagnosis (
    diagnosis_id int not null auto_increment,
    symptoms varchar(255),
    diagnosis_value varchar(50),
    primary key (diagnosis_id)
);

insert into diagnosis values (DEFAULT, "Runny nose, sneezing, sore throat, cough, body aches, chills, fatigue", "Common Cold");
insert into diagnosis values (DEFAULT, "Fever, chills, body aches, headache, sneezing, runny nose, sore throat, cough", "Flu");
insert into diagnosis values (DEFAULT, "Shortness of breath, wheezing, chest tightness, cough", "Asthma");
insert into diagnosis values (DEFAULT, "Burning sensation in the chest, pain or discomfort in the chest, sour taste in the mouth", "Heartburn");
insert into diagnosis values (DEFAULT, "Pain or discomfort in the abdomen, nausea, vomiting, diarrhea", "Stomachache");
insert into diagnosis values (DEFAULT, "Pain in the head, pressure in the head, tenderness in the head", "Headache");
insert into diagnosis values (DEFAULT, "Redness in the eye, itching in the eye, watery eyes", "Pink Eye");
insert into diagnosis values (DEFAULT, "Rash, itch, blisters, swelling", "Eczema");
insert into diagnosis values (DEFAULT, "Pain, swelling, weight loss, fatigue", "Cancer");

drop table if exists treatment_courses;
create table treatment_courses (
    treatment_course_id int not null auto_increment,
    treatment_courses_value varchar(255),
    primary key (treatment_course_id)
);

insert into treatment_courses values (DEFAULT, "Over-the-counter medication, rest, drink plenty of fluids");
insert into treatment_courses values (DEFAULT, "Over-the-counter medication, rest, drink plenty of fluids, vaccination");
insert into treatment_courses values (DEFAULT, "Inhaled medication, Epinephrine, Oxygen therapy");
insert into treatment_courses values (DEFAULT, "Over-the-counter medication, prescription medication, diet changes");
insert into treatment_courses values (DEFAULT, "Over-the-counter medication, prescription medication, diet changes");
insert into treatment_courses values (DEFAULT, "Over-the-counter medication, prescription medication, rest");
insert into treatment_courses values (DEFAULT, "Over-the-counter medication, prescription medication, eye drops");
insert into treatment_courses values (DEFAULT, "Topical medication, oral medication, eczema cream");
insert into treatment_courses values (DEFAULT, "Chemotherapy, radiation therapy, surgery");

select * from doctors;
select * from patients;
select * from departments;
select * from diagnosis;
select * from treatment_courses;