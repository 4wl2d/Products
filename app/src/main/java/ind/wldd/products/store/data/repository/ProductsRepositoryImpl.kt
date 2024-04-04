package ind.wldd.products.store.data.repository

import arrow.core.Either
import ind.wldd.products.store.data.mapper.toNetworkError
import ind.wldd.products.store.data.remote.ProductsAPI
import ind.wldd.products.store.domain.models.NetworkError
import ind.wldd.products.store.domain.models.Product
import ind.wldd.products.store.domain.repository.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productApi: ProductsAPI
): ProductsRepository {
    override suspend fun getProducts(): Either<NetworkError, List<Product>> {

        return Either.catch {
            productApi.getProducts()
        }.mapLeft { it.toNetworkError() }
    }
}