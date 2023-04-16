package vn.unicloud.fnb.dto

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateBranchCommand(val name: String)

data class UpdateBranchCommand(
    @TargetAggregateIdentifier
    val id: Long,
    val name: String
)

data class DeleteBranchCommand(
    @TargetAggregateIdentifier
    val id: Long,
)

data class BranchQuery(
    val id: Long?,
)