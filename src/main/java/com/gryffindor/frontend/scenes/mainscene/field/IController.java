package com.gryffindor.frontend.scenes.mainscene.field;

import javafx.stage.FileChooser;
import java.awt.Desktop;

public interface IController {
    Desktop desktop = Desktop.getDesktop();
    FileChooser fileChooser = new FileChooser();
}
