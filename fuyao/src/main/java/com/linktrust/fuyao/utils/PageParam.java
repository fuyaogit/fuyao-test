package com.linktrust.fuyao.utils;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**   
*    
* 项目名称：wk-platform   
* 类名称：PageParam   
* 类描述： 分页查询参数 
* 创建人：jdhuang   
* 创建时间：2016年6月21日 上午11:39:02   
* 修改人：jdhuang   
* 修改时间：2016年6月21日 上午11:39:02   
* 修改备注：   
* @version    
*    
*/
@ApiModel
public class PageParam implements Serializable{

	private static final long serialVersionUID = 4614505456948290463L;

	/*
	 * 第几页
	 * */
	@ApiModelProperty(value = "起始页", required = true)
	private int pageNo = 1;
	
	/*
	 * 分页起始条数
	 */
	@ApiModelProperty(value = "起始条数,页面不用传", required = false)
	private int sizeNow = 0;
	
	/*
	 * 每页多少条数据 
	 * 默认5条
	 * */
	@ApiModelProperty(value = "每页显示多少条数据 ", required = true)
	private int pageSize=5;

	public int getSizeNow() {
		return sizeNow;
	}

	public void setSizeNow() {
		this.sizeNow = (pageNo-1)*pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
 
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
		setSizeNow();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		setSizeNow();
	}
}

