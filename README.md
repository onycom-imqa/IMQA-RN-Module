
# imqa-react-native-module

## Getting started

`$ npm install imqa-react-native-module --save`

### Mostly automatic installation

`$ react-native link imqa-react-native-module`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `imqa-react-native-module` and add `RNMpmAgentModule.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNMpmAgentModule.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Append the following lines to `android/settings.gradle`:
  	```
  	include ':imqa-react-native-module'
  	project(':imqa-react-native-module').projectDir = new File(rootProject.projectDir, 	'../node_modules/imqa-react-native-module/android')
  	```
2. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      implementation project(':imqa-react-native-module')
  	```



## Usage
```javascript
import RNMpmAgentModule from 'imqa-react-native-module';

// TODO: What to do with the module?
RNMpmAgentModule;
```
