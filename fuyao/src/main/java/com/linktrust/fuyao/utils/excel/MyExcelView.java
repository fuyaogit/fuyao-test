package com.linktrust.fuyao.utils.excel;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.web.servlet.view.document.AbstractExcelView;


/**     
* @describe：导出excel对象
* @author：liudz  2014年12月19日 
* @modifyer：无  
* @remark：【修改请填写备注】   
* @version 1.0.0    
*/
public class MyExcelView extends AbstractExcelView {  
	
	/**
	 * 数据
	 */
	private List<Map<String,Object>> dataList;
	
	/**
	 * 表头数据
	 */
	private List<Map<String,Object>> columnList;
	
	public MyExcelView() {}
	
	public MyExcelView(List<Map<String,Object>> datalist,List<Map<String,Object>> columnlist) {
		this.dataList = datalist;
		this.columnList = columnlist;
	}
	
    @Override  
    protected void buildExcelDocument(Map<String, Object> obj,  
            HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)  
            throws Exception {  
		if (dataList != null && columnList != null) {
			HSSFSheet sheet = workbook.createSheet("数据信息");
			HSSFRow row = sheet.createRow((int) 0);
			HSSFCellStyle style = workbook.createCellStyle();
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			HSSFCell cell = row.createCell((short) 0);
			Map<String, Object> titleMap=null;
			int cellNum=0;
			List<String> columnNameLst = new ArrayList<String>();
			int columnSize = columnList.size();
			for (int i = 0; i < columnSize; i++) {//循环生成将要导出的title
				titleMap=columnList.get(i);
				String title = titleMap.get("displayname").toString();
				cell.setCellValue(title);
				sheet.setColumnWidth(i, title.length()*2*256);
				columnNameLst.add(titleMap.get("columnname").toString());
				cell.setCellStyle(style);
				cell = row.createCell(++cellNum);
			}
			int rowNum=0;
			Map<String, Object> dataMap=null;
			int len = dataList.size();
			for (int i = 0; i < len; i++) {
				dataMap=dataList.get(i);
				row = sheet.createRow(++rowNum);
				for(int j=0;j<columnSize;j++){
					String key = columnNameLst.get(j);
					Object obj2 = dataMap.get(key);
					String cellValue = null;
					if(obj2 != null){
						cellValue = obj2.toString();
					}
					
					if(cellValue != null){
						int width = sheet.getColumnWidth(j);
						int width2 = cellValue.length()*2*256;
						if(width2>width){
							sheet.setColumnWidth(j, width2);
						}
					}
					row.createCell(j).setCellValue(cellValue);
				}
			}
			String filename = "直播统计_"+System.currentTimeMillis()/1000+".xls";//设置下载时客户端Excel的名称     
	        filename = new String(filename.getBytes("gbk"),"iso-8859-1");//处理中文文件名  
			OutputStream ouputStream = null;
			try {
				response.setContentType("application/vnd.ms-excel");     
		        response.setHeader("Content-disposition", "attachment;filename=" + filename);     
		        ouputStream = response.getOutputStream();     
		        workbook.write(ouputStream);     
		        ouputStream.flush();     
			} catch (Exception e) {
			}finally{
				ouputStream.close();   
			}
	    } 
    }  
}  
