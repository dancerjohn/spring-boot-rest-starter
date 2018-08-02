package org.example;

import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;

import org.springframework.context.annotation.Configuration;

/**
 * This is here because when using some version of Spring Boot with metrics-spring, an infinite
 * loop can result if EnableMetrics is added to a "normal" configuration class.
 */
@Configuration
@EnableMetrics(proxyTargetClass = true)
public class MetricsConfiguration extends MetricsConfigurerAdapter {
}
