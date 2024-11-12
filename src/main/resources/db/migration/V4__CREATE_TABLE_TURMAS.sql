create table turmas (
    id int primary key auto_increment,
    ano int not null,
    semestre int not null,
    curso_id int not null,
    foreign key (curso_id) references cursos(id)
)