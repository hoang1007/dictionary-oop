package com.gryffindor.frontend.scenes.mainscene.field.export;

import com.gryffindor.frontend.scenes.mainscene.field.IController;

import java.io.File;

import javafx.stage.FileChooser.ExtensionFilter;

public class ExportController implements IController {

    // Khởi tạo
    public ExportController() {
        initialize();
        addExtensionFilter();
    }

    // Khởi tạo đường dân mặc định khi click vào button export
    public void initialize() {
        // String dic = "";
        // IController.fileChooser.setInitialDirectory(new File());
    }

    public void setTitleAndFileName(String title, String nameOfFile) {
        IController.fileChooser.setInitialFileName(nameOfFile);
        IController.fileChooser.setTitle(title);
    }

    public void addExtensionFilter() {
        // Định dạng file save
        ExtensionFilter txt = new ExtensionFilter("TEXT files", "*.txt");
        ExtensionFilter pdf = new ExtensionFilter("PDF", "*.pdf");
        ExtensionFilter allFile = new ExtensionFilter("All Files", "*.*");
        IController.fileChooser.getExtensionFilters().addAll(txt, pdf, allFile);
    }

    // export to file
    public void export() {
        setTitleAndFileName("Save file", "file_name");
        IController.fileChooser.showSaveDialog(null);
        // TO DO
    }

}
