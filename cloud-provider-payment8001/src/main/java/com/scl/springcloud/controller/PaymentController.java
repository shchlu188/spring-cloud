package com.scl.springcloud.controller;

import com.scl.springcloud.entities.CommonResult;
import com.scl.springcloud.entities.Payment;
import com.scl.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/3/8
 * Description:
 */
@RestController
@Slf4j
@RequestMapping("payment")
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value(value = "${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        services.stream().forEach((s) -> log.info("****element:" + s));

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        instances.stream().forEach((instance) ->
                log.info(instance.getHost() + "\t"
                        + instance.getInstanceId() + "\t"
                        + instance.getPort() + "\t"
                        + instance.getUri()
                ));
        return this.discoveryClient;

    }

    @PostMapping(value = "create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        int rest = paymentService.create(payment);
        log.info("*****插入结果" + rest);
        if (rest > 0) {
            return new CommonResult(200, "插入数据成功,serverPort:" + serverPort, rest);
        } else {
            return new CommonResult(444, "插入数据失败,serverPort:" + serverPort);
        }
    }

    @GetMapping("get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment result = paymentService.getPaymentById(id);
        log.info("***查询结果：" + result);

        if (result != null) {
            return new CommonResult(200, "查询成功,serverPort:" + serverPort, result);
        } else {
            return new CommonResult(444, "查询失败,没有对应的id:" + id + ",serverPort:" + serverPort);
        }
    }

}
