package com.julianolarte.netflix.configurations;

import com.julianolarte.netflix.models.Profile;
import com.julianolarte.netflix.models.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RestConfiguration extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Profile.class)
                .exposeIdsFor(User.class);
    }
}
