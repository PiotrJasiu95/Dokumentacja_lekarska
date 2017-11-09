/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dokumentacja_lekarska;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

//import org.apache.poi.ss.usermodel.

/**
 *
 * @author Piotr
 */
public class Dokumentacja_lekarska {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic herejhvkjhvhjkgvhjkgvjcvbvcbcvb
//        dfgsg
//                dfg
        //fghfgh
        //asdkas
             
        String fileName = "C:\\Users\\Piotr\\Desktop\\Test.xls";
        String cellContent = "test";
        List<Integer> colTrue = new ArrayList<Integer>();
        List<Integer> rowTrue = new ArrayList<Integer>();
        boolean[][] col_row = new boolean[1000][1000];
                

        InputStream input = new FileInputStream(fileName);

        HSSFWorkbook wb = new HSSFWorkbook(input);
        HSSFSheet sheet = wb.getSheetAt(0);
        int colnr = 100;

        //rownr = findRow(sheet, cellContent, colnr);
        wyszukiwanie_excel excel;
        excel = new wyszukiwanie_excel();
        col_row = excel.findRow(sheet, cellContent, colnr);
        //rowTrue = excel.findCol(sheet, cellContent, colnr);

        for(int n=0,m=0;n<=1000;n++,m++){
        System.out.print("col= " + n +" row = " + m + "  ");
        System.out.println(col_row[n][m]);
        //System.out.println(rowTrue);
        //System.out.println(colnr);
        }
        
    }
    
}
