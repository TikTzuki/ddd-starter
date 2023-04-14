package vn.unicloud.fnb.dto

import org.springframework.data.domain.Pageable
import java.util.Optional


data class CategoryQuery(val id: Optional<Long>, val pageable: Pageable)
data class CreateCategoryCommand(val name: String)
data class UpdateCategoryCommand(val id: String, val name: String)
data class DeleteCategoryCommand(val id: Long)

data class CategoryDto(val id: Long, val name: String)
