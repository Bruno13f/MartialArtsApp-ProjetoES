package pt.ipleiria.estg.dei.ei.esoft.atletas;

import pt.ipleiria.estg.dei.ei.esoft.calendario.CalendarioEventos;
import pt.ipleiria.estg.dei.ei.esoft.eventos.GestaoEventos;
import pt.ipleiria.estg.dei.ei.esoft.resultados.Resultados;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EditarAtleta extends JFrame{
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
    private JPanel esquerdoPanel;
    private JLabel nome;
    private JTextField textNome;
    private JLabel local;
    private JTextField textLocal;
    private JLabel modalidade;
    private JTextField textModalidade;
    private JLabel genero;
    private JPanel generoPanel;
    private JRadioButton radioBtnMasculino;
    private JRadioButton radioBtnFeminino;
    private JPanel direitoPanel;
    private JLabel pais;
    private JComboBox paisesComboBox;
    private JLabel dataNascimento;
    private JTextField textDataNascimento;
    private JLabel contacto;
    private JTextField textContacto;

    public EditarAtleta(String title){
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

    public static void abrirPaginaEditarAtleta (){
        new EditarAtleta("Editar Atleta").setVisible(true);
    }

    private void abrirPaginaAtletas(){
        GestaoAtletas.abrirPaginaGestaoAtletas();
        this.dispose();
    }

    private void btnConfirmarActionPerformed(ActionEvent actionEvent) {
        // TODO: EDITAR ATLETA
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
}
