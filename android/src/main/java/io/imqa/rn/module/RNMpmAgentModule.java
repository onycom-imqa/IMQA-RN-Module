
package io.imqa.rn.module;

import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import io.imqa.core.dump.ActivityRenderData;
import io.imqa.core.http.HttpCollector;
import io.imqa.core.http.HttpData;
import io.imqa.mpm.IMQAMpmAgent;

public class RNMpmAgentModule extends ReactContextBaseJavaModule {
  RNMpmAgentModule(ReactApplicationContext context) {
    super(context);
  }

  private HttpData httpData = null;
  private String screenName = "";

  @Override
  public String getName() {
    return "RNMpmAgentModule";
  }

  @ReactMethod
  public void startReactNativeRender(String componentName) {
    IMQAMpmAgent.getInstance().startRender(getReactApplicationContext().getCurrentActivity(), ActivityRenderData.CREATED);
    IMQAMpmAgent.getInstance().endRender(getReactApplicationContext().getCurrentActivity(), ActivityRenderData.CREATED);
    IMQAMpmAgent.getInstance().startRender(getReactApplicationContext().getCurrentActivity(), ActivityRenderData.STARTED);
    IMQAMpmAgent.getInstance().endRender(getReactApplicationContext().getCurrentActivity(),ActivityRenderData.STARTED);
    IMQAMpmAgent.getInstance().startRender(getReactApplicationContext().getCurrentActivity(), ActivityRenderData.RESUMED);
  }

  @ReactMethod
  public void endReactNativeRender(String componentName) {
    IMQAMpmAgent.getInstance().endRender(getReactApplicationContext().getCurrentActivity(),ActivityRenderData.RESUMED);
  }

  /**
   *  android native 코드의 onCreate 시점 화면 rendering 이 시작될 시점에 호출
   */
  @ReactMethod
  public void startCreateRender() {
    IMQAMpmAgent.getInstance().startRender(getReactApplicationContext().getCurrentActivity(), ActivityRenderData.CREATED);
    Log.d("ImqaWrappingClass", "OnCreate Start Render");
  }

  /**
   * android native 코드의 onCreate 시점 화면 rendering 이 종료된 시점에 호출
   */
  @ReactMethod
  public void endCreateRender() {
    IMQAMpmAgent.getInstance().endRender(getReactApplicationContext().getCurrentActivity(), ActivityRenderData.CREATED);
    Log.d("ImqaWrappingClass", "OnCreate End Render");
  }

  /**
   * android native 코드의 onStart 시점 화면 rendering 이 시작될 시점에 호출
   */
  @ReactMethod
  public void startRender(){
    IMQAMpmAgent.getInstance().startRender(getReactApplicationContext().getCurrentActivity(), ActivityRenderData.STARTED);
    Log.d("ImqaWrappingClass", "OnStart Start Render");
  }

  /**
   * android native 코드의 onStart 시점 화면 rendering 이 종료된 시점에 호출
   */
  @ReactMethod
  public void endRender(){
    IMQAMpmAgent.getInstance().endRender(getReactApplicationContext().getCurrentActivity(),ActivityRenderData.STARTED);
    Log.d("ImqaWrappingClass", "OnStart End Render");
  }

  /**
   * android native 코드의 onResume 시점 화면 rendering 이 시작될 시점에 호출
   */
  @ReactMethod
  public void startResumeRender(){
    IMQAMpmAgent.getInstance().startRender(getReactApplicationContext().getCurrentActivity(),ActivityRenderData.RESUMED);
    Log.d("ImqaWrappingClass", "OnResume Start Render");
  }

  /**
   * android native 코드의 onResume 시점 화면 rendering 이 종료된 시점에 호출
   */
  @ReactMethod
  public void endResumeRender(){
    IMQAMpmAgent.getInstance().endRender(getReactApplicationContext().getCurrentActivity(),ActivityRenderData.RESUMED);
    Log.d("ImqaWrappingClass", "OnResume End Render");
  }

  /**
   * React Native axios, fetch 네트워크 통신 이전에 호출 ( 네트워크 통신 시작 시점 )
   * @param startTime 네트워크 통신 시작 시간 (요청 직전) (1682335612)
   * @param hostName 호스트 명 ( http://imqa.io )
   * @param pathName URL path ( /api/smaple )
   * @param method method ( GET, POST, PUT, DELETE )
   * @param protocol protocol ( http, https )
   */
  @ReactMethod
  public void startReactNativeNetwork(String startTime, String hostName,String pathName, String method, String protocol){
    httpData = new HttpData();
    httpData.setStartTime(Long.parseLong(startTime));
    httpData.setHostName(hostName);
    httpData.setPathName(pathName);
    httpData.setMethod(method);
    httpData.setProtocol(protocol);
    Log.d("ImqaWrappingClass", "startNetworkRender");
  }

  /**
   * 네트워크 통신 후 응답 시점 호출 ( 네트워크 통신 종료 시점 )
   * @param status 응답 코드 (200,500,404,400 ...)
   */
  @ReactMethod
  public void endReactNativeNetwork(String status, String endTime){
    httpData.setStatus(status);
    httpData.setEndTime(Long.parseLong(endTime));
    HttpCollector.collect(httpData);
    Log.d("ImqaWrappingClass", "endNetworkRender");
  }
}
