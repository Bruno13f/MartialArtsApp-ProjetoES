package pt.ipleiria.estg.dei.ei.esoft.atletas;

import pt.ipleiria.estg.dei.ei.esoft.EscalaoEtario;
import pt.ipleiria.estg.dei.ei.esoft.eventos.Evento;

public class Inscricao {
    private Atleta atleta;
    private Evento evento;
    private float pesoInscricao;
    private EscalaoEtario escalaoEtario;

    public Inscricao (Atleta atleta, Evento evento, float pesoInscricao, EscalaoEtario escalaoEtario){
        // ja se verifica que o atleta existe e o evento
        this.atleta = atleta;
        this.evento = evento;
        // verificar peso
        this.pesoInscricao = pesoInscricao;
        this.escalaoEtario = escalaoEtario;
    }

    public float getPesoInscricao() {
        return pesoInscricao;
    }

    public Evento getEvento() {
        return evento;
    }

    public void atletarPesoInscricao (float pesoNovo){
        // verificar se peso novo valido
        this.pesoInscricao = pesoNovo;
    }
}
