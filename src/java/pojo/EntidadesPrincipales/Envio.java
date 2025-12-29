package pojo.EntidadesPrincipales;

import java.io.Serializable;

public class Envio implements Serializable {

    private int idCliente;
    private String nombreReceptor;
    private String apellidoPaternoReceptor;
    private String apellidoMaternoReceptor;
    private String telefono;
    private String correo;
    private int idSucursal;
    private String nombreSucursal;
    private String calleDestino;
    private String numeroDestino;
    private int idColoniaDestino;
    private int codigoPostal;
    private String ciudad;
    private String estado;
    private String noGuia;
    private int idEnvio;
    private float costo;
    private Integer idConductor;
    private String status;

    public Envio() {
    }

    public Envio(int idCliente, String nombreReceptor, String apellidoPaternoReceptor, String apellidoMaternoReceptor, int idSucursal, String calleDestino, String numeroDestino, int idColoniaDestino, int codigoPostal, String ciudad, String estado, String noGuia, int idEnvio, float costo, Integer idConductor) {
        this.idCliente = idCliente;
        this.nombreReceptor = nombreReceptor;
        this.apellidoPaternoReceptor = apellidoPaternoReceptor;
        this.apellidoMaternoReceptor = apellidoMaternoReceptor;
        this.idSucursal = idSucursal;
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
    }

    public int getIdCliente() {
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public String getCalleDestino() {
        return calleDestino;
    }

    public String getNumeroDestino() {
        return numeroDestino;
    }

    public int getIdColoniaDestino() {
        return idColoniaDestino;
    }

    public int getCodigoPostal() {
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

    public int getIdEnvio() {
        return idEnvio;
    }

    public float getCosto() {
        return costo;
    }

    public Integer getIdConductor() {
        return idConductor;
    }

    public void setIdCliente(int idCliente) {
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

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public void setCalleDestino(String calleDestino) {
        this.calleDestino = calleDestino;
    }

    public void setNumeroDestino(String numeroDestino) {
        this.numeroDestino = numeroDestino;
    }

    public void setIdColoniaDestino(int idColoniaDestino) {
        this.idColoniaDestino = idColoniaDestino;
    }

    public void setCodigoPostal(int codigoPostal) {
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

    public void setIdEnvio(int idEnvio) {
        this.idEnvio = idEnvio;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public void setIdConductor(Integer idConductor) {
        this.idConductor = idConductor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}