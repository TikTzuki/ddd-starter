package vn.unicloud.fnb.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unicloud.fnb.dto.CreateCategoryCommand;
import vn.unicloud.fnb.dto.DeleteCategoryCommand;
import vn.unicloud.fnb.dto.UpdateCategoryCommand;
import vn.unicloud.fnb.domain.product.category.CategorySpec;


@RequestMapping("/api/1.0/categories")
@Tags(@Tag(name = "Category"))
public interface CategoryController {


    @Operation(
            summary = ""
    )
    @GetMapping("/{id}")
    ResponseEntity<Object> get(
            CategorySpec specification,
            Pageable pageable
    );

    @Operation(
            summary = ""
    )
    @PostMapping
    ResponseEntity<Object> create(@RequestBody CreateCategoryCommand command);


    @Operation(
            summary = ""
    )
    @PutMapping
    ResponseEntity<Object> update(@RequestBody UpdateCategoryCommand command);

    @Operation(
            summary = ""
    )
    @DeleteMapping
    ResponseEntity<Void> delete(@RequestBody DeleteCategoryCommand command);
}
