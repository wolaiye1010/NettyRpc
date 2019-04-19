# 轻量rpc
## Design:
![design](https://pic1.58cdn.com.cn/p1/big/n_v2f60ebe688293431288861996682c2429.jpg)
## How to use

1. Define an interface:
    ```java
            public interface HelloService { 
                String hello(String name); 
                String hello(Person person);
            }
    ```

2. Implement the interface with annotation @RpcService:
    ```java
            @RpcService(HelloService.class)
            public class HelloServiceImpl implements HelloService {
                public HelloServiceImpl(){}
                
                @Override
                public String hello(String name) {
                    return "Hello! " + name;
                }
    
                @Override
                public String hello(Person person) {
                    return "Hello! " + person.getFirstName() + " " + person.getLastName();
                }
            }
    ```

3. Run zookeeper

        For example: zookeeper is running on 127.0.0.1:2181

4. Start server:
    ```java
        RpcInit.builder()
                .setEnv("offline")
                .setServiceName("serviceName")
                .setServerAddress("127.0.0.1:18866")
                .setZkConnectionString("127.0.0.1:2181")
                .addService(HelloService.class.getName(),helloService)
                .serverInit();
    ```


5. Use the client:
    ```java
            RpcInit.builder()
                    .setEnv("offline")
                    .setServiceName("serviceName")
                    .setZkConnectionString("127.0.0.1:2181")
                    .clientInit();
            HelloService helloService = RpcClient.create(HelloService.class);
            String result = helloService.hello("World");
            Assert.assertEquals("Hello! World", result);
    ```

