
#import "RNImqaReactNativeModule.h"
#import <IMQAMPMAgent/IMQAMPMAgent.h>

@implementation RNImqaReactNativeModule

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

RCT_EXPORT_METHOD(startReactNativeRender:(NSString *)screenName)
{
  [[IMQAMpm sharedInstance] startScreen:screenName];
}

RCT_EXPORT_METHOD(endReactNativeRender:(NSString *)screenName)
{
  [[IMQAMpm sharedInstance] endScreen:screenName];
}

RCT_EXPORT_MODULE()

@end
  