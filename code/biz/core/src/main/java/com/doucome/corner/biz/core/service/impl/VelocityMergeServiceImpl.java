package com.doucome.corner.biz.core.service.impl;

import org.apache.commons.lang.text.StrBuilder;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.service.VelocityMergeService;

/**
 * 类VelocityMergeService.java的实现描述：模板合并服务
 * 
 * @author ib 2012-4-26 上午12:12:27
 */
public class VelocityMergeServiceImpl implements VelocityMergeService{

    private static final Logger LOG = LoggerFactory.getLogger(VelocityMergeServiceImpl.class);

    private String              defaultEncoding;
    @Autowired
    private VelocityEngine      velocityEngine;

    public VelocityMergeServiceImpl(){
    }

    public VelocityMergeServiceImpl(String location){
    }

    public void setDefaultEncoding(String val) {
        defaultEncoding = val;
    }

    /**
     * Creates a Velocity context from the action, loads a Velocity template and executes the template. Output is
     * written to the servlet output stream.
     * 
     * @param finalLocation the location of the Velocity template
     * @param invocation an encapsulation of the action execution state.
     * @throws Exception if an error occurs when creating the Velocity context, loading or executing the template or
     * writing output to the servlet response stream.
     */
    public String doExecute(String finalLocation, Context context) throws Exception {

        try {
            String encoding = getEncoding(finalLocation);
            String contentType = getContentType(finalLocation);

            if (encoding != null) {
                contentType = contentType + ";charset=" + encoding;
            }

            Template t = getTemplate(velocityEngine, finalLocation, encoding);
            StrBuilder builder = new StrBuilder();

            t.merge(context, builder.asWriter());
            return builder.toString();
        } catch (Exception e) {
            LOG.error("Unable to render Velocity Template, '" + finalLocation + "'", e);
            throw e;
        }
    }

    /**
     * Retrieve the content type for this template.
     * <p/>
     * People can override this method if they want to provide specific content types for specific templates (eg
     * text/xml).
     * 
     * @return The content type associated with this template (default "text/html")
     */
    protected String getContentType(String templateLocation) {
        return "text/html";
    }

    /**
     * Retrieve the encoding for this template.
     * <p/>
     * People can override this method if they want to provide specific encodings for specific templates.
     * 
     * @return The encoding associated with this template (defaults to the value of 'struts.i18n.encoding' property)
     */
    protected String getEncoding(String templateLocation) {
        String encoding = defaultEncoding;
        if (encoding == null) {
            encoding = System.getProperty("file.encoding");
        }
        if (encoding == null) {
            encoding = "GBK";
        }
        return encoding;
    }

    /**
     * Given a value stack, a Velocity engine, and an action invocation, this method returns the appropriate Velocity
     * template to render.
     * 
     * @param stack the value stack to resolve the location again (when parse equals true)
     * @param velocity the velocity engine to process the request against
     * @param invocation an encapsulation of the action execution state.
     * @param location the location of the template
     * @param encoding the charset encoding of the template
     * @return the template to render
     * @throws Exception when the requested template could not be found
     */
    protected Template getTemplate(VelocityEngine velocity, String location, String encoding) throws Exception {
        // if (!location.startsWith("/")) {
        // location = invocation.getProxy().getNamespace() + "/" + location;
        // }

        Template template = velocity.getTemplate(location, encoding);

        return template;
    }

}
