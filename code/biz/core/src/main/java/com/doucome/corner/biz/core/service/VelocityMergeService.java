package com.doucome.corner.biz.core.service;

import org.apache.velocity.context.Context;

/**
 * 类VelocityMergeService.java的实现描述：模板合并服务
 * 
 * @author ib 2012-4-26 上午12:12:27
 */
public interface VelocityMergeService {

    /**
     * Creates a Velocity context from the action, loads a Velocity template and executes the template. Output is
     * written to the servlet output stream.
     * 
     * @param finalLocation the location of the Velocity template
     * @param invocation an encapsulation of the action execution state.
     * @throws Exception if an error occurs when creating the Velocity context, loading or executing the template or
     * writing output to the servlet response stream.
     */
    public String doExecute(String finalLocation, Context context) throws Exception;

}
