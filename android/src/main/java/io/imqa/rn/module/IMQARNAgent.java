package io.imqa.rn.module;

import static io.imqa.core.util.Constants.IMQA_MPM_TAG;

import android.app.Application;

import io.imqa.core.IMQAOption;
import io.imqa.core.dump.Resource.activity.ActivityStack;
import io.imqa.core.util.Logger;
import io.imqa.mpm.collector.BehaviorFileManager;
import io.imqa.mpm.collector.CollectorManager;
import io.imqa.rn.module.dump.ComponentRenderData;
import io.imqa.rn.module.dump.ComponentStack;

public class IMQARNAgent {

    private static long startComponentTime;
    private static long endComponentTime;

    private static IMQARNAgent mIMQARnAgnet = new IMQARNAgent();

    private IMQARNAgent() {}

    public static IMQARNAgent getInstance(){
        return mIMQARnAgnet;
    }

    public void startComponentRender(String componentName, boolean isParents){
        startComponentTime = System.currentTimeMillis();
        Logger.d(IMQA_MPM_TAG,"START Component Time" + " Component Name : " + componentName + " / isParents : " + isParents + " / Time" + startComponentTime);
    }

    public void endComponentRender(String componentName, boolean isParents) {
                ComponentStack.getInstance().setCurrentComponent(componentName);
                endComponentTime = System.currentTimeMillis();
                Logger.d(IMQA_MPM_TAG, "END Component Time" + " Component Name : " + componentName + " / isParents : " + isParents + " / Time" + endComponentTime);
                ComponentRenderData data =
                        new ComponentRenderData(
                                componentName,
                                isParents,
                                startComponentTime,
                                endComponentTime
                        );

                if (BehaviorFileManager.getInstance().getCurrentBehavior() != null){
                    data.setBehaviorTxId(BehaviorFileManager.getInstance().getCurrentBehavior().getBehaviorTxId());
                }

                CollectorManager.getInstance().collect(data);
    }

    public void projectInit(Application context,String serverUrl,String crashServerUrl, String projectKey){

        io.imqa.core.IMQAOption imqaOption = new io.imqa.core.IMQAOption();
        imqaOption.setBuildType(false);
        imqaOption.setUploadPeriod(true);
        imqaOption.setKeepFileAtUploadFail(false);
        imqaOption.setHttpTracing(true);
        imqaOption.setPrintLog(true);
        imqaOption.setBehaviorTracing(true);
        imqaOption.setEventTracing(true);
        imqaOption.setDumpInterval(5000);
        imqaOption.setFileInterval(5);
        imqaOption.setForceHttps(true);

        if(serverUrl != null) {
            imqaOption.setServerUrl(serverUrl);
        }

        if(crashServerUrl != null) {
            imqaOption.setCrashServerUrl(serverUrl);
        }

        io.imqa.mpm.IMQAMpmAgent.getInstance()
                .setOption(imqaOption) // MPM 의 동작 방식을 정하는 옵션을 설정합니다.
                .setContext(context, "") // Application Context 를 초기화 합니다.
                .setProjectKey(projectKey) // IMQA MPM Client 의 Project Key 를 설정합니다.
                .init();
    }

}
