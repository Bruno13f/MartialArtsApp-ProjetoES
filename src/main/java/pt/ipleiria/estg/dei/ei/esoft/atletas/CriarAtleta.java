package pt.ipleiria.estg.dei.ei.esoft.atletas;

import pt.ipleiria.estg.dei.ei.esoft.Genero;
import pt.ipleiria.estg.dei.ei.esoft.calendario.CalendarioEventos;
import pt.ipleiria.estg.dei.ei.esoft.eventos.GestaoEventos;
import pt.ipleiria.estg.dei.ei.esoft.resultados.Resultados;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

            if (validarNome() != 0){
                mostrarErro(validarNome());
                return;
            }

            if (validarPeso() != 0){
                mostrarErro(validarPeso());
                return;
            }

            if (validarGenero() != 0){
                mostrarErro(validarGenero());
                return;
            }

            if (validarPais() != 0){
                mostrarErro(validarPais());
                return;
            }

            if (validarData() != 0){
                mostrarErro(validarData());
                return;
            }

            if (validarContacto() != 0){
                mostrarErro(validarContacto());
                return;
            }

        abrirPaginaAtletas();
    }

    private void mostrarErro (int codigo){

        switch(codigo){
            case 1:
                JOptionPane.showMessageDialog(mainPanel, "Preencha o campo nome");
                break;
            case 2:
                JOptionPane.showMessageDialog(mainPanel, "O campo nome possui mais do que 50 caracteres");
                break;
            case 3:
                JOptionPane.showMessageDialog(mainPanel, "O campo nome possui caracteres numéricos");
                break;
            case 4:
                JOptionPane.showMessageDialog(mainPanel, "Selecione um país");
                break;
            case 5:
                JOptionPane.showMessageDialog(mainPanel, "Selecione um género");
                break;
            case 6:
                JOptionPane.showMessageDialog(mainPanel, "Preencha o campo peso");
                break;
            case 7:
                JOptionPane.showMessageDialog(mainPanel, "O campo peso possui um formato errado");
                break;
            case 8:
                JOptionPane.showMessageDialog(mainPanel, "Preencha o campo data");
                break;
            case 9:
                JOptionPane.showMessageDialog(mainPanel, "Insira uma data válida");
                break;
            case 10:
                JOptionPane.showMessageDialog(mainPanel, "Atleta possui menos de 8 anos de idade");
                break;
            case 11:
                JOptionPane.showMessageDialog(mainPanel, "Preencha o campo contacto");
                break;
            case 12:
                JOptionPane.showMessageDialog(mainPanel, "O campo contacto possui tem de possuir 9 caracteres");
                break;
            case 13:
                JOptionPane.showMessageDialog(mainPanel, "O campo contacto possui caracteres inválidos (a-z,A-Z, espaços em branco, caracteres especiais)");
                break;
            case 14:
                JOptionPane.showMessageDialog(mainPanel, "O campo contacto é um número negativo");
                break;
            case 15:
                JOptionPane.showMessageDialog(mainPanel, "O contacto do atleta já se encontra em uso");
                break;
            case 16:
                JOptionPane.showMessageDialog(mainPanel, "O campo nome possui caracteres especiais");
                break;
            case 17:
                JOptionPane.showMessageDialog(mainPanel, "Dois campos géneros selecionados");
                break;
        }
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

    private int validarData(){

        String data = textDataNascimento.getText();

        if (data.isEmpty()){
            return 8;
        }

        Pattern pattern = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");
        Matcher matcher = pattern.matcher(data);

        if (!matcher.matches()){
            return  9;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInserida = LocalDate.parse(data, formatter);

        LocalDate dataAtual = LocalDate.now();

        if (!dataInserida.isBefore(dataAtual)){
            return  9;
        }

        Period ageDifference = Period.between(dataInserida, dataAtual);

        if (ageDifference.getYears() < 8) {
            return 10; // or any other error code you prefer
        }

        return 0;
    }
    private int validarPeso() {

        String pesoIN = textPeso.getText();

        if (pesoIN.isEmpty()){
            return 6;
        }

        if (!Pattern.matches(".*\\d.*", pesoIN)) {
            return 7;
        }

        Float peso;
        try {
            peso = Float.parseFloat(pesoIN);
        } catch (NumberFormatException e) {
            return 7; // Invalid peso format
        }

        if (peso == null) {
            return 7; // Null peso value
        }

        String pesoString = String.valueOf(peso);
        int indexOfDecimalSeparator = pesoString.indexOf(".");

        if (indexOfDecimalSeparator != -1) {
            int decimalPlaces = pesoString.length() - indexOfDecimalSeparator - 1;
            if(decimalPlaces > 2){
                return 7;
            }
        }

        return 0;
    }



    private int validarGenero(){

        if (radioBtnMasculino.isSelected() && radioBtnFeminino.isSelected()){
            return 17;
        }

        if (!radioBtnMasculino.isSelected() && !radioBtnFeminino.isSelected()){
            return 5;
        }

        return 0;
    }

    private int validarPais(){

        String pais = (String) paisesComboBox.getSelectedItem();

        if (pais.equals("Selecione um país")){
            return 4;
        }

        System.out.println(pais);

        return 0;
    }

    private int validarNome() {

        String nome = textNome.getText();

        if (nome.isEmpty()){
            return 1;
        }

        if (Pattern.matches(".*\\d.*", nome)) {
            return 3;
        }

        if (!Pattern.matches("^[a-zA-Z ]+$", nome)){
            return 16;
        }

        if (nome.length() > 50){
            return 2;
        }

        return 0;
    }

    private int validarContacto() {

        String contacto = textContacto.getText();

        if (contacto.isEmpty()){
            return 11;
        }

        if (contacto.length() != 9){
            return 12;
        }

        if (!Pattern.matches(".*\\d.*", contacto)) {
            return 13;
        }

        int contactoInt;
        contactoInt = Integer.parseInt(contacto);

        if (contactoInt > 0) {
            return 0;
        } else {
            return 14;
        }
    }
}
