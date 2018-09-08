# sc
Spring Boot Base Project 

### sc note

* sc 是外面的总的空项目
* transport 是里面的分项目


### Start
* `TransportApplication` 是总的启动类


### Shiro
`配置参照的 github的项目 后期你可以在基础上增加配置，现在的比较简陋 地址` 
[shiro项目参照地址](https://github.com/HowieYuan/Shiro-SpringBoot)
`shiro` 相关的示例类

``` 
AdminController 
ExceptionController
GuestController
LoginController
UserController

```

`Postgresql` 集成好了

`Mybatis` 集成好了

* `/sc/transport/src/main/resources/mapper` 是 Mybatis 的配置xml的文件夹

`memcached ` 没集成,用的 `redis` 替代做缓存数据库



### [Swagger](https://blog.csdn.net/saytime/article/details/74937664)

`Swagger` 相关示例在 `AppController.class` 61行以后, 集成了但是没出来效果

查看地址`http://localhost:8088/swagger-ui.html`

```
swagger通过注解表明该接口会生成文档，包括接口名、请求方法、参数、返回信息的等等。

@Api：修饰整个类，描述Controller的作用
@ApiOperation：描述一个类的一个方法，或者说一个接口
@ApiParam：单个参数描述
@ApiModel：用对象来接收参数
@ApiProperty：用对象接收参数时，描述对象的一个字段
@ApiResponse：HTTP响应其中1个描述
@ApiResponses：HTTP响应整体描述
@ApiIgnore：使用该注解忽略这个API
@ApiError ：发生错误返回的信息
@ApiImplicitParam：一个请求参数
@ApiImplicitParams：多个请求参数

```