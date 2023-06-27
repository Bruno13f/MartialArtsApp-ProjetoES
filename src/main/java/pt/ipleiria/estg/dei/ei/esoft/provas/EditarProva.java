package pt.ipleiria.estg.dei.ei.esoft.provas;

import com.sun.tools.javac.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import pt.ipleiria.estg.dei.ei.esoft.atletas.GestaoAtletas;
import pt.ipleiria.estg.dei.ei.esoft.calendario.CalendarioEventos;
import pt.ipleiria.estg.dei.ei.esoft.eventos.GestaoEventos;
import pt.ipleiria.estg.dei.ei.esoft.resultados.Resultados;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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
    private JPanel meioPanel;
    private JLabel escalaoEtario;
    private JPanel dropdownPanel;
    private JComboBox dropdownEscalaoEtario;
    private final String[] escaloes = {"Bejamins", "Infantis", "Iniciados", "Juvenis", "Cadetes", "Juniores", "Sub23", "Seniores","Veteranos"};

    private final String[] rdbtns;

    private int idEvento;
    private int idProva;

    public EditarProva(String title, int idEvento, int idProva){
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

        Field[] fields = EditarProva.class.getDeclaredFields();
        List<String> nomesVariaveis = new ArrayList<>();

        for (Field field : fields) {
            if (field.getType() == JRadioButton.class) {
                nomesVariaveis.add(field.getName());
            }
        }

        rdbtns = nomesVariaveis.toArray(new String[0]);

        this.idEvento = idEvento;

        ButtonGroup groupGenero = new ButtonGroup();
        groupGenero.add(masculinoRadioButton);
        groupGenero.add(femininoRadioButton);

        grupoCategoriasPesoMasculino();
        grupoCategoriasPesoFeminino();

        fillComboBoxEscaloes(dropdownEscalaoEtario);
        dropdownEscalaoEtario.setEditable(true);

        configurarProva();

    }

    public static void abrirPaginaEditarProva (int idEvento, int idProva){
        new EditarProva("Editar Prova", idEvento, idProva).setVisible(true);
    }

    private void abrirPaginaProvas(){
        GestaoProvas.abrirPaginaGestaoProvas(idEvento);
        this.dispose();
    }

    private void configurarProva(){
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("src/main/java/pt/ipleiria/estg/dei/ei/esoft/eventos/eventosApp.json")) {
            // Faz o parsing do arquivo JSON
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            JSONObject evento = (JSONObject) jsonArray.get(idEvento);

            JSONArray provasArray = (JSONArray) evento.get("provas");

            JSONObject prova = (JSONObject) provasArray.get(idProva);

            String genero = (String) prova.get("genero");

            if (genero.contains("Masculino")){
                masculinoRadioButton.setSelected(true);
            }
            if (genero.contains("Feminino")){
                femininoRadioButton.setSelected(true);
            }

            String categoriaPeso = (String) prova.get("categoriaPeso");
            if (categoriaPeso.contains("-40")){
                CB40.setSelected(true);
            }else if(categoriaPeso.contains("-44")){
                CB44.setSelected(true);
            }else if(categoriaPeso.contains("-48")){
                CB48.setSelected(true);
            }else if(categoriaPeso.contains("-52")){
                CB52.setSelected(true);
            }else if(categoriaPeso.contains("-57")){
                CB57.setSelected(true);
            }else if(categoriaPeso.contains("-63")){
                CB63.setSelected(true);
            }else if(categoriaPeso.contains("-70")){
                CB70.setSelected(true);
            }else if(categoriaPeso.contains("+70")){
                CB70M.setSelected(true);
            }else if(categoriaPeso.contains("-78")){
                CB78.setSelected(true);
            }else if(categoriaPeso.contains("+78")){
                CB78M.setSelected(true);
            }

            if (categoriaPeso.contains("-38")){
                CB38.setSelected(true);
            }else if(categoriaPeso.contains("-42")){
                CB42.setSelected(true);
            }else if(categoriaPeso.contains("-46")){
                CB46.setSelected(true);
            }else if(categoriaPeso.contains("-50")){
                CB50.setSelected(true);
            }else if(categoriaPeso.contains("-55")){
                CB55.setSelected(true);
            }else if(categoriaPeso.contains("-60")){
                CB60.setSelected(true);
            }else if(categoriaPeso.contains("-66")){
                CB66.setSelected(true);
            }else if(categoriaPeso.contains("-73")){
                CB73.setSelected(true);
            }else if(categoriaPeso.contains("-81")){
                CB81.setSelected(true);
            }else if(categoriaPeso.contains("+81")){
                CB81M.setSelected(true);
            }else if(categoriaPeso.contains("-90")){
                CB90.setSelected(true);
            }else if(categoriaPeso.contains("+90")){
                CB90M.setSelected(true);
            }else if(categoriaPeso.contains("-100")){
                CB100.setSelected(true);
            }else if(categoriaPeso.contains("+100")){
                CB100M.setSelected(true);
            }

            dropdownEscalaoEtario.setSelectedItem(prova.get("escalaoEtario"));

        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private void fillComboBoxEscaloes(JComboBox<String> dropdownEscalaoEtario) {
        for (String escalao: escaloes){
            dropdownEscalaoEtario.addItem(escalao);
        }
    }
}
