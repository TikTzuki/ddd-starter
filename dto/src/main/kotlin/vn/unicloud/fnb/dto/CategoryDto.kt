package vn.unicloud.fnb.dto

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification

data class CategoryQuery<T>(
    val specification: Specification<T>,
    val pageable: Pageable
)

data class CreateCategoryCommand(val name: String)
data class UpdateCategoryCommand(val id: String, val name: String)
data class DeleteCategoryCommand(val id: Long)

data class CategoryDto(val id: Long, val name: String)
