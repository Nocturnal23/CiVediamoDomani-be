package com.progettoweb.civediamodomanibe.core.templates;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.NotNull;
@AllArgsConstructor
public abstract class ControllerTemplate<
        D extends BaseDto,
        C extends BaseCriteria,
        S extends ServiceTemplate> {

    protected final S service;

    @PostMapping(value = "/filter")
    public ResponseEntity<Page<D>> filter(@Nullable @RequestBody C criteria) {
        return new ResponseEntity<>(service.filter(criteria), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> save(@NotNull @RequestBody D dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getDto(id), HttpStatus.OK);
    }

    @GetMapping(value = "/getByUrl/{url}")
    public ResponseEntity<Object> findByUrl(@PathVariable String url) {
        return new ResponseEntity<>(service.getDto(url), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@NotNull @RequestBody D dto,
                                         @PathVariable Long id) {
        return new ResponseEntity<>(service.update(dto, id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{url}")
    public ResponseEntity<Void> delete(@PathVariable String url) {
        service.delete(url);
        return ResponseEntity.noContent().build();
    }
}
