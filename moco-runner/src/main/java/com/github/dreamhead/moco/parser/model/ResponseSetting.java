package com.github.dreamhead.moco.parser.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.github.dreamhead.moco.ResponseHandler;
import com.github.dreamhead.moco.parser.ResponseHandlerFactory;

import java.util.Map;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ResponseSetting extends BaseResourceSetting {
    private final ResponseHandlerFactory factory = new DynamicResponseHandlerFactory();

    private String status;
    private ProxyContainer proxy;
    private Map<String, TextContainer> headers;
    private Map<String, CookieContainer> cookies;
    private LatencyContainer latency;

    private TextContainer version;
    private AttachmentSetting attachment;
    private CollectionContainer seq;
    private CollectionContainer cycle;

    public final ResponseSetting asResponseSetting() {
        ResponseSetting responseSetting = new ResponseSetting();
        responseSetting.text = text;
        responseSetting.file = file;
        responseSetting.pathResource = pathResource;
        responseSetting.status = status;
        responseSetting.proxy = proxy;
        responseSetting.headers = headers;
        responseSetting.cookies = cookies;
        responseSetting.latency = latency;
        responseSetting.version = version;
        responseSetting.json = json;
        responseSetting.attachment = attachment;
        responseSetting.seq = seq;
        responseSetting.cycle = cycle;

        return responseSetting;
    }

    @Override
    public String toString() {
        return toStringHelper()
                .add("version", version)
                .add("status", status)
                .add("headers", headers)
                .add("cookies", cookies)
                .add("proxy", proxy)
                .add("latency", latency)
                .add("attachment", attachment)
                .add("seq", seq)
                .add("cycle", cycle)
                .toString();
    }

    public ResponseHandler getResponseHandler() {
        return factory.createResponseHandler(this);
    }
}
