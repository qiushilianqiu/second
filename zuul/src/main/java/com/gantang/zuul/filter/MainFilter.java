package com.gantang.zuul.filter;

import com.gantang.common.redis.RedisUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MainFilter extends ZuulFilter {

    private static final Logger LOG = LoggerFactory.getLogger(ZuulFilter.class);

    @Autowired
    RedisUtil util;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        LOG.info("进入拦截器..");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String url = request.getRequestURI();
        String method = request.getMethod();
        System.out.println(url);
        if (url.contains("management") || url.contains("order") || url.contains("interface") ) {
            LOG.info("请求不需要校验token，直接通过...");
            return null;
        }
        String token = request.getHeader("token");
        try {
            if (null == token || "".equals(token) || !util.hasKey(token)) {
                LOG.info("token不存在或token已过期:【" + token + "】");
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(401);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
        }
        //刷新缓存时间
        util.expire(token, 7200l);
        return null;
    }

    /**
     * @param
     * @author hl.wang
     * @Title:
     * @date :
     */
    private boolean ifUploadFile(String url) {
        String[] files = {".jpg", ".jpeg", ".gif", ".tif", ".png", ".JPG", ".JPEG", "GIF", "TIF", "PNG", "doc", "DOC", "docx", "DOCX", "pdf", "PDF", "ppt", "PPT", "pptx", "PPTX"};
        for (int i = 0; i < files.length; i++) {
            if (url.indexOf(files[i]) >= 0) {
                return true;
            }
        }
        return false;
    }
}
