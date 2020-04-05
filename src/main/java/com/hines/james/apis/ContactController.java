package com.hines.james.apis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("contact")
@RequiredArgsConstructor
@Slf4j
public class ContactController {
    private final ContactRepository contactRepository;
    private final EmailService emailService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void saveContact(@RequestBody ContactDto contactDto) {
        new Thread(() -> {
            log.info("Saving {} to the contact table", contactDto);

            contactRepository.save(new Contact(contactDto.getName(),
                    contactDto.getEmail(),
                    String.join(",", contactDto.getBusinessAreas())));
        }).start();

        new Thread(() -> {
            log.info("Sending an email from {} to james@jhinesconsulting.com", contactDto.getEmail());

            emailService.sendSimpleMessage("james@jhinesconsulting.com",
                    "Someone wants to get in touch!",
                    "Name: " + contactDto.getName() + "\nEmail: " + contactDto.getEmail() + "\nBusiness areas I would like to improve: " + String.join(",", contactDto.getBusinessAreas()));

            log.info("Successfully sent an email from {} to james@jhinesconsulting.com", contactDto.getEmail());
        }).start();
    }
}
