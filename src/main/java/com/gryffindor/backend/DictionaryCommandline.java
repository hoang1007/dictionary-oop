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

    /**
     * In toàn bộ từ trong từ điển.
     */
    public void showAllWords() {
        System.out.println("No | English | Vietnamese");
        int i = 1;

        for (Word w : dictionaryManagement.dictionary.getAllWords()) {
            System.out.println(String.format("%d | %s | %s", i++, w.getWordTarget(), w.getWordExplain()));
        }
    }

    /**
     * Từ điển cơ bản.
     */
    public void dictionaryBasic() {
        dictionaryManagement.insertFromCommandline();

        showAllWords();
    }

    /**
     * Từ điển nâng cao.
     */
    public void dictionaryAdvance() {
        dictionaryManagement.insertFromFile();

        Scanner scannerTarget = new Scanner(System.in);

        menuCommandline();
        String target = scannerTarget.nextLine();

        while (!target.equals("-1")) {
            if (target.equals("1")) {
                String start = "Y";

                while (true) {
                    dictionaryManagement.dictionaryLookup();
                    System.out.println("Bạn có muốn tiếp tục tìm kiếm từ (Y/N): ");
                    start = scannerTarget.nextLine();
                    if (!start.toUpperCase().equals("Y")) {
                        break;
                    }
                }

            } else if (target.equals("2")) {
                dictionaryManagement.updateWordFromCommandline();
                System.out.println("Chúng tôi rất cảm ơn những đóng góp của bạn.");
            } else if (target.equals("3")) {
                dictionaryManagement.insertFromCommandline();
                System.out.println("Chúng tôi rất cảm ơn những đóng góp của bạn.");
            } else if (target.equals("4")) {
                showAllWords();
                System.out.println("Enter để tiếp tục.");
                target = scannerTarget.nextLine();
            } else {
                dictionaryManagement.dictionaryExportToFile();
            }

            menuCommandline();
            target = scannerTarget.nextLine();
        }

        System.out.println("Tạm biệt. Rất hân hạnh được đồng hành cùng bạn.");
        scannerTarget.close();
    }

    /**
     * Menu trong từ điển nâng cao.
     */
    public void menuCommandline() {
        System.out.println("Bạn muốn xử dụng chức năng nào ?");
        System.out.println("1. Tìm kếm từ.");
        System.out.println("2. Sửa nghĩa của từ.");
        System.out.println("3. Đóng góp từ mới vào từ điển.");
        System.out.println("4. Xem toàn bộ từ trong từ điển.");
        System.out.println("5. Xuất file toàn bộ từ trong từ điển.");
        System.out.println("-1. Thoát chương trình.");
        System.out.println("Mời bạn nhâp giá trị tương ứng với hành động: ");
    }

    /**
     * Tìm list các từ có điểm chung. Ex: Khi nhập tra tìn các từ translate,
     * transformation.
     * 
     * @return List<Word>
     */
    public List<Word> dictionarySearch() {
        System.out.println("Nhập từ cần tra: ");
        Scanner scanner = new Scanner(System.in);
        String word_target = scanner.nextLine();

        scanner.close();
        return dictionaryManagement.dictionary.searchWords(word_target);
    }

}
