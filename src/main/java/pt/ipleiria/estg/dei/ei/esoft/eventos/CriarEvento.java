package pt.ipleiria.estg.dei.ei.esoft.eventos;

import pt.ipleiria.estg.dei.ei.esoft.EscalaoEtario;
import pt.ipleiria.estg.dei.ei.esoft.atletas.GestaoAtletas;
import pt.ipleiria.estg.dei.ei.esoft.calendario.CalendarioEventos;
import pt.ipleiria.estg.dei.ei.esoft.resultados.Resultados;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.Collator;
import java.util.*;

import static pt.ipleiria.estg.dei.ei.esoft.EscalaoEtario.getValues;

public class CriarEvento extends JFrame{
    private JPanel mainPanel;
    private JPanel topPanel;
    private JButton btnEventos;
    private JButton btnAtletas;
    private JButton btnResultados;
    private JButton btnCalendario;
    private JPanel bodyPanel;
    private JButton btnCriar;
    private JButton btnCancelar;
    private JPanel btnsPanel;
    private JPanel formPanel;
    private JPanel esquerdoPanel;
    private JPanel direitoPanel;
    private JLabel nome;
    private JTextField textNome;
    private JLabel local;
    private JTextField textLocal;
    private JLabel modalidade;
    private JTextField textModalidade;
    private JLabel escalaoEtario;
    private JComboBox<String> dropdownEscalaoEtario;
    private JLabel genero;
    private JPanel generoPanel;
    private JCheckBox masculinoCheckBox;
    private JCheckBox femininoCheckBox;
    private JLabel pais;
    private JComboBox<String> paisesComboBox;
    private JPanel masculinoPanel;
    private JLabel masculino;
    private JCheckBox CB38;
    private JCheckBox CB42;
    private JCheckBox CB46;
    private JCheckBox CB50;
    private JCheckBox CB55;
    private JCheckBox CB60;
    private JCheckBox CB66;
    private JCheckBox CB73;
    private JCheckBox CB81;
    private JCheckBox CB81M;
    private JCheckBox CB90;
    private JCheckBox CB90M;
    private JCheckBox CB100;
    private JCheckBox CB100M;
    private JPanel femininoPanel;
    private JCheckBox CB40;
    private JCheckBox CB44;
    private JCheckBox CB48;
    private JCheckBox CB52;
    private JCheckBox CB57;
    private JCheckBox CB63;
    private JCheckBox CB70;
    private JCheckBox CB70M;
    private JCheckBox CB78;
    private JCheckBox CB78M;
    private JLabel categoriasPeso;
    private JPanel categoriasPesoPanel;
    private JPanel textoPanel;
    private JTextField textDataFinal;
    private JTextField textDataInicio;
    private JPanel infoDataFinalPanel;
    private JPanel infoDataInicioPanel;
    private JPanel baixoPanel;
    private JPanel cimaPanel;
    private JPanel textDataInicioPanel;
    private JPanel textDataFinalPanel;
    private JLabel dataInicio;
    private JLabel dataFinal;
    private JLabel formataData;
    private JLabel espaco;
    private final String[] escaloes = getValues(EscalaoEtario.class);

    public CriarEvento(String title){

        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setResizable(false);
        pack();

        btnEventos.addActionListener(this::btnEventosActionPerformed);
        btnAtletas.addActionListener(this::btnAtletasActionPerformed);
        btnResultados.addActionListener(this::btnResultadosActionPerformed);
        btnCalendario.addActionListener(this::btnCalendarioActionPerformed);
        btnCriar.addActionListener(this::btnConfirmarEventoActionPerformed);
        btnCancelar.addActionListener(this::btnCancelarEventoActionPerformed);

        configurarTxtModalidade();

        fillComboBoxPaises(paisesComboBox);
        paisesComboBox.setEditable(true);
        paisesComboBox.setSelectedItem("Selecione um país");

        fillComboBoxEscaloes(dropdownEscalaoEtario);
        dropdownEscalaoEtario.setEditable(true);
        dropdownEscalaoEtario.setSelectedItem("Selecione um escalão");

    }

    private void configurarTxtModalidade() {
        textModalidade.setEditable(false);
        textModalidade.setText("Judo");
    }

    public static void abrirPaginaCriarEvento(){
        new CriarEvento("Criar Evento").setVisible(true);
    }

    private void abrirPaginaEventos(){
        GestaoEventos.abrirPaginaGestaoEventos();
        this.dispose();
    }
    public void btnConfirmarEventoActionPerformed(ActionEvent actionEvent) {
        // TODO: CRIAR EVENTO
        abrirPaginaEventos();
    }

    public void btnCancelarEventoActionPerformed(ActionEvent actionEvent) {
        abrirPaginaEventos();
    }

    private void btnEventosActionPerformed(ActionEvent actionEvent) {
        abrirPaginaEventos();
    }
    private void btnAtletasActionPerformed(ActionEvent actionEvent) {
        GestaoAtletas.abrirPaginaGestaoAtletas();
        this.dispose();
    }

    private void btnResultadosActionPerformed(ActionEvent actionEvent) {
        Resultados.abrirPaginaResultados();
        this.dispose();
    }

    private void btnCalendarioActionPerformed(ActionEvent actionEvent) {
        CalendarioEventos.abrirPaginaCalendario();
        this.dispose();
    }

    private void fillComboBoxPaises(JComboBox<String> comboBox){
        String[] paises = new String[Locale.getISOCountries().length];
        String[] codPais = Locale.getISOCountries();
        for (int i = 0; i < codPais.length; i++) {
            Locale obj = new Locale("", codPais[i]);
            paises[i] = obj.getDisplayCountry(Locale.forLanguageTag("pt-PT"));
        }

        Collator collator = Collator.getInstance(Locale.forLanguageTag("pt-PT"));
        List<String> paisesOrdenados = new ArrayList<>(List.of(paises));
        paisesOrdenados.sort(collator);

        for (String nacionalidade : paisesOrdenados) {
            comboBox.addItem(nacionalidade);
        }
    }

    private void fillComboBoxEscaloes(JComboBox<String> dropdownEscalaoEtario) {
        for (String escalao: escaloes){
            dropdownEscalaoEtario.addItem(escalao);
        }
    }

    private void adicionarEventoJSON (){

    }

}
