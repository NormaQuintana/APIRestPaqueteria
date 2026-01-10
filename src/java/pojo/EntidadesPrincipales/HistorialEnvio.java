package pojo.EntidadesPrincipales;

import java.util.Date;
import pojo.Catalogo.EstatusEnvio;

public class HistorialEnvio {

    private Integer idHistorialEnvio;
    private Integer idEnvio;
    private Date fechaHora;
    private Integer idColaborador;
    private EstatusEnvio estatusEnvio;
    private Integer idEstatusEnvio;

    public HistorialEnvio() {
    }

    public Integer getIdHistorialEnvio() {
        return idHistorialEnvio;
    }

    public void setIdHistorialEnvio(Integer idHistorialEnvio) {
        this.idHistorialEnvio = idHistorialEnvio;
    }

    public Integer getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Integer idEnvio) {
        this.idEnvio = idEnvio;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Integer getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Integer idColaborador) {
        this.idColaborador = idColaborador;
    }

    public EstatusEnvio getEstatusEnvio() {
        return estatusEnvio;
    }

    public void setEstatusEnvio(EstatusEnvio estatusEnvio) {
        this.estatusEnvio = estatusEnvio;
    }

    public Integer getIdEstatusEnvio() {
        return idEstatusEnvio;
    }

    public void setIdEstatusEnvio(Integer idEstatusEnvio) {
        this.idEstatusEnvio = idEstatusEnvio;
    }

}
