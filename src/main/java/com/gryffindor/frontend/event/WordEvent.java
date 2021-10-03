package com.gryffindor.frontend.event;

import com.gryffindor.backend.entities.Word;

import javafx.event.Event;
import javafx.event.EventType;

public class WordEvent extends Event {
  public static final EventType<WordEvent> EVENTTYPE = new EventType<>(Event.ANY, "WORD");

  private Word word;

  /** Constructor. */
  public WordEvent(Word word) {
    super(EVENTTYPE);
    this.word = word;
  }

  public Word getWord() {
    return word;
  }
}
