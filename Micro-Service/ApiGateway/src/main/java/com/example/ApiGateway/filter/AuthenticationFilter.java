package com.example.ApiGateway.filter;

import com.example.ApiGateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtUtil jwtUtil;


    public AuthenticationFilter() {

        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        System.out.println("\"hello\" = " + "hello");
        return (((exchange, chain) -> {
                if(routeValidator.isSecured.test(exchange.getRequest()))
                {

                    //header contains token or not
                    if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
                    {
                         throw  new RuntimeException("Missing Authorization header");
                    }

                    String authHeaders=exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                    if(authHeaders!=null && authHeaders.startsWith("Bearer "))
                    {
                        authHeaders=authHeaders.substring(7);

                    }
                    try
                    {
                        //restTemplate.getForObject("http://SECURITY-SERVICE/"+authHeaders, String.class);
                        jwtUtil.validateToken(authHeaders);
                    }
                    catch (Exception e)
                    {
                            throw  new RuntimeException("Un Authorize");
                    }
                }

                return chain.filter(exchange);
            }));
    }


    public  static class Config
    {

    }
}
