import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;
//    File menu items
    JMenuItem newFile, openFile, saveFile;
//    Edit menu items
    JMenuItem cut, copy, paste, selectAll, close;
    JTextArea textArea;
    TextEditor() {
//        Intialize a frame
        frame = new JFrame();

//        Initialize menubar
        menuBar = new JMenuBar();
//        Initialize text Area
        textArea = new JTextArea();

//        Initialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit");

//        Initialise file menu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

//        Add action listeners to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

//        Add file menu item
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

//        Initialise edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

//        Add action listeners to file menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

//        Add edit menu items
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        menuBar.add(file);
        menuBar.add(edit);

//        Set menubar to frame
        frame.setJMenuBar(menuBar);
////      Add text area to frame
//        frame.add(textArea);

//        Create content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
//        Add text area to panel
        panel.add(textArea, BorderLayout.CENTER);
//        Create scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        Add scroll pane to panel
        panel.add(scrollPane);
//        Add panel to frame
        frame.add(panel);

//        Set dimensions of frame
        frame.setBounds(200, 200, 400, 600);
        frame.setVisible(true);
        frame.setLayout(null);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == cut) {
//          perform cut operation
            textArea.cut();
        }
        if(actionEvent.getSource() == copy) {
//          perform the operation
            textArea.copy();
        }
        if(actionEvent.getSource() == paste) {
//          perform the operation
            textArea.paste();
        }
        if(actionEvent.getSource() == selectAll) {
//          perform the operation
            textArea.selectAll();
        }
        if(actionEvent.getSource() == close) {
//          perform the operation
            System.exit(0);
        }
        if(actionEvent.getSource() == openFile) {
//            Open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
//            If clicked the open button
            if(chooseOption == JFileChooser.APPROVE_OPTION) {
//                Getting selected file
                File file = fileChooser.getSelectedFile();
//                Get the path of selected file
                String filePath = file.getPath();
                try {
//                    Initialize file reader
                    FileReader fileReader = new FileReader(filePath);
//                    Initialize buffered reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
//                    Read contents of file line by line
                    while((intermediate = bufferedReader.readLine()) != null) {
                        output += intermediate + "\n";
                    }
//                    Set the output string to text area
                    textArea.setText(output);
                } catch(IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

        if(actionEvent.getSource() == saveFile) {
//            Initialize file picker
            JFileChooser fileChooser = new JFileChooser("C:");
//            Get choose option from file server
            int chooseOption = fileChooser.showSaveDialog(null);
//            Check if we clicked on save button
            if(chooseOption == JFileChooser.APPROVE_OPTION) {
//                Create a new file with chosen directory path & file
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");
                try{
//                    Initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
//                    Initialize buffered writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//                    Write contents  of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                } catch(IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

        if(actionEvent.getSource() == newFile) {
            TextEditor newTextEditor = new TextEditor();
        }
    }
    public static void main(String[] args) {

        TextEditor textEditor = new TextEditor();

    }
}