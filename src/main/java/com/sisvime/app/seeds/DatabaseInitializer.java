package com.sisvime.app.seeds;

import com.sisvime.app.models.Dao.*;
import com.sisvime.app.models.entity.*;
import com.sisvime.app.share.RolesType;

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
            IVehiculoDao vehiculoDao) {
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
        // Personal
        initPersonal();        
        initHoras();
        initVehiculos();
        initHorarioTrabajo();
        initCitas();
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
            admin.setId(RolesType.ADMIN.getIndex());
            admin.setPerfil("ADMIN");
            perfilesDao.save(admin);

            var paciente = new Perfil();
            paciente.setId(RolesType.PACIENTE.getIndex());
            paciente.setPerfil("PACIENTE");
            perfilesDao.save(paciente);

            var doctor = new Perfil();
            doctor.setId(RolesType.DOCTOR.getIndex());
            doctor.setPerfil("DOCTOR");
            perfilesDao.save(doctor);

            var jefeEnfemeria = new Perfil();
            jefeEnfemeria.setId(RolesType.JEFA_ENFERMERIA.getIndex());
            jefeEnfemeria.setPerfil("JEFA ENFERMERIA");
            perfilesDao.save(jefeEnfemeria);
        }
    }

    private void initUsers() {
        var countUsuarios = usuarioDao.count();
        if (countUsuarios <= 0) {
            var userAdmin = createUserAdmin();
            var userPaciente = createUserPaciente();
            var userDoctor = createUserDoctor();
            var userJEnf = createUserJEnf();

            usuarioDao.save(userPaciente);
            usuarioDao.save(userDoctor);
            usuarioDao.save(userAdmin);
            usuarioDao.save(userJEnf);
        }
    }

    private Usuario createUserAdmin() {
        var userAdmin = new Usuario();
        var admin = perfilesDao.getReferenceById(RolesType.ADMIN.getIndex());
        var perfils = new ArrayList<Perfil>();
        perfils.add(admin);
        userAdmin.setNombre("admin");
        userAdmin.setUsername("admin");
        userAdmin.setApellido("admin");
        userAdmin.setEmail("admin@mail.com");
        userAdmin.setPerfiles(perfils);
        userAdmin.setEstatus(1);// ACTIVE
        userAdmin.setPassword(encoder.encode("admin"));
        return userAdmin;
    }

    private Usuario createUserPaciente() {
        var userPaciente = new Usuario();
        var paciente = perfilesDao.getReferenceById(RolesType.PACIENTE.getIndex());
        var perfils = new ArrayList<Perfil>();
        perfils.add(paciente);
        userPaciente.setNombre("Richard");
        userPaciente.setUsername("Smith");
        userPaciente.setApellido("paciente");
        userPaciente.setEmail("r.ramirez@mail.com");
        userPaciente.setPerfiles(perfils);
        userPaciente.setEstatus(1);// ACTIVE
        userPaciente.setPassword(encoder.encode("123qwe"));

        return userPaciente;
    }

    private Usuario createUserDoctor() {
        var userDoctor = new Usuario();
        var doctor = perfilesDao.getReferenceById(RolesType.DOCTOR.getIndex());
        var perfils = new ArrayList<Perfil>();
        perfils.add(doctor);
        userDoctor.setNombre("Cesar");
        userDoctor.setUsername("Cerda");
        userDoctor.setApellido("Cerdita");
        userDoctor.setEmail("ccerda@mail.com");
        userDoctor.setPerfiles(perfils);
        userDoctor.setEstatus(1);// ACTIVE
        userDoctor.setPassword(encoder.encode("123qwe"));
        return userDoctor;
    }
    private Usuario createUserJEnf() {
        var userJenf = new Usuario();
        var jEnf = perfilesDao.getReferenceById(RolesType.JEFA_ENFERMERIA.getIndex());
        var perfils = new ArrayList<Perfil>();
        perfils.add(jEnf);
        userJenf.setNombre("jefaEnf");
        userJenf.setUsername("jefaEnf");
        userJenf.setApellido("jefaEnf");
        userJenf.setEmail("jefaEnf@mail.com");
        userJenf.setPerfiles(perfils);
        userJenf.setEstatus(1);// ACTIVE
        userJenf.setPassword(encoder.encode("123qwe"));
        return userJenf;
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

            var enfermera = new Especialidad();
            enfermera.setNomespecialidad("Enfermera");

            var psicologo = new Especialidad();
            psicologo.setNomespecialidad("Psicologo");

            var nutricionista = new Especialidad();
            nutricionista.setNomespecialidad("Nutricionista");

            var chofer = new Especialidad();
            chofer.setNomespecialidad("Chofer");

            var tecnicaEnf = new Especialidad();
            tecnicaEnf.setNomespecialidad("T\u00E9cnica en Enfermer\u00EDa");

            var cirujano = new Especialidad();
            cirujano.setNomespecialidad("Cirujano");

            especialidadDao.save(geriatra);
            especialidadDao.save(general);
            especialidadDao.save(especialista);
            especialidadDao.save(odontologo);
            especialidadDao.save(enfermera);
            especialidadDao.save(psicologo);
            especialidadDao.save(nutricionista);
            especialidadDao.save(chofer);
            especialidadDao.save(tecnicaEnf);
            especialidadDao.save(cirujano);
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
                    new Hora(LocalTime.of(17, 0, 0)));
            horaDao.saveAll(horas);
        }
    }

    private void initPersonal() {
        if (especialidadDao.count() <= 0) {
            return;
        }
        var geriatra = especialidadDao.findById(1L).orElse(null);
        var enfermera = especialidadDao.findById(5L).orElse(null);
        var chofer = especialidadDao.findById(8L).orElse(null);
        var tecEnfermera = especialidadDao.findById(9L).orElse(null);

        var doc1 = new Personal(
                "87654321", "Anderson", "Diaz", "Diaz",
                "M", new Date(), "anderson@gmail.com", "999222333", geriatra);
        doc1.setFoto("perfil9.jpg");
        doc1.setDireccion("Villa");
        doc1.setEstadocivil("Casado");
        doc1.setTelefonofijo("014311212");
        doc1.setCelular("980441255");

        var enf1 = new Personal(
                "212131", "Stefany", "Ramirez", "Diaz",
                "F", new Date(), "stefany@gmail.com", "98840012", enfermera);
        enf1.setFoto("perfil9.jpg");
        enf1.setDireccion("Surco");
        enf1.setEstadocivil("Casado");
        enf1.setTelefonofijo("2920983");
        enf1.setFoto("img.jpg");

        var ch1 = new Personal(
                "74299833", "Manuel", "Perez", "Sanchez",
                "M", new Date(), "perez@gmail.com", "980441299", chofer);
        ch1.setFoto("perfil4.jpg");
        ch1.setDireccion("Parques de Surco");
        ch1.setEstadocivil("Casado");
        ch1.setTelefonofijo("2920983");

        var tec1 = new Personal(
                "2192058", "Maria", "Loaysa", "Cruz",
                "M", new Date(), "maria@gmail.com", "980441277", tecEnfermera);
        tec1.setFoto("perfil7.jpg");
        tec1.setDireccion("parques de san juan");
        tec1.setEstadocivil("Soltero");
        tec1.setTelefonofijo("2192058");

        personaDao.save(enf1);
        personaDao.save(doc1);
        personaDao.save(ch1);
        personaDao.save(tec1);
    }

    private void initPacientes() {
        var countPacientes = pacienteDao.count();

        if (countPacientes <= 0) {
            var pac1 = new Paciente();
            pac1.setDni("7654321");
            pac1.setNombre("John");
            pac1.setApellido_pa("Smith");
            pac1.setApellidoma("Paredes");
            pac1.setParentesco("Padre");
            pac1.setSexo("M");
            pac1.setFecha_nacimiento(new Date());
            pac1.setCorreo("doe@gmail.com");
            pac1.setCelular("912855091");
            pac1.setTelefonofijo("2920983");
            pac1.setDireccion("Villa");
            pac1.setAltura("1.60");
            pac1.setMasa("60");
            pac1.setPeso("60");
            pac1.setDescalergia("Alergica al polvo, estornuda y duerme mucho.");
            pac1.setTitular("Tecnico");
            pac1.setDireccion("Ate");
            pac1.setEstadocivil("Soltero");
            pac1.setFoto("perfil3.jpg");
            pac1.setDistrito("LIMA");
            pac1.setSangre("O");
            pacienteDao.save(pac1);
        }
    }

    private void initCitas() {
        var countCitas = citaDao.count();
        if (countCitas <= 0) {
            var paciente = pacienteDao.findByNombre("John");
            var doctor = (List<Personal>) personaDao.findAll();

            var hora = (List<Hora>) horaDao.findAll();

            var cita1 = new Cita();
            cita1.setPac(paciente.get(0));
            cita1.setEsp(doctor.get(0).getEspec().getNomespecialidad());
            cita1.setIdhora(hora.get(0));
            cita1.setFecha(new Date());
            cita1.setMed("Manuel");
            cita1.setEstado("Este paciente se encuentra delicado de salud");
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
            ambulancia.setFoto("amb3.jpg");
            ambulancia.setEjes(2);
            ambulancia.setMotor(1200L);
            ambulancia.setFabrica(new Date());
            vehiculoDao.save(ambulancia);
        }
    }
}
