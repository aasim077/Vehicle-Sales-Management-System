SELECT * FROM sample.vehicle;

create table vehicle (id int auto_increment not null primary key,model int  not null ,color varchar(30) not null, chasis_no varchar(30) not null, state varchar(30) default 'new');
create table model(id int primary key auto_increment,modelname varchar(30) not null);
#"ID", "SALES DATE", "VEHICLE", "CUSTOMER NAME", "ADDRESS", "CONTACT", "CITY", "STATE"
create table salesp(id int auto_increment primary key not null,sales_date varchar(8)  not null,vehicle_id int not null,cname varchar(30) not null,address varchar(40) not null, cont bigint not null,city varchar(30) not null,state varchar(30) not null);
