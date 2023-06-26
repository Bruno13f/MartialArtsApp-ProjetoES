package pt.ipleiria.estg.dei.ei.esoft.provas;

import pt.ipleiria.estg.dei.ei.esoft.atletas.GestaoAtletas;
import pt.ipleiria.estg.dei.ei.esoft.calendario.CalendarioEventos;
import pt.ipleiria.estg.dei.ei.esoft.eventos.GestaoEventos;
import pt.ipleiria.estg.dei.ei.esoft.resultados.Resultados;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EditarProva extends JFrame{
    private JPanel mainPanel;
    private JPanel topPanel;
    private JButton btnEventos;
    private JButton btnAtletas;
    private JButton btnResultados;
    private JButton btnCalendario;
    private JPanel bodyPanel;
    private JPanel btnsPanel;
    private JButton btnConfirmar;
    private JButton btnCancelar;
    private JLabel espaco;
    private JPanel formPanel;
    private JPanel esqPanel;
    private JLabel genero;
    private JPanel radioBtnPanel;
    private JRadioButton masculinoRadioButton;
    private JRadioButton femininoRadioButton;
    private JPanel dirPanel;
    private JPanel categoriasPesoPanel;
    private JPanel textoPanel;
    private JLabel categoriasPeso;
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
    private JPanel meioPanel;
    private JLabel escalaoEtario;
    private JPanel dropdownPanel;
    private JComboBox dropdownEscalaoEtario;

    public EditarProva(String title){
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setResizable(false);
        pack();

        btnEventos.addActionListener(this::btnEventosActionPerformed);
        btnAtletas.addActionListener(this::btnAtletasActionPerformed);
        btnResultados.addActionListener(this::btnResultadosActionPerformed);
        btnCalendario.addActionListener(this::btnCalendarioActionPerformed);

        btnConfirmar.addActionListener(this::btnConfirmarActionPerformed);
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

    }

    public static void abrirPaginaEditarProva (){
        new CriarProva("Editar Prova").setVisible(true);
    }

    private void abrirPaginaProvas(){
        GestaoProvas.abrirPaginaGestaoProvas();
        this.dispose();
    }
    private void btnConfirmarActionPerformed(ActionEvent actionEvent) {
        // TODO: EDITAR PROVA

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
}
