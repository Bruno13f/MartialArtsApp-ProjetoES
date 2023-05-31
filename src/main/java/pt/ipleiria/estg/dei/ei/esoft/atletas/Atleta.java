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

    public Atleta(String nome, String nacionalidade, Genero genero, String modalidade, Float peso, Date dataNascimento, Integer contacto) {

        if(nome_Valido(nome)){
            this.nome = nome;
        }

        if(nacionalidade_Valida(nacionalidade)){
            this.nacionalidade = nacionalidade;
        }

        if(genero_Valido(genero)){
            this.genero = genero;
        }

        if(peso_Valido(peso)){
            this.peso = peso;
        }

        if(data_Valida(dataNascimento)){
            this.dataNascimento = dataNascimento;
        }

        this.contacto = contacto;

        this.modalidade = modalidade;
    }

    private boolean data_Valida(Date dataNascimento){
        if(dataNascimento == null){
            return false;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.format(dataNascimento);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    private boolean peso_Valido(Float peso) {

        if (peso == null) {
            return false;
        }

        String pesoString = String.valueOf(peso);
        int indexOfDecimalSeparator = pesoString.indexOf(".");

        if (indexOfDecimalSeparator != -1) {
            int decimalPlaces = pesoString.length() - indexOfDecimalSeparator - 1;
            return decimalPlaces <= 2;
        }
        return true;
    }

    private boolean genero_Valido(Genero genero) {
        if (genero == Genero.Masculino || genero == Genero.Feminino){
            return true;
        }
        return false;
    }

    private boolean nacionalidade_Valida(String nacionalidade){
        if (!nacionalidade.trim().isEmpty()) {
            return true;
        }else {
            return false;
        }
    }

    private boolean nome_Valido (String nome){
        if (!nome_Numero(nome)){
            return false;
        }

        if (nome.trim().isEmpty()) {
            return false;
        }

        if (!(nome.length() <= 50)){
            return false;
        }

        if (tem_Caracter_Especial(nome)){
            return false;
        }
        return true;
    }

    private boolean nome_Numero(String nome) {
        for (char a : nome.toCharArray()) {
            if (Character.isDigit(a)) {
                return false;
            }
        }
        return true;
    }

    private boolean tem_Caracter_Especial(String nome) {
        String specialCharacters = "!@#$%^&*()_+|<>?{}\\[\\]~-";
        for (char c : nome.toCharArray()) {
            if (specialCharacters.contains(String.valueOf(c))) {
                return true;
            }
        }
        return false;
    }

}
