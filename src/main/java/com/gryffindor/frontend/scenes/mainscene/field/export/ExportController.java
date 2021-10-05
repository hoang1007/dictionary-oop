package com.gryffindor.frontend.scenes.mainscene.field.export;

import com.gryffindor.frontend.scenes.mainscene.field.IController;
import com.gryffindor.DictionaryApplication;
import com.gryffindor.backend.entities.Word;

import java.util.List;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ExportController implements IController {

    // Khởi tạo
    public ExportController() {
        initialize();
        addExtensionFilter();
    }

    // Khởi tạo đường dân mặc định khi click vào button export
    public void initialize() {
        // String dic = "C:/";
        // IController.fileChooser.setInitialDirectory(new File(dic));
    }

    public void setTitleAndFileName(String title, String nameOfFile) {
        IController.fileChooser.setInitialFileName(nameOfFile);
        IController.fileChooser.setTitle(title);
    }

    public void addExtensionFilter() {
        // Định dạng file save
        ExtensionFilter txt = new ExtensionFilter("TEXT files", "*.txt");
        // ExtensionFilter pdf = new ExtensionFilter("PDF", "*.pdf");
        ExtensionFilter allFile = new ExtensionFilter("All Files", "*.*");
        IController.fileChooser.getExtensionFilters().addAll(txt, allFile);
    }

    // export to file
    public void export() {
        setTitleAndFileName("Save file", "file_name");

        File file = IController.fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            System.out.println("Start saving file");
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(file);

                // get All Word in dictionary
                List<Word> ls = DictionaryApplication.INSTANCE.getDictionaryManagement().getDictionary().getAllWords();
                ls.add(new Word("father", "bo"));
                ls.add(new Word("grandfather", "ong"));
                ls.add(new Word("mother", "me"));
                ls.add(new Word("nephew", "chau trai"));
                int list_size = ls.size();
                for (int i = 0; i < list_size; i++) {
                    pw.write(ls.get(i).toString());
                    pw.write("\n");
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                pw.close();
            }
            System.out.println("You saved file '" + file.getName() + "' to '" + file.getPath() + "'");
            System.out.println("Finish saving file");
        } else {
            System.out.println("Failed saving file");
        }
    }

}
