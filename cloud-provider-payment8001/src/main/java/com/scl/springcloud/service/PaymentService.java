package com.scl.springcloud.service;

import com.scl.springcloud.entities.Payment;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/3/8
 * Description:
 */
public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(Long id);
}
