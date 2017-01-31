package org.learn.prospring4.chapter16.mvc;

import org.apache.commons.io.IOUtils;
import org.learn.prospring4.chapter16.mvc.entities.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;


/**
 * Created by User on 22.01.2017.
 */

@Controller
@RequestMapping(value = "/contacts")
public class ContactController {
    final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        logger.info("Listing contacts");
        List<Contact> contacts = contactService.findAll();

        uiModel.addAttribute("contacts", contacts);
        uiModel.addAttribute("contact", !contacts.isEmpty() ? contacts.get(0) : null);
        logger.info("No. of contacts: " + contacts.size());
        return "contacts/list";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping( value = "/mylist", method = RequestMethod.GET)
    public String listAll(Model uiModel) {
        return "contacts/mylist";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid Contact contact, BindingResult bindingResult,
                         Model uiModel, HttpServletRequest httpServletRequest,
                         RedirectAttributes redirectAttributes, Locale locale,
                         @RequestParam(value = "file", required = false) Part file) {

        logger.info("Creating contact");
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("contact", contact);
            return "contacts/create";
        }

        uiModel.asMap().clear();
        logger.info("Contact id: " + contact.getId());

        if (file != null) {
            logger.info("File name: " + file.getName());
            logger.info("File size: " + file.getSize());
            logger.info("File content type: " + file.getContentType());
            byte[] fileContent = null;
            try {
                InputStream inputStream = file.getInputStream();
                if (inputStream == null) logger.info("File inputstream is null");
                fileContent = IOUtils.toByteArray(inputStream);
                contact.setPhoto(fileContent);
            } catch (IOException ех) {
                logger.error("Error saving uploaded file");
            }
            contact.setPhoto(fileContent);
        }

        contactService.save(contact);
        return "redirect:/contacts/" + UrlUtil.encodeUrlPathSegment(
                contact.getId().toString(), httpServletRequest);


    }


    @RequestMapping(value = "/photo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public byte[] downloadPhoto(@PathVariable("id") Long id) {
        Contact contact = contactService.findByid(id);
        if (contact.getPhoto() != null) {
            logger.info("Downloading photo for id: {} with size: {}", contact.getId(), contact.getPhoto().length);
        }
        return contact.getPhoto();
    }


}
