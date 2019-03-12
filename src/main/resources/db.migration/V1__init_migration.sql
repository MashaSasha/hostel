create table hibernate_sequence (next_val bigint) engine=MyISAM
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )

create table bonus (
    id bigint not null,
    cost double precision,
    title varchar(255),
    room_type_id bigint,
    primary key (id)
) engine=MyISAM

create table hotel (
    id bigint not null,
    address varchar(255),
    days_count_to_discount integer,
    description varchar(2048),
    discount_on_days double precision,
    discount_on_room_count double precision,
    hotel_name varchar(255),
    room_count_to_discount integer,
    primary key (id)
) engine=MyISAM

create table images (
    img_id bigint not null,
    images varchar(255)
) engine=MyISAM

create table message (id integer not null,
    filename varchar(255),
    tag varchar(255),
    text varchar(2048),
    user_id bigint,
    primary key (id)
) engine=MyISAM

create table reservation (
    id bigint not null,
    cost double precision,
    end_date date,
    start_date date,
    room_id bigint,
    room_type_id bigint,
    user_id bigint,
    primary key (id)
) engine=MyISAM

create table reservation_bonuses (
    reservation_id bigint not null,
    bonus_id bigint not null
) engine=MyISAM

create table room (
    id bigint not null,
    room_number integer,
    room_type_id bigint,
    primary key (id)
) engine=MyISAM

create table room_type (
    id bigint not null,
    cost integer,
    description varchar(2048),
    preview_image varchar(255),
    sleep_places_amount integer,
    title varchar(255),
    hotel_id bigint,
    primary key (id)
) engine=MyISAM

create table user_role (
    user_id bigint not null,
    roles varchar(255)
) engine=MyISAM

create table usr (
    id bigint not null,
    activation_code varchar(255),
    active bit not null,
    email varchar(255),
    name varchar(255),
    passport varchar(255),
    password varchar(255),
    second_name varchar(255),
    primary key (id)
) engine=MyISAM

alter table bonus add constraint bonus_room_type_fk foreign key (room_type_id) references room_type (id)
alter table images add constraint hotel_images_fk foreign key (img_id) references hotel (id)
alter table message add constraint user_message_fk foreign key (user_id) references usr (id)
alter table reservation add constraint reservation_room_fk foreign key (room_id) references room (id)
alter table reservation add constraint reservation_room_type_fk foreign key (room_type_id) references room_type (id)
alter table reservation add constraint reservation_user_fk foreign key (user_id) references usr (id)
alter table reservation_bonuses add constraint reservation_bonuses_fk foreign key (bonus_id) references bonus (id)
alter table reservation_bonuses add constraint bonus_reservation_fk foreign key (reservation_id) references reservation (id)
alter table room add constraint FKd468eq7j1cbue8mk20qfrj5et foreign key (room_type_id) references room_type (id)
alter table room_type add constraint hotel_room_type_fk foreign key (hotel_id) references hotel (id)
alter table user_role add constraint user_role_fk foreign key (user_id) references usr (id)