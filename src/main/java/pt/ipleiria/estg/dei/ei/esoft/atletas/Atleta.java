package pt.ipleiria.estg.dei.ei.esoft.atletas;

import pt.ipleiria.estg.dei.ei.esoft.EscalaoEtario;
import pt.ipleiria.estg.dei.ei.esoft.Genero;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Atleta {
    private String nome;
    private String nacionalidade;
    private Genero genero;
    private String modalidade;
    private float peso;
    private Date dataNascimento;
    private int contacto;
    private EscalaoEtario escalaoEtario;
    private int idade;
    private List<Inscricao> inscricoes;

    public Atleta(String nome, String nacionalidade, Genero genero, String modalidade, float peso, Date dataNascimento, int contacto, EscalaoEtario escalaoEtario) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.genero = genero;
        this.modalidade = modalidade;
        this.peso = peso;
        this.dataNascimento = dataNascimento;
        this.contacto = contacto;
        this.escalaoEtario = escalaoEtario;
    }

    public String getNome() {
        return nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public Genero getGenero() {
        return genero;
    }

    public String getModalidade() {
        return modalidade;
    }

    public float getPeso() {
        return peso;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public int getContacto() {
        return contacto;
    }

    public EscalaoEtario getEscalaoEtario() {
        return escalaoEtario;
    }
}
