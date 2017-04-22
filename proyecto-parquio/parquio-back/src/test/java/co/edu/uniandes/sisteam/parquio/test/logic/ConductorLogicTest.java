package co.edu.uniandes.sisteam.parquio.test.logic;

//package co.edu.uniandes.sisteam.corazon.test.logic;
//
//import co.edu.uniandes.sisteam.corazon.api.IPacienteLogic;
//import co.edu.uniandes.sisteam.corazon.ejbs.PacienteLogic;
//import co.edu.uniandes.sisteam.corazon.entities.PacienteEntity;
//import co.edu.uniandes.sisteam.corazon.exceptions.BusinessLogicException;
//import co.edu.uniandes.sisteam.corazon.persistence.PacientePersistence;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.TypedQuery;
//import javax.transaction.NotSupportedException;
//import javax.transaction.UserTransaction;
//import org.junit.Assert;
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.spec.JavaArchive;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import uk.co.jemos.podam.api.PodamFactory;
//import uk.co.jemos.podam.api.PodamFactoryImpl;
//
///**
// *
// */
//@RunWith(Arquillian.class)
//public class PacienteLogicTest {
//
//    /**
//     *
//     */
//    private PodamFactory factory = new PodamFactoryImpl();
//
//    /**
//     *
//     */
//    @Inject
//    private IPacienteLogic pacienteLogic;
//
//
//    /**
//     *
//     */
//    @PersistenceContext
//    private EntityManager em;
//
//    /**
//     *
//     */
//    @Inject
//    private UserTransaction utx;
//
//    /**
//     *
//     */
//    private List<PacienteEntity> data = new ArrayList<PacienteEntity>();
//
//    /**
//     *
//     */
//    @Deployment
//    public static JavaArchive createDeployment() {
//        return ShrinkWrap.create(JavaArchive.class)
//                .addPackage(PacienteEntity.class.getPackage())
//                .addPackage(PacienteLogic.class.getPackage())
//                .addPackage(IPacienteLogic.class.getPackage())
//                .addPackage(PacientePersistence.class.getPackage())
//                .addPackage(BusinessLogicException.class.getPackage())
////                .addPackage(MesaPersistence.class.getPackage())
////                .addPackage(MesaEntity.class.getPackage())
////                .addPackage(MesaLogic.class.getPackage())
////                .addPackage(IMesaLogic.class.getPackage())
//                
//                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
//                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
//    }
//
//    /**
//     * Configuración inicial de la prueba.
//     *
//     *
//     */
//    @Before
//    public void setUp() {
//        try {
//            utx.begin();
//            clearData();
//            insertData();
//            utx.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            try {
//                utx.rollback();
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * Limpia las tablas que están implicadas en la prueba.
//     *
//     *
//     */
//    private void clearData() {
//       
//        em.createQuery("delete from PacienteEntity").executeUpdate();
//    }
//
//    /**
//     * Inserta los datos iniciales para el correcto funcionamiento de las
//     * pruebas.
//     *
//     *
//     */
//    private void insertData() {
//
//        for (int i = 0; i < 3; i++) 
//        {
//            PacienteEntity entity = factory.manufacturePojo(PacienteEntity.class);
//            em.persist(entity);
//            data.add(entity);
//        }
//    }
//
//    /**
//     * Prueba para crear un Paciente
//     *
//     *
//     * @throws javax.transaction.NotSupportedException
//     */
//    @Test
//    public void createPacienteTest() throws NotSupportedException, Exception {
//        PacienteEntity newEntity = factory.manufacturePojo(PacienteEntity.class);    
//        PacienteEntity result = pacienteLogic.createPaciente(newEntity);
//        Assert.assertNotNull(result);
//        
//        PacienteEntity entity = em.find(PacienteEntity.class, result.getId());
//        
//        Assert.assertEquals(newEntity.getNombres(), entity.getNombres());
//        Assert.assertEquals(newEntity.getId(), entity.getId());
//        
//        
//        
//    }
//
//    /**
//     * Prueba para consultar la lista de Pacientes
//     *
//     *
//     */
//    @Test
//    public void getPacientesTest() {
//        List<PacienteEntity> list = pacienteLogic.getPacientes();
//        Assert.assertEquals(data.size(), list.size());
//        for (PacienteEntity entity : list) {
//            boolean found = false;
//            for (PacienteEntity storedEntity : data) {
//                if (entity.getId().equals(storedEntity.getId())) {
//                    found = true;
//                }
//            }
//            Assert.assertTrue(found);
//        }
//    }
//
//    /**
//     * Prueba para consultar un Paciente
//     *
//     *
//     */
//    @Test
//    public void getPacienteTest() {
//        PacienteEntity entity = data.get(0);
//        PacienteEntity resultEntity = pacienteLogic.getPaciente(entity.getId());
//        Assert.assertNotNull(resultEntity);
//        Assert.assertEquals(entity.getNombres(), resultEntity.getNombres());
//        Assert.assertEquals(entity.getId(), resultEntity.getId());
//    }
//
//    /**
//     * Prueba para eliminar un Paciente
//     *
//     *
//     */
//    @Test
//    public void deletePacienteTest() {
//        PacienteEntity entity = data.get(1);
//        pacienteLogic.deletePaciente(entity.getId());
//        PacienteEntity deleted = em.find(PacienteEntity.class, entity.getId());
//        Assert.assertNull(deleted);
//    }
//
//    /**
//     * Prueba para actualizar un Paciente
//     *
//     *
//     */
//    @Test
//    public void updatePacienteTest() {
//        PacienteEntity entity = data.get(0);
//        PacienteEntity pojoEntity = factory.manufacturePojo(PacienteEntity.class);
//
//        pojoEntity.setId(entity.getId());
//
//        pacienteLogic.updatePaciente(pojoEntity);
//
//        PacienteEntity resp = em.find(PacienteEntity.class, entity.getId());
//
//        Assert.assertEquals(pojoEntity.getNombres(), resp.getNombres());
//        Assert.assertEquals(pojoEntity.getId(), resp.getId());
//    }
//}
