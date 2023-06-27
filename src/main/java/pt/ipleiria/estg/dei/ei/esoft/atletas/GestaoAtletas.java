package pt.ipleiria.estg.dei.ei.esoft.atletas;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pt.ipleiria.estg.dei.ei.esoft.EscalaoEtario;
import pt.ipleiria.estg.dei.ei.esoft.Genero;
import pt.ipleiria.estg.dei.ei.esoft.calendario.CalendarioEventos;
import pt.ipleiria.estg.dei.ei.esoft.JanelaPrincipal;
import pt.ipleiria.estg.dei.ei.esoft.eventos.Evento;
import pt.ipleiria.estg.dei.ei.esoft.eventos.ModeloTabelaEventos;
import pt.ipleiria.estg.dei.ei.esoft.resultados.Resultados;
import pt.ipleiria.estg.dei.ei.esoft.eventos.GestaoEventos;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class GestaoAtletas extends JFrame{
    private JPanel mainPanel;
    private JPanel topPanel;
    private JButton btnEventos;
    private JButton btnAtletas;
    private JButton btnResultados;
    private JButton btnCalendario;
    private JPanel bodyPanel;
    private JPanel adicionarAtletaPanel;
    private JButton btnCriarAtleta;
    private JButton btnSetaAtras;
    private JButton espaco;
    private JPanel importarFicheiroPanel;
    private JButton btnImportFile;
    private JPanel tablePanel;
    private JScrollPane tableScrollPlane;
    private JTable atletasTable;
    private JButton espacoDir;
    private JButton espacoTopo;
    private JButton espacoEsq;
    private JButton espacoBaixo;
    private JFileChooser fileChooser;

    public GestaoAtletas(String title){
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setResizable(false);
        pack();

        btnEventos.addActionListener(this::btnEventosActionPerformed);
        btnResultados.addActionListener(this::btnResultadosActionPerformed);
        btnCalendario.addActionListener(this::btnCalendarioActionPerformed);
        btnSetaAtras.addActionListener(this::btnSetaAtrasActionPerformed);
        btnCriarAtleta.addActionListener(this::btnCriarAtletaActionPerformed);
        btnImportFile.addActionListener(this::btnImportarAtletasActionPerformed);

        mostrarAtletas();

    }

    public static void abrirPaginaGestaoAtletas(){
        new GestaoAtletas("Gestão Atletas").setVisible(true);
    }

    private void configurarScrollPlane(JScrollPane plane) {
        plane.getViewport().setBackground(new Color(23,23,23));
        plane.setBorder(null);
    }

    private void mostrarAtletas(){
        configurarScrollPlane(tableScrollPlane);
        configurartabelaAtletas(atletasTable);
    }

    private void configurartabelaAtletas (JTable tabela){

        // TODO - PASSAR LISTA TABELA PARA MODELO DA TABELA
        // TODO - LER FICHEIRO JSON E COLOCAR TABELAS EM LISTA

        ModeloTabelaAtletas modeloTabelaAtletas = popuplarTabelaAtletas();
        System.out.println(modeloTabelaAtletas);
        if (modeloTabelaAtletas != null) {
            tabela.setModel(modeloTabelaAtletas);
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
        JMenuItem menuItemEditar = new JMenuItem("Editar Atleta");
        JMenuItem menuItemEliminar = new JMenuItem("Eliminar Atleta");
        JMenuItem menuItemAssociarProvas = new JMenuItem("Associar Prova");
        JMenuItem menuItemDesassociarProvas = new JMenuItem("Desassociar Prova");

        mudarCorItemPopupMenu(menuItemEditar);
        mudarCorItemPopupMenu(menuItemEliminar);
        mudarCorItemPopupMenu(menuItemAssociarProvas);
        mudarCorItemPopupMenu(menuItemDesassociarProvas);

        popupMenu.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        popupMenu.add(menuItemEditar);
        popupMenu.add(menuItemEliminar);
        popupMenu.add(menuItemAssociarProvas);
        popupMenu.add(menuItemDesassociarProvas);

        // action listener para items menu

        menuItemEditar.addActionListener(this::menuItemEditarActionPerformed);
        menuItemEliminar.addActionListener(this::menuItemEliminarActionPerformed);
        menuItemAssociarProvas.addActionListener(this::menuItemAssociarProvas);
        menuItemDesassociarProvas.addActionListener(this::menuItemDesassociarProvas);


        //adicionar popup menu tabela

        tabela.setComponentPopupMenu(popupMenu);
        tabela.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                // TODO - SELECIONAR EVENTO CLICADO
                Point point = e.getPoint();
                int currentRow = tabela.rowAtPoint(point);
                tabela.setRowSelectionInterval(currentRow, currentRow);
            }
        });

    }

    private void menuItemDesassociarProvas(ActionEvent actionEvent) {
        // TODO - DESASSOCIAR ATLETA
    }

    private void menuItemAssociarProvas(ActionEvent actionEvent) {
        // TODO - MOSTRAR PROVAS
        AssociarProva.abrirPaginaAssociarAtletaProva();
        this.dispose();
    }

    private void menuItemEliminarActionPerformed(ActionEvent actionEvent) {
        // TODO - ELIMINAR ATLETA
    }

    private void menuItemEditarActionPerformed(ActionEvent actionEvent) {
        // TODO - PREENCHER VALORES COM OS DO Atleta
        EditarAtleta.abrirPaginaEditarAtleta();
        this.dispose();
    }

    private ModeloTabelaAtletas popuplarTabelaAtletas(){
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("src/main/java/pt/ipleiria/estg/dei/ei/esoft/atletas/AtletasApp.json")) {
            // Faz o parsing do arquivo JSON
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            // Cria uma lista de Atletas
            java.util.List<Atleta> atletas = new ArrayList<>();

            // Itera sobre o array JSON e cria objetos Atleta
            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;

                // Extrai os valores do objeto JSON
                String nome = (String) jsonObject.get("nome");
                String dataNascimento = (String) jsonObject.get("dataNascimento");
                SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
                Date data = formatador.parse(dataNascimento);

                String generostr = (String) jsonObject.get("genero");
                Genero genero = Genero.valueOf(generostr);

                String pais = (String) jsonObject.get("nacionalidade");
                String modalidade = (String) jsonObject.get("modalidade");
                Float peso = Float.parseFloat((String) jsonObject.get("peso"));

                String contactoValue = (String) jsonObject.get("contacto");
                Integer contacto = Integer.parseInt(contactoValue);

                String escalaoEtarioString = (String) jsonObject.get("escalaoEtario");
                EscalaoEtario escalaoEtario = EscalaoEtario.valueOf(escalaoEtarioString);

                // Cria um objeto Atleta e adiciona à lista
                Atleta atleta = new Atleta(nome,pais,genero, modalidade, peso, data,contacto,escalaoEtario);
                atletas.add(atleta);
            }

            // Cria o modelo da tabela com a lista de Atletas

            return new ModeloTabelaAtletas(atletas);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void mudarCorItemPopupMenu (JMenuItem item){
        item.setBackground(new Color(37,37,37));
        item.setForeground(Color.WHITE);
        item.setFont(new Font("Inter", Font.BOLD, 14));
    }

    private void btnCriarAtletaActionPerformed(ActionEvent actionEvent) {
        CriarAtleta.abrirPaginaCriarAtletas();
        this.dispose();
    }

    private void btnSetaAtrasActionPerformed(ActionEvent actionEvent) {
        JanelaPrincipal.abrirJanelaPrincipal();
        this.dispose();
    }

    private void btnEventosActionPerformed(ActionEvent actionEvent) {
        GestaoEventos.abrirPaginaGestaoEventos();
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

    public void btnImportarAtletasActionPerformed(ActionEvent actionEvent){
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

            JOptionPane.showMessageDialog(mainPanel, "Atleta(s) importado(s)");
            mostrarAtletas();
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
}
