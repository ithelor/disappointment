package sample;

import javafx.fxml.FXML;

import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.awt.*;
import java.io.*;

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
        BufferedReader in = null;
        ArrayList<String> stringArrayList = new ArrayList<>();
        boolean write = false;

        try {
            in = new BufferedReader(new FileReader(file));
            while ((str = in.readLine()) != null) {

                if (write == true) {
                    if (!str.contains("<div>")) {
                        str = str.replaceAll("\\s+", "");
                        str = str.replaceAll("\\p{Ll}+", "");
                        str = str.replaceAll("\\Q.\\E</>", "");
                        str = str.replaceAll("<>", "");
                        str = str.replaceAll(",", ".");
                        str = str.replaceAll("\\Q$\\E", "");
                        str = str.replaceAll("\\p{Lu}+", "");
                        str = str.replaceAll("</>", "");

                        System.out.println("Найдено значение: " + str);

                        textArea.appendText(str + "\n");
                        stringArrayList.add(str);
                        write = false;
                    }
                } else {


                    String pattern = "<td class=\"wht_total \">";

                    // Создание Pattern объекта
                    Pattern r = Pattern.compile(pattern);

                    // Создание matcher объекта
                    Matcher m = r.matcher(str);
                    if (m.find()) {
                        write = true;
                    }
                }
            }
        } catch (IOException e) {
        } finally {
            try { in.close(); } catch (Exception ex) { }
        }

        System.out.println(stringArrayList);

        double sum = 0;
        int i = 0;
        for (i = 0; i < stringArrayList.size(); i++)
            if (stringArrayList.get(i) != "") {
                System.out.println(stringArrayList.get(i));
                sum += Double.parseDouble(stringArrayList.get(i));
            }

        System.out.println("Your disappointment: " + sum + ". Number of transactions: " + i);
        resultField.setText(String.valueOf(sum));

//        System.out.println(stringLinkedList);

//        String str_n = "19 Mar, 2021";
//        String pattern = "^\\d{2} \\p{Lu}\\p{Ll}{2}, \\d{4}";

//        String pattern = "<td class=\"wht_total \">";
//        Pattern r = Pattern.compile(pattern);
//        boolean write = false;
//        AtomicReference<Matcher> m = new AtomicReference<>();
//
//        ArrayList<String> whtPreFinal = new ArrayList<>();

//        stringArrayList.forEach((temp) -> {
//
//            if (write == true) {
//                whtPreFinal.add(temp);
//                write = false;
//            }
//            else {
//
////            System.out.println(temp);
//
//                m.set(r.matcher(temp));
//                if (m.get().find()) {
//                    System.out.println("Найдено значение: " + temp);
//                }
//
//            }
//
//        });

//        String str_n = "19 Mar, 2021";
//        String pattern = "^\\d{2} \\p{Lu}\\p{Ll}{2}, \\d{4}$";
//
//        // Создание Pattern объекта
//        Pattern r = Pattern.compile(pattern);
//
//        // Создание matcher объекта
//        var ref = new Object() {
//            Matcher m;
//        };
//
//        stringLinkedList.forEach((temp) -> {
//
//            System.out.println(temp);
//
//            ref.m = r.matcher(temp);
//            if (ref.m.find( )) {
//                System.out.println("Найдено значение: " + ref.m.group(0));
//            }
//
//        });

    }

}
