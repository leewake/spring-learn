package com.pangpang.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by leewake on 2017/8/1 0001.
 */
public class ExcelUtil {

    private POIFSFileSystem fileSystem;
    private HSSFWorkbook workbook;
    private HSSFSheet sheet;
    private HSSFRow row;

    /**
     * 读取Excel表格表头的内容
     * @param inputStream
     * @return String 表头内容的数组
     */
    public String[] readTitle(InputStream inputStream) {
        try {
            fileSystem = new POIFSFileSystem(inputStream);
            workbook = new HSSFWorkbook(fileSystem);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sheet = workbook.getSheetAt(0);
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        System.out.println("标题总列数：" + colNum);
        String[] title = new String[colNum];
        for (int i = 0; i < colNum; i++) {
            title[i] = getCellFormatValue(row.getCell(i));
        }

        return title;
    }


    /**
     * 读取Excel数据内容
     * @param inputStream
     * @return Map 包含单元格数据内容的Map对象
     */
    public Map<Integer, String> readExcelContent(InputStream inputStream) {
        Map<Integer, String> content = new HashMap<>();
        String str = "";
        try {
            fileSystem = new POIFSFileSystem(inputStream);
            workbook = new HSSFWorkbook(fileSystem);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = workbook.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            while (j < colNum) {
                // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
                // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
                // str += getStringCellValue(row.getCell((short) j)).trim() +
                // "-";
                str += getCellFormatValue(row.getCell((short) j)).trim() + "-";
                j++;
            }
            content.put(i, str);
            str = "";
        }
        return content;
    }


    /**
     * 获取单元格数据内容并转为字符串类型的数据
     *
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    private String getStringCellValue(HSSFCell cell) {
        String result;
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                result = cell.getStringCellValue();
                break;

            case HSSFCell.CELL_TYPE_NUMERIC:
                result = String.valueOf(cell.getNumericCellValue());
                break;

            case HSSFCell.CELL_TYPE_BOOLEAN:
                result = String.valueOf(cell.getBooleanCellValue());
                break;

            case HSSFCell.CELL_TYPE_BLANK:
                result = "";
                break;

            default:
                result = "";
                break;
        }

        return result;
    }

    /**
     * 获取单元格数据内容为日期类型的数据
     *
     * @param cell
     * @return String 单元格数据内容
     */
    private String getDateCellValue(HSSFCell cell) {
        String result = "";
        int cellType = cell.getCellType();
        if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
            Date date = cell.getDateCellValue();
            result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
                    + "-" + date.getDate();
        } else if (cellType == HSSFCell.CELL_TYPE_STRING) {
            String date = getStringCellValue(cell);
            result = date.replaceAll("[年月]", "-").replace("日", "").trim();
        } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
            result = "";
        }

        return result;
    }


    /**
     * 根据HSSFCell类型设置数据
     *
     * @param cell
     *
     * @return
     *
     */
    private String getCellFormatValue(HSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                // 如果当前Cell的Type为NUMERIC
                case HSSFCell.CELL_TYPE_NUMERIC:
                    BigDecimal bigDecimal = new BigDecimal(cell.getNumericCellValue());
                    cellvalue = bigDecimal.toPlainString();
                    break;

                case HSSFCell.CELL_TYPE_FORMULA: {
                    // 判断当前的cell是否为Date
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {

                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        cellvalue = sdf.format(date);

                    }
                    // 如果是纯数字
                    else {
                        // 取得当前Cell的数值
                        cellvalue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }

                // 如果当前Cell的Type为STRIN
                case HSSFCell.CELL_TYPE_STRING:
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                // 默认的Cell值
                default:
                    cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }

        return cellvalue;

    }


}
