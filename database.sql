
CREATE DATABASE `jobabroad`;

USE `jobabroad`;

create table `company` (
	`id` int not null,
    `description` varchar(255) not null,
    primary key (`id`)
);

create table `jobs` (
	`id` int not null,
    `company_id` int not null,
    `description` varchar(255) not null,
    `title` varchar(255) default null,
    primary key (`id`),
    foreign key (`company_id`) references company(`id`)
);

create table `skills` (
	`id` int not null,
    `description` varchar(255) default null,
    primary key (`id`)
);

create table `jobs_skills` (
	`id_job` int not null,
    `id_skill` int not null,
    primary key (id_job, id_skill),
    foreign key (`id_job`) references jobs(`id`),
    foreign key (`id_skill`) references skills(`id`)
);

create table `user` (
	`id` int not null AUTO_INCREMENT,
	`username` varchar(255) not null,
    `name` varchar(255) default null,
    `email` varchar(255) not null,
    `password` varchar(255) not null,
    `permission` varchar(20) not null,
    primary key (`id`)
);

create table `resume` (
	`id_user` int not null,
    `observations` varchar(255) default null,
	primary key (`id_user`),
    foreign key (`id_user`) references user(`id`)
);

create table `personal_experience` (
	`id` int not null,
	`id_user` int not null,
    `description_exp` varchar(255),
    `years_exp` int not null,
    `type_exp` int not null,
    primary key (`id`),
    foreign key (`id_user`) references user(`id`)
);

create table `applied_jobs`(
	`id_user` int not null,
	`id_job` int not null,
    `applied` int default null,
    primary key (id_user, id_job),
    foreign key (`id_user`) references user(`id`),
    foreign key (`id_job`) references jobs(`id`)
)


