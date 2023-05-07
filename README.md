
# imqa-react-native-module


## Getting started

`$ npm install imqa-react-native-module --save`

## Usage


### Measure screen rendering time

```javascript

import MpmAgent from ‘imqa-react-native-module’;


class ParentsComponent extends React.Component {

	...

	componentDidMount() {
		MpmAgent?.setBehaviorData("MyComponent");
		MpmAgent?.startReactNativeRender("MyComponent", true);

	...

		
		MpmAgent?.endReactNativeRender("MyComponent", true);
	}
	
}


}

```

```javascript

import MpmAgent from 'imqa-react-native-module';


class ChildComponent extends React.Component {

	...

	
	componentDidMount() {
		MpmAgent?.startReactNativeRender("MyChildComponent", false);
	...

		 
		MpmAgent?.endReactNativeRender("MyChildComponent", false);
	}

}


```


### Measuring Network Time

#### Axios 
```javascript

const axiosInstance = axios.create({
	baseURL:"http://sample-api"
});
axiosInstance.interceptors.request.use(
		(config) => {
			try{
				MpmAgent?.startReactNativeNetwork(
						config.baseURL?.toString(),
						config.url?.toString(),
						config.method?.toString(),
						config.baseURL?.split('://')[0]
				);
			}catch(e){
				console.error(e);
			}
			return config;
		},
		error => {
			MpmAgent?.endReactNativeNetwork("500");
		}
);
axiosInstance.interceptors.response.use( response => {
	MpmAgent?.endReactNativeNetwork(response.status.toString());
	return response;
});



```

#### Fetch
```javascript

let url = "http://sample-api";

MpmAgent?.startReactNativeNetwork(url, "/sample", "post", url?.split('://')[0]);

		fetch(url)
		.then(response => {
			MpmAgent?.endReactNativeNetwork(response.status.toString());
			return response;
		})
		.catch(error => {
			MpmAgent?.endReactNativeNetwork("500");
		});




```