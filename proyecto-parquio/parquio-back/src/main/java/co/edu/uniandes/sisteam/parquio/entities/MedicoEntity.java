/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.parquio.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author BarraganJeronimo
 */
@Entity
public class MedicoEntity extends BaseEntity implements Serializable {

    private int cedula;
    private String tarjetaProfesional;
    private String nombres;
    private boolean especialista;
    private String apellidos;
    private char sexo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNacimiento;

    @PodamExclude
    @OneToMany (mappedBy = "medicoTratante",cascade = CascadeType.ALL)
    private List<PacienteEntity> pacientesTratando = new ArrayList<>();

    @PodamExclude
    @ManyToMany(mappedBy ="medicos")
    private List<PacienteEntity> pacientes = new ArrayList<>();

    @PodamExclude
    @OneToMany(mappedBy = "medico",cascade = CascadeType.ALL)
    private List<ConsejoEntity> consejosRealizados = new ArrayList<>();

    public boolean getEspecialista() {
        return especialista;
    }

    public void setEspecialista(boolean especialista) {
        this.especialista = especialista;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getTarjetaProfesional() {
        return tarjetaProfesional;
    }

    public void setTarjetaProfesional(String tarjetaProfesional) {
        this.tarjetaProfesional = tarjetaProfesional;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<ConsejoEntity> getConsejosRealizados() {
        return consejosRealizados;
    }

    public void setConsejosRealizados(List<ConsejoEntity> consejosRealizados) {
        this.consejosRealizados = consejosRealizados;
    }

    public void addConsejosRealizados(ConsejoEntity consejosRealizado) {
        this.consejosRealizados.add(consejosRealizado);
    }
    
    public void addPacienteTrantando(PacienteEntity paciente) {
        this.pacientesTratando.add(paciente);
    }

    public List<PacienteEntity> getPacientesTratando() {
        return pacientesTratando;
    }

    public void setPacientesTratando(List<PacienteEntity> pacientesTratando) {
        this.pacientesTratando = pacientesTratando;
    }

    public List<PacienteEntity> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<PacienteEntity> pacientes) {
        this.pacientes = pacientes;
    }

}
