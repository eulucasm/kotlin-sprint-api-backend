package br.com.mercadolivro.service

import br.com.mercadolivro.controller.request.PostCustomerRequest
import br.com.mercadolivro.controller.request.PutCustomerRequest
import br.com.mercadolivro.model.CustomerModel
import org.springframework.stereotype.Service

@Service
class CustomerService {

    val customers = mutableListOf<CustomerModel>()

    fun getAllCustomers(name: String?): List<CustomerModel> {
        name?.let {
            return customers.filter {
                it.name.contains(name, ignoreCase = true)
            }
        }
        return customers
    }

    fun getCustomer(id: String): CustomerModel {
        return customers.first { it.id == id }
    }

    fun createCustomer(customer: PostCustomerRequest) {
        val id = if (customers.isEmpty()) {
            "1"
        } else {
            customers.last().id.toInt() + 1
        }.toString()
        customers.add(CustomerModel(id, customer.name, customer.email))
    }

    fun updateCustomer(id: String, customer: PutCustomerRequest) {
        customers.first { it.id == id }.let {
            it.name = customer.name
            it.email = customer.email
        }
    }

    fun deleteCustomer(id: String) {
        customers.removeIf { it.id == id }
    }
}