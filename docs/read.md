# My Frist Doc

##1.springboot
用于快速构建发布，安装了许多依赖，快速开发
##2.注解
* @Data :一般用于有成员的类上，简化（省去getter,setter的编写）
* @Builder: 直接用build生成一个对象  
```
eg: return HelloResponseDto.builder().addresss(entity.getAddr()).build();                              
```
* @AllArgsConstructor  全部参数的构造函数的自动生成
* @NoArgsConstructor  无参构造函数
* @Service 接口，实现
* @Component 
* @Mapper 映射
* @Configuration 配置类
```
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("chinatelecom")
//用于从配置文件读取属性，生成一个对象
```
* @Repository 数据
* @RestController 
* @RequestMapping 请求url
* @RequestBody 接收入参
* @PostMapping 请求地址
* @Validated 用于检验参数
* @Transactional 事务

