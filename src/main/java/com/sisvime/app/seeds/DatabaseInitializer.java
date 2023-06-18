package com.sisvime.app.seeds;


import com.sisvime.app.models.Dao.*;
import com.sisvime.app.models.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class DatabaseInitializer {

    private final BCryptPasswordEncoder encoder;
    private final IUsuarioDao usuarioDao;
    private final IPerfilesDao perfilesDao;
    private final IEspecialidadDao especialidadDao;
    private final IPacienteDao pacienteDao;
    private final ICitaDao citaDao;
    private final IHoraDao horaDao;
    private final IMedicamentoDao medicamentoDao;
    private final IHoratrabajoDao horatrabajoDao;
    private final IPersonaDao personaDao;
    private final IVehiculoDao vehiculoDao;

    @Autowired
    public DatabaseInitializer(
            BCryptPasswordEncoder encoder,
            IUsuarioDao usuarioDao,
            IPerfilesDao perfilesDao,
            IEspecialidadDao especialidadDao,
            IPacienteDao pacienteDao,
            ICitaDao citaDao,
            IHoraDao horaDao,
            IMedicamentoDao medicamentoDao,
            IHoratrabajoDao horatrabajoDao,
            IPersonaDao personaDao,
            IVehiculoDao vehiculoDao
    ) {
        this.encoder = encoder;
        this.usuarioDao = usuarioDao;
        this.perfilesDao = perfilesDao;
        this.especialidadDao = especialidadDao;
        this.pacienteDao = pacienteDao;
        this.citaDao = citaDao;
        this.horaDao = horaDao;
        this.medicamentoDao = medicamentoDao;
        this.horatrabajoDao = horatrabajoDao;
        this.personaDao = personaDao;
        this.vehiculoDao = vehiculoDao;
    }

    @PostConstruct
    @Transactional
    public void init() {
        initPerfil();
        initUsers();
        initMedicamentos();
        initSpecialidad();
        initPacientes();
        initDoctor();
        initHoras();
        initVehiculos();
        initCitas();
        initHorarioTrabajo();
    }

    private void initHorarioTrabajo() {
        var count = horatrabajoDao.count();
        if (count == 0) {
            var horario = new Horatrabajo();
            var persona = (List<Personal>) personaDao.findAll();
            var hora = (List<Hora>) horaDao.findAll();
            horario.setFecha(new Date());
            horario.setIdper(persona.get(0));
            horario.setIdhora(hora.get(0));
            horatrabajoDao.save(horario);
        }
    }


    private void initPerfil() {
        var countPerfiles = perfilesDao.count();

        if (countPerfiles <= 0) {
            var admin = new Perfil();
            admin.setPerfil("ADMIN");
            perfilesDao.save(admin);

            var user = new Perfil();
            user.setPerfil("USER");
            perfilesDao.save(user);
        }
    }

    private void initUsers() {
        var countUsuarios = usuarioDao.count();
        if (countUsuarios <= 0) {
            var userAdmin = createUserAdmin();
            usuarioDao.save(userAdmin);
        }
    }

    private Usuario createUserAdmin() {
        var userAdmin = new Usuario();
        var admin = perfilesDao.getReferenceById(1);
        var perfils = new ArrayList<Perfil>();
        perfils.add(admin);
        userAdmin.setNombre("alex");
        userAdmin.setUsername("alex");
        userAdmin.setApellido("torre");
        userAdmin.setEmail("alex@mail.com");
        userAdmin.setPerfiles(perfils);
        userAdmin.setEstatus(1);//ACTIVE
        userAdmin.setPassword(encoder.encode("123qwe"));
        return userAdmin;
    }

    private void initMedicamentos() {
        var countMed = medicamentoDao.count();
        if (countMed <= 0) {
            var medicamento = new Medicamento();
            medicamento.setNombre("Paracetamol");
            medicamento.setDescripcion("Panacea");
            medicamentoDao.save(medicamento);
        }
    }

    private void initSpecialidad() {
        var countSpeciality = especialidadDao.count();
        if (countSpeciality <= 0) {
            var geriatra = new Especialidad();
            geriatra.setNomespecialidad("Geriatra");

            var general = new Especialidad();
            general.setNomespecialidad("Medico General");

            var especialista = new Especialidad();
            especialista.setNomespecialidad("Medico Especialista");

            var odontologo = new Especialidad();
            odontologo.setNomespecialidad("Odontologo");

            especialidadDao.save(geriatra);
            especialidadDao.save(general);
            especialidadDao.save(especialista);
            especialidadDao.save(odontologo);
        }
    }

    private void initHoras() {
        var countHoras = horaDao.count();
        if (countHoras <= 0) {
            List<Hora> horas = Arrays.asList(
                    new Hora(LocalTime.of(9, 0, 0)),
                    new Hora(LocalTime.of(9, 30, 0)),
                    new Hora(LocalTime.of(10, 0, 0)),
                    new Hora(LocalTime.of(10, 30, 0)),
                    new Hora(LocalTime.of(11, 0, 0)),
                    new Hora(LocalTime.of(11, 30, 0)),
                    new Hora(LocalTime.of(12, 0, 0)),
                    new Hora(LocalTime.of(12, 30, 0)),
                    new Hora(LocalTime.of(13, 0, 0)),
                    new Hora(LocalTime.of(13, 30, 0)),
                    new Hora(LocalTime.of(14, 0, 0)),
                    new Hora(LocalTime.of(14, 30, 0)),
                    new Hora(LocalTime.of(15, 0, 0)),
                    new Hora(LocalTime.of(15, 30, 0)),
                    new Hora(LocalTime.of(16, 0, 0)),
                    new Hora(LocalTime.of(16, 30, 0)),
                    new Hora(LocalTime.of(17, 0, 0))
            );
            horaDao.saveAll(horas);
        }
    }

    private void initDoctor() {
        var countCitas = personaDao.count();
        if (countCitas <= 0) {
            var esp = especialidadDao.findById(1L).orElse(null);
            var doctor1 = new Personal(
                    "12345678", "Manuel", "Perez", "Sanchez",
                    "M", new Date(), "manuel@mail.com", "abc", esp);
            personaDao.save(doctor1);
        }
    }

    private void initPacientes() {
        var countPacientes = pacienteDao.count();

        if (countPacientes <= 0) {
            var pac1 = new Paciente();
            pac1.setDni("7654321");
            pac1.setNombre("Alexander");
            pac1.setApellido_pa("Torre");
            pac1.setApellidoma("Arteaga");
            pac1.setParentesco("Padre");
            pac1.setFecha_nacimiento(new Date());
            pac1.setDireccion("Mi casa");
            pac1.setCorreo("alex@gmail.com");
            pac1.setCelular("933855094");
            pacienteDao.save(pac1);
        }
    }

    private void initCitas() {
        var countCitas = citaDao.count();
        if (countCitas <= 0) {
            //var paciente = pacienteDao.findById(1L).orElseThrow();
            var paciente = pacienteDao.findByNombre("Alexander");
//            var doctor = personaDao.findById(1L).orElseThrow();
            var doctor = (List<Personal>) personaDao.findAll();
//            var hora = horaDao.findById(1).orElseThrow();
            var hora = (List<Hora>) horaDao.findAll();

            var cita1 = new Cita();
//            cita1.setPac(paciente);
            cita1.setPac(paciente.get(0));
//            cita1.setEsp(doctor.getEspec().getNomespecialidad());
            cita1.setEsp(doctor.get(0).getEspec().getNomespecialidad());
//            cita1.setIdhora(hora);
            cita1.setIdhora(hora.get(0));
            cita1.setFecha(new Date());
            cita1.setEstado("ABC ABC ABC");
            citaDao.save(cita1);
        }
    }

    private void initVehiculos() {
        var count = vehiculoDao.count();
        if (count <= 0) {
            var ambulancia = new Vehiculo();
            ambulancia.setPlaca("FOO-425");
            ambulancia.setMarca("TOYOTA");
            ambulancia.setModelo("4x4");
            ambulancia.setColor("Blanco");
            ambulancia.setCombustible("Gasolina");
            ambulancia.setEjes(2);
            ambulancia.setMotor(1200L);
            ambulancia.setFabrica(new Date());
            vehiculoDao.save(ambulancia);
        }
    }
}
