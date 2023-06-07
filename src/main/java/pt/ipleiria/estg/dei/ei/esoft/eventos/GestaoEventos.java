package pt.ipleiria.estg.dei.ei.esoft.eventos;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import pt.ipleiria.estg.dei.ei.esoft.*;
import pt.ipleiria.estg.dei.ei.esoft.atletas.GestaoAtletas;
import pt.ipleiria.estg.dei.ei.esoft.calendario.CalendarioEventos;
import pt.ipleiria.estg.dei.ei.esoft.provas.GestaoProvas;
import pt.ipleiria.estg.dei.ei.esoft.resultados.Resultados;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;

public class GestaoEventos extends JFrame{
    private JPanel mainPanel;
    private JPanel topPanel;
    private JButton btnEventos;
    private JButton btnAtletas;
    private JButton btnResultados;
    private JButton btnCalendario;
    private JPanel bodyPanel;
    private JPanel adicionarEventoPanel;
    private JButton btnCriarEvento;
    private JButton btnSetaAtras;
    private JButton espaco;
    private JButton btnImportFile;
    private JPanel importarFicheiroPanel;
    private JPanel tablePanel;
    private JScrollPane tableScrollPlane;
    private JButton espacoDir;
    private JTable eventosTable;
    private JButton espacoTopo;
    private JButton espacoEsq;
    private JButton espacoBaixo;
    private JFileChooser fileChooser;
    public GestaoEventos(String title){
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setResizable(false);
        pack();

        btnAtletas.addActionListener(this::btnAtletasActionPerformed);
        btnResultados.addActionListener(this::btnResultadosActionPerformed);
        btnCalendario.addActionListener(this::btnCalendarioActionPerformed);
        btnSetaAtras.addActionListener(this::btnSetaAtrasActionPerformed);
        btnCriarEvento.addActionListener(this::btnCriarEventoActionPerformed);
        btnImportFile.addActionListener(this::btnImportarEventosActionPerformed);

        configurarScrollPlane(tableScrollPlane);
        //configurartabelaEventos(eventosTable);

    }

    public static void abrirPaginaGestaoEventos(){
        new GestaoEventos("Gestão Eventos").setVisible(true);
    }

    private void configurarScrollPlane(JScrollPane plane) {
        plane.getViewport().setBackground(new Color(23,23,23));
        plane.setBorder(null);
    }

    private void configurartabelaEventos (JTable tabela){

        // TODO - PASSAR LISTA EVENTOS PARA MODELO DA TABELA
        // TODO - LER FICHEIRO JSON E COLOCAR EVENTOS EM LISTA

        //ModeloTabelaEventos modeloTabelaEventos = new ModeloTabelaEventos();
        //tabela.setModel(modeloTabelaEventos);
        tabela.setAutoCreateRowSorter(true);
        tabela.getTableHeader().setOpaque(false);
        //header
        JTableHeader header= tabela.getTableHeader();
        header.setBackground(new Color(37,37,37));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Inter",Font.BOLD,18));
        Border border = BorderFactory.createLineBorder(new Color(23,23,23));
        header.setBorder(border);
        UIManager.getDefaults().put("TableHeader.cellBorder", border);
        //desabilitar mexer colunas e tamanho
        header.setReorderingAllowed(false);
        header.setResizingAllowed(false);
        // alinhar meio
        ((DefaultTableCellRenderer)tabela.getDefaultRenderer(String.class)).setHorizontalAlignment(JLabel.CENTER);
        //popup quando cell cliacada
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem menuItemEditar = new JMenuItem("Editar Evento");
        JMenuItem menuItemEliminar = new JMenuItem("Eliminar Evento");
        JMenuItem menuItemProvas = new JMenuItem("Ver Provas");

        mudarCorItemPopupMenu(menuItemEditar);
        mudarCorItemPopupMenu(menuItemEliminar);
        mudarCorItemPopupMenu(menuItemProvas);

        popupMenu.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        popupMenu.add(menuItemEditar);
        popupMenu.add(menuItemEliminar);
        popupMenu.add(menuItemProvas);

        // action listener para items menu

        menuItemEditar.addActionListener(this::menuItemEditarActionPerformed);
        menuItemEliminar.addActionListener(this::menuItemEliminarActionPerformed);
        menuItemProvas.addActionListener(this::menuItemProvasActionPerformed);


        //adicionar popup menu tabela

        tabela.setComponentPopupMenu(popupMenu);
        tabela.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                // TODO - SELECIONAR EVENTO CLICADO
                Point point = e.getPoint();
                int currentRow = tabela.rowAtPoint(point);
                tabela.setRowSelectionInterval(currentRow, currentRow);
            }
        });

    }

    private void menuItemEditarActionPerformed (ActionEvent actionEvent){
        // TODO - PREENCHER VALORES COM OS DO EVENTO
        EditarEvento.abrirPaginaEditarEvento();
        this.dispose();
    }

    private void menuItemEliminarActionPerformed (ActionEvent actionEvent){
        // TODO - ELIMINAR EVENTO
    }

    private void menuItemProvasActionPerformed (ActionEvent actionEvent){
        // TODO - Abrir Provas do Evento
        GestaoProvas.abrirPaginaGestaoProvas();
        this.dispose();
    }

    private void mudarCorItemPopupMenu (JMenuItem item){
        item.setBackground(new Color(37,37,37));
        item.setForeground(Color.WHITE);
        item.setFont(new Font("Inter", Font.BOLD, 14));
    }

    private void btnCriarEventoActionPerformed(ActionEvent actionEvent) {
        CriarEvento.abrirPaginaCriarEvento();
        this.dispose();
    }

    private void btnSetaAtrasActionPerformed(ActionEvent actionEvent) {
        JanelaPrincipal.abrirJanelaPrincipal();
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

    private void btnImportarEventosActionPerformed(ActionEvent actionEvent){
        fileChooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Ficheiros JSON","json");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileHidingEnabled(false);
        fileChooser.setFileFilter(filter);

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            java.io.File file = fileChooser.getSelectedFile();
            System.out.println(file);
            FileReader reader = lerFicheiroJSON(file);

            if (reader == null || !escreverFicheiroJSONImportado(reader)){
                //TODO - POPUP MENSAGEM ERRO
            }

            //TODO - CONCATENAR NO FICHEIRO AONDE ESTAO GUARDADO OS EVENTOS
        }

    }

    private FileReader lerFicheiroJSON(java.io.File file) {

        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(file)) {
            return reader;
        } catch (IOException e) {
            return null;
        }
    }

    private boolean escreverFicheiroJSONImportado(FileReader reader){
        JSONParser parser = new JSONParser();

        JSONArray jsonArray = null;
        try {
            jsonArray = (JSONArray) parser.parse(reader);

            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;

                // Acessando os campos dinamicamente
                for (Object key : jsonObject.keySet()) {
                    Object value = jsonObject.get(key);
                    // TODO - CONCATENAR VALORES AO FICHEIRO APP EVENTOS
                }

            }

        } catch (IOException | ParseException e) {
            return false;
        }

        return true;
    }

}
