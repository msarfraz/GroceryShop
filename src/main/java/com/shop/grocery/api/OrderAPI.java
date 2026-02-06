package com.shop.grocery.api;

import com.shop.grocery.dto.OrderItemDTO;
import com.shop.grocery.dto.OrderPackageDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/orders")
public interface OrderAPI {

    @Operation(summary = "Get Order details", responses = {
            @ApiResponse(description = "Order Details", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderPackageDTO.class))),
    })
    @GetMapping("/{orderId}")
    public List<OrderPackageDTO> getOrder(@PathVariable Long orderId) ;

    @Operation(summary = "Create a new Order", responses = {
            @ApiResponse(description = "Order Created", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderItemDTO.class))),
    })
    @PostMapping("/")
    ResponseEntity<List<OrderPackageDTO>> createOrder(@RequestBody List<OrderItemDTO> order);

}
