package ind.wldd.products.store.domain.repository

import arrow.core.Either
import ind.wldd.products.store.domain.models.NetworkError
import ind.wldd.products.store.domain.models.Product

interface ProductsRepository {

    suspend fun getProducts() : Either<NetworkError, List<Product>>
}