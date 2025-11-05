package br.com.mercadolivro.repository

import br.com.mercadolivro.model.CustomerModel
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<CustomerModel, Int> {
}