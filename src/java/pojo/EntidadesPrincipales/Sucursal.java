package pojo.EntidadesPrincipales;

public class Sucursal {
    private Integer idSucursal;
    private String numeroUnicoSucursal;
    private String nombre;
    private String estado;
    private String numero;
    private String calle;
    private Integer idColonia;
    private String nombreColonia;   
    private Integer codigoPostal;       
    private String nombreMunicipio; 
    private String nombreEstado;

    public Sucursal() {
    }

    public Sucursal(Integer idSucursal, String numeroUnicoSucursal, String nombre, String estado, String numero, String calle, Integer idColonia, String nombreColonia, Integer codigoPostal, String nombreMunicipio, String nombreEstado) {
        this.idSucursal = idSucursal;
        this.numeroUnicoSucursal = numeroUnicoSucursal;
        this.nombre = nombre;
        this.estado = estado;
        this.numero = numero;
        this.calle = calle;
        this.idColonia = idColonia;
        this.nombreColonia = nombreColonia;
        this.codigoPostal = codigoPostal;
        this.nombreMunicipio = nombreMunicipio;
        this.nombreEstado = nombreEstado;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNumeroUnicoSucursal() {
        return numeroUnicoSucursal;
    }

    public void setNumeroUnicoSucursal(String numeroUnicoSucursal) {
        this.numeroUnicoSucursal = numeroUnicoSucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNombreColonia() {
        return nombreColonia;
    }

    public Integer getIdColonia() {
        return idColonia;
    }

    public void setIdColonia(Integer idColonia) {
        this.idColonia = idColonia;
    }

    public void setNombreColonia(String nombreColonia) {
        this.nombreColonia = nombreColonia;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }
    
}
