
#import "RNImqaReactNativeModule.h"
// #import <IMQAMPMAgent/IMQAMPMAgent.h>

@implementation RNImqaReactNativeModule

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

RCT_EXPORT_METHOD(setBehaviorData:(NSString *)componentName)
{
}

RCT_EXPORT_METHOD(startReactNativeRender:(NSString *)componentName)
{
//   [[IMQAMpm sharedInstance] startScreen:screenName];
}

RCT_EXPORT_METHOD(endReactNativeRender:(NSString *)componentName)
{
//   [[IMQAMpm sharedInstance] endScreen:screenName];
}

RCT_EXPORT_METHOD(startReactNativeNetwork:(NSString *)componentName)
{
}

RCT_EXPORT_METHOD(endReactNativeNetwork:(NSString *)componentName)
{
}

RCT_EXPORT_MODULE()

@end
  