package vn.unicloud.fnb.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unicloud.fnb.dto.*;

import java.util.Optional;

@RequestMapping("/categories")
public interface CategoryController {

    @Operation(
            tags = {"Category"},
            summary = ""
    )
    @GetMapping("/{id}")
    ResponseEntity<Object> get(@PathVariable Optional<Long> id, @ParameterObject Pageable pageable);

    @Operation(
            tags = {"Category"},
            summary = ""
    )
    @PostMapping
    ResponseEntity<Object> create(@RequestBody CreateCategoryCommand command);


    @Operation(
            tags = {"Category"},
            summary = ""
    )
    @PutMapping
    ResponseEntity<Object> update(@RequestBody UpdateCategoryCommand command);

    @Operation(
            tags = {"Category"},
            summary = ""
    )
    @DeleteMapping
    ResponseEntity<Void> delete(@RequestBody DeleteCategoryCommand command);
}
