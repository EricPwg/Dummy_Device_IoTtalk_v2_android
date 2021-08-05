# Dummy_Device_IoTtalk_v2_android
兩種 Android 版本的範例 activity
* Dummy Device
  * IDF 依照 push interval 數值自動 push。
* Dummy Device Event Driven
  * IDF 需要 push 時，透過 dan.push。

## How to use
將兩個範例的 `...Activity.java`, `SA_...java`, `<layout>.xml` 檔案移動至 Android Project 中適當位置。

## 需要的 jar 函式庫
* [iottalk](https://github.com/IoTtalk/iottalk-java)
* [org.json](https://mvnrepository.com/artifact/org.json/json) : 版本需求 >= 20131018 , 預設版本 : 20210307
* [org.eclipse.paho.client.mqttv3](https://mvnrepository.com/artifact/org.eclipse.paho/org.eclipse.paho.client.mqttv3/1.2.5) : 版本需求 >= 1.2.5 , 預設版本 : 1.2.5
