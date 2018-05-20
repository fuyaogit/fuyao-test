package com.linktrust.fuyao.utils;

/**   
*    
* 项目名称：wk-platform   
* 类名称：ErrorEntity   
* 类描述：   包装了错误代码和错误消息
* 创建人：007   
* 创建时间：2016年6月21日 上午9:25:26   
* 修改人：007   
* 修改时间：2016年6月21日 上午9:25:26   
* 修改备注：   
* @version    
*    
*/ 
public class ErrorEntity
{
    /** 错误代码 */
    private int code;

    /** 错误消息 */
    private String message;

    private ErrorEntity(int code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public int getCode()
    {
        return code;
    }

    public String getMessage()
    {
        return message;
    }

    public static final ErrorEntity define(int code, String message)
    {
        return new ErrorEntity(code, message);
    }

    @Override
    public String toString()
    {
        return String.valueOf(code);
    }

}
