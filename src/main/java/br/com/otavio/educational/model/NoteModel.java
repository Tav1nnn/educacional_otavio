package br.com.otavio.educational.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "notas")
public class NoteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nota")
    private Double note;

    @Column(name = "data_lancamento")
    @Temporal(TemporalType.DATE)
    private Date launch_date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "matricula_id", nullable = false)
    private MatriculationModel matriculationModel;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "disciplina_id", nullable = false)
    private DisciplineModel disciplineModel;

    public NoteModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public Date getLaunch_date() {
        return launch_date;
    }

    public void setLaunch_date(Date launch_date) {
        this.launch_date = launch_date;
    }

    public MatriculationModel getMatriculationModel() {
        return matriculationModel;
    }

    public void setMatriculationModel(MatriculationModel matriculationModel) {
        this.matriculationModel = matriculationModel;
    }

    public DisciplineModel getDisciplineModel() {
        return disciplineModel;
    }

    public void setDisciplineModel(DisciplineModel disciplineModel) {
        this.disciplineModel = disciplineModel;
    }
}
