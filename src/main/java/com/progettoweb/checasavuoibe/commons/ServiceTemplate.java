package com.progettoweb.checasavuoibe.commons;

import com.progettoweb.checasavuoibe.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
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

    @Autowired
    protected UserService userService;

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

    public Page<? extends BaseDto> filter(@Nullable C criteria) {
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

    public abstract String[] getHeaders();

    public abstract String[] populate(E entity);

    public BaseDto save(@NotNull D dto) {
        E entity = mapper.toEntity(dto);
//        entity.setDeleted(Constants.Boolean.FALSE);
        return mapper.toDto(repository.save(entity));
    }

//    public D update(@NotNull D dto, Long id) {
//        E entityFromDb = getEntity(id);
//        E updatedEntity = mapper.toUpdateEntity(dto, entityFromDb);
//        E savedEntity = repository.save(updatedEntity);
//        return mapper.toDto(savedEntity);
//
//    }

    public void delete(Long id) {
//        if (!eligibleToDelete(id)) {
//            throw new DeleteRestrictionException(getEntityName(), id);
//        }
//        E entity = getEntity(id);
//        entity.setDeleted(Constants.Boolean.TRUE);
//        repository.save(entity);
    }

    public E getEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Non trovato!!"));
    }

    public BaseDto getDto(Long id) {
        return mapper.toDto(getEntity(id));
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

    protected abstract boolean eligibleToDelete(Long id);

    public abstract String getEntityName();

}
