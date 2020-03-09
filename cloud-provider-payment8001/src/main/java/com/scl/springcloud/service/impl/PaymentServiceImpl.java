package com.scl.springcloud.service.impl;

import com.scl.springcloud.dao.PaymentDao;
import com.scl.springcloud.entities.Payment;
import com.scl.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/3/8
 * Description:
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    //    @Autowired
    @Resource
    PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
