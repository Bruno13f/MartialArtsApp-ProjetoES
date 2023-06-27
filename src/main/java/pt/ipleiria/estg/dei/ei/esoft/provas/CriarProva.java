package pt.ipleiria.estg.dei.ei.esoft.provas;

import pt.ipleiria.estg.dei.ei.esoft.atletas.GestaoAtletas;
import pt.ipleiria.estg.dei.ei.esoft.calendario.CalendarioEventos;
import pt.ipleiria.estg.dei.ei.esoft.eventos.GestaoEventos;
import pt.ipleiria.estg.dei.ei.esoft.resultados.Resultados;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CriarProva extends JFrame{
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
    private JPanel esqPanel;
    private JPanel meioPanel;
    private JPanel dirPanel;
    private JPanel categoriasPesoPanel;
    private JPanel textoPanel;
    private JLabel categoriasPeso;
    private JPanel masculinoPanel;
    private JLabel masculino;
    private JRadioButton CB38;
    private JRadioButton CB42;
    private JRadioButton CB46;
    private JRadioButton CB50;
    private JRadioButton CB55;
    private JRadioButton CB60;
    private JRadioButton CB66;
    private JRadioButton CB73;
    private JRadioButton CB81;
    private JRadioButton CB81M;
    private JRadioButton CB90;
    private JRadioButton CB90M;
    private JRadioButton CB100;
    private JRadioButton CB100M;
    private JPanel femininoPanel;
    private JRadioButton CB40;
    private JRadioButton CB44;
    private JRadioButton CB48;
    private JRadioButton CB52;
    private JRadioButton CB57;
    private JRadioButton CB63;
    private JRadioButton CB70;
    private JRadioButton CB70M;
    private JRadioButton CB78;
    private JRadioButton CB78M;
    private JRadioButton masculinoRadioButton;
    private JRadioButton femininoRadioButton;
    private JPanel radioBtnPanel;
    private JLabel genero;
    private JLabel escalaoEtario;
    private JPanel dropdownPanel;
    private JComboBox<String> dropdownEscalaoEtario;

    private final String[] escaloes = {"Bejamins", "Infantis", "Iniciados", "Juvenis", "Cadetes", "Juniores", "Sub23", "Seniores","Veteranos"};

    private int idEvento;

    public CriarProva(String title, int idEvento){
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setResizable(false);
        pack();

        this.idEvento = idEvento;
        btnEventos.addActionListener(this::btnEventosActionPerformed);
        btnAtletas.addActionListener(this::btnAtletasActionPerformed);
        btnResultados.addActionListener(this::btnResultadosActionPerformed);
        btnCalendario.addActionListener(this::btnCalendarioActionPerformed);

        btnCriar.addActionListener(this::btnCriarActionPerformed);
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        ButtonGroup groupGenero = new ButtonGroup();
        groupGenero.add(masculinoRadioButton);
        groupGenero.add(femininoRadioButton);

        grupoCategoriasPesoMasculino();
        grupoCategoriasPesoFeminino();

        fillComboBoxEscaloes(dropdownEscalaoEtario);
        dropdownEscalaoEtario.setEditable(true);

    }

    public static void abrirPaginaCriarProva (int idEvento){
        new CriarProva("Criar Prova", idEvento).setVisible(true);
    }

    private void abrirPaginaProvas() {
        GestaoProvas.abrirPaginaGestaoProvas(idEvento);
        this.dispose();
    }

    private void grupoCategoriasPesoMasculino(){
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(CB38);
        grupo.add(CB42);
        grupo.add(CB46);
        grupo.add(CB50);
        grupo.add(CB55);
        grupo.add(CB60);
        grupo.add(CB66);
        grupo.add(CB73);
        grupo.add(CB81);
        grupo.add(CB81M);
        grupo.add(CB90);
        grupo.add(CB90M);
        grupo.add(CB100);
        grupo.add(CB100M);
    }

    private void grupoCategoriasPesoFeminino(){
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(CB40);
        grupo.add(CB44);
        grupo.add(CB48);
        grupo.add(CB52);
        grupo.add(CB57);
        grupo.add(CB63);
        grupo.add(CB70);
        grupo.add(CB70M);
        grupo.add(CB78);
        grupo.add(CB78M);
    }

    private void btnCriarActionPerformed(ActionEvent actionEvent) {
        // TODO: CRIAR PROVA

        if (validarNumeroCampos() != 0){
            mostrarErro(validarNumeroCampos());
            return;
        }

        if (validarGenero() != 0){
            mostrarErro(validarGenero()); // data inicio
            return;
        }

        if (validarCategoriaEtaria() != 0){
            mostrarErro(validarCategoriaEtaria()); // data final
            return;
        }

        if (validarCategoriaPeso() != 0){
            mostrarErro(validarCategoriaPeso());
            return;
        }

        abrirPaginaProvas();
    }

    private void mostrarErro (int codigo){

        switch(codigo){
            case 1:
                JOptionPane.showMessageDialog(mainPanel, "Necessário selecionar uma opção para cada campo");
                break;
            case 2:
                JOptionPane.showMessageDialog(mainPanel, "Género é mandatório");
                break;
            case 3:
                JOptionPane.showMessageDialog(mainPanel, "Escalão etário é mandatório");
                break;
            case 4:
                JOptionPane.showMessageDialog(mainPanel, "Categoria de Peso é mandatório");
                break;
            case 5:
                JOptionPane.showMessageDialog(mainPanel, "Categoria de Peso não coincide com o Género");
                break;
        }
    }

    private int validarNumeroCampos(){

        String escalaoEtario = (String) dropdownEscalaoEtario.getSelectedItem();

        if ((femininoRadioButton.isSelected() || masculinoRadioButton.isSelected()) && escalaoEtario != null && (categoriasPesoFemininoSelected() || categoriasPesoMasculinoSelected())){
            return 0;
        }

        return 1;
    }

    private boolean categoriasPesoFemininoSelected() {
        return CB40.isSelected() || CB44.isSelected() || CB48.isSelected() || CB52.isSelected() || CB57.isSelected() || CB63.isSelected() || CB70.isSelected() || CB70M.isSelected() || CB78.isSelected() || CB78M.isSelected();
    }

    private boolean categoriasPesoMasculinoSelected() {
        return CB38.isSelected() || CB42.isSelected() || CB46.isSelected() || CB50.isSelected() || CB55.isSelected() || CB60.isSelected() || CB66.isSelected() || CB73.isSelected() || CB81.isSelected() || CB81M.isSelected() || CB90.isSelected() || CB90M.isSelected() || CB100.isSelected() || CB100M.isSelected();
    }

    private int validarGenero(){
        if (femininoRadioButton.isSelected() || masculinoRadioButton.isSelected()){
            return 0;
        }

        return 2;
    }

    private int validarCategoriaEtaria(){
        String escalaoEtario = (String) dropdownEscalaoEtario.getSelectedItem();

        if  (escalaoEtario == null){
            return 3;
        }

        return 0;
    }

    private int validarCategoriaPeso(){

        if (!categoriasPesoMasculinoSelected() && !categoriasPesoFemininoSelected()){
            return 4;
        }


        if (femininoRadioButton.isSelected() && categoriasPesoMasculinoSelected()){
            return 5;
        }

        if (masculinoRadioButton.isSelected() && categoriasPesoFemininoSelected()){
            return 5;
        }

        return 0;
    }


    private void btnCancelarActionPerformed(ActionEvent actionEvent) {
        abrirPaginaProvas();
    }

    private void btnEventosActionPerformed (ActionEvent actionEvent){
        GestaoEventos.abrirPaginaGestaoEventos();
        this.dispose();
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

    private void fillComboBoxEscaloes(JComboBox<String> dropdownEscalaoEtario) {
        for (String escalao: escaloes){
            dropdownEscalaoEtario.addItem(escalao);
        }
    }
}
