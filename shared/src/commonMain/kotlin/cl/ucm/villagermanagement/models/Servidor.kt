package cl.ucm.villagermanagement.models

import org.example.models.Usuario

class Servidor(val ip: String, owner: Usuario) {
    private val aldeanos: MutableList<Aldeano> = mutableListOf()
    private val usuarios: MutableList<Usuario> = mutableListOf()
    private val historialBaneos: MutableList<RegistroBaneo> = mutableListOf()

    init {
        require(ip.isNotBlank()) { "IP can not be blank" }
        owner.asignarOwner()
        usuarios.add(owner)
    }

    // --- FUNCIONES AUXILIARES ---

    private fun buscarAldeanoPorId(id: Int): Aldeano? {
        return aldeanos.find { it.id == id }
    }

    private fun registrarBaneo(usuarioBaneado: String, responsable: String, motivo: String) {
        historialBaneos.add(RegistroBaneo(usuarioBaneado, responsable, motivo))
    }

    // --- CONSULTAS DE SOLO LECTURA ---

    fun obtenerAldeanos(): List<Aldeano> = aldeanos.toList()
    fun obtenerUsuarios(): List<Usuario> = usuarios.toList()
    fun obtenerHistorialBaneos(): List<RegistroBaneo> = historialBaneos.toList()

    // --- GESTIÓN LISTA DE ALDEANOS ---

    fun agregarAldeano(aldeano: Aldeano): Boolean {
        if (aldeanos.any { it.id == aldeano.id }) return false
        return aldeanos.add(aldeano)
    }

    fun eliminarAldeano(id: Int): Boolean {
        return aldeanos.removeIf { it.id == id }
    }

    // --- GESTIÓN DATOS DE ALDEANO ---

    fun editarNombreAldeano(id: Int, nuevoNombre: String): Boolean {
        val aldeano = buscarAldeanoPorId(id) ?: return false
        return aldeano.cambiarNombre(nuevoNombre)
    }

    fun editarPosicionAldeano(id: Int, nuevaPosicion: IntArray): Boolean {
        val aldeano = buscarAldeanoPorId(id) ?: return false
        return aldeano.cambiarPosicion(nuevaPosicion)
    }

    fun editarProfesionAldeano(id: Int, nuevaProfesion: TipoProfesion): Boolean {
        val aldeano = buscarAldeanoPorId(id) ?: return false
        aldeano.cambiarProfesion(nuevaProfesion)
        return true
    }

    fun agregarTradeoAldeano(id: Int, trade: Trade): Boolean {
        val aldeano = buscarAldeanoPorId(id) ?: return false
        return aldeano.agregarTradeo(trade)
    }

    fun eliminarTradeoAldeano(id: Int, index: Int): Boolean {
        val aldeano = buscarAldeanoPorId(id) ?: return false
        return aldeano.eliminarTradeoPorIndice(index - 1)
    }

    // --- GESTIÓN LISTA DE USUARIOS ---

    fun agregarUsuario(user: Usuario, rango: RangoUsuario = RangoUsuario.NOOB): Boolean {
        if (rango == RangoUsuario.OWNER || rango == RangoUsuario.BANEADO) return false
        if (user.rango == RangoUsuario.OWNER || user.rango == RangoUsuario.BANEADO) return false
        if (usuarios.any { it.nickname.equals(user.nickname, ignoreCase = true) }) return false

        user.cambiarRango(rango)
        return usuarios.add(user)
    }

    fun eliminarUsuario(nickname: String): Boolean {
        val nicknameLimpio = nickname.trim()
        return usuarios.removeIf {
            it.rango != RangoUsuario.OWNER && it.nickname.equals(nicknameLimpio, ignoreCase = true)
        }
    }

    fun banearUsuario(nickname: String, responsable: String, motivo: String): Boolean {
        val nicknameLimpio = nickname.trim()
        val user = usuarios.find { it.nickname.equals(nicknameLimpio, ignoreCase = true) }

        if (user == null || user.rango == RangoUsuario.OWNER || user.rango == RangoUsuario.BANEADO) {
            return false
        }

        user.asignarBaneado()
        registrarBaneo(user.nickname, responsable.trim(), motivo.trim())
        return true
    }
}