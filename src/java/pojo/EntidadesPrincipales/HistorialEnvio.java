package pojo.EntidadesPrincipales;

// IMPORTANTE: Quitamos los objetos anidados (Envio, Colaborador, EstatusEnvio)
// para la entrada de datos, y solo dejamos los IDs necesarios.
// Esto evita la confusión del serializador (MOXy/Jersey).

import java.util.Date;
import pojo.Catalogo.EstatusEnvio;

public class HistorialEnvio {

    private Integer idEnvio;
    private Integer idColaborador;
    private Integer idEstatusEnvio;
    private String comentario;
    private Colaborador colaborador;
    private EstatusEnvio estatusEnvio;

    private Date fechaHoraCambio;
    
    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public EstatusEnvio getEstatusEnvio() {
        return estatusEnvio;
    }

    public void setEstatusEnvio(EstatusEnvio estatusEnvio) {
        this.estatusEnvio = estatusEnvio;
    }

    public Integer getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Integer idEnvio) {
        this.idEnvio = idEnvio;
    }

    public Integer getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Integer idColaborador) {
        this.idColaborador = idColaborador;
    }

    public Integer getIdEstatusEnvio() {
        return idEstatusEnvio;
    }

    public void setIdEstatusEnvio(Integer idEstatusEnvio) {
        this.idEstatusEnvio = idEstatusEnvio;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFechaHoraCambio() {
        return fechaHoraCambio;
    }

    public void setFechaHoraCambio(Date fechaHoraCambio) {
        this.fechaHoraCambio = fechaHoraCambio;
    }
}