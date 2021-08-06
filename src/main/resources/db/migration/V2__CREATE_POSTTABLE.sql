CREATE TABLE POSTS
(
    ID number(15,0) not null,
    TITLE varchar(50),
    DATE timestamp,
    USER_ID number(19, 0),
    primary key (ID)
);

alter table USERS
add constraint FK foreign key (ID) references USERS;

