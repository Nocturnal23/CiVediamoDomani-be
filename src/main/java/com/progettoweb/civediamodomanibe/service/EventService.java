package com.progettoweb.civediamodomanibe.service;

import com.progettoweb.civediamodomanibe.core.exception.RestrictedActionException;
import com.progettoweb.civediamodomanibe.core.templates.ServiceTemplate;
import com.progettoweb.civediamodomanibe.core.utils.Utils;
import com.progettoweb.civediamodomanibe.dto.EventDto;
import com.progettoweb.civediamodomanibe.entity.Event;
import com.progettoweb.civediamodomanibe.repository.EventRepository;
import com.progettoweb.civediamodomanibe.repository.criteria.EventCriteria;
import com.progettoweb.civediamodomanibe.repository.specification.EventSpecificationBuilder;
import com.progettoweb.civediamodomanibe.service.mapper.EventBidirectionalMapper;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class EventService extends ServiceTemplate<Event, EventDto, EventCriteria,
        EventSpecificationBuilder, EventBidirectionalMapper, EventRepository> {
    protected EventService(EventRepository repository, EventBidirectionalMapper mapper, EventSpecificationBuilder specificationBuilder) {
        super(repository, mapper, specificationBuilder);
    }

    @Override
    public String getEntityName() {
        return Event.class.getSimpleName();
    }

    @Override
    public EventDto save(@NotNull EventDto eventDto) {
        eventDto.setUrl(Utils.generateString(8L));
        return super.save(eventDto);
    }

    public EventDto addImageToEvent(String url, MultipartFile image) {
        Event event = getEntity(url);
        if (event == null) {
            throw new RestrictedActionException("Impossibile caricare l'immagine: evento non trovato!");
        }
        try {
            event.setImage(image.getBytes());
        } catch (IOException e) {
            throw new RestrictedActionException("Impossibile caricare l'immagine: errore nel caricamento!");
        }
        return mapper.toDto(save(event));
    }
}
