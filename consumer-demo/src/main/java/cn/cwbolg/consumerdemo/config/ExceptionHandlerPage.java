package cn.cwbolg.consumerdemo.config;

import cn.cwbolg.consumerdemo.model.BaseModel;
import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chenw
 * @title: ExceptionHandlerPage
 * @description: 自定义Sentinel异常返回
 * @date 2020/3/16 10:00
 */
@Component
public class ExceptionHandlerPage implements UrlBlockHandler {
    @Override
    public void blocked(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws IOException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        BaseModel baseModel = null;
        if(e instanceof FlowException){
            baseModel =new BaseModel("接口被限流","fail",510,null);
        }else if (e instanceof DegradeException){
            baseModel = new BaseModel("接口被降级","fail",511,null);
        }else if (e instanceof ParamFlowException){
            baseModel = new BaseModel("接口触发热点规则","fail",512,null);
        }else if (e instanceof AuthorityException){
            baseModel = new BaseModel("接口触发授权规则","fail",513,null);
        }else if (e instanceof SystemBlockException){
            baseModel = new BaseModel("触发系统规则","fail",514,null);
        }
        httpServletResponse.getWriter().write(JSON.toJSONString(baseModel));
    }
}
