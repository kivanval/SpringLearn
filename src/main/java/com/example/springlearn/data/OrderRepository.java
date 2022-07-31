package com.example.springlearn.data;

import com.example.springlearn.domain.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
