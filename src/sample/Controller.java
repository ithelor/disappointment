package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

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

    @FXML TextArea textArea;
    @FXML TextField sumField;
    @FXML TextField transactionsField;

    @FXML
    private void closeApp() {
        Platform.exit();
    }

    private File getFile() {

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame();

        FileDialog fd = new FileDialog(frame, "Choose a file", FileDialog.LOAD);
        fd.setDirectory(System.getProperty("user.dir") + "\\..");
        fd.setVisible(true);

        return new File(fd.getDirectory() + fd.getFile());
    }

    private void iterateFile(File file, ArrayList<String> stringArrayList) {

        String str;
        BufferedReader in;
        boolean makeRecord = false;

        try {

            in = new BufferedReader(new FileReader(file));
            while ((str = in.readLine()) != null) {

                if (makeRecord) {
                    if (!str.contains("<div>")) {

                        str = str.replaceAll("[^0-9,]", "");
                        str = str.replaceAll(",", ".");

                        textArea.appendText(str + "\n");
                        stringArrayList.add(str);

                        makeRecord = false;
                    }
                } else {

                    String pattern = "<td class=\"wht_total \">";

                    Pattern r = Pattern.compile(pattern);
                    Matcher m = r.matcher(str);

                    if (m.find())
                        makeRecord = true;
                }
            }
        } catch (IOException ignored) {}
    }

    @FXML
    private void openFile() {

        File file = getFile();

        ArrayList<String> stringArrayList = new ArrayList<>();
        iterateFile(file, stringArrayList);

        int transactions;
        double sum = 0;

        for (transactions = 0; transactions < stringArrayList.size(); transactions++)
            if (!stringArrayList.get(transactions).equals(""))
                sum += Double.parseDouble(stringArrayList.get(transactions));

        sumField.setText(String.valueOf(sum));
        transactionsField.setText(String.valueOf(transactions));
    }
}