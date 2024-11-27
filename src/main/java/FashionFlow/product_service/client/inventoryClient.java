package FashionFlow.product_service.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.service.annotation.PostExchange;

public interface inventoryClient {
    @PostExchange("/api/inventory/{skuCode}")
    void addToStock(@PathVariable("skuCode") String skuCode);
}