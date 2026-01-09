package dto;

public class RSAutenticacion {

    private boolean error;
    private String mensaje;
    private ColaboradorDTO colaborador;

    public RSAutenticacion() {
    }

    public RSAutenticacion(boolean error, String mensaje, ColaboradorDTO colaborador) {
        this.error = error;
        this.mensaje = mensaje;
        this.colaborador = colaborador;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ColaboradorDTO getColaborador() {
        return colaborador;
    }

    public void setColaborador(ColaboradorDTO colaborador) {
        this.colaborador = colaborador;
    }
}
