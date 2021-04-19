package com.demo.products.proxies;

import com.demo.products.controllers.CustomException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class DrmClientErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {

        if (403 == response.status()) {

            return CustomException.forbidden("Sem acesso para executar essa operação");
        }

        if (401 == response.status()) {

            return CustomException.forbidden("401");
        }

        return defaultErrorDecoder.decode(s, response);
    }

}