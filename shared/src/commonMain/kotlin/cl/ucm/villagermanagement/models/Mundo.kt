package cl.ucm.villagermanagement.models

/**
 * Representa un mundo base dentro del sistema de gestión.
 *
 * Centraliza el almacenamiento, consultas y operaciones sobre los aldeanos
 * registrados, dejando la gestión de usuarios y autenticación a sus subclases.
 *
 * @property ip Dirección IP o identificador de red del mundo.
 * @throws IllegalArgumentException Si [ip] está en blanco.
 */
abstract class Mundo(val ip: String) {
    init {
        require(ip.isNotBlank()) { "IP can not be blank" }
    }

    /** Lista interna de aldeanos pertenecientes al mundo. */
    protected val aldeanos: MutableList<Aldeano> = mutableListOf()

    // Métodos auxiliares

    /**
     * Busca un aldeano registrado por su identificador único.
     *
     * @param id Identificador numérico del [Aldeano].
     * @return El [Aldeano] encontrado, o `null` si no existe.
     */
    protected fun buscarAldeanoPorId(id: Int): Aldeano? {
        return aldeanos.find { it.id == id }
    }

    // Consultas de lectura y métricas

    /**
     * Obtiene una copia de solo lectura con todos los aldeanos del mundo.
     *
     * @return Lista inmutable de [Aldeano].
     */
    fun obtenerAldeanos(): List<Aldeano> = aldeanos.toList()

    /**
     * Devuelve la cantidad total de aldeanos registrados en el mundo.
     *
     * @return Número entero de aldeanos actuales.
     */
    fun cantidadAldeanos(): Int = aldeanos.size

    /**
     * Devuelve la cantidad total de usuarios asociados al mundo.
     *
     * @return Número entero de usuarios activos o registrados.
     */
    abstract fun cantidadUsuarios(): Int

    // Gestión lista de aldeanos

    /**
     * Agrega un nuevo aldeano al mundo evitando duplicados por identificador.
     *
     * @param aldeano Instancia de [Aldeano] a registrar.
     * @return `true` si se añadió correctamente; `false` si ya existía su ID.
     */
    fun agregarAldeano(aldeano: Aldeano): Boolean {
        return !aldeanos.any { it.id == aldeano.id } && aldeanos.add(aldeano)
    }

    /**
     * Elimina un aldeano del mundo según su identificador.
     *
     * @param id Identificador numérico del [Aldeano] a remover.
     * @return `true` si el aldeano fue encontrado y eliminado; `false` en caso contrario.
     */
    fun eliminarAldeano(id: Int): Boolean {
        return aldeanos.removeIf { it.id == id }
    }

    // Gestión datos de aldeano

    /**
     * Modifica el nombre de un aldeano existente.
     *
     * @param id Identificador del [Aldeano].
     * @param nuevoNombre Nuevo nombre a asignar.
     * @return `true` si se encontró y actualizó el nombre; `false` si no existe o el nombre es inválido.
     */
    fun editarNombreAldeano(id: Int, nuevoNombre: String): Boolean {
        val aldeano = buscarAldeanoPorId(id) ?: return false
        return aldeano.cambiarNombre(nuevoNombre)
    }

    /**
     * Actualiza las coordenadas espaciales (X, Y, Z) de un aldeano existente.
     *
     * @param id Identificador del [Aldeano].
     * @param nuevaPosicion Arreglo de 3 enteros con las coordenadas.
     * @return `true` si la posición fue actualizada; `false` si el aldeano no existe o el arreglo no tiene 3 elementos.
     */
    fun editarPosicionAldeano(id: Int, nuevaPosicion: IntArray): Boolean {
        val aldeano = buscarAldeanoPorId(id) ?: return false
        return aldeano.cambiarPosicion(nuevaPosicion)
    }

