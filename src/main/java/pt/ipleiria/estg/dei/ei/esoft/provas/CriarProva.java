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
    private JRadioButton masculinoRadioButton;
    private JRadioButton femininoRadioButton;
    private JPanel radioBtnPanel;
    private JLabel genero;
    private JLabel escalaoEtario;
    private JPanel dropdownPanel;
    private JComboBox<String> dropdownEscalaoEtario;

    private final String[] escaloes = {"Varios","Bejamins", "Infantis", "Iniciados", "Juvenis", "Cadetes", "Juniores", "Sub23", "Seniores","Veteranos"};

    public CriarProva(String title){
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

        fillComboBoxEscaloes(dropdownEscalaoEtario);
        dropdownEscalaoEtario.setEditable(true);
        dropdownEscalaoEtario.setSelectedItem("Selecione um escalão");

    }

    public static void abrirPaginaCriarProva (){
        new CriarProva("Criar Prova").setVisible(true);
    }

    private void abrirPaginaProvas() {
        GestaoProvas.abrirPaginaGestaoProvas();
        this.dispose();
    }
    private void btnCriarActionPerformed(ActionEvent actionEvent) {
        // TODO: CRIAR PROVA
        abrirPaginaProvas();
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
