package com.example.authservice.services

import com.example.authservice.data.dto.UserDTO
import com.example.authservice.data.dto.UserInfoDTO
import com.example.authservice.data.entities.User
import com.example.authservice.data.repositories.OrderRepository
import com.example.authservice.data.repositories.UserRepository
import com.example.authservice.exceptions.blueprint.CustomException
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val hash: PasswordEncoder,
    private val userRepository: UserRepository,
    private val orderRepository: OrderRepository
) {
    fun registration(user: UserDTO): User {
        if (userRepository.findByEmail(user.email) != null) throw CustomException(
            "Conflict",
            "There is already an account with email ${user.email} registered.",
            HttpStatus.CONFLICT
        )

        val res = User(
            nickname = user.nickname,
            email = user.email,
            password = hash.encode(user.password)
        )

        return userRepository.save(res)
    }

    fun getInfo(): Pair<UserInfoDTO, List<Int>> {
        val user = userRepository.findByEmail(SecurityContextHolder.getContext().authentication.name)

        val userInfoDto = UserInfoDTO(
            nickname = user!!.nickname,
            email = user.email,
            created = user.created
        )

        val orders = orderRepository.findByUserId(user.id)
        val ordersId = mutableListOf<Int>()

        if (orders != null) {
            for (order in orders) {
                ordersId.add(order.id)
            }
        }

        return Pair(userInfoDto, ordersId)
    }
}