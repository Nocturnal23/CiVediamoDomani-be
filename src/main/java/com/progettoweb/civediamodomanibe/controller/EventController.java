package com.progettoweb.civediamodomanibe.controller;

import com.progettoweb.civediamodomanibe.core.templates.ControllerTemplate;
import com.progettoweb.civediamodomanibe.core.utils.Constants.Endpoint;
import com.progettoweb.civediamodomanibe.dto.EventDto;
import com.progettoweb.civediamodomanibe.repository.criteria.EventCriteria;
import com.progettoweb.civediamodomanibe.service.EventService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoint.Events)
public class EventController extends ControllerTemplate<EventDto, EventCriteria, EventService> {

    public EventController(EventService service) {
        super(service);
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World!";
    }
}