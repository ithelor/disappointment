package sample;

import javafx.fxml.FXML;

import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.JFrame;
import java.awt.FileDialog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    @FXML MenuBar menuBar;
    @FXML MenuItem menuBtnOpen;
    @FXML TextArea textArea;
    @FXML TextField resultField;


    public void openFile() {

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("A window");

        FileDialog fd = new FileDialog(frame, "Choose a file", FileDialog.LOAD);

        String currentDirectory = System.getProperty("user.dir");
        System.out.println("Cur dir is " + currentDirectory);
        fd.setDirectory(currentDirectory + "\\..");
        fd.setVisible(true);
        String filename = fd.getFile();
        System.out.println("You chose " + filename);

        String path = fd.getDirectory() + fd.getFile();
        File file = new File(path);
        System.out.println(file);

        String str;
        BufferedReader in;
        ArrayList<String> stringArrayList = new ArrayList<>();
        boolean write = false;

        try {
            in = new BufferedReader(new FileReader(file));
            while ((str = in.readLine()) != null) {

                if (write) {
                    if (!str.contains("<div>")) {

                        str = str.replaceAll("[^0-9,]", "");
                        str = str.replaceAll(",", ".");

                        System.out.println(str);

                        textArea.appendText(str + "\n");
                        stringArrayList.add(str);

                        write = false;

                    }
                } else {

                    String pattern = "<td class=\"wht_total \">";

                    Pattern r = Pattern.compile(pattern);
                    Matcher m = r.matcher(str);

                    if (m.find())
                        write = true;

                }
            }
        } catch (IOException ignored) {

        }

        System.out.println(stringArrayList);

        double sum = 0;
        int i;

        for (i = 0; i < stringArrayList.size(); i++)
            if (!stringArrayList.get(i).equals("")) {

                System.out.println(stringArrayList.get(i));
                sum += Double.parseDouble(stringArrayList.get(i));

            }

        System.out.println("Your disappointment: " + sum + ". Number of transactions: " + i);
        resultField.setText(String.valueOf(sum));

    }

}
