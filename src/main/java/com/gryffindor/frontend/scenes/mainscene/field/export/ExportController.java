package com.gryffindor.frontend.scenes.mainscene.field.export;

import com.gryffindor.frontend.scenes.mainscene.field.IController;

import java.io.File;

import javafx.stage.FileChooser.ExtensionFilter;

public class ExportController implements IController {
    // Khởi tạo
    public static void initialize() {
        String dic = "C:\\";
        IController.fileChooser.setInitialDirectory(new File(dic));
    }

    public static void setTitleAndFileName(String title, String file_name) {
        IController.fileChooser.setInitialFileName(file_name);
        IController.fileChooser.setTitle(title);
    }

    public static void addExtensionFilter() {
        // Định dạng file save
        ExtensionFilter txt = new ExtensionFilter("TEXT files", "*.txt");
        ExtensionFilter pdf = new ExtensionFilter("PDF", "*.pdf");
        ExtensionFilter html = new ExtensionFilter("Web Page", "*.html");
        IController.fileChooser.getExtensionFilters().addAll(txt, pdf, html);

    }
}
