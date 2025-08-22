package com.rishman.blog.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "📘 Blog App APIs",
                version = "v1.0.0",
                description = """
                        🚀 **Welcome to the Blog App API Documentation**

                        This API supports:
                        - 📝 Blog Post Management
                        - 👥 User Management
                        - 🔐 JWT-based Secure Endpoints

                        👉 Use the **Authorize** button to test secured endpoints.
                        Format: `Bearer <your-token>`
                        """,
                contact = @Contact(
                        name = "Rishabh Srivastava",
                        email = "rishabhchandra2408@gmail.com",
                        url = "https://github.com/rishman-blog-app-apis"
                )
        ),
        security = @SecurityRequirement(name = "bearerAuth")  // Applies globally
)
public class SwaggerConfig {
    // Nothing else needed here
}