package com.progettoweb.civediamodomanibe.core.templates;

import com.progettoweb.civediamodomanibe.core.exception.RestrictedActionException;
import com.progettoweb.civediamodomanibe.core.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.constraints.NotNull;
import java.util.List;

@Transactional
@Slf4j
public abstract class ServiceTemplate<
        E extends BaseEntity,
        D extends BaseDto,
        C extends BaseCriteria,
        S extends SpecificationBuilder<E, C>,
        M extends BidirectionalMapper<D, E>,
        R extends BaseJpaRepository<E>> {
    protected final R repository;
    protected final M mapper;
    protected final S specificationBuilder;

//    @Autowired
//    protected UserService userService;

    protected ServiceTemplate(
            @NonNull R repository,
            @NonNull M mapper,
            @NonNull S specificationBuilder) {
        this.repository = repository;
        this.mapper = mapper;
        this.specificationBuilder = specificationBuilder;
    }

    public R getRepository() {
        return repository;
    }

    public M getMapper() {
        return mapper;
    }

    public Page<D> filter(@Nullable C criteria) {
        return getEntities(criteria).map(mapper::toDto);
    }

    protected Page<E> getEntities(@Nullable C criteria) {
        Pageable pageable;
        if (criteria != null) {
            pageable = PageRequest.of(criteria.getPageNumber(), criteria.getPageSize(),
                    Sort.Direction.valueOf(criteria.getSortDirection()), criteria.getOrderBy());
        } else {
            pageable = PageRequest.of(0, 20, Sort.Direction.ASC, "id");
        }

        return (criteria != null)
                ? repository.findAll(specificationBuilder.filter(criteria), pageable)
                : repository.findAll(pageable);

    }

    protected List<E> getListByCriteria(@Nullable C criteria) {
        return repository.findAll(specificationBuilder.filter(criteria));
    }

    public D save(@NotNull D dto) {
        E entity = mapper.toEntity(dto);
        return mapper.toDto(save(entity));
    }

    public E save(@NotNull E entity) {
        entity.setDeleted(Constants.Boolean.FALSE);
        return repository.save(entity);
    }

    public D update(@NotNull D dto, String url) {
        E entityFromDb = getEntity(url);
        E updatedEntity = mapper.toUpdateEntity(dto, entityFromDb);
        E savedEntity = repository.save(updatedEntity);
        return mapper.toDto(savedEntity);

    }

    public D delete(String url) {
        E entity = getEntity(url);
        entity.setDeleted(Constants.Boolean.TRUE);
        return mapper.toDto( repository.save(entity) );
    }

    public D record (String url) {
        E entity = getEntity(url);
        entity.setDeleted(Constants.Boolean.FALSE);
        return mapper.toDto(repository.save(entity));
    }

    public E getEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RestrictedActionException("Non trovato!"));
    }

    public BaseDto getDto(Long id) {
        return mapper.toDto(getEntity(id));
    }

    public E getEntity(String url) {
        return repository.findByUrl(url).orElseThrow(() -> new RestrictedActionException("Non trovato!"));
    }

    public BaseDto getDto(String url) {
        return mapper.toDto(getEntity(url));
    }

//    public UserDto getCurrentUserDto() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
//            return userService.getUserDtoByUid(authentication.getName());
//        } else {
//            throw new SecurityException("Permesso negato");
//        }
//    }

//    public User getCurrentUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
//            return userService.getUserByUid(authentication.getName());
//        } else {
//            throw new SecurityException("Permesso negato");
//        }
//    }

    public abstract String getEntityName();

}
