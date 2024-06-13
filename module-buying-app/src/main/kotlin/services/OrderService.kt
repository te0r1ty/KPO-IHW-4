package com.example.buyservice.services

import com.example.buyservice.data.dto.OrderDTO
import com.example.buyservice.data.dto.OrderInfoDTO
import com.example.buyservice.data.entities.Order
import com.example.buyservice.data.repositories.OrderRepository
import com.example.buyservice.data.repositories.SessionRepository
import com.example.buyservice.data.repositories.StationRepository
import com.example.buyservice.data.repositories.UserRepository
import com.example.buyservice.exceptions.blueprint.CustomException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class OrderService(
    private val sessionRepository: SessionRepository,
    private val stationRepository: StationRepository,
    private val orderRepository: OrderRepository,
    private val userRepository: UserRepository,
) {
    fun createOrder(token: String, orderDTO: OrderDTO) {
        val userId = sessionRepository.findByToken(token.substring(7))!!.userId
        val from = stationRepository.findByStation(orderDTO.from)
            ?: throw CustomException(
                "Not found", "The station you decided to depart from does not exist", HttpStatus.NOT_FOUND
            )
        val to = stationRepository.findByStation(orderDTO.to)
            ?: throw CustomException(
                "Not found", "The station you decided to go to does not exist", HttpStatus.NOT_FOUND
            )

        if (from == to) throw CustomException(
            "Bad request", "You choose same stations", HttpStatus.BAD_REQUEST
        )

        val order = Order(
            userId = userId,
            fromStationId = from.id,
            toStationId = to.id
        )

        orderRepository.save(order)

        return
    }

    fun processOrders() {

        val orders = orderRepository.findAll().filter { it.status == 1 }

        for (order in orders) {
            Thread.sleep(Random.nextInt(1000, 3000).toLong())
            order.status = Random.nextInt(2, 4)
        }

        orderRepository.saveAll(orders)

        return
    }

    fun getInfo(id: Int, token: String): OrderInfoDTO {
        val userId = sessionRepository.findByToken(token.substring(7))!!.userId

        val order = orderRepository.findById(id)
            ?: throw CustomException(
                "Not found", "The order you decided to retrieve the info does not exist", HttpStatus.NOT_FOUND
            )

        if (order.userId != userId)
            throw CustomException(
                "Forbidden", "You have no access to this order", HttpStatus.FORBIDDEN
            )

        val user = userRepository.findById(order.userId)
        val from = stationRepository.findById(order.fromStationId)
        val to = stationRepository.findById(order.toStationId)

        var status = ""
        if (order.status == 1) status = "check"
        if (order.status == 2) status = "success"
        if (order.status == 3) status = "rejection"

        val res = OrderInfoDTO(
            nickname = user!!.nickname,
            from = from!!.station,
            to = to!!.station,
            status = status,
            created = order.created
        )

        return res
    }
}