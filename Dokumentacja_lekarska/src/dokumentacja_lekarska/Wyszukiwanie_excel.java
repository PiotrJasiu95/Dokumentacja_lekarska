/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dokumentacja_lekarska;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author Piotr
 */
public class Wyszukiwanie_excel {

    List<Integer> colNr;
    List<Integer> rowNr;
    List<Integer> test;
    List<String> opis;
    List<Boolean> dlugi;
    char[] text = {'t', 'e', 'k', 'd', 's', 't', 't', 'e'};
    char[] szukanie = {'t', 'e', 's', 't'};

    public Wyszukiwanie_excel() {
        this.colNr = new ArrayList<Integer>();
        this.rowNr = new ArrayList<Integer>();
        this.test = new ArrayList<Integer>();
        this.opis = new ArrayList<String>();
        this.dlugi = new ArrayList<Boolean>();

    }

    public void przeszukajPlik(HSSFSheet sheet, String slowoKluczowe) {

        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    if (cell.getRichStringCellValue().getString().trim().equals(slowoKluczowe)) {
                        this.colNr.add(cell.getColumnIndex() + 1);
                        this.rowNr.add(cell.getRowIndex() + 1);
                        this.opis.add("Brak Opisu");
                        this.dlugi.add(false);
                    } else if (cell.getRichStringCellValue().getString().length() > slowoKluczowe.length()) {
                        //this.test.add(BoyerMooreHorspoolSimpleSearch(slowoKluczowe.toCharArray(), cell.getRichStringCellValue().getString().toCharArray()));
                        if (BoyerMooreHorspoolSimpleSearch2(slowoKluczowe.toCharArray(), cell.getRichStringCellValue().getString().toCharArray()) == true) {
                            this.colNr.add(cell.getColumnIndex() + 1);
                            this.rowNr.add(cell.getRowIndex() + 1);
                            this.opis.add("Index: " + BoyerMooreHorspoolSimpleSearch(slowoKluczowe.toCharArray(), cell.getRichStringCellValue().getString().toCharArray()));
                            this.dlugi.add(false);

                        }
                    }
                }
            }
        }
    }

    public static int BoyerMooreHorspoolSimpleSearch(char[] pattern, char[] text) {
        int patternSize = pattern.length;
        int textSize = text.length;

        int i = 0, j = 0;

        while ((i + patternSize) <= textSize) {
            j = patternSize - 1;
            while (text[i + j] == pattern[j]) {
                j--;
                if (j < 0) {
                    return i;
                }
            }
            i++;
        }
        return -1;
    }

    public static boolean BoyerMooreHorspoolSimpleSearch2(char[] pattern, char[] text) {
        int patternSize = pattern.length;
        int textSize = text.length;

        int i = 0, j = 0;

        while ((i + patternSize) <= textSize) {
            j = patternSize - 1;
            while (text[i + j] == pattern[j]) {
                j--;
                if (j < 0) {
                    return true;
                }
            }
            i++;
        }
        return false;
    }

    public int getRow(int indexWyniku) {
        return rowNr.get(indexWyniku);
    }

    public int getColumn(int indexWyniku) {
        return colNr.get(indexWyniku);
    }

    public int getTest(int indexWyniku) {
        return test.get(indexWyniku);
    }

    public String getOpis(int indexWyniku) {
        return opis.get(indexWyniku);
    }

    public boolean getDlugi(int indexWyniku) {
        return dlugi.get(indexWyniku);
    }

}
