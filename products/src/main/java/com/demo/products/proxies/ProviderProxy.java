package com.demo.products.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.demo.products.config.ClientConfiguration;
import com.demo.products.models.Provider;

@FeignClient(name = "providerProxy", url = "${microservice.providers.url}", configuration = {ClientConfiguration.class})
public interface ProviderProxy {

	@GetMapping(value = "/providers/{id}")
	public ResponseEntity<Provider> getDetails(@PathVariable("id") String id);
}
