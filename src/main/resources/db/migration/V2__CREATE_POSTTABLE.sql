CREATE TABLE POSTS
(
    ID number(15,0) not null,
    TITLE varchar(50),
    DATE timestamp,
    primary key (ID),

    foreign key (ID) references USERS (ID)
);
