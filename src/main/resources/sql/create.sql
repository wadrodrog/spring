create table account (
	id bigserial primary key,
	name varchar(64) unique not null,
	birth_date date,
	status varchar(50) not null
);
