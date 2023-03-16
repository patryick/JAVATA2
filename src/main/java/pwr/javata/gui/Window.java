package pwr.javata.gui;

import pwr.javata.loader.DirectoryLoader;
import pwr.javata.loader.FileDataLoader;
import pwr.javata.loader.FileDataLoaderImpl;
import pwr.javata.model.FileData;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Window extends JFrame {

    private final JList<String> listOfFiles = new JList<>();
    private final JList<String> listOfDirectories = new JList<>();
    private final JTextArea dataTextField = new JTextArea();
    private final JLabel stateLabel = new JLabel("state: ", SwingConstants.CENTER);
    private String currentDirectory = "";
    FileDataLoader fileDataLoader = new FileDataLoaderImpl();
    JScrollPane filesScrollPane = new JScrollPane();
    JScrollPane directoriesScrollPane = new JScrollPane();

    public Window() throws IOException {

        this.setName("Application");
        this.setVisible(true);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());


        this.filesScrollPane.setViewportView(listOfFiles);
        this.listOfFiles.setLayoutOrientation(JList.VERTICAL);
        this.filesScrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
        this.filesScrollPane.setPreferredSize(new Dimension(300, 200));

        this.directoriesScrollPane.setViewportView(listOfDirectories);
        this.listOfDirectories.setLayoutOrientation(JList.VERTICAL);
        this.directoriesScrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
        this.directoriesScrollPane.setPreferredSize(new Dimension(300, 200));

        this.dataTextField.setPreferredSize(new Dimension(750, 200));
        this.dataTextField.setBorder(BorderFactory.createLineBorder(Color.black));

        this.stateLabel.setPreferredSize(new Dimension(800, 30));

        this.add(filesScrollPane);
        this.add(directoriesScrollPane);
        this.add(dataTextField);
        this.add(stateLabel);

        DefaultListModel<String> directoriesModel = new DefaultListModel<>();
        DirectoryLoader directoryLoader = new DirectoryLoader(new File("data"));
        directoriesModel.addAll(List.of(directoryLoader.readDirectory()));
        listOfDirectories.setModel(directoriesModel);
        listOfDirectories.addListSelectionListener(e -> {
            currentDirectory = listOfDirectories.getSelectedValue();


            DefaultListModel<String> filesModel = new DefaultListModel<>();
            listOfFiles.setModel(filesModel);
            DirectoryLoader fileDirectoryLoader = new DirectoryLoader(new File("data\\" + currentDirectory));
            filesModel.addAll(List.of(fileDirectoryLoader.readDirectory()));
        });

        listOfFiles.addListSelectionListener(e -> {
            FileData fileData = null;
            if (!e.getValueIsAdjusting()) {
                try {
                    if (listOfFiles.getSelectedValue() != null) {
                        fileData = fileDataLoader.loadFileData("data\\" + currentDirectory + "\\" + listOfFiles.getSelectedValue());
                    }
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                if (fileData != null) {
                    String str = fileData.getText();
                    dataTextField.setText(str);
                    stateLabel.setText("state: " + fileData.getState().toString());
                }
                else {
                    dataTextField.setText("");
                    stateLabel.setText("");
                }
            }
        });
    }

}
