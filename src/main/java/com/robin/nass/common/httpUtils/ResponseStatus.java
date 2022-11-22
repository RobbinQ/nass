

package com.robin.nass.common.httpUtils;


/**
 * 与前端进行特殊交互需要使用的状态码
 * 注意：如果状态码以4开始，则表示需要用户进行重新登录，这个要给客户端说清楚
  */
public enum ResponseStatus {

    //定义一个成功的状态码，200
    SUCCESS(200,"任务执行成功!",true),
    ILLEGALUSER(402, "非法用户!",false),
    FORBIDDEN(403,"用户没有访问此功能的权限！",false),
    WRONG(500, "系统内部发生错误！",false),
    ;


    private final int code;

    private final String msg;

    private final Boolean success;


    ResponseStatus(int code, String msg, Boolean success) {
        this.code = code;
        this.msg = msg;
        this.success=success;
    }


    /**
     * Return the integer value of this status code.
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Return the msg of this status code.
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Return the msg of this status code.
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * Return a string representation of this status code.
     */
    @Override
    public String toString() {
        return this.code + " " + name();
    }

}
