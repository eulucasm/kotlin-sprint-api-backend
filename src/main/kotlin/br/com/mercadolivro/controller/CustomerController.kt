package br.com.mercadolivro.controller

import br.com.mercadolivro.controller.request.PostCustomerRequest
import br.com.mercadolivro.controller.request.PutCustomerRequest
import br.com.mercadolivro.extension.toCustomerModel
import br.com.mercadolivro.model.CustomerModel
import br.com.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customers")
class CustomerController(
    val customerServer: CustomerService
) {

    @GetMapping
    fun getAllCustomers(@RequestParam name: String?): List<CustomerModel> {
        return customerServer.getAllCustomers(name)
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Int): CustomerModel {
        return customerServer.getCustomer(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@RequestBody customer: PostCustomerRequest) {
        customerServer.createCustomer(customer.toCustomerModel())
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateCustomer(@PathVariable id: Int, @RequestBody customer: PutCustomerRequest) {
        customerServer.updateCustomer(customer.toCustomerModel(id))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: Int) {
        customerServer.deleteCustomer(id)
    }
}