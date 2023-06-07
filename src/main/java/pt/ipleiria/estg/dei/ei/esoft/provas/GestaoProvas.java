package pt.ipleiria.estg.dei.ei.esoft.provas;

import pt.ipleiria.estg.dei.ei.esoft.atletas.GestaoAtletas;
import pt.ipleiria.estg.dei.ei.esoft.calendario.CalendarioEventos;
import pt.ipleiria.estg.dei.ei.esoft.eventos.GestaoEventos;
import pt.ipleiria.estg.dei.ei.esoft.resultados.Resultados;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;

public class GestaoProvas extends JFrame{
    private JPanel mainPanel;
    private JPanel topPanel;
    private JButton btnEventos;
    private JButton btnAtletas;
    private JButton btnResultados;
    private JButton btnCalendario;
    private JPanel bodyPanel;
    private JPanel adicionarEventoPanel;
    private JButton btnCriarProva;
    private JButton btnSetaAtras;
    private JButton espaco;
    private JPanel importarFicheiroPanel;
    private JButton btnImportFile;
    private JFileChooser fileChooser;

    public GestaoProvas(String title){
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setResizable(false);
        pack();

        btnEventos.addActionListener(this::btnEventosActionPerformed);
        btnAtletas.addActionListener(this::btnAtletasActionPerformed);
        btnResultados.addActionListener(this::btnResultadosActionPerformed);
        btnCalendario.addActionListener(this::btnCalendarioActionPerformed);
        btnSetaAtras.addActionListener(this::btnSetaAtrasActionPerformed);
        btnCriarProva.addActionListener(this::btnCriarProvaActionPerformed);
        btnImportFile.addActionListener(this::btnImportarProvasActionPerformed);

    }

    public static void abrirPaginaGestaoProvas(){
        new GestaoProvas("Gestão Provas").setVisible(true);
    }

    private void btnCriarProvaActionPerformed(ActionEvent actionEvent) {
        CriarProva.abrirPaginaCriarProva();
        this.dispose();
    }

    private void abrirPaginaEventos(){
        GestaoEventos.abrirPaginaGestaoEventos();
        this.dispose();
    }

    private void btnSetaAtrasActionPerformed(ActionEvent actionEvent) {
        abrirPaginaEventos();
    }


    private void btnEventosActionPerformed (ActionEvent actionEvent){
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

    private void btnImportarProvasActionPerformed(ActionEvent actionEvent) {
        fileChooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".json","json");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(filter);
        fileChooser.showSaveDialog(null);
    }
}
