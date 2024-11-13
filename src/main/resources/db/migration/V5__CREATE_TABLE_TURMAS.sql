create table disciplinas (
    id int primary key auto_increment,
    nome varchar(100) not null,
    codigo varchar(20) unique not null,
    curso_id int not null,
    professor_id int not null,
    foreign key (curso_id) references cursos(id),
    foreign key (professor_id) references professores(id)
)