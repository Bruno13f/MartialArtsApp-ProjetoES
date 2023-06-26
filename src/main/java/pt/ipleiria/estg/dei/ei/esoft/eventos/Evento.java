package pt.ipleiria.estg.dei.ei.esoft.eventos;

import pt.ipleiria.estg.dei.ei.esoft.EscalaoEtario;
import pt.ipleiria.estg.dei.ei.esoft.Genero;
import pt.ipleiria.estg.dei.ei.esoft.provas.Prova;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Evento {
    private String nome;
    private Date dataInicio;
    private Date dataFim;
    private String pais;
    private String local;
    private EscalaoEtario escalaoEtario;
    private List<String> categoriasPeso;
    private List<Genero> genero;
    private String modalidade;
    private List<Prova> provas;

    // como nao há argumentos opcionais - criar construtor com todos atributos evento, na implementação verificar se user inseriu os opcionais, se não passar valor inválido a definir
    // cateogiras peso como tem o "-" e "+" tem de ser String, Lista porque um evento pode ter varias
    // provas nao esta no construtor porque nao é opção ao criar prova
    // ler eventos sempre que a aplicação é iniciada

    // eventos aplicação guardados ficheiro JSON, adicionado id de forma a identificar os eventos no ficheiro (atributo nao usado na aplicação)
    // eventos importados para a aplicação ficheiro JSON sem id
    public Evento(String nome, Date dataInicio, Date dataFim, String pais, String local, EscalaoEtario escalaoEtario, List<String> categoriasPeso, List<Genero> genero, String modalidade) {

        // Obrigatorios
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.pais = pais;
        this.local = local;
        this.modalidade = modalidade;

        // Opcionais

        if (escalaoEtario != null){
            this.escalaoEtario = escalaoEtario;
        }

        if (categoriasPeso != null){
            this.categoriasPeso = categoriasPeso;
        }

        if (genero != null){
            this.genero = genero;
        }

    }

    public String getNome(){
        return nome;
    }

    public String getDuracaoString (){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataInicio = dateFormat.format(this.dataInicio);
        String dataFim = dateFormat.format(this.dataFim);

        return dataInicio + " - " + dataFim;
    }

    public String getLocal(){
        return local;
    }

    public String getCodigoIsoPais(){
        // pais -> codigo pais
        Map<String, String> paises = new HashMap<>();

        Locale[] locais = Locale.getAvailableLocales();
        for (Locale locale : locais) {
            try {
                String iso = locale.getISO3Country();
                if (!iso.isEmpty()){
                    paises.put(locale.getDisplayCountry(new Locale("pt")), locale.getCountry());
                }
            }catch (MissingResourceException e){
                //nao faz nada
            }
        }

        return paises.get(pais);
    }

    public String getModalidade() {
        return modalidade;
    }

    public String getGenero(){
        return this.genero.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
    }

    public String getEscalaoEtario(){
        return escalaoEtario.toString();
    }

    public String getCategoriasPeso(){
        return this.categoriasPeso.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
    }

    public List<Evento> getEventos (){

        return null;
    }
}
