package ind.wldd.products.store.presentation.productsScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ind.wldd.products.store.domain.repository.ProductsRepository
import ind.wldd.products.store.presentation.util.sendEvent
import ind.wldd.products.util.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ProductsViewState())
    val state = _state.asStateFlow()

    init {
        getProducts()
    }
    private fun getProducts() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            productsRepository.getProducts()
                .onRight { products ->
                    _state.update {
                        it.copy(products = products)
                    }
                }
                .onLeft { error ->
                    _state.update {
                        it.copy(error = error.error.message)
                    }
                    sendEvent(Event.Toast(error.error.message))
                }
            _state.update {
                it.copy(isLoading = false)
            }
        }
    }
}