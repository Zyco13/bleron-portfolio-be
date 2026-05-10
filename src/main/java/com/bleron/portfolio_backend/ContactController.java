package com.bleron.portfolio_backend;

import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = {
        "http://localhost:3000",
        "https://bleron-portfolio-fe.vercel.app",
        "https://bleron-portfolio-jtzpjd6mg-bleron-s-projects.vercel.app"
})
public class ContactController {

    private final JavaMailSender mailSender;

    public ContactController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody ContactRequest request) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("bleronzekaj17@gmail.com");
        mail.setTo("bleronzekaj@icloud.com");
        mail.setSubject("Portfolio - meddelande från " + request.getName());
        mail.setText(
                "Namn: " + request.getName() + "\n" +
                        "Email: " + request.getEmail() + "\n\n" +
                        request.getMessage()
        );

        mailSender.send(mail);
        return ResponseEntity.ok("Skickat!");
    }
}