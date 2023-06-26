package pt.ipleiria.estg.dei.ei.esoft.atletas;

import pt.ipleiria.estg.dei.ei.esoft.Genero;
import pt.ipleiria.estg.dei.ei.esoft.calendario.CalendarioEventos;
import pt.ipleiria.estg.dei.ei.esoft.eventos.GestaoEventos;
import pt.ipleiria.estg.dei.ei.esoft.resultados.Resultados;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CriarAtleta extends JFrame{
    private JPanel mainPanel;
    private JPanel topPanel;
    private JButton btnEventos;
    private JButton btnAtletas;
    private JButton btnResultados;
    private JButton btnCalendario;
    private JPanel bodyPanel;
    private JPanel btnsPanel;
    private JButton btnCriar;
    private JButton btnCancelar;
    private JLabel espaco;
    private JPanel formPanel;
    private JPanel esquerdoPanel;
    private JLabel nome;
    private JTextField textNome;
    private JLabel local;
    private JTextField textPeso;
    private JLabel modalidade;
    private JTextField textModalidade;
    private JLabel genero;
    private JPanel generoPanel;
    private JPanel direitoPanel;
    private JLabel pais;
    private JComboBox<String> paisesComboBox;
    private JLabel dataNascimento;
    private JTextField textDataNascimento;
    private JLabel contacto;
    private JTextField textContacto;
    private JRadioButton radioBtnMasculino;
    private JRadioButton radioBtnFeminino;

    public CriarAtleta(String title){
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setResizable(false);
        pack();

        btnEventos.addActionListener(this::btnEventosActionPerformed);
        btnAtletas.addActionListener(this::btnAtletasActionPerformed);
        btnResultados.addActionListener(this::btnResultadosActionPerformed);
        btnCalendario.addActionListener(this::btnCalendarioActionPerformed);
        btnCriar.addActionListener(this::btnCriarActionPerformed);
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        fillComboBoxPaises(paisesComboBox);
        paisesComboBox.setEditable(true);
        paisesComboBox.setSelectedItem("Selecione um país");

        configurarTxtModalidade();
    }

    public static void abrirPaginaCriarAtletas(){
        new CriarAtleta("Criar Atleta").setVisible(true);
    }

    private void configurarTxtModalidade() {
        textModalidade.setEditable(false);
        textModalidade.setText("Judo");
    }

    private void abrirPaginaAtletas(){
        GestaoAtletas.abrirPaginaGestaoAtletas();
        this.dispose();
    }
    private void btnCriarActionPerformed(ActionEvent actionEvent) {
        // TODO: CRIAR ATLETA
        abrirPaginaAtletas();
    }
    private void btnCancelarActionPerformed(ActionEvent actionEvent) {
        abrirPaginaAtletas();
    }

    private void btnEventosActionPerformed(ActionEvent actionEvent) {
        GestaoEventos.abrirPaginaGestaoEventos();
        this.dispose();
    }

    private void btnAtletasActionPerformed(ActionEvent actionEvent) {
        abrirPaginaAtletas();
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

    private boolean validarData(Date dataNascimento){
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
    private boolean validarPeso(Float peso) {

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

    private boolean validarGenero(Genero genero) {
        if (genero == Genero.Masculino || genero == Genero.Feminino){
            return true;
        }
        return false;
    }

    private boolean validarPais(String nacionalidade){
        if (!nacionalidade.trim().isEmpty()) {
            return true;
        }else {
            return false;
        }
    }

    private boolean validarNome (String nome){
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
