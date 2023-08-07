package com.snackhub.infrastructure.configuration.usecases;

import com.snackhub.application.order.create.CreateOrderUseCase;
import com.snackhub.application.order.retrieve.FindAllOrdersUseCase;
import com.snackhub.application.order.retrieve.FindOrdersByStatusUseCase;
import com.snackhub.application.order.retrieve.FindPaymentStatusByOrderIdUseCase;
import com.snackhub.application.order.update.UpdateOrderStatusUseCase;
import com.snackhub.domain.customer.CustomerGateway;
import com.snackhub.domain.order.OrderGateway;
import com.snackhub.domain.product.ProductGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderUseCaseConfig {

    private final OrderGateway orderGateway;
    private final CustomerGateway customerGateway;
    private final ProductGateway productGateway;

    public OrderUseCaseConfig(final OrderGateway orderGateway,
                              final CustomerGateway customerGateway,
                              final ProductGateway productGateway) {
        this.orderGateway = orderGateway;
        this.customerGateway = customerGateway;
        this.productGateway = productGateway;
    }

    @Bean
    public CreateOrderUseCase createOrderUseCase(){
        return new CreateOrderUseCase(orderGateway, customerGateway, productGateway);
    }

    @Bean
    public FindAllOrdersUseCase findAllOrders() {
        return new FindAllOrdersUseCase(orderGateway);
    }

    @Bean
    public FindOrdersByStatusUseCase findOrdersByStatus() {
        return new FindOrdersByStatusUseCase(orderGateway);
    }

    @Bean
    public UpdateOrderStatusUseCase updateOrderStatus() {
        return new UpdateOrderStatusUseCase(orderGateway);
    }

    @Bean
    public FindPaymentStatusByOrderIdUseCase findPaymentStatusByOrderIdUseCase(){
        return new FindPaymentStatusByOrderIdUseCase(orderGateway);
    }
}
