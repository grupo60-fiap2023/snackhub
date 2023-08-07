package com.snackhub.infrastructure.api.controllers;

import com.snackhub.application.order.create.CreateOrderCommand;
import com.snackhub.application.order.create.CreateOrderUseCase;
import com.snackhub.application.order.retrieve.FindAllOrdersUseCase;
import com.snackhub.application.order.retrieve.FindOrdersByStatusUseCase;
import com.snackhub.application.order.retrieve.FindPaymentStatusByOrderIdUseCase;
import com.snackhub.application.order.update.UpdateOrderStatusCommand;
import com.snackhub.application.order.update.UpdateOrderStatusUseCase;
import com.snackhub.domain.exceptions.DomainException;
import com.snackhub.domain.order.OrderStatus;
import com.snackhub.infrastructure.api.OrderAPI;
import com.snackhub.infrastructure.order.models.CreateOrderRequest;
import com.snackhub.infrastructure.order.models.OrderResponse;
import com.snackhub.infrastructure.order.models.UpdateStatusRequest;
import com.snackhub.infrastructure.order.presenters.OrderApiPresenter;
import com.snackhub.infrastructure.order.presenters.OrderItemApiPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class OrderController implements OrderAPI {

    private final CreateOrderUseCase createOrderUseCase;
    private final FindAllOrdersUseCase findAllOrdersUseCase;
    private final FindOrdersByStatusUseCase findOrdersByStatusUseCase;
    private final UpdateOrderStatusUseCase updateOrderStatusUseCase;

    private final FindPaymentStatusByOrderIdUseCase findPaymentStatusByOrderIdUseCase;

    public OrderController(final CreateOrderUseCase createOrderUseCase,
                           final FindAllOrdersUseCase findAllOrdersUseCase,
                           final FindOrdersByStatusUseCase findOrdersByStatusUseCase,
                           final UpdateOrderStatusUseCase updateOrderStatusUseCase,
                           FindPaymentStatusByOrderIdUseCase findPaymentStatusByOrderIdUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.findAllOrdersUseCase = findAllOrdersUseCase;
        this.findOrdersByStatusUseCase = findOrdersByStatusUseCase;
        this.updateOrderStatusUseCase = updateOrderStatusUseCase;
        this.findPaymentStatusByOrderIdUseCase = findPaymentStatusByOrderIdUseCase;
    }

    @Override
    public ResponseEntity<?> createOrder(CreateOrderRequest request) {
        OrderResponse response;
        try{
            var items = OrderItemApiPresenter.present(request.items());
            var command = CreateOrderCommand.with(items, request.customerId(), request.observation());
            var order = this.createOrderUseCase.execute(command);
            response = OrderApiPresenter.present(order);
        }catch (DomainException e){
            return ResponseEntity.unprocessableEntity().body(e.getErrors());
        }

        return ResponseEntity.created(URI.create("/orders" + response.id())).body(response);
    }

    @Override
    public ResponseEntity<List<OrderResponse>> listAllOrders() {
        return ResponseEntity.ok().body(this.findAllOrdersUseCase.execute().stream().map(OrderApiPresenter::present).toList());
    }

    @Override
    public ResponseEntity<List<OrderResponse>> listOrdersByStatus(OrderStatus status) {
        return ResponseEntity.ok().body(this.findOrdersByStatusUseCase.execute(status).stream().map(OrderApiPresenter::present).toList());
    }

    @Override
    public ResponseEntity<?> updateStatusById(Long id, UpdateStatusRequest request) {
        OrderResponse output;
        try{
            var command = UpdateOrderStatusCommand.with(id, request.status());
            var order = this.updateOrderStatusUseCase.execute(command);
            output = OrderApiPresenter.present(order);
        }catch (DomainException e){
            return ResponseEntity.unprocessableEntity().body(e.getErrors());
        }

        return ResponseEntity.ok().body(output);
    }

    @Override
    public ResponseEntity<?> getPaymentStatusById(Long id) {
        try{
            return ResponseEntity.ok().body(this.findPaymentStatusByOrderIdUseCase.execute(id));
        }catch (DomainException e){
            return ResponseEntity.unprocessableEntity().body(e.getErrors());
        }
    }
}
