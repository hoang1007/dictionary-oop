package com.gryffindor.backend;

import com.gryffindor.backend.entities.Word;
import com.gryffindor.backend.utils.DictionaryManagement;

import java.util.List;
import java.util.Scanner;

public class DictionaryCommandline {
    private DictionaryManagement dictionaryManagement;

    public DictionaryCommandline() {
        dictionaryManagement = AppData.INSTANCE.dictionaryManagement;
    }

    public void showAllWords() {
        System.out.println("No | English | Vietnamese");
        int i = 1;
        for (Word w : dictionaryManagement.dictionary.getAllWords()) {
            System.out.println(String.format("%d | %s | %s", i++, w.getWordTarget(), w.getWordExplain()));
        }
    }

    public void dictionaryBasic() {
        dictionaryManagement.insertFromCommandline();

        showAllWords();
    }

    public void dictionaryAdvance() {
        dictionaryManagement.insertFromFile();

        showAllWords();

        dictionaryManagement.dictionaryLookup();

        dictionaryManagement.dictionaryExportToFile();
    }

    public List<Word> dictionarySearch() {
        System.out.println("Nhap tu can tra: ");
        Scanner scanner = new Scanner(System.in);
        String word_target = scanner.nextLine();

        scanner.close();
        return dictionaryManagement.dictionary.searchWords(word_target);
    }

}
