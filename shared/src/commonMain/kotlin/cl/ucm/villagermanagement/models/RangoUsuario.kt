package cl.ucm.villagermanagement.models

enum class RangoUsuario(val nombreVisible: String) {
    BANEADO("Baneado"),
    NOOB("Noob"),
    PRO("Pro"),
    ADMIN("Admin"),
    OWNER("Owner");

    override fun toString(): String = nombreVisible

    companion object {
        fun buscarPorNombre(texto: String): RangoUsuario? {
            val textoLimpio = texto.trim()
            return entries.find {
                it.name.equals(textoLimpio, ignoreCase = true)
                        || it.nombreVisible.equals(textoLimpio, ignoreCase = true)
            }
        }
        fun esValido(texto: String): Boolean {
            return buscarPorNombre(texto) != null
        }
    }
}
