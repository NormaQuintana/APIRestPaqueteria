package dto;

import pojo.EntidadesPrincipales.Colaborador;

public class ColaboradorDTO {

    private Integer idColaborador;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String rol;
    private Integer idSucursal;
    private String noLicencia;

    public static ColaboradorDTO from(Colaborador c) {
        if (c == null) {
            return null;
        }
        ColaboradorDTO dto = new ColaboradorDTO();
        dto.idColaborador = c.getIdColaborador();
        dto.nombre = c.getNombre();
        dto.apellidoPaterno = c.getApellidoPaterno();
        dto.apellidoMaterno = c.getApellidoMaterno();
        dto.rol = c.getNombreRol();
        dto.idSucursal = c.getIdSucursal();
        dto.noLicencia = c.getNoLicencia();

        return dto;
    }

    public ColaboradorDTO() {
    }

    public ColaboradorDTO(Integer idColaborador, String nombre, String apellidoPaterno,
            String apellidoMaterno, String rol, Integer idSucursal) {
        this.idColaborador = idColaborador;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.rol = rol;
        this.idSucursal = idSucursal;
    }

    public Integer getIdColaborador() {
        return idColaborador;
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

    public String getRol() {
        return rol;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdColaborador(Integer idColaborador) {
        this.idColaborador = idColaborador;
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

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNoLicencia() {
        return noLicencia;
    }

    public void setNoLicencia(String noLicencia) {
        this.noLicencia = noLicencia;
    }

}