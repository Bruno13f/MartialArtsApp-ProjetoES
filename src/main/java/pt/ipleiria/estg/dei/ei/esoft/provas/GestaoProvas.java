package pt.ipleiria.estg.dei.ei.esoft.provas;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pt.ipleiria.estg.dei.ei.esoft.atletas.GestaoAtletas;
import pt.ipleiria.estg.dei.ei.esoft.calendario.CalendarioEventos;
import pt.ipleiria.estg.dei.ei.esoft.eventos.GestaoEventos;
import pt.ipleiria.estg.dei.ei.esoft.resultados.Resultados;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.io.IOException;

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

    public void btnCriarProvaActionPerformed(ActionEvent actionEvent) {
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

    private void mostrarProvas(){
    }

    public void btnImportarProvasActionPerformed(ActionEvent actionEvent){
        fileChooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Ficheiros JSON","json");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileHidingEnabled(false);
        fileChooser.setFileFilter(filter);

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            java.io.File file = fileChooser.getSelectedFile();
            System.out.println(file);

            if (!escreverFicheiroJSON(file)){
                //TODO - POPUP MENSAGEM ERRO
                JOptionPane.showMessageDialog(mainPanel, "Não foi possivel importar o ficheiro");
            }

            JOptionPane.showMessageDialog(mainPanel, "Prova(s) importada(s)");
            mostrarProvas();
        }
    }

    private boolean escreverFicheiroJSON(java.io.File file) {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(file)) {

            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            System.out.println("Array: " + jsonArray.get(0));

            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;

                // Acessando os campos dinamicamente
                for (Object key : jsonObject.keySet()) {
                    Object value = jsonObject.get(key);

                    // TODO - CONCATENAR VALORES AO FICHEIRO APP EVENTOS

                    System.out.println("Chave: " + key);
                    System.out.println("Valor: " + value);
                }
            }

            return true;
        } catch (IOException | ParseException e) {
            //e.printStackTrace();
            return false;
        }
    }

    private void verificarAtletasProva (int id){

    }

    private void cancelarProvaJSON (int id){

    }
    private void eliminarProvaJSON (int id){

    }
}
