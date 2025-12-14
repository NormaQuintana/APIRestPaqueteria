package pojo.EntidadesPrincipales;

public class Cliente {
    private Integer idCliente;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;

    private String telefono;
    private String correo;
    private String calle;
    private String numero;
    private Integer idColonia;
    
    private String nombreColonia;
    private String codigoPostal;
    private String nombreMunicipio;
    private String nombreEstado;
    

    private Boolean statusRegistro; 

    public Cliente() {
    }

    public Cliente(Integer idCliente, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correo, String calle, String numero, Integer idColonia, String nombreColonia, String codigoPostal, String nombreMunicipio, String nombreEstado, Boolean statusRegistro) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.correo = correo;
        this.calle = calle;
        this.numero = numero;
        this.idColonia = idColonia;
        this.nombreColonia = nombreColonia;
        this.codigoPostal = codigoPostal;
        this.nombreMunicipio = nombreMunicipio;
        this.nombreEstado = nombreEstado;
        this.statusRegistro = statusRegistro;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getCalle() {
        return calle;
    }

    public String getNumero() {
        return numero;
    }

    public Integer getIdColonia() {
        return idColonia;
    }

    public String getNombreColonia() {
        return nombreColonia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public Boolean getStatusRegistro() {
        return statusRegistro;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setIdColonia(Integer idColonia) {
        this.idColonia = idColonia;
    }

    public void setNombreColonia(String nombreColonia) {
        this.nombreColonia = nombreColonia;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public void setStatusRegistro(Boolean statusRegistro) {
        this.statusRegistro = statusRegistro;
    }

}