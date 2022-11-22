package com.robin.nass.common.httpUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult implements Serializable {
    private Integer code;//返回数据的代码
    private String msg;//返回信息提示
    private String tip;//返回信息的有针对性的提示，有些可能没针对性，需要前端手动添加
    private Object data;//返回的数据

    public ApiResult(ResponseStatus status, String tip, Object data){
        this.code=status.getCode();
        this.msg=status.getMsg();
        this.tip=tip;
        this.data=data;
    }

    public ApiResult(ResponseStatus status,String msg, String tip, Object data){
        this.code=status.getCode();
        this.msg=msg;
        this.tip=tip;
        this.data=data;
    }

}
