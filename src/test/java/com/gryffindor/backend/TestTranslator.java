package com.gryffindor.backend;

import com.gryffindor.Language;
import com.gryffindor.backend.api.GoogleTranslator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTranslator {
  @Test
  public void testTranslator() throws Exception {
    assertEquals(
        "Hoa", GoogleTranslator.translate("flower", Language.ENGLISH, Language.VIETNAMESE));
  }

  @Test
  public void testDetectTranslator() throws Exception {
    assertEquals("Hoa", GoogleTranslator.translate("flower", Language.DETECT, Language.VIETNAMESE));
  }
}
