package cl.ucm.villagermanagement.models

class Aldeano {
    private val tradeos: MutableList<Trade> = mutableListOf()

    val id: Int = ++contadorId
    companion object {
        private var contadorId = 0
    }

    var nombre: String = "Unnamed"
        private set

    var posicion: IntArray = intArrayOf(0, 0, 0)
        private set

    var profesion: TipoProfesion = TipoProfesion.NONE
        private set(value) {
            if (field != value) {
                field = value
                tradeos.clear()
            }
        }

    fun cambiarNombre(newName: String): Boolean {
        if (newName.isBlank()) return false
        this.nombre = newName.trim()
        return true
    }

    fun cambiarPosicion(newPosition: IntArray): Boolean {
        if (newPosition.size != 3) return false
        this.posicion = newPosition.copyOf()
        return true
    }

    fun cambiarProfesion(newProfession: TipoProfesion) {
        this.profesion = newProfession
    }

    // --- GESTIÓN DE TRADEOS ---

    fun agregarTradeo(trade: Trade): Boolean {
        if (this.profesion == TipoProfesion.NONE || this.profesion == TipoProfesion.NITWIT) {
            return false
        }
        if (tradeos.contains(trade)) return false
        return tradeos.add(trade)
    }

    fun eliminarTradeo(trade: Trade): Boolean {
        if (this.profesion == TipoProfesion.NONE || this.profesion == TipoProfesion.NITWIT) {
            return false
        }
        return tradeos.remove(trade)
    }

    fun eliminarTradeoPorIndice(index: Int): Boolean {
        if (index !in tradeos.indices) return false
        tradeos.removeAt(index)
        return true
    }

    fun tieneTradeo(trade: Trade): Boolean {
        return tradeos.contains(trade)
    }

    fun obtenerTradeos(): List<Trade> {
        return tradeos.toList()
    }

    override fun toString(): String {
        return "#$id - $nombre [$profesion] (X:${posicion[0]}, Y:${posicion[1]}, Z:${posicion[2]})"
    }
}