package cl.ucm.villagermanagement.models

/**
 * Representa las profesiones que puede adquirir un [Aldeano].
 *
 * @property nombreVisible Nombre formateado de la profesión.
 */
enum class TipoProfesion(val nombreVisible: String) {
    /** Aldeano sin profesión asignada y sin intercambios.*/
    NONE("None"),
    ARMORER("Armorer"),
    BUTCHER("Butcher"),
    CARTOGRAPHER("Cartographer"),
    CLERIC("Cleric"),
    FARMER("Farmer"),
    FISHERMAN("Fisherman"),
    FLETCHER("Fletcher"),
    LEATHERWORKER("Leatherworker"),
    LIBRARIAN("Librarian"),
    MASON("Mason"),
    /** Aldeano incapaz de adquirir profesión ni comerciar.*/
    NITWIT("Nitwit"),
    SHEPHERD("Shepherd"),
    TOOLSMITH("Toolsmith"),
    WEAPONSMITH("Weaponsmith");

    override fun toString(): String = nombreVisible
    companion object {
        fun buscarPorNombre(texto: String): TipoProfesion? {
            val textoLimpio = texto.trim()
            return entries.find {
                it.name.equals(textoLimpio, ignoreCase = true) ||
                        it.nombreVisible.equals(textoLimpio, ignoreCase = true)
            }
        }
        fun esValido(texto: String): Boolean {
            return buscarPorNombre(texto) != null
        }
    }
}

