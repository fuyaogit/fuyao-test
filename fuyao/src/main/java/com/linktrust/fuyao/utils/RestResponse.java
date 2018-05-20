package com.linktrust.fuyao.utils;

import java.util.HashMap;
import java.util.Map;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;


/**
 * REST 接口返回的 JSON 数据模板
 * 
 * @author 宋翔
 * @date 2015年11月17日 下午3:59:29
 */
@ApiModel
public class RestResponse
{
    /** 成功或失败 */
	@ApiModelProperty(value="成功或失败")
    public boolean flag;

    /** 错误代码 */
	@ApiModelProperty(value="错误代码")
    public int code;

    /** 消息文本 */
	@ApiModelProperty(value="消息文本")
    public String message;

    /** 消息数据 */
	@ApiModelProperty(value="消息数据")
    public Object data;

    /** 额外数据 */
	@ApiModelProperty(value="额外数据")
    public Map<String, Object> extra;

    private RestResponse()
    {
    }

    public static final RestResponse success()
    {
        RestResponse response = new RestResponse();
        response.setFlag(true);
        response.setCode(0);
        response.setMessage("成功");
        return response;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static final RestResponse success(Object data)
    {
        RestResponse response = new RestResponse();
        response.setFlag(true);
        response.setCode(0);
        response.setMessage("成功");
        if(data instanceof ListVO)
        {
            response.setExtra(new HashMap<String, Object>());
            response.getExtra().put("pageData", ((ListVO)data).getPageData());
            if(((ListVO)data).getExtras() != null)
            {
                response.getExtra().putAll(((ListVO)data).getExtras());
            }
            response.setData(((ListVO)data).getList());
        }
        else
        {
            response.setData(data);
        }
        return response;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static final RestResponse success(Object data, Map<String, Object> extra)
    {
        RestResponse response = new RestResponse();
        response.setFlag(true);
        response.setCode(0);
        response.setMessage("成功");
        response.setExtra(extra);
        if(data instanceof ListVO)
        {
            if(extra == null)
            {
                response.setExtra(new HashMap<String, Object>());
            }
            response.getExtra().put("pageData", ((ListVO)data).getPageData());
            if(((ListVO)data).getExtras() != null)
            {
                response.getExtra().putAll(((ListVO)data).getExtras());
            }
            response.setData(((ListVO)data).getList());
        }
        else
        {
            response.setData(data);
        }
        return response;
    }

    public static final RestResponse failure(ErrorEntity error)
    {
        RestResponse response = new RestResponse();
        response.setFlag(false);
        response.setCode(error.getCode());
        response.setMessage(error.getMessage());
        return response;
    }

    public static final RestResponse failure(Object data)
    {
        RestResponse response = new RestResponse();
        response.setFlag(false);
        response.setCode(GenericError.ERROR.getCode());
        response.setMessage(GenericError.ERROR.getMessage());
        response.setData(data);
        return response;
    }

    public static final RestResponse failure(ErrorEntity error, Object data)
    {
        RestResponse response = new RestResponse();
        response.setFlag(false);
        response.setCode(error.getCode());
        response.setMessage(error.getMessage());
        response.setData(data);
        return response;
    }

    public static final RestResponse failure(ErrorEntity error, Object data, Map<String, Object> extra)
    {
        RestResponse response = new RestResponse();
        response.setFlag(false);
        response.setCode(error.getCode());
        response.setMessage(error.getMessage());
        response.setData(data);
        response.setExtra(extra);
        return response;
    }

    public static final RestResponse failure(GenericException e)
    {
        RestResponse response = new RestResponse();
        response.setFlag(false);
        response.setCode(e.getCode());
        response.setMessage(e.getMessage());

        return response;
    }

    public static final RestResponse failure(Exception e)
    {
        RestResponse response = new RestResponse();
        response.setFlag(false);
        response.setCode(GenericError.ERROR.getCode());
        response.setMessage(e.getMessage());
        // TODO 以后要清理
        e.printStackTrace();

        return response;
    }

    public boolean isFlag()
    {
        return flag;
    }

    public void setFlag(boolean flag)
    {
        this.flag = flag;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

    public Map<String, Object> getExtra()
    {
        return extra;
    }

    public void setExtra(Map<String, Object> extra)
    {
        this.extra = extra;
    }

}
