package com.talento_tech.mercado_liebre.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Indica a Spring que esta clase contiene definiciones de beans y configuraciones
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica CORS a todas las rutas de tu API
                .allowedOrigins("http://localhost:4200", "http://localhost:5173") // **MUY IMPORTANTE: Reemplaza con los orígenes reales de tu frontend**
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
                .allowedHeaders("*") // Cabeceras permitidas
                .allowCredentials(true) // Permite el envío de credenciales (cookies, encabezados de autorización)
                .maxAge(3600); // Tiempo en segundos que la respuesta preflight puede ser almacenada en caché (opcional)
    }
}
