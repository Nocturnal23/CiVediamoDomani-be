package com.progettoweb.civediamodomanibe.controller;

import com.progettoweb.civediamodomanibe.core.templates.ControllerTemplate;
import com.progettoweb.civediamodomanibe.core.utils.Constants.Endpoint;
import com.progettoweb.civediamodomanibe.dto.EventDto;
import com.progettoweb.civediamodomanibe.repository.criteria.EventCriteria;
import com.progettoweb.civediamodomanibe.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(Endpoint.Events)
public class EventController extends ControllerTemplate<EventDto, EventCriteria, EventService> {

    public EventController(EventService service) {
        super(service);
    }


    @PutMapping("/{url}/addImage")
    public ResponseEntity<EventDto> addImageToEvent(@PathVariable String url, @RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(service.addImageToEvent(url, file), HttpStatus.OK);
    }
}