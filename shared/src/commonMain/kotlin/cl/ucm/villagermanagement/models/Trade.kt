package cl.ucm.villagermanagement.models

/**
 * Representa un intercambio de un [Aldeano].
 *
 * @property itemRequerido Objeto de tipo [Item] que se cobra.
 * @property cantidadRequerida Cantidad del objeto cobrado.
 * @property itemOfrecido Objeto de tipo [Item] que vende el aldeano.
 * @property cantidadOfrecida Cantidad del objeto en venta.
 * @throws IllegalArgumentException Si alguna de las cantidades ingresadas es menor o igual a 0.
 */
data class Trade(
    val itemRequerido: Item,
    val cantidadRequerida: Int,
    val itemOfrecido: Item,
    val cantidadOfrecida: Int,
) {
    init {
        require(cantidadRequerida > 0) { "Amount must be greater than 0" }
        require(cantidadOfrecida > 0) { "Amount must be greater than 0" }
    }

    override fun toString(): String {
        return "${cantidadRequerida}x $itemRequerido ➔ ${cantidadOfrecida}x $itemOfrecido"
    }
}