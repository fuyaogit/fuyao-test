package com.linktrust.fuyao.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * 
 * @Title(项目名称): linktrust-management
 * @Class(类名称): ReadExcelUtil.java
 * @Description(类描述): 读取Excell表数据的工具类
 * @Author(创建人): zhangxw
 * @Date(创建时间): 2017年1月19日
 * @version(版本): V1.0
 */
public class ReadExcelUtil {

	 /**
	  * 读取2007文件内容
	  * @param file
	  * @return
	  * @throws IOException
	  */
	 public static List<String> readExcel2007(MultipartFile file) throws IOException {
	  //存放获取的数据
	  List<String> result = new ArrayList<String>();
	  //创建一个输入流
	 // FileInputStream in= new FileInputStream(file);
	  InputStream in = file.getInputStream();
	  //创建一个Excel操作对象
	  XSSFWorkbook workbook = new XSSFWorkbook(in);
	  //行大小
	  int rowSize = 0 ;
	  //工作簿个数
	  int sheets = workbook.getNumberOfSheets();
	  //工作簿对象
	  XSSFSheet sheet;
	  //工作簿中的行
	  XSSFRow row;
	  //行中的单元格
	  XSSFCell cell;
	  //循环工作簿
	  for(int st=0 ; st<sheets ; st++){
	   //获得对应的工作簿
	   sheet = workbook.getSheetAt(st);
	   //工作簿中的行数
	   int rowNum = sheet.getLastRowNum();
	   //循环行
	   for(int rowIndex = 2; rowIndex <=rowNum ; rowIndex++){
	    row = sheet.getRow(rowIndex);
	    if(row == null){
	     continue;
	    }
	    //当前行列数
	    int tempRowSize = row.getLastCellNum();
	    //始终让rowSize为最大列数
	    if(tempRowSize > rowSize){
	     rowSize = tempRowSize;
	    }
	    //单元格数据暂存
	    //String[] values = new String[rowSize];
	    List<String> values = new ArrayList<>();
	    //标识行中是否有数据
	    boolean hasValue = false;
	    //该行中列数
	    int cols = row.getLastCellNum();
	    for(int col = 0; col < cols; col++){
	     //暂存党员个数据
	     String value = "";
	     //单元格
	     cell = row.getCell(col);
	     //如果为空继续下一个单元格
	     if(cell == null){
	      continue;
	     }
	     //获得对应单元格中对应格式 的数据
	     value = getFormatValue2007(cell);
	     System.out.print(value+"\t");
	     values.add(value);
	     hasValue = true;
	    }
	    System.out.println();
	    //如果有值加入到result
	    if(hasValue){
	    	
	     result.addAll(values);
	     
	    }
	   }
	  }
	  return result;
	 }
	/**
	 * 读取2007文件内容 文件中为空的单元格用 "" 字符串代替
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static List<String> readExcel2007HasNull(MultipartFile file) throws IOException {
		// 存放获取的数据
		List<String> result = new ArrayList<String>();
		// 创建一个输入流
		// FileInputStream in= new FileInputStream(file);
		InputStream in = file.getInputStream();
		// 创建一个Excel操作对象
		XSSFWorkbook workbook = new XSSFWorkbook(in);
		// 行大小
		int rowSize = 0;
		// 工作簿个数
		int sheets = workbook.getNumberOfSheets();
		// 工作簿对象
		XSSFSheet sheet;
		// 工作簿中的行
		XSSFRow row;
		// 行中的单元格
		XSSFCell cell;
		// 循环工作簿
		for (int st = 0; st < sheets; st++) {
			// 获得对应的工作簿
			sheet = workbook.getSheetAt(st);
			// 工作簿中的行数
			int rowNum = sheet.getLastRowNum();
			//查询最大列数（第二行的列数）
			rowSize = sheet.getRow(1).getLastCellNum();
			// 循环行
			for (int rowIndex = 2; rowIndex <= rowNum; rowIndex++) {
				row = sheet.getRow(rowIndex);
				if (row == null) {
					continue;
				}
				/*// 当前行列数
				int tempRowSize = row.getLastCellNum();
				// 始终让rowSize为最大列数
				if (tempRowSize > rowSize) {
					rowSize = tempRowSize;
				}*/
				// 单元格数据暂存
				// String[] values = new String[rowSize];
				List<String> values = new ArrayList<>();
				// 标识行中是否有数据
				boolean hasValue = false;
				/*// 该行中列数
				int cols = row.getLastCellNum();*/
				for (int col = 0; col < rowSize; col++) {
					// 暂存党员个数据
					String value = "";
					// 单元格
					cell = row.getCell(col);
					// 如果为空继续下一个单元格
					if (cell == null) {
						value = "";
					}else {
						value = getFormatValue2007(cell);
					}
					// 获得对应单元格中对应格式 的数据
					
					System.out.print(value + "\t");
					values.add(value);
					hasValue = true;
				}
				System.out.println();
				// 如果有值加入到result
				if (hasValue) {

					result.addAll(values);

				}
			}
		}
		return result;
	}
	/**
	 * 返回2007对应单元格的格式化数据
	 * 
	 * @param cell
	 *            单元格
	 * @return
	 */
	public static String getFormatValue2007(XSSFCell cell) {

		// 暂存单元格内容
		String value = "";
		// 匹配单元格内容
		switch (cell.getCellType()) {
		// 数据格式类型
		case XSSFCell.CELL_TYPE_NUMERIC:
			// 判断是否是日期类型
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				Date date = cell.getDateCellValue();
				if (date != null) {
					// 格式化日期
					value = new SimpleDateFormat("yyyy-mm-dd").format(date);
				} else {
					value = "";
				}
			} else {
				// 格式化数据
				value = new DecimalFormat("0").format(cell.getNumericCellValue());
			}
			break;
		// 字符串类型
		case XSSFCell.CELL_TYPE_STRING:
			value = cell.getStringCellValue();
			break;
		// 公式生成类型
		case XSSFCell.CELL_TYPE_FORMULA:
			// 导入时如果为公式生成的数据则无值
			if (!cell.getStringCellValue().equals("")) {
				value = cell.getStringCellValue();
			} else {
				value = cell.getNumericCellValue() + "";
			}
			break;
		// 空白
		case XSSFCell.CELL_TYPE_BLANK:
			value = "";
			break;
		// 布尔型
		case XSSFCell.CELL_TYPE_BOOLEAN:
			value = cell.getBooleanCellValue() + "";
			break;
		// 错误格式
		case XSSFCell.CELL_TYPE_ERROR:
			value = "";
			break;
		default:
			value = cell.toString();
		}
		return value;
	}

	/**
	 * Excel2003处理方法
	 * 
	 * @param file
	 *            所要处理的文件
	 * @return
	 * @throws IOException
	 */
	public static List<String> readExcel2003(MultipartFile file) throws IOException {
		// 存放获取的数据
		List<String> result = new ArrayList<String>();
		// 创建一个输入流
		// FileInputStream in = new FileInputStream(file);
		InputStream in = file.getInputStream();
		// 创建对Excel操作的对象
		HSSFWorkbook workbook = new HSSFWorkbook(in);
		// 行大小
		int rowSize = 0;
		// 工作簿的个数
		int sheets = workbook.getNumberOfSheets();
		// 工作簿对象
		HSSFSheet sheet;
		// 行
		HSSFRow row;
		// 单元格
		HSSFCell cell;
		// 对每个工作簿进行循环
		for (int i = 0; i < sheets; i++) {
			// 获得工作簿
			sheet = workbook.getSheetAt(i);
			// 工作簿中的行数
			int rowNum = sheet.getLastRowNum();
			for (int rowIndex = 2; rowIndex <= rowNum; rowIndex++) {
				row = sheet.getRow(rowIndex);
				if (row == null) {
					continue;
				}
				// 获取当前行的列数
				int tempRowSize = row.getLastCellNum();
				// 总是最大列数
				if (tempRowSize > rowSize) {
					rowSize = tempRowSize;
				}
				// 获得单元格的内容暂存
				// String[] values = new String[rowSize];
				List<String> values = new ArrayList<>();
				// 标识Excel中是否有数据
				boolean hasValue = false;
				// 该行中列数
				int cols = row.getLastCellNum();
				// 循环对列取值
				for (int col = 0; col < cols; col++) {
					// 单元格暂存
					String value = "";
					// 获得单元格
					cell = row.getCell(col);
					// 如果为空继续下一个单元格
					if (cell == null) {
						continue;
					}
					// 获得对应单元格中的格式化数据
					value = getFormatValue2003(cell);
					System.out.print(value + "\t");
					values.add(value);
					hasValue = true;
				}
				System.out.println();
				// 有内容则加入到result中
				if (hasValue) {

					result.addAll(values);
				}
			}
		}
		return result;
	}

	/**
	 * 返回2003对应单元格的格式化数据
	 * 
	 * @param cell
	 *            单元格
	 * @return
	 */
	public static String getFormatValue2003(HSSFCell cell) {

		// 暂存单元格内容
		String value = "";
		// 匹配单元格内容
		switch (cell.getCellType()) {
		// 数据格式类型
		case XSSFCell.CELL_TYPE_NUMERIC:
			// 判断是否是日期类型
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				Date date = cell.getDateCellValue();
				if (date != null) {
					// 格式化日期
					value = new SimpleDateFormat("yyyy-mm-dd").format(date);
				} else {
					value = "";
				}
			} else {
				// 格式化数据
				value = new DecimalFormat("0").format(cell.getNumericCellValue());
			}
			break;
		// 字符串类型
		case XSSFCell.CELL_TYPE_STRING:
			value = cell.getStringCellValue();
			break;
		// 公式生成类型
		case XSSFCell.CELL_TYPE_FORMULA:
			// 导入时如果为公式生成的数据则无值
			if (!cell.getStringCellValue().equals("")) {
				value = cell.getStringCellValue();
			} else {
				value = cell.getNumericCellValue() + "";
			}
			break;
		// 空白
		case XSSFCell.CELL_TYPE_BLANK:
			value = "";
			break;
		// 布尔型
		case XSSFCell.CELL_TYPE_BOOLEAN:
			value = cell.getBooleanCellValue() + "";
			break;
		// 错误格式
		case XSSFCell.CELL_TYPE_ERROR:
			value = "";
			break;
		default:
			value = cell.toString();
		}
		return value;
	}

}
