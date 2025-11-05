package br.com.mercadolivro.service

import br.com.mercadolivro.model.CustomerModel
import br.com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository
) {

    val customers = mutableListOf<CustomerModel>()

    fun getAllCustomers(name: String?): List<CustomerModel> {
        name?.let {
            return customers.filter {
                it.name.contains(name, ignoreCase = true)
            }
        }
        return customers
    }

    fun getCustomer(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow()
    }

    fun createCustomer(customer: CustomerModel) {
        customerRepository.save(customer)
    }

    fun updateCustomer(customer: CustomerModel) {
        customers.first { it.id == customer.id }.let {
            it.name = customer.name
            it.email = customer.email
        }
    }

    fun deleteCustomer(id: Int) {
        customers.removeIf { it.id == id }
    }
}