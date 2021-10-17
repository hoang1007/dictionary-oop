package com.gryffindor;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import com.gryffindor.backend.entities.Word;
import com.gryffindor.backend.utils.TextUtils;

import java.io.IOException;
import java.io.InputStreamReader;

public class ResourcesManager {
  /** Tải dữ liệu gợi ý nếu thực hiện tìm kiếm online. */
  public void loadSuggest() {
    try {
      JsonElement element = JsonParser.parseReader(
          new InputStreamReader(DictionaryApplication.INSTANCE.config.getSuggestDataStream()));

      String[] suggest = new Gson().fromJson(element, String[].class);

      for (String target : suggest) {
        DictionaryApplication.INSTANCE.getDictionaryManagement()
            .getDictionary().addWord(new Word(target));
      }
    } catch (Exception e) {
      System.out.println("Unexpected error while load suggestion");
    }
  }

  /** Giải phóng các tài nguyên sau khi kết thúc chương trình. */
  public void free() {
    TextUtils.free();

    // Viết lại file dữ liệu sau khi người dùng thực hiện các sửa đổi
    if (DictionaryApplication.INSTANCE.getStatus().equals(Status.OFFLINE)) {
      try {
        System.out.println("Writing change to datafile...");
        DictionaryApplication.INSTANCE.dictionaryManagement.exportToJson();
      } catch (IOException e) {
        DictionaryApplication.INSTANCE.exceptionHandler.add(e);
      }
    }
  }
}
