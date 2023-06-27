package com.progettoweb.civediamodomanibe.service;

import com.progettoweb.civediamodomanibe.core.templates.ServiceTemplate;
import com.progettoweb.civediamodomanibe.core.utils.Constants;
import com.progettoweb.civediamodomanibe.core.utils.Utils;
import com.progettoweb.civediamodomanibe.dto.SocialUserDto;
import com.progettoweb.civediamodomanibe.dto.UserDto;
import com.progettoweb.civediamodomanibe.entity.Event;
import com.progettoweb.civediamodomanibe.entity.UserAccount;
import com.progettoweb.civediamodomanibe.repository.UserRepository;
import com.progettoweb.civediamodomanibe.repository.criteria.UserCriteria;
import com.progettoweb.civediamodomanibe.repository.specification.UserSpecificationBuilder;
import com.progettoweb.civediamodomanibe.service.mapper.UserBidirectionalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService extends ServiceTemplate<UserAccount, UserDto, UserCriteria,
        UserSpecificationBuilder, UserBidirectionalMapper, UserRepository> {

    @Autowired
    private EventService eventService;

    protected UserService(UserRepository repository, UserBidirectionalMapper mapper, UserSpecificationBuilder specificationBuilder) {
        super(repository, mapper, specificationBuilder);
    }

    @Override
    public String getEntityName() {
        return UserAccount.class.getSimpleName();
    }

    public UserAccount findByEmail(String email) {
        return repository.findByEmailIgnoreCase(email);
    }

    public UserDto registerUser (UserDto newUser) {
        newUser.setAppRole(Constants.Role.NORMAL);
        newUser.setUrl(Utils.generateString(8L));
        newUser.setState(Constants.UserState.ENABLED);
        return save(newUser);
    }

    public UserDto registerUser (SocialUserDto newUser) {
        UserDto user = new UserDto();
        user.setEmail(newUser.getEmail());
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        return registerUser(user);
    }

    public UserDto disableUser (String url) {
        UserAccount entity = getEntity(url);
        entity.setState(Constants.UserState.DISABLED);
        return mapper.toDto(repository.save(entity));
    }

    public UserDto enableUser (String url) {
        UserAccount entity = getEntity(url);
        entity.setState(Constants.UserState.ENABLED);
        return mapper.toDto(repository.save(entity));
    }

    public Boolean setEventPreferred(String userUrl, String eventUrl) {
        UserAccount user = getEntity(userUrl);
        List<Event> favorites = user.getFavorites();
        UserAccount savedUser;
        boolean eventFound = favorites.stream().anyMatch( eventElem -> eventElem.getUrl().equals(eventUrl) );
        int originalSize = favorites.size();

        if (eventFound) {
            //Removing event from favorites
            boolean removed = favorites.removeIf(eventElem -> eventElem.getUrl().equals(eventUrl));

            if (removed) {
                user.setFavorites(favorites);
                savedUser = save(user);
                eventFound = savedUser.getFavorites().stream().anyMatch( eventElem -> eventElem.getUrl().equals(eventUrl) );
            }

            if (!removed || eventFound || favorites.size() == originalSize) {
                throw new RuntimeException("Errore nella rimozione dell'evento dai preferiti.");
            }
        } else {
            //Adding event to favorites
            boolean added = favorites.add(eventService.getEntity(eventUrl));
            user.setFavorites(favorites);
            savedUser = save(user);

            eventFound = savedUser.getFavorites().stream().anyMatch( eventElem -> eventElem.getUrl().equals(eventUrl) );
            if (!added || !eventFound || favorites.size() == originalSize) {
                throw new RuntimeException("Errore nell'aggiunta dell'evento ai preferiti.");
            }
        }

        return eventFound;
    }

    public Boolean setAttendEvent(String userUrl, String eventUrl) {
        UserAccount user = getEntity(userUrl);
        List<Event> attending = user.getAttending();
        UserAccount savedUser;
        boolean eventFound = attending.stream().anyMatch( eventElem -> eventElem.getUrl().equals(eventUrl) );
        int originalSize = attending.size();

        if (eventFound) {
            //Removing event from attending
            boolean removed = attending.removeIf(eventElem -> eventElem.getUrl().equals(eventUrl));

            if (removed) {
                user.setAttending(attending);
                savedUser = save(user);
                eventFound = savedUser.getAttending().stream().anyMatch( eventElem -> eventElem.getUrl().equals(eventUrl) );
            }

            if (!removed || eventFound || attending.size() == originalSize) {
                throw new RuntimeException("Errore nella rimozione dell'evento dai partecipati.");
            }
        } else {
            //Adding event to attending
            boolean added = attending.add(eventService.getEntity(eventUrl));
            user.setAttending(attending);
            savedUser = save(user);

            eventFound = savedUser.getAttending().stream().anyMatch( eventElem -> eventElem.getUrl().equals(eventUrl) );
            if (!added || !eventFound || attending.size() == originalSize) {
                throw new RuntimeException("Errore nell'aggiunta dell'evento ai partecipati.");
            }
        }

        return eventFound;
    }
}
