package com.shop.grocery.service;

import com.shop.grocery.dto.OrderItemDTO;
import com.shop.grocery.dto.OrderPackageDTO;
import com.shop.grocery.model.*;
import com.shop.grocery.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderPackageRepository orderPackageRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductPackageRepository productPackageRepository;

    @Override
    public Order saveOrder(List<OrderItemDTO> orderItems) {

        Order order = new Order();

        for(int i=0; i<orderItems.size(); i++){
            OrderItemDTO item = orderItems.get(i);
            Optional<Product> product = productRepository.findByCode(item.getCode());
            Double price = 0d;

            if(product.isPresent()){
                List<OrderPackage> packages = new ArrayList<>();
                List<ProductPackage> productPackages = productPackageRepository.findAllByCode(item.getCode());
                productPackages = productPackages.stream().sorted(
                        Comparator.comparingInt(ProductPackage::getQuantity).reversed()
                ).collect(Collectors.toUnmodifiableList());

                int totalQuantity = item.getQuantity();
                for(ProductPackage pkg : productPackages){
                    OrderPackage orderPkg = null;
                    while (totalQuantity>0){
                        if(pkg.getQuantity() <= totalQuantity){
                            if(orderPkg != null){
                                orderPkg.setPackagesQuantity(orderPkg.getPackagesQuantity()+1);
                                orderPkg.setPackagesPrice(orderPkg.getPackagesPrice()+pkg.getPrice());
                            }
                            else{
                                orderPkg = OrderPackage.builder()
                                        .product(product.get())
                                        .itemQuantity(pkg.getQuantity())
                                        .itemPrice(pkg.getPrice())
                                        .packagesQuantity(1)
                                        .packagesPrice(pkg.getPrice())
                                        .order(order)
                                        .build();
                                packages.add(orderPkg);
                            }
                            totalQuantity = totalQuantity - pkg.getQuantity();

                        }
                        else
                            break;
                    }
                }
                if(totalQuantity > 0){ // if no package found or items remaining
                    packages.add(OrderPackage.builder()
                            .product(product.get())
                            .itemQuantity(1)
                                    .itemPrice(product.get().getPrice())
                            .packagesQuantity(totalQuantity)
                            .packagesPrice(product.get().getPrice() * totalQuantity)
                                    .order(order)
                            .build()
                    );
                }
                order.getOrderItems().addAll(packages);

            }
            else
                throw new RuntimeException("Invalid product " + item.getCode());
        }
        Order savedOrder = orderRepository.saveAndFlush(order);
        return getOrder(savedOrder.getId());
    }

    @Override
    public Order getOrder(Long orderId) {
        Optional<com.shop.grocery.model.Order> order = orderRepository.findById(orderId);
        return order.orElse(null);
    }
}