    /**
     * Cambia la profesión de un aldeano existente, limpiando sus intercambios previos.
     *
     * @param id Identificador del [Aldeano].
     * @param nuevaProfesion Nueva profesión de tipo [TipoProfesion].
     * @return `true` si el aldeano fue encontrado y actualizado; `false` en caso contrario.
     */
    fun editarProfesionAldeano(id: Int, nuevaProfesion: TipoProfesion): Boolean {
        val aldeano = buscarAldeanoPorId(id) ?: return false
        aldeano.cambiarProfesion(nuevaProfesion)
        return true
    }

    /**
     * Añade un nuevo intercambio comercial a un aldeano existente.
     *
     * @param id Identificador del [Aldeano].
     * @param trade Objeto [Trade] a agregar.
     * @return `true` si el intercambio fue añadido; `false` si el aldeano no existe, no puede comerciar o ya posee el tradeo.
     */
    fun agregarTradeoAldeano(id: Int, trade: Trade): Boolean {
        val aldeano = buscarAldeanoPorId(id) ?: return false
        return aldeano.agregarTradeo(trade)
    }

    /**
     * Elimina un intercambio comercial de un aldeano según su posición visible (base 1).
     *
     * @param id Identificador del [Aldeano].
     * @param index Número de lista del intercambio (comenzando en 1).
     * @return `true` si se eliminó correctamente; `false` si el aldeano o el índice no son válidos.
     */
    fun eliminarTradeoAldeano(id: Int, index: Int): Boolean {
        val aldeano = buscarAldeanoPorId(id) ?: return false
        return aldeano.eliminarTradeoPorIndice(index - 1)
    }

    // Autenticación

    /**
     * Valida las credenciales de acceso de un usuario en el mundo.
     *
     * @param nickname Nombre de usuario con el que intenta ingresar.
     * @param password Contraseña asociada a la cuenta.
     * @return `true` si la autenticación es exitosa; `false` en caso contrario.
     */
    abstract fun autenticarUsuario(nickname: String, password: String): Boolean
}

/**
 * Representa un mundo local de un solo jugador.
 *
 * Asigna automáticamente el rango [RangoUsuario.OWNER] al propietario recibido
 * al momento de crear la instancia.
 *
 * @property owner Único usuario propietario y jugador del mundo.
 * @param ip Dirección local del mundo (por defecto `"localhost"`).
 * @throws IllegalArgumentException Si [ip] está en blanco.
 */
class Singleplayer(
    val owner: Usuario,
    ip: String = "localhost"
) : Mundo(ip) {

    init {
        owner.asignarOwner()
    }

    /**
     * Devuelve siempre `1`, ya que un mundo de un jugador solo admite a su propietario.
     *
     * @return `1`.
     */
    override fun cantidadUsuarios(): Int = 1

    /**
     * Verifica que el [nickname] y [password] correspondan al propietario del mundo.
     *
     * @param nickname Nombre de usuario ingresado.
     * @param password Contraseña ingresada.
     * @return `true` si coincide con el [owner] y su contraseña; `false` en caso contrario.
     */
    override fun autenticarUsuario(nickname: String, password: String): Boolean {
        return nickname.trim().equals(owner.nickname, ignoreCase = true) && owner.verificarPassword(password)
    }
}

/**
 * Representa un servidor multijugador con soporte para múltiples usuarios y sanciones.
 *
 * Registra al propietario inicial con rango [RangoUsuario.OWNER] e impide la creación
 * o ascenso de otros propietarios.
 *
 * @param ip Dirección IP del servidor.
 * @param owner Usuario creador que recibirá el rango único [RangoUsuario.OWNER].
 * @throws IllegalArgumentException Si [ip] está en blanco.
 */
