package ind.wldd.products.store.presentation.productsScreen.viewModel

import ind.wldd.products.store.domain.models.Product

data class ProductsViewState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = ""
)
