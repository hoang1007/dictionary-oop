package com.gryffindor.backend.utils;

import com.asprise.ocr.Ocr;
import com.gryffindor.DictionaryApplication;
import marytts.LocalMaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.util.data.audio.AudioPlayer;

import javax.sound.sampled.AudioInputStream;
import java.io.File;
import java.io.IOException;

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

  /**
   * Lấy chữ trong ảnh.
   *
   * @param imgPath
   * @return String
   */
  public static String fromImage(String imgPath) {
    return fromImage(new File(imgPath));
  }

  /**
   * Phát hiện chữ trong ảnh.
   *
   * @param imgFile file ảnh
   * @return văn bản có trong ảnh
   */
  public static String fromImage(File imgFile) {
    String s =
        ocr.recognize(new File[] {imgFile}, Ocr.RECOGNIZE_TYPE_ALL, Ocr.OUTPUT_FORMAT_PLAINTEXT);

    return format(s);
  }

  /**
   * Chuyển văn bản thành giọng nói.
   *
   * @param content văn bản muốn chuyển
   * @throws SynthesisException lỗi khởi tạo audio
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

  /** Định dạng lại chuỗi. */
  public static String format(String arg) {
    return arg.replaceAll("[-_]", " ").trim();
  }

  /** Định dạng lại các chuỗi. */
  public static String[] format(String[] args) {
    for (String arg : args) {
      arg = format(arg);
    }

    return args;
  }

  /**
   * Xâu rỗng.
   *
   * @return String
   */
  public static String empty() {
    return "";
  }
}
