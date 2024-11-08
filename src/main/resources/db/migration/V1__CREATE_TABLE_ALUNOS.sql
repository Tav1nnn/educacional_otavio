create table alunos (
    id int primary key auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null,
    matricula varchar(20) not null,
    data_nascimento date not null
)