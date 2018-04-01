package com.lardi.configuration;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabasePropertiesListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    private static final Logger LOG = Logger.getLogger(DatabasePropertiesListener.class.getName());

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(System.getProperty("lardi.conf")));
        } catch (IOException e){
            LOG.log(Level.SEVERE,"Error reading file.", e);
        }
        environment.getPropertySources().addFirst(new PropertiesPropertySource("properties", properties));
    }
}
