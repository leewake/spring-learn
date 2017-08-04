package com.pangpang.controller;

import com.pangpang.util.ExcelUtil;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by leewake on 2017/8/1 0001.
 */
public class ExportCsvController {

    //@RequestMapping(/downloadExcel)

    public static void main(String[] args) {
        try {
            InputStream inputStream = new FileInputStream("d:\\test.xls");

            ExcelUtil excelUtil = new ExcelUtil();
            String[] title = excelUtil.readTitle(inputStream);
            System.out.println("获得Excel表格的标题：");
            for (String s : title) {
                System.out.print(s + " ");
            }

            System.out.println();

            //不加这一行，会报inputStream流关闭
            InputStream newInputStream = new FileInputStream("d:\\test.xls");
            Map<Integer, String> map = excelUtil.readExcelContent(newInputStream);
            System.out.println("获取Excel表格的内容：");
            for (int i = 0; i < map.size(); i++) {
                String tmp = map.get(i + 1);
                System.out.println(tmp.substring(0, tmp.length() - 1));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
