package pojo.EntidadesPrincipales;

import java.io.Serializable;

public class Envio implements Serializable {

    private int idEnvio;
    private float costo;
    private String noGuia;

    private String nombreReceptor;
    private String apellidoPaternoReceptor;
    private String apellidoMaternoReceptor; // puede ser null

    private String numeroDestino;
    private String calleDestino;

    private Integer idConductor;
    private int idColoniaDestino;
    private int idSucursal;
    private int idCliente;

    public Envio() {
    }

    public Envio(int idEnvio, float costo, String noGuia, String nombreReceptor,
            String apellidoPaternoReceptor, String apellidoMaternoReceptor,
            String numeroDestino, String calleDestino,
            int idConductor, int idSucursal, int idCliente, int idColoniaDestino) {

        this.idEnvio = idEnvio;
        this.costo = costo;
        this.noGuia = noGuia;
        this.nombreReceptor = nombreReceptor;
        this.apellidoPaternoReceptor = apellidoPaternoReceptor;
        this.apellidoMaternoReceptor = apellidoMaternoReceptor;
        this.numeroDestino = numeroDestino;
        this.calleDestino = calleDestino;
        this.idConductor = idConductor;
        this.idSucursal = idSucursal;
        this.idCliente = idCliente;
        this.idColoniaDestino = idColoniaDestino;
    }

    public int getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(int idEnvio) {
        this.idEnvio = idEnvio;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public String getNoGuia() {
        return noGuia;
    }

    public void setNoGuia(String noGuia) {
        this.noGuia = noGuia;
    }

    public String getNombreReceptor() {
        return nombreReceptor;
    }

    public void setNombreReceptor(String nombreReceptor) {
        this.nombreReceptor = nombreReceptor;
    }

    public String getApellidoPaternoReceptor() {
        return apellidoPaternoReceptor;
    }

    public void setApellidoPaternoReceptor(String apellidoPaternoReceptor) {
        this.apellidoPaternoReceptor = apellidoPaternoReceptor;
    }

    public String getApellidoMaternoReceptor() {
        return apellidoMaternoReceptor;
    }

    public void setApellidoMaternoReceptor(String apellidoMaternoReceptor) {
        this.apellidoMaternoReceptor = apellidoMaternoReceptor;
    }

    public String getNumeroDestino() {
        return numeroDestino;
    }

    public void setNumeroDestino(String numeroDestino) {
        this.numeroDestino = numeroDestino;
    }

    public String getCalleDestino() {
        return calleDestino;
    }

    public void setCalleDestino(String calleDestino) {
        this.calleDestino = calleDestino;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(int idConductor) {
        this.idConductor = idConductor;
    }

    public int getIdColoniaDestino() {
        return idColoniaDestino;
    }

    public void setIdColoniaDestino(int idColoniaDestino) {
        this.idColoniaDestino = idColoniaDestino;
    }

}
