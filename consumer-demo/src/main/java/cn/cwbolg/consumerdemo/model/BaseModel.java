package cn.cwbolg.consumerdemo.model;

/**
 * @author chenw
 * @title: BaseModel
 * @description: 响应模型
 * @date 2020/3/16 10:10
 */
public class BaseModel {

    private String message;

    private String result;

    private Integer code;

    private Object data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public BaseModel() {
    }

    public BaseModel(String message, String result, Integer code, Object data) {
        this.message = message;
        this.result = result;
        this.code = code;
        this.data = data;
    }
}
