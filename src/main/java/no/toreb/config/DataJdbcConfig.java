package no.toreb.config;

import lombok.RequiredArgsConstructor;
import no.toreb.domain.Todo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.data.relational.core.mapping.event.BeforeConvertCallback;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.IdGenerator;

import java.util.Optional;
import java.util.function.Supplier;

@Configuration
@EnableJdbcAuditing
@RequiredArgsConstructor
class DataJdbcConfig {

    private final IdGenerator idGenerator;

    private final Supplier<SecurityContext> securityContextSupplier;

    @Bean
    BeforeConvertCallback<Todo> beforeConvertCallback() {
        return todo -> {
            if (todo.getId() != null) {
                return todo;
            }

            return todo.toBuilder()
                       .id(idGenerator.generateId())
                       .build();
        };
    }

    @Bean
    AuditorAware<String> springSecurityAuditorAware() {
        return () -> Optional.ofNullable(securityContextSupplier.get().getAuthentication())
                             .filter(Authentication::isAuthenticated)
                             .map(authentication -> (UserDetails) authentication.getPrincipal())
                             .map(UserDetails::getUsername);
    }
}
