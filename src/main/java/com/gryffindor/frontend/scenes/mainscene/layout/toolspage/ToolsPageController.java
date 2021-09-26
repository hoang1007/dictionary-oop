package com.gryffindor.frontend.scenes.mainscene.layout.toolspage;

import com.gryffindor.frontend.scenes.mainscene.field.ExportField;
import com.gryffindor.frontend.scenes.mainscene.layout.IPageController;

public class ToolsPageController implements IPageController {
  ExportField exportField;

  public ToolsPageController(ToolsPage toolsPage) {
    exportField = toolsPage.getExportField();
  }
}
