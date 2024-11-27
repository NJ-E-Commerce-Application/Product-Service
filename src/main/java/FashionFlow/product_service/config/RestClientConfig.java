package FashionFlow.product_service.config;

import FashionFlow.product_service.client.inventoryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {
    @Value("${inventory.url}")
    private String inventoryServiceUrl;
    @Bean
    public inventoryClient inventoryClient(){
        RestClient restClient=RestClient.builder()
                .baseUrl(inventoryServiceUrl)
                .build();
        var restClientAdapter= RestClientAdapter.create(restClient);
        var httpServiceProxyFactory= HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return httpServiceProxyFactory.createClient(inventoryClient.class);
    }
}
