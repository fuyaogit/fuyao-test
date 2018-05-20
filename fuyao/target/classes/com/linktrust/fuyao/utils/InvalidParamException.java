package com.linktrust.fuyao.utils;

import java.util.Map;

/**   
*    
* 项目名称：wk-platform   
* 类名称：InvalidParamException   
* 类描述：输入数据错误异常   
* 创建人：007   
* 创建时间：2016年6月21日 上午10:41:56   
* 修改人：007   
* 修改时间：2016年6月21日 上午10:41:56   
* 修改备注：   
* @version    
*    
*/ 
public class InvalidParamException extends GenericException
{
    private static final long serialVersionUID = -1915209497885608410L;

    public InvalidParamException(Exception e)
    {
        super(e);
        this.code = ParamErrors.PARAM_ERROR.getCode();
    }

    public InvalidParamException(ErrorEntity error)
    {
        super(error);
    }

    public InvalidParamException(ErrorEntity error, String appendMessage)
    {
        super(error, appendMessage);
    }

    public InvalidParamException(int code)
    {
        super(code);
    }

    public InvalidParamException(String message)
    {
        super(message);
        super.code = ParamErrors.INVALID_FORMAT_ERROR.getCode();
    }

    public InvalidParamException(int code, String message)
    {
        super(code, message);
    }

    public InvalidParamException(int code, String message, Map<String, Object> extra)
    {
        super(code, message, extra);
    }
}
