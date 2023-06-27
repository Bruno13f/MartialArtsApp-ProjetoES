package pt.ipleiria.estg.dei.ei.esoft.provas;

import pt.ipleiria.estg.dei.ei.esoft.EscalaoEtario;
import pt.ipleiria.estg.dei.ei.esoft.Genero;
import pt.ipleiria.estg.dei.ei.esoft.atletas.Atleta;
import pt.ipleiria.estg.dei.ei.esoft.eventos.Evento;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Prova {
    private Date horaInicio;
    private Date horaFim;
    private String categoriaPeso;
    private Genero genero;
    private EscalaoEtario escalaoEtario;
    private List<Pool> pools;
    private String modalidade;
    private Atleta vencedor;
    private Evento evento;
    private List<Atleta> atletas;

    public Prova(Date horaInicio, Date horaFim, String categoriaPeso, EscalaoEtario escalaoEtario, Genero genero, Evento evento) {
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.categoriaPeso = categoriaPeso;
        this.evento = evento;
        this.genero = genero;
        this.escalaoEtario = escalaoEtario;
        // criar lista no construtor - 4 - A B C D
    }

    public String getHora(){
        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");

        return formatador.format(horaInicio) + " - " + formatador.format(horaFim) ;
    }
    public String getCategoriaPeso(){
        return this.categoriaPeso;
    }
    public String getGenero(){
        return this.genero.toString();
    }

    public String getEscalaoEtario(){
        return this.escalaoEtario.toString();
    }
}
