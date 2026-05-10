package com.bleron.portfolio_backend;
import com.resend.Resend;
import com.resend.services.emails.model.CreateEmailOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = {
        "http://localhost:3000",
        "https://bleron-portfolio-fe.vercel.app",
        "https://bleron-portfolio-jtzpjd6mg-bleron-s-projects.vercel.app"
})
public class ContactController {

    @Value("${RESEND_API_KEY:}")
    private String resendApiKey;

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody ContactRequest request) {
        try {
            Resend resend = new Resend(resendApiKey);

            CreateEmailOptions emailRequest = CreateEmailOptions.builder()
                    .from("onboarding@resend.dev")
                    .to("bleronzekaj@icloud.com")
                    .subject("Portfolio - meddelande från " + request.getName())
                    .html(
                            "<p><strong>Namn:</strong> " + request.getName() + "</p>" +
                                    "<p><strong>Email:</strong> " + request.getEmail() + "</p>" +
                                    "<p><strong>Meddelande:</strong> " + request.getMessage() + "</p>"
                    )
                    .build();

            resend.emails().send(emailRequest);
            return ResponseEntity.ok("Skickat!");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Fel: " + e.getMessage());
        }
    }
}