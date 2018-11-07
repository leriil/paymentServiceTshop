package com.tsystems.services.payment.paymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.any;

@SpringBootApplication
//documentation enabled
@EnableSwagger2
public class PaymentServiceApplication {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("Payment").select()
                .apis(RequestHandlerSelectors.basePackage("com.tsystems.services.payment.paymentservice"))
                .paths(any()).build().apiInfo(new ApiInfo("Payment Service",
                        "An Imitation of a payment service for Tshop", "1.0.0", null,
                        new Contact("Frank Moley", "https://twitter.com/fpmoles", "frank.moley@yahoo.com"),null, null));
    }


    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }
}
