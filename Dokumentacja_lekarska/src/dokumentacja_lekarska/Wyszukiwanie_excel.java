/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dokumentacja_lekarska;

import java.util.ArrayList;
import java.util.Iterator;
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
//    List<Integer> colNr2;
//    List<Integer> rowNr2;
    List<Integer> test;
    List<String> opis;
    List<Boolean> dlugi;
    char[] text = {'t', 'e', 'k', 'd', 's', 't', 't', 'e'};
    char[] szukanie = {'t', 'e', 's', 't'};

    public Wyszukiwanie_excel() {
        this.colNr = new ArrayList<Integer>();
        this.rowNr = new ArrayList<Integer>();
//        this.colNr2 = new ArrayList<Integer>();
//        this.rowNr2 = new ArrayList<Integer>();
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
                            this.opis.add("W tej kolumnie szukana słowo znajduje się na indeksie: "+ BoyerMooreHorspoolSimpleSearch(slowoKluczowe.toCharArray(), cell.getRichStringCellValue().getString().toCharArray()));
                            this.dlugi.add(false);

                        }
                        //this.test.add(indexOf(cell.getRichStringCellValue().getString().toCharArray(), slowoKluczowe.toCharArray()));

                    }
                }
            }
        }
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

    /**
     * Returns the index within this string of the first occurrence of the
     * specified substring. If it is not a substring, return -1.
     *
     * @param haystack The string to be scanned
     * @param needle The target string to search
     * @return The start index of the substring
     */
    public int indexOf(char[] haystack, char[] needle) {
        if (needle.length == 0) {
            return 0;
        }
        int charTable[] = makeCharTable(needle);
        int offsetTable[] = makeOffsetTable(needle);
        for (int i = needle.length - 1, j; i < haystack.length;) {
            for (j = needle.length - 1; needle[j] == haystack[i]; --i, --j) {
                if (j == 0) {
                    return i;
                }
            }
            // i += needle.length - j; // For naive method
            i += Math.max(offsetTable[needle.length - 1 - j], charTable[haystack[i]]);
        }
        return -1;
    }

    /**
     * Makes the jump table based on the mismatched character information.
     */
    private static int[] makeCharTable(char[] needle) {
        final int ALPHABET_SIZE = Character.MAX_VALUE + 1; // 65536
        int[] table = new int[ALPHABET_SIZE];
        for (int i = 0; i < table.length; ++i) {
            table[i] = needle.length;
        }
        for (int i = 0; i < needle.length - 1; ++i) {
            table[needle[i]] = needle.length - 1 - i;
        }
        return table;
    }

    /**
     * Makes the jump table based on the scan offset which mismatch occurs.
     */
    private static int[] makeOffsetTable(char[] needle) {
        int[] table = new int[needle.length];
        int lastPrefixPosition = needle.length;
        for (int i = needle.length; i > 0; --i) {
            if (isPrefix(needle, i)) {
                lastPrefixPosition = i;
            }
            table[needle.length - i] = lastPrefixPosition - i + needle.length;
        }
        for (int i = 0; i < needle.length - 1; ++i) {
            int slen = suffixLength(needle, i);
            table[slen] = needle.length - 1 - i + slen;
        }
        return table;
    }

    /**
     * Is needle[p:end] a prefix of needle?
     */
    private static boolean isPrefix(char[] needle, int p) {
        for (int i = p, j = 0; i < needle.length; ++i, ++j) {
            if (needle[i] != needle[j]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the maximum length of the substring ends at p and is a suffix.
     */
    private static int suffixLength(char[] needle, int p) {
        int len = 0;
        for (int i = p, j = needle.length - 1;
                i >= 0 && needle[i] == needle[j]; --i, --j) {
            len += 1;
        }
        return len;
    }
}
