package io.imqa.rn.module.dump.network;

import org.json.JSONException;
import org.json.JSONObject;

import io.imqa.core.CoreContext;
import io.imqa.core.dump.DumpData;
import io.imqa.core.dump.Resource.activity.ActivityStack;
import io.imqa.core.http.HttpData;
import io.imqa.rn.module.dump.ComponentStack;
import io.imqa.rn.module.util.Common;

public class RNHttpResponseData implements DumpData {
    HttpData httpData;
    String behaviorTxId;

    public void setBehaviorTxId(String behaviorTxId) {
        this.behaviorTxId = behaviorTxId;
    }

    public RNHttpResponseData(HttpData httpData) {
        this.httpData = httpData;
    }

    @Override
    public JSONObject getDumpData() {
        JSONObject httpResponseData = new JSONObject();

        try {
            //type
            httpResponseData.put("type", "component_request");

            // network info
            httpResponseData.put("request_time", httpData.getStartTime());
            httpResponseData.put("response_time", httpData.getEndTime());
            httpResponseData.put("host", httpData.getHostName());
            httpResponseData.put("status", httpData.getStatus());
            httpResponseData.put("path_name", httpData.getPathName());
            httpResponseData.put("method", httpData.getMethod());
            httpResponseData.put("protocol", httpData.getProtocol());
            httpResponseData.put("port", httpData.getPort());
            httpResponseData.put("behaviorTxId", behaviorTxId);
            httpResponseData.put("txId", Common.getTxId());
            httpResponseData.put("screen_name", ActivityStack.getInstance().getCurrentActivity());
            httpResponseData.put("component_name", ComponentStack.getInstance().getCurrentComponent());

            if (CoreContext.getInstance().getOption().isHttpAddon()
                    && httpData.getAddonData() != null)
                httpResponseData.put("addonData", httpData.getAddonData().getData());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return httpResponseData;
    }


}