class Multiplayer(
    ip: String,
    owner: Usuario
) : Mundo(ip) {

    private val usuarios: MutableList<Usuario> = mutableListOf()
    private val historialBaneos: MutableList<RegistroBaneo> = mutableListOf()

    init {
        owner.asignarOwner()
        usuarios.add(owner)
    }

    /**
     * Añade un nuevo registro de sanción al historial interno del servidor.
     *
     * @param usuarioBaneado Nickname del usuario sancionado.
     * @param responsable Nickname del administrador o propietario que aplicó la sanción.
     * @param motivo Razón del baneo.
     */
    private fun registrarBaneo(usuarioBaneado: String, responsable: String, motivo: String) {
        historialBaneos.add(RegistroBaneo(usuarioBaneado, responsable, motivo))
    }

    // Consultas de lectura y métricas

    /**
     * Devuelve la cantidad total de usuarios registrados en el servidor.
     *
     * @return Número entero de usuarios en la lista.
     */
    override fun cantidadUsuarios(): Int = usuarios.size

    /**
     * Obtiene una copia de solo lectura de los usuarios registrados en el servidor.
     *
     * @return Lista inmutable de [Usuario].
     */
    fun obtenerUsuarios(): List<Usuario> = usuarios.toList()

    /**
     * Obtiene una copia de solo lectura del historial de baneos aplicados.
     *
     * @return Lista inmutable de [RegistroBaneo].
     */
    fun obtenerHistorialBaneos(): List<RegistroBaneo> = historialBaneos.toList()

    // Gestión lista de usuarios

    /**
     * Autentica a un usuario registrado verificando su contraseña y que no esté baneado.
     *
     * @param nickname Nombre de usuario ingresado.
     * @param password Contraseña ingresada.
     * @return `true` si el usuario existe, no tiene rango [RangoUsuario.BANEADO] y su contraseña coincide; `false` en caso contrario.
     */
    override fun autenticarUsuario(nickname: String, password: String): Boolean {
        val nicknameLimpio = nickname.trim()
        val usuarioRegistrado = usuarios.find {
            it.nickname.equals(nicknameLimpio, ignoreCase = true)
        } ?: return false

        return usuarioRegistrado.rango != RangoUsuario.BANEADO &&
                usuarioRegistrado.verificarPassword(password)
    }

    /**
     * Registra un nuevo usuario en el servidor con un rango activo válido.
     *
     * @param user Instancia de [Usuario] a registrar.
     * @param rango Rango inicial asignado (por defecto [RangoUsuario.NOOB]). No permite [RangoUsuario.OWNER] ni [RangoUsuario.BANEADO].
     * @return `true` si el usuario fue registrado; `false` si el rango es inválido o el nickname ya existe.
     */
    fun agregarUsuario(user: Usuario, rango: RangoUsuario = RangoUsuario.NOOB): Boolean {
        if (rango == RangoUsuario.OWNER || rango == RangoUsuario.BANEADO) return false
        if (user.rango == RangoUsuario.OWNER || user.rango == RangoUsuario.BANEADO) return false
        if (usuarios.any { it.nickname.equals(user.nickname, ignoreCase = true) }) return false

        user.cambiarRango(rango)
        return usuarios.add(user)
    }

    /**
     * Elimina un usuario del servidor por su nickname, protegiendo siempre al [RangoUsuario.OWNER].
     *
     * @param nickname Nombre del usuario a eliminar.
     * @return `true` si fue eliminado; `false` si no existe o es el propietario del servidor.
     */
    fun eliminarUsuario(nickname: String): Boolean {
        val nicknameLimpio = nickname.trim()
        return usuarios.removeIf {
            it.rango != RangoUsuario.OWNER && it.nickname.equals(nicknameLimpio, ignoreCase = true)
        }
    }

    /**
     * Sanciona a un usuario asignándole el rango [RangoUsuario.BANEADO] y dejando constancia en el historial.
     *
     * @param nickname Nombre del usuario a sancionar.
     * @param responsable Nombre de quien ejecuta la sanción.
     * @param motivo Razón del baneo.
     * @return `true` si el baneo se aplicó; `false` si el usuario no existe, ya está baneado o es el propietario.
     */
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