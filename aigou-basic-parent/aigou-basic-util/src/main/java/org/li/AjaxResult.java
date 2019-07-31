package org.li;

/**
 * 请求返回结果的封装
 */
public class AjaxResult {
    private boolean success = true;
    private String message = "操作成功!!";
    private Object obj;
    private Integer errorCode;

    public AjaxResult() {
    }

    //提供一个对外的获取AjaxResult的方法
    public static AjaxResult getAjaxResult(){
        return new AjaxResult();
    }

    public boolean isSuccess() {
        return success;
    }

    //操作成功
    public AjaxResult setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    //设置返回信息
    public AjaxResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getObj() {
        return obj;
    }

    public AjaxResult setObj(Object obj) {
        this.obj = obj;
        return this;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public AjaxResult setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
        return this;
    }
}
