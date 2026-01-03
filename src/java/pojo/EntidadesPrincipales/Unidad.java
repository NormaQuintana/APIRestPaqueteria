package pojo.EntidadesPrincipales;


import java.util.Date;

public class Unidad {

    private Integer idUnidad;
    private String marca;
    private String modelo;
    private Integer anio;
    private String vin;
    private String noIdentificacion; 
    private Boolean status;
    private String motivoBaja;
    private Date fechaBaja;
    private String tipoUnidad;
    private Integer idTipoUnidad;
    private Colaborador conductor;
    private Integer idConductor;

    public Unidad() {
    
    }

    public Unidad(Integer idUnidad, String marca, String modelo, Integer anio, String vin, String noIdentificacion, Boolean status, String tipoUnidad, Integer idTipoUnidad) {
        this.idUnidad = idUnidad;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.vin = vin;
        this.noIdentificacion = noIdentificacion;
        this.status = status;
        this.tipoUnidad = tipoUnidad;
        this.idTipoUnidad = idTipoUnidad;
    }

    public Integer getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(Integer idConductor) {
        this.idConductor = idConductor;
    }
    
    public Integer getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getNoIdentificacion() {
        return noIdentificacion;
    }

    public void setNoIdentificacion(String noIdentificacion) {
        this.noIdentificacion = noIdentificacion;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMotivoBaja() {
        return motivoBaja;
    }

    public void setMotivoBaja(String motivoBaja) {
        this.motivoBaja = motivoBaja;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getTipoUnidad() {
        return tipoUnidad;
    }
    
    public void setTipoUnidad(String tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }

    public Integer getIdTipoUnidad() {
        return idTipoUnidad;
    }

    public void setIdTipoUnidad(Integer idTipoUnidad) {
        this.idTipoUnidad = idTipoUnidad;
    }

    public Colaborador getConductor() {
        return conductor;
    }

    public void setConductor(Colaborador conductor) {
        this.conductor = conductor;
    }

}
