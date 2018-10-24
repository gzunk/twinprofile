package com.gzunk.twinprofile.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import java.util.Arrays;
import java.util.List;

@Configuration
public class PropertyFiles {

    List<String> environments = Arrays.asList("local","prod","qa");
    List<String> reconciliations = Arrays.asList("atob","btoc","ctod");

    @Bean
    PropertyPlaceholderConfigurer placeholderConfigurer(@Autowired Environment env) {
        PropertyPlaceholderConfigurer placeholderConfigurer = new PropertyPlaceholderConfigurer();
        String[] activeProfiles = env.getActiveProfiles();

        // Find out which environment we are in
        String environment = Arrays.stream(activeProfiles).filter(e -> environments.contains(e)).findFirst().get();

        // Find out which recon
        String reconciliation = Arrays.stream(activeProfiles).filter(r -> reconciliations.contains(r)).findFirst().get();

        // Build up the property files
        String propertyFileCombined = "/properties/combined/" + environment + "-" + reconciliation + ".properties";
        String propertyFileEnvironment = "/properties/environment/" + environment + ".properties";
        String propertyFileReconciliation = "/properties/reconciliations/" + reconciliation + ".properties";

        placeholderConfigurer.setLocations(
                new ClassPathResource(propertyFileCombined),
                new ClassPathResource(propertyFileEnvironment),
                new ClassPathResource(propertyFileReconciliation));

        return placeholderConfigurer;
    }

}
