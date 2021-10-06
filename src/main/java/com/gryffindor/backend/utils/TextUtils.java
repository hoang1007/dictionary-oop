package com.gryffindor.backend.utils;

import com.gryffindor.DictionaryApplication;

import java.io.File;
import java.util.Locale;

import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TextUtils {
  private static final Tesseract tesseract;
  private static Synthesizer synthesizer;

  static {
    tesseract = new Tesseract();
    tesseract.setDatapath(DictionaryApplication.INSTANCE.config.getTessdata());

    System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory");

    try {
      SynthesizerModeDesc dModeDesc = new SynthesizerModeDesc(Locale.US);
      Central.registerEngineCentral("com.sun.speech.freetts" + ".jsapi.FreeTTSEngineCentral");
      
      synthesizer = Central.createSynthesizer(dModeDesc);
      synthesizer.allocate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static String fromImage(String imgPath) throws TesseractException {
    return tesseract.doOCR(new File(imgPath));
  }

  /**
   * Text to speech.
   * 
   * @throws EngineStateError         State error
   * @throws AudioException           Audio error
   * @throws IllegalArgumentException arguments error
   * @throws InterruptedException     Interrupted error
   * @throws EngineException          engine error
   */
  public static void toSpeech(String content) 
      throws AudioException, EngineStateError, IllegalArgumentException, InterruptedException {
    synthesizer.resume();

    synthesizer.speakPlainText(content, null);

    synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
  }

  /** Free api. */
  public static void free() {
    try {
      synthesizer.deallocate();
    } catch (EngineException | EngineStateError e) {
      e.printStackTrace();
    }
  }

  public static String format(String arg) {
    return arg.replaceAll("[-_]", " ").trim();
  }

  public static String[] format(String[] args) {
    for (String arg : args) {
      arg = format(arg);
    }

    return args;
  }

  public static String empty() {
    return "";
  }
}
