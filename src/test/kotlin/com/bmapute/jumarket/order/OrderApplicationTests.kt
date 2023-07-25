package com.bmapute.jumarket.order

import com.bmapute.jumarket.order.domain.OrderTest
import com.bmapute.jumarket.order.domain.service.OrderDomainServiceTest
import org.junit.jupiter.api.Test
import org.junit.platform.suite.api.SelectClasses
import org.junit.platform.suite.api.Suite
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@Suite
@SelectClasses(value = [OrderTest::class,OrderDomainServiceTest::class])
class OrderApplicationTests {

}
