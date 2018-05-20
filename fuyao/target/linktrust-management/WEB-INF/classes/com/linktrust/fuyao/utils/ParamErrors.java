package com.linktrust.fuyao.utils;

/**   
*    
* 项目名称：wk-platform   
* 类名称：ParamErrors   
* 类描述：传参错误代码   
* 创建人：007   
* 创建时间：2016年6月21日 上午9:29:12   
* 修改人：007   
* 修改时间：2016年6月21日 上午9:29:12   
* 修改备注：   
* @version    
*    
*/ 
public interface ParamErrors
{
    /** 通用参数错误 */
    public static final ErrorEntity PARAM_ERROR = ErrorEntity.define(1000, "错误的输入");

    /** 数据格式错误 */
    public static final ErrorEntity INVALID_FORMAT_ERROR = ErrorEntity.define(1001, "输入格式错误");

    /** 数据不合法错误 */
    public static final ErrorEntity INVALID_VALUE_ERROR = ErrorEntity.define(1002, "输入值不合法");

    /** 文件类型不正确 */
    public static final ErrorEntity INVALID_FILE_TYPE_ERROR = ErrorEntity.define(1003, "文件类型不正确");

    /** 参数配置错误 */
    public static final ErrorEntity INVALID_PARAM_CONFIG_ERROR = ErrorEntity.define(1004, "参数配置错误");

    /** 参数只读，不允许设值 */
    public static final ErrorEntity READ_ONLY = ErrorEntity.define(1005, "参数只读，不允许设值");

}
