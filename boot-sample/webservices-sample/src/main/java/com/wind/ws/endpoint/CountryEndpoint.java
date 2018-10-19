package com.wind.es.endpoint;

import com.wind.es.CountryRepository;
import com.wind.es.service.GetCountryRequest;
import com.wind.es.service.GetCountryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * @Title: CountryEndpoint
 * @Package com.wind.es.endpoint
 * @Description: TODO
 * @author wind
 * @date 2018/10/19 10:02
 * @version V1.0
 */
@Endpoint
public class CountryEndpoint {

    private static final String NAMESPACE_URI = "http://wind.com/webservices/service";

    private CountryRepository countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));
        return response;
    }
}
