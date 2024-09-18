# Apollo-Metrics-Poc
该仓库基于Springboot搭建了一个简易的测试应用，需事先打包开发仓库的代码为2.4.0-SNAPSHOT版本，然后在application.yml中选择对应配置的配置文件即可快速查看效果。

提供以下测试场景

- 不使用monitor功能 （no-change）
- 错误配置monitor (error-internal-type)
- 启用jmx  (jmx-only)
- 启用prometheus   (prometheus-only)
- 同时启用jmx和prometheus   (jmx-prometheus)
- 同时启用jmx和prometheus并配置了500个错误的命名空间（jmx-prometheus-500-error-namespace）
