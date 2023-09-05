package io.imqa.rn.module.dump;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;

import io.imqa.core.CoreContext;
import io.imqa.core.dump.DumpData;
import io.imqa.core.dump.Resource.activity.ActivityStack;
import io.imqa.core.util.Logger;
import io.imqa.rn.module.util.Common;

public class ComponentRenderData implements DumpData {

    private String activityName; // 현재 Activity Name
    private String componentName; // 현재 Component Name
    private Long startTime;
    private Long endTime;
    private Boolean isParents; // 현재 컴포넌트가 최상위 컴포넌트인지
    String behaviorTxId;

    public ComponentRenderData() {
        this.activityName = "";
        this.componentName = "";
        this.startTime = (long) 0;
        this.endTime= (long) 0;
        this.isParents = false;
    }

    public ComponentRenderData(String activityName, Long startTime, Long endTime) {
        this.activityName = activityName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ComponentRenderData(String componentName, boolean isParents, Long startTime, Long endTime){
        this.componentName = componentName;
        this.isParents = isParents;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void setBehaviorTxId(String behaviorTxId) {
        this.behaviorTxId = behaviorTxId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Long getStartTime() {
        return startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public Boolean getParents() {
        return isParents;
    }

    public void setParents(Boolean parents) {
        isParents = parents;
    }

    @Override
    //type이 render인 dump JSON object 생성하기
    public JSONObject getDumpData() {
        JSONObject renderData = new JSONObject();

        try {
            //type
            renderData.put("type", "component_render");
            renderData.put("activity_name", ActivityStack.getInstance().getCurrentActivity());
            renderData.put("component_name", getComponentName());
            renderData.put("start_time", getStartTime());
            renderData.put("end_time", getEndTime());
            renderData.put("behaviorTxId", behaviorTxId);
            renderData.put("is_parents", getParents());
            renderData.put("txId", Common.getTxId());

        } catch (JSONException e) {
            StringWriter stacktrace = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stacktrace);
            e.printStackTrace(printWriter);
            Logger.e("ComponentRenderData", stacktrace.toString());
        }

        return renderData;
    }
}
