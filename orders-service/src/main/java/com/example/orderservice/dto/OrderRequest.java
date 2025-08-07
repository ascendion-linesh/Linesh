package com.example.orderservice.dto;

import com.example.orderservice.entity.CartItem;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class OrderRequest {
    @NotNull
    private Long userId;

    @NotEmpty
    private List<CartItem> cartItems;
}
