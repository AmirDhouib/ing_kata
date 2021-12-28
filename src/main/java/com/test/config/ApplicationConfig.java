package com.test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.math.BigDecimal;

/**
 * get application properties from application.properties
 */
@Configuration
@EnableAsync
public class ApplicationConfig {

    private final BigDecimal depositLimitValue;

    /**
     * constructor
     * @param depositLimitValue: minimum value for deposit money
     */
    public ApplicationConfig(@Value("${depositLimitValue}") BigDecimal depositLimitValue) {
        this.depositLimitValue = depositLimitValue;
    }

    /**
     * get defined value for minimum value of an deposit transaction
     * @return the value of minimum of deposit money
     */
    public BigDecimal getMinDepositValue() {
        return depositLimitValue;
    }
}




