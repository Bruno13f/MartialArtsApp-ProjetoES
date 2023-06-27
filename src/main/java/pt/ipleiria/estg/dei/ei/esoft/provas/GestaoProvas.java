package pt.ipleiria.estg.dei.ei.esoft.provas;

import com.sun.jdi.Value;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pt.ipleiria.estg.dei.ei.esoft.EscalaoEtario;
import pt.ipleiria.estg.dei.ei.esoft.Genero;
import pt.ipleiria.estg.dei.ei.esoft.atletas.GestaoAtletas;
import pt.ipleiria.estg.dei.ei.esoft.calendario.CalendarioEventos;
import pt.ipleiria.estg.dei.ei.esoft.eventos.EditarEvento;
import pt.ipleiria.estg.dei.ei.esoft.eventos.Evento;
import pt.ipleiria.estg.dei.ei.esoft.eventos.GestaoEventos;
import pt.ipleiria.estg.dei.ei.esoft.eventos.ModeloTabelaEventos;
import pt.ipleiria.estg.dei.ei.esoft.resultados.Resultados;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.List;

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
    private JPanel tablePanel;
    private JTable provasTable;
    private JButton espacoDir;
    private JScrollPane tableScrollPlane;
    private JFileChooser fileChooser;

    private int idEvento;

    public GestaoProvas(String title, int idEvento){
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
        btnSetaAtras.addActionListener(this::btnSetaAtrasActionPerformed);
        btnCriarProva.addActionListener(this::btnCriarProvaActionPerformed);
        btnImportFile.addActionListener(this::btnImportarProvasActionPerformed);

        mostrarProvas();

    }

    public static void abrirPaginaGestaoProvas(int idEvento){
        new GestaoProvas("Gestão Provas", idEvento).setVisible(true);
    }

    public void btnCriarProvaActionPerformed(ActionEvent actionEvent) {
        CriarProva.abrirPaginaCriarProva(idEvento);
        this.dispose();
    }

    private void abrirPaginaEventos(){
        GestaoEventos.abrirPaginaGestaoEventos();
        this.dispose();
    }

    private void configurarScrollPlane(JScrollPane plane) {
        plane.getViewport().setBackground(new Color(23,23,23));
        plane.setBorder(null);
    }

    private void mostrarProvas(){
        configurarScrollPlane(tableScrollPlane);
        configurartabelaProvas(provasTable);
    }
    private void configurartabelaProvas (JTable tabela){

        // TODO - PASSAR LISTA EVENTOS PARA MODELO DA TABELA
        // TODO - LER FICHEIRO JSON E COLOCAR EVENTOS EM LISTA

        ModeloTabelaProvas modeloTabelaProvas = popuplarTabelaProvas();
        System.out.println(modeloTabelaProvas);
        if (modeloTabelaProvas != null) {
            tabela.setModel(modeloTabelaProvas);
        }
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
        header.setResizingAllowed(true);
        // alinhar meio
        ((DefaultTableCellRenderer)tabela.getDefaultRenderer(String.class)).setHorizontalAlignment(JLabel.CENTER);
        //popup quando cell cliacada
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem menuItemEditar = new JMenuItem("Editar Prova");
        JMenuItem menuItemEliminar = new JMenuItem("Eliminar Prova");
        JMenuItem menuItemHorario = new JMenuItem("Horário Combates");
        JMenuItem menuItemAtletas = new JMenuItem("Atletas");

        mudarCorItemPopupMenu(menuItemEditar);
        mudarCorItemPopupMenu(menuItemEliminar);
        mudarCorItemPopupMenu(menuItemHorario);
        mudarCorItemPopupMenu(menuItemAtletas);

        popupMenu.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        popupMenu.add(menuItemEditar);
        popupMenu.add(menuItemEliminar);
        popupMenu.add(menuItemHorario);
        popupMenu.add(menuItemAtletas);

        // action listener para items menu

        menuItemEditar.addActionListener(this::menuItemEditarActionPerformed);
        menuItemEliminar.addActionListener(this::menuItemEliminarActionPerformed);
        menuItemHorario.addActionListener(this::menuItemHorarioActionPerformed);
        menuItemAtletas.addActionListener(this::menuItemAtletasActionPerformed);

        //adicionar popup menu tabela

        tabela.setComponentPopupMenu(popupMenu);
        tabela.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    Point point = e.getPoint();
                    int currentRow = tabela.rowAtPoint(point);
                    tabela.setRowSelectionInterval(currentRow, currentRow);
                }
            }
        });

    }

    private ModeloTabelaProvas popuplarTabelaProvas(){
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("src/main/java/pt/ipleiria/estg/dei/ei/esoft/eventos/eventosApp.json")) {
            // Faz o parsing do arquivo JSON

            if (reader == null){
                return null;
            }

            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            // Cria uma lista de eventos
            java.util.List<Prova> provas = new ArrayList<>();

            JSONObject evento = (JSONObject) jsonArray.get(idEvento);

            if (evento.containsKey("provas")){

                JSONArray provasArray = (JSONArray) evento.get("provas");

                for (Object provaObj : provasArray) {

                    JSONObject provaJsonObj = (JSONObject) provaObj;

                    String categoria = (String) provaJsonObj.get("categoriaPeso");
                    String generoString = (String) provaJsonObj.get("genero");
                    String escalaoEtarioString = (String) provaJsonObj.get("escalaoEtario");

                    Genero genero = Genero.valueOf(generoString);
                    EscalaoEtario escalaoEtario = EscalaoEtario.valueOf(escalaoEtarioString);

                    Random random = new Random();
                    int hora = random.nextInt(24);
                    int minutos = random.nextInt(60);

                    Date horaInicio = new Date();
                    horaInicio.setHours(hora);
                    horaInicio.setMinutes(minutos);

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(horaInicio);
                    calendar.add(Calendar.MINUTE, minutos);

                    Date horaFinal = calendar.getTime();

                    Prova prova = new Prova(horaInicio,horaFinal,categoria,escalaoEtario,genero,null);
                    provas.add(prova);
                }
            }

            return new ModeloTabelaProvas(provas);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void menuItemEditarActionPerformed (ActionEvent actionEvent){
        // TODO - PREENCHER VALORES COM OS DA PROVA
        EditarProva.abrirPaginaEditarProva(idEvento,getLinha(actionEvent));
        this.dispose();
    }

    private int getLinha(ActionEvent actionEvent){
        JMenuItem menuItem = (JMenuItem) actionEvent.getSource();
        JPopupMenu popupMenu = (JPopupMenu) menuItem.getParent();
        JTable tabela = (JTable) popupMenu.getInvoker();
        int row = tabela.getSelectedRow();
        return row;
    }

    private void menuItemEliminarActionPerformed (ActionEvent actionEvent){
        // TODO - ELIMINAR PROVA
    }

    private void menuItemHorarioActionPerformed (ActionEvent actionEvent){
        // TODO - Abrir Página Horarios Prova
        CombatesProva.abrirPaginaCombatesProva(idEvento);
        this.dispose();
    }

    private void menuItemAtletasActionPerformed (ActionEvent actionEvent){
        // TODO - Abrir Página Atletas Prova
        AtletasProva.abrirPaginaAtletasProvas(idEvento);
        this.dispose();
    }

    private void mudarCorItemPopupMenu (JMenuItem item){
        item.setBackground(new Color(37,37,37));
        item.setForeground(Color.WHITE);
        item.setFont(new Font("Inter", Font.BOLD, 14));
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
