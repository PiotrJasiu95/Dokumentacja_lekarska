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

public class wyszukiwanie_excel {


    
public static boolean[][] findRow(HSSFSheet sheet, String cellContent, int colnr) {
        List<Integer> colTrue = new ArrayList<Integer>();
        List<Integer> rowTrue = new ArrayList<Integer>();
        boolean[][] col_row = new boolean[1000][1000];
        //int colListIndex=0;
        //int rowListIndex=0;
        //Iterator<Row> iterator = sheet.iterator();
        //List<List<Boolean>> col_row = new ArrayList<List<Boolean>>();

        int currentRow = 0;
        
    for (int currentColnr=1; currentColnr<=colnr; currentColnr++){
    for (Row row : sheet) {
        currentRow++;
        for (Cell cell : row) {        
            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
                   // colTrue.add(currentColnr);
                   // rowTrue.add(row.getRowNum()+1);                    
                   // return row.getRowNum();
                   //return rowTrue;
                   
                  col_row[currentColnr][currentRow]=true;
                  //colListIndex++;
                 // rowListIndex++;
                   //col_row[0][0] = new ArrayList();
                }
            }
        }
    }   
   }
return col_row;
}
    
public static List<Integer> findCol(HSSFSheet sheet, String cellContent, int colnr) {
        List<Integer> colTrue = new ArrayList<Integer>();
        List<Integer> rowTrue = new ArrayList<Integer>();
    for (int currentColnr=0; currentColnr<=colnr; currentColnr++){
    for (Row row : sheet) {
        for (Cell cell : row) {        
            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
                    colTrue.add(currentColnr);
                  //  rowTrue.add(row.getRowNum());
                   // return row.getRowNum();
                  // return colTrue;
                   // return colTrue;
                }
            }
        }
    }   
   }
return colTrue;
}    

}
