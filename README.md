# Apollo-Metrics-Poc
启动TestApplication后，会暴露出8080端口的4个接口，/test,/test1,/test2,/response
前三个接口用于测试触发指标收集，最后一个接口用于返回Prometheus格式的指标数据
支持Jconsole,VisualVM查看指标
