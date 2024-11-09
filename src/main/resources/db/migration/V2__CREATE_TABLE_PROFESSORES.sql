create table professores (
    id int primary key auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null,
    telefone varchar(15) not null,
    especialidade varchar(100) not null
)