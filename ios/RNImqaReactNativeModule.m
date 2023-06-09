
#import "RNImqaReactNativeModule.h"
#import <IMQAMPMAgent/IMQAMPMAgent.h>

@implementation RNImqaReactNativeModule

RCT_EXPORT_MODULE(MpmAgent)

RCT_EXPORT_METHOD(setBehaviorData:(NSString *)componentName)
{
    NSLog(@"[IMQA] SET BEHAVIOR DATA : %@", componentName);
}

RCT_EXPORT_METHOD(startReactNativeRender:(NSString *)componentName isParents:(BOOL)isParents)
{
    NSLog(@"[IMQA] START Component Render : %@", componentName);
    [[IMQAMpm sharedInstance] startComponent:componentName :isParents];
}

RCT_EXPORT_METHOD(endReactNativeRender:(NSString *)componentName isParents:(BOOL)isParents)
{
    NSLog(@"[IMQA] END Component Render : %@", componentName);
    [[IMQAMpm sharedInstance] endComponent:componentName :isParents];
}

RCT_EXPORT_METHOD(startReactNativeNetwork:(NSString *)componentName)
{
    NSLog(@"[IMQA] START Network : %@", componentName);
}

RCT_EXPORT_METHOD(endReactNativeNetwork:(NSString *)componentName)
{
    NSLog(@"[IMQA] END Network : %@", componentName);
}

@end
  
