package com.budget.api.controller

import com.budget.api.dto.request.PasswordRequestDTO
import com.budget.api.dto.request.UserRequestDTO
import com.budget.api.dto.response.error.ObjectErrorResponse
import com.budget.api.dto.response.success.SuccessResponseDTO
import com.budget.api.dto.response.success.UserResponseDTO
import com.budget.api.model.User
import com.budget.api.service.IUserService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * Created by Victor Santos on 24/11/2019
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
class UserController(
    private val userService: IUserService
) {

    @PostMapping
    fun createUser(@RequestBody @Valid userRequestDTO: UserRequestDTO): ResponseEntity<Any> {
        return ResponseEntity(userService.createUser(userRequestDTO), HttpStatus.CREATED)
    }

    @PatchMapping("/password")
    fun updatePassword(@RequestBody @Valid passwordRequestDTO: PasswordRequestDTO): ResponseEntity<Any> {
        return ResponseEntity.ok().body(userService.updateUser(passwordRequestDTO))
    }

    @GetMapping
    fun getAllUsers(): ResponseEntity<MutableList<UserResponseDTO>> {
        return ResponseEntity.ok().body(userService.getAll())
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<UserResponseDTO> {
        return ResponseEntity.ok().body(userService.getById(id))
    }

    @GetMapping("/cpf")
    fun getByCpf(@RequestParam @ApiParam(required = true) cpf: String): ResponseEntity<UserResponseDTO> {
        return ResponseEntity.ok().body(userService.getByCpf(cpf))
    }

    @DeleteMapping("/delete")
    fun deleteUser(@RequestParam(required = true, defaultValue = "") @ApiParam(required = true) cpf: String): ResponseEntity<Any> {
        userService.deleteByCpf(cpf)

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}