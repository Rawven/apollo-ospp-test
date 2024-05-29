# Apollo-Metrics-Poc
启动TestApplication后，会暴露出8080端口的3个接口，/test,/test2,/response,
前两个方法用于测试触发指标收集，最后一个方法用于返回Prometheus格式的指标数据
支持Jconsole,VisualVM查看指标
