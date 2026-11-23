package org.example.models

import cl.ucm.villagermanagement.models.RangoUsuario

/**
 * Representa un usuario de un servidor.
 *
 * @param password Contraseña privada con la que accede un usuario a su cuenta.
 * @property nickname Nombre con el que se identifica la cuenta del usuario.
 * @property rango Nivel de permisos de tipo [RangoUsuario] (inicia siempre en [RangoUsuario.NOOB]).
 * @throws IllegalArgumentException Si [nickname] o [password] están en blanco.
 */
class Usuario(val nickname: String, private val password: String) {
    init {
        require(nickname.isNotBlank()) { "Nickname should not be empty" }
        require(password.isNotBlank()) { "Password should not be empty" }
    }

    var rango: RangoUsuario = RangoUsuario.NOOB
        private set

    /**
     * Cambia el [rango] del usuario por un rango activo válido (distinto de [RangoUsuario.OWNER]
     * y [RangoUsuario.BANEADO]).
     *
     * @param nuevoRango Nuevo rango que tendrá el usuario.
     * @return `true` si el rango fue actualizado; `false` si se intentó asignar un rango restringido.
     */
    fun cambiarRango(nuevoRango: RangoUsuario): Boolean {
        if (nuevoRango == RangoUsuario.OWNER || nuevoRango == RangoUsuario.BANEADO) return false
        this.rango = nuevoRango
        return true
    }

    internal fun asignarOwner() {
        this.rango = RangoUsuario.OWNER
    }

    internal fun asignarBaneado() {
        this.rango = RangoUsuario.BANEADO
    }

    /**
     * Verifica a través de la contraseña ingresada un intento de inicio de sesión.
     *
     * @param password Contraseña con la que se intenta iniciar sesión.
     * @return `true` si las contraseñas coinciden; `false` en caso contrario.
     */
    fun verificarPassword(password: String): Boolean {
        return password == this.password
    }

    override fun toString(): String = "$nickname [$rango]"
}