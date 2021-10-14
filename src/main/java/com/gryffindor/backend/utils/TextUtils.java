package com.gryffindor.backend.utils;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;

import com.asprise.ocr.Ocr;
import com.gryffindor.DictionaryApplication;

import marytts.LocalMaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.util.data.audio.AudioPlayer;

public class TextUtils {
  private static final Ocr ocr;
  private static LocalMaryInterface marytts;
  private static AudioPlayer player;

  static {
    ///////////// Init image to text /////////////////////////////////
    Ocr.setUp();
    ocr = new Ocr();
    ocr.startEngine("eng", Ocr.SPEED_FAST);

    //////////// Init text to speech ////////////////////////

    try {
      marytts = new LocalMaryInterface();
    } catch (MaryConfigurationException e) {
      DictionaryApplication.INSTANCE.exceptionHandler.add(
          new Exception("Could not initialize voice"));
    }
  }

  public static String fromImage(String imgPath) {
    return fromImage(new File(imgPath));
  }

  public static String fromImage(File imgFile) {
    String s = ocr.recognize(new File[]{imgFile}, Ocr.RECOGNIZE_TYPE_ALL, Ocr.OUTPUT_FORMAT_PLAINTEXT);

    return format(s);
  }

  /**
   * Text to speech.
   * @throws SynthesisException
   * 
   * @throws EngineStateError         State error
   * @throws AudioException           Audio error
   * @throws IllegalArgumentException arguments error
   * @throws InterruptedException     Interrupted error
   * @throws EngineException          engine error
   */
  public static void toSpeech(String content) throws SynthesisException {
    stopSpeaking();

    try (AudioInputStream audio = marytts.generateAudio(content)) {
      player = new AudioPlayer(audio);

      player.setDaemon(true);
      player.start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void stopSpeaking() {
    if (player != null) {
      player.cancel();
    }
  }

  /** Free api. */
  public static void free() {
    stopSpeaking();
  }

  public static String format(String arg) {
    return arg.replaceAll("[-_\n]", " ").trim().toLowerCase();
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
