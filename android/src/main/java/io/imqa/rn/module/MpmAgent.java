
package io.imqa.rn.module;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import io.imqa.core.CoreContext;
import io.imqa.core.behavior.BehaviorData;
import io.imqa.core.http.HttpData;
import io.imqa.core.util.Logger;
import io.imqa.mpm.collector.BehaviorFileManager;
import io.imqa.mpm.collector.CollectorManager;
import io.imqa.rn.module.dump.network.RNHttpResponseData;

public class MpmAgent extends ReactContextBaseJavaModule {

    MpmAgent(ReactApplicationContext context) {
        super(context);
    }

    private HttpData httpData = null;

    @Override
    public String getName() {
        return "MpmAgent";
    }

    @ReactMethod
    public void setBehaviorData(String componentName) {
        if (CoreContext.getInstance().getAppContext() == null) return;

        BehaviorFileManager.getInstance().saveBehaviorData(
                new BehaviorData(
                        System.currentTimeMillis(),
                        componentName,
                        BehaviorFileManager.getInstance().currentDepth()
                )
        );
    }

    @ReactMethod
    public void startReactNativeRender(String componentName, boolean isParents) {
        IMQARNAgent.getInstance().startComponentRender(componentName, isParents);
    }

    @ReactMethod
    public void endReactNativeRender(String componentName, boolean isParents) {
        IMQARNAgent.getInstance().endComponentRender(componentName, isParents);
    }

    /**
     * React Native axios, fetch 네트워크 통신 이전에 호출 ( 네트워크 통신 시작 시점 )
     *
     * @param hostName 호스트 명 ( http://imqa.io )
     * @param pathName URL path ( /api/smaple )
     * @param method   method ( GET, POST, PUT, DELETE )
     * @param protocol protocol ( http, https )
     */
    @ReactMethod
    public void startReactNativeNetwork(String hostName, String pathName, String method, String protocol) {
        httpData = new HttpData();
        httpData.setStartTime(System.currentTimeMillis());
        httpData.setHostName(hostName);
        httpData.setPathName(pathName);
        httpData.setMethod(method);
        httpData.setProtocol(protocol);
        Logger.d("MpmAgent", "startReactNativeNetwork");
    }

    /**
     * 네트워크 통신 후 응답 시점 호출 ( 네트워크 통신 종료 시점 )
     *
     * @param status 응답 코드 (200,500,404,400 ...)
     */
    @ReactMethod
    public void endReactNativeNetwork(String status) {
        httpData.setStatus(status);
        httpData.setEndTime(System.currentTimeMillis());
        RNHttpResponseData data = new RNHttpResponseData(httpData);
        if (BehaviorFileManager.getInstance().getCurrentBehavior() != null) {
            data.setBehaviorTxId(BehaviorFileManager.getInstance().getCurrentBehavior().getBehaviorTxId());
        }
        CollectorManager.getInstance().collect(data);
        Logger.d("MpmAgent", "endReactNativeNetwork");
    }
}
