package pt.ipleiria.estg.dei.ei.esoft.provas;

import pt.ipleiria.estg.dei.ei.esoft.Genero;
import pt.ipleiria.estg.dei.ei.esoft.atletas.Atleta;
import pt.ipleiria.estg.dei.ei.esoft.eventos.Evento;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Prova {
    private Date horaInicio;
    private Date horaFim;
    private String categoriaPeso;
    private Genero genero;
    private List<Pool> pools;
    private String modalidade;
    private Atleta vencedor;
    private Evento evento;
    private List<Atleta> atletas;

    public Prova(Date horaInicio, Date horaFim, String categoriaPeso, Genero genero, Evento evento) {
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.categoriaPeso = categoriaPeso;
        this.evento = evento;
        // criar lista no construtor - 4 - A B C D
    }
}
