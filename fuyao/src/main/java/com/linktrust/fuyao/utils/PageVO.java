package com.linktrust.fuyao.utils;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 分页计算结果
 * 
 * @author SongXiang
 * @date 2013-3-20 上午10:06:59
 */
@ApiModel
public class PageVO implements Serializable
{
    private static final long serialVersionUID = -2907448189497614108L;

    /** 记录总数 */
    @ApiModelProperty(value="记录总数")
    private int totalCount;

    /** 每页数量 */
    @ApiModelProperty(value="每页数量")
    private int pageSize;

    /** 页码总数 */
    @ApiModelProperty(value="页码总数")
    private int pageCount;

    protected PageVO()
    {
    }

    public PageVO(int totalCount, int pageSize)
    {
        this.totalCount = totalCount;
        this.pageSize = pageSize;

        // 用出发计算出页码数量，如果有零头，则加一页
        this.pageCount = totalCount / pageSize + (totalCount % pageSize > 0 ? 1 : 0);
    }
    
    public PageVO(int totalCount)
    {
        this.totalCount = totalCount;
    }

    /**
     * 根据页码计算记录起始位置
     * 
     * @param page 页码
     * @return 记录起始位置
     */
    public int getStart(int page)
    {
        return (page * this.getPageSize()) - this.getPageSize();
    }

    /**
     * 根据页码计算记录结束位置
     * 
     * @param page 页码
     * @return 记录结束位置
     */
    public int getEnd(int page)
    {
        return page * this.getPageSize();
    }

    public int getTotalCount()
    {
        return totalCount;
    }

    public int getPageCount()
    {
        return pageCount;
    }

    public int getPageSize()
    {
        return pageSize;
    }
}
