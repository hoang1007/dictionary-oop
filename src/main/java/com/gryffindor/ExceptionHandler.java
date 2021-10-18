package com.gryffindor;

import com.gryffindor.frontend.entities.AlertDialog;
import javafx.application.Platform;
import javafx.scene.control.Alert.AlertType;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/** Nơi nhận các ngoại lệ của chương trình và hiển thị thông báo tới người dùng. */
public class ExceptionHandler extends TimerTask implements Thread.UncaughtExceptionHandler {
  private final BlockingQueue<Exception> queue;
  Timer timer = new Timer();

  /** Khởi tạo. */
  public ExceptionHandler() {
    queue = new LinkedBlockingQueue<>();

    timer.schedule(this, 0, 1000);
  }

  public void add(Exception e) {
    queue.offer(e);
  }

  @Override
  public void run() {
    if (!queue.isEmpty()) {
      Platform.runLater(() -> new AlertDialog(AlertType.ERROR).show(queue.poll()));
    }
  }

  @Override
  public void uncaughtException(Thread t, Throwable e) {
    if (e instanceof IndexOutOfBoundsException) {
      // ignore this exception
    } else if (e instanceof NullPointerException) {
      // ignore this exception
    } else {
      Platform.runLater(() -> new AlertDialog(AlertType.ERROR).setContent(e.getMessage()).show());
      e.printStackTrace();
    }
  }

  public void free() {
    timer.cancel();
  }
}
