/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.util;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 *
 * @author Administrator jxl操作excel
 */
public class ExportExcel {

    public static boolean createExcel(String path, Map<String, Object[][]> map) {
        boolean flag = true;
        try {
            WritableWorkbook book = Workbook.createWorkbook(new File(path));
            int i = 0;
            for (String name : map.keySet()) {
                WritableSheet sheet = book.createSheet(name, i);
                i++;
                Object[][] object = map.get(name);
                for (int j = 0; j < object.length; j++) {
                    for (int k = 0; k < object[j].length; k++) {
                        try {
                            jxl.write.Number number = new jxl.write.Number(k, j, Double.parseDouble((String) object[j][k]));
                            sheet.addCell(number);
                        } catch (Exception e) {
                            Label label = new Label(k, j, (String) object[j][k]);
                            sheet.addCell(label);
                        }
                    }
                }
            }
            book.write();
            book.close();
        } catch (Exception e) {
            flag = false;
            System.out.println(e);
        }
        return flag;
    }

    public static void readExcel() {
        try {
            Workbook book = Workbook.getWorkbook(new File(" test.xls "));
            //  获得第一个工作表对象 
            Sheet sheet = book.getSheet(0);
            //  得到第一列第一行的单元格 
            Cell cell1 = sheet.getCell(0, 0);
            String result = cell1.getContents();
            System.out.println(result);
            book.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void updateExcel() {
        try {
            //  Excel获得文件 
            Workbook wb = Workbook.getWorkbook(new File(" test.xls "));
            //  打开一个文件的副本，并且指定数据写回到原文件 
            WritableWorkbook book = Workbook.createWorkbook(new File(" test.xls "),
                    wb);
            //  添加一个工作表 
            WritableSheet sheet = book.createSheet(" 第二页 ", 1);
            sheet.addCell(new Label(0, 0, " 第二页的测试数据 "));
            book.write();
            book.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        Map<String, Object[][]> map = new LinkedHashMap<String, Object[][]>();
        Object[][] object = {{"订单编号", "商品", "价格"}, {"编号1", "红牛", "99"}};
        map.put("订单表", object);
        ExportExcel.createExcel("E:\\site\\jiamei\\ROOT\\upload\\test.xls", map);
//        ExportExcel.readExcel();
//        ExportExcel.updateExcel();
    }
}
