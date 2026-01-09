package pojo.EntidadesPrincipales;

import java.io.Serializable;

public class Envio implements Serializable {

    private Integer idCliente;
    private String nombreReceptor;
    private String apellidoPaternoReceptor;
    private String apellidoMaternoReceptor;
    private Integer idSucursal;
    private String nombreSucursal;
    private String calleDestino;
    private String numeroDestino;
    private Integer idColoniaDestino;
    private Integer codigoPostal;
    private String ciudad;
    private String estado;
    private String noGuia;
    private Integer idEnvio;
    private float costo;
    private Integer idConductor;
    private String nombreConductor;
    private String apellidoPaternoConductor;
    private String apellidoMaternoConductor;
    private Integer idEstatusEnvio;
    private String estatus;
    private Integer idUltimoEstatusEnvio;

    public Envio() {
    }

    public Envio(Integer idCliente, String nombreReceptor, String apellidoPaternoReceptor, String apellidoMaternoReceptor,
            Integer idSucursal, String nombreSucursal, String calleDestino, String numeroDestino, Integer idColoniaDestino,
            Integer codigoPostal, String ciudad, String estado, String noGuia, Integer idEnvio, float costo,
            Integer idConductor, String nombreConductor, String apellidoPaternoConductor,
            String apellidoMaternoConductor, Integer idEstatusEnvio, String estatus,
            Integer idUltimoEstatusEnvio) {

        this.idCliente = idCliente;
        this.nombreReceptor = nombreReceptor;
        this.apellidoPaternoReceptor = apellidoPaternoReceptor;
        this.apellidoMaternoReceptor = apellidoMaternoReceptor;
        this.idSucursal = idSucursal;
        this.nombreSucursal = nombreSucursal;
        this.calleDestino = calleDestino;
        this.numeroDestino = numeroDestino;
        this.idColoniaDestino = idColoniaDestino;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.estado = estado;
        this.noGuia = noGuia;
        this.idEnvio = idEnvio;
        this.costo = costo;
        this.idConductor = idConductor;
        this.nombreConductor = nombreConductor;
        this.apellidoPaternoConductor = apellidoPaternoConductor;
        this.apellidoMaternoConductor = apellidoMaternoConductor;
        this.idEstatusEnvio = idEstatusEnvio;
        this.estatus = estatus;

    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public String getNombreReceptor() {
        return nombreReceptor;
    }

    public String getApellidoPaternoReceptor() {
        return apellidoPaternoReceptor;
    }

    public String getApellidoMaternoReceptor() {
        return apellidoMaternoReceptor;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public String getCalleDestino() {
        return calleDestino;
    }

    public String getNumeroDestino() {
        return numeroDestino;
    }

    public Integer getIdColoniaDestino() {
        return idColoniaDestino;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public String getNoGuia() {
        return noGuia;
    }

    public Integer getIdEnvio() {
        return idEnvio;
    }

    public float getCosto() {
        return costo;
    }

    public Integer getIdConductor() {
        return idConductor;
    }

    public String getNombreConductor() {
        return nombreConductor;
    }

    public String getApellidoPaternoConductor() {
        return apellidoPaternoConductor;
    }

    public String getApellidoMaternoConductor() {
        return apellidoMaternoConductor;
    }

    public Integer getIdEstatusEnvio() {
        return idEstatusEnvio;
    }

    public String getEstatus() {
        return estatus;
    }

    public Integer getIdUltimoEstatusEnvio() {
        return idUltimoEstatusEnvio;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public void setNombreReceptor(String nombreReceptor) {
        this.nombreReceptor = nombreReceptor;
    }

    public void setApellidoPaternoReceptor(String apellidoPaternoReceptor) {
        this.apellidoPaternoReceptor = apellidoPaternoReceptor;
    }

    public void setApellidoMaternoReceptor(String apellidoMaternoReceptor) {
        this.apellidoMaternoReceptor = apellidoMaternoReceptor;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public void setCalleDestino(String calleDestino) {
        this.calleDestino = calleDestino;
    }

    public void setNumeroDestino(String numeroDestino) {
        this.numeroDestino = numeroDestino;
    }

    public void setIdColoniaDestino(Integer idColoniaDestino) {
        this.idColoniaDestino = idColoniaDestino;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setNoGuia(String noGuia) {
        this.noGuia = noGuia;
    }

    public void setIdEnvio(Integer idEnvio) {
        this.idEnvio = idEnvio;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public void setIdConductor(Integer idConductor) {
        this.idConductor = idConductor;
    }

    public void setNombreConductor(String nombreConductor) {
        this.nombreConductor = nombreConductor;
    }

    public void setApellidoPaternoConductor(String apellidoPaternoConductor) {
        this.apellidoPaternoConductor = apellidoPaternoConductor;
    }

    public void setApellidoMaternoConductor(String apellidoMaternoConductor) {
        this.apellidoMaternoConductor = apellidoMaternoConductor;
    }

    public void setIdEstatusEnvio(Integer idEstatusEnvio) {
        this.idEstatusEnvio = idEstatusEnvio;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public void setIdUltimoEstatusEnvio(Integer idUltimoEstatusEnvio) {
        this.idUltimoEstatusEnvio = idUltimoEstatusEnvio;
    }

}
