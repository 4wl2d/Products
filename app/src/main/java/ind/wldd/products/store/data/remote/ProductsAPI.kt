package ind.wldd.products.store.data.remote

import ind.wldd.products.store.domain.models.Product
import retrofit2.http.GET

interface ProductsAPI {

    @GET("products")
    suspend fun getProducts(): List<Product>

}