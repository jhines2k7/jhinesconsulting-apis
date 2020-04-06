package com.hines.james.apis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("contact")
@RequiredArgsConstructor
@Slf4j
public class ContactController {
    private final ContactRepository contactRepository;
    private final EmailService emailService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void saveContact(@Valid @RequestBody ContactRequestData contactRequestData) {
        Thread saveContactThread = new Thread(() -> {
            log.info("Saving {} to the contact table", contactRequestData);

            contactRepository.save(new Contact(contactRequestData.getName(),
                    contactRequestData.getEmail(),
                    String.join(",", contactRequestData.getBusinessAreas())));
        });

        saveContactThread.setName("saveContact");

        Thread sendEmailThread = new Thread(() -> {
            log.info("Sending an email from {} to james@jhinesconsulting.com", contactRequestData.getEmail());

            emailService.sendSimpleMessage("james@jhinesconsulting.com",
                    "Someone wants to get in touch!",
                    "Name: " + contactRequestData.getName() + "\nEmail: " + contactRequestData.getEmail() + "\nBusiness areas I would like to improve: " + String.join(",", contactRequestData.getBusinessAreas()));

            log.info("Successfully sent an email from {} to james@jhinesconsulting.com", contactRequestData.getEmail());
        });

        sendEmailThread.setName("sendEmail");

        saveContactThread.start();
        sendEmailThread.start();
    }
}
