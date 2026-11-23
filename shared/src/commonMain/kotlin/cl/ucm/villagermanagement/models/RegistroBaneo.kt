package cl.ucm.villagermanagement.models

data class RegistroBaneo(
    val usuarioBaneado: String,
    val responsable: String,
    val motivo: String
) {
    init {
        require(usuarioBaneado.isNotBlank()) { "Banned user nickname cannot be blank" }
        require(responsable.isNotBlank()) { "Responsible nickname cannot be blank" }
        require(motivo.isNotBlank()) { "Ban reason cannot be blank" }
    }

    override fun toString(): String {
        return "$usuarioBaneado fue baneado por $responsable. Motivo: $motivo"
    }
}