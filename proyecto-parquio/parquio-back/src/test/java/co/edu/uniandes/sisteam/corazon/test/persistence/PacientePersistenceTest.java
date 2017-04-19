//package co.edu.uniandes.sisteam.corazon.test.persistence;
//
//import co.edu.uniandes.sisteam.parquio.entities.PacienteEntity;
//import co.edu.uniandes.sisteam.parquio.persistence.PacientePersistence;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
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
//@RunWith(Arquillian.class)
//public class PacientePersistenceTest {
//
//    /**
//     *
//     * @return el jar que va a desplegar para la prueba
//     */
//    @Deployment
//    public static JavaArchive createDeployment() {
//        return ShrinkWrap.create(JavaArchive.class)
//                .addPackage(PacienteEntity.class.getPackage())
//                .addPackage(PacientePersistence.class.getPackage())
//                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
//                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
//    }
//
//    @Inject
//    private PacientePersistence pacientePersistence;
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @Inject
//    UserTransaction utx;
//
//    private List<PacienteEntity> data = new ArrayList<PacienteEntity>();
//
//    /**
//     * Configuración inicial de la prueba.
//     */
//    @Before
//    public void setUp() 
//    {
//        try 
//        {
//            utx.begin();
//            em.joinTransaction();
//            clearData();
//            insertData();
//            utx.commit();
//        } 
//        catch (Exception e) 
//        {
//            e.printStackTrace();
//            try 
//            {
//                utx.rollback();
//            }
//            catch (Exception e1) 
//            {
//                e1.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * Limpia las tablas que están implicadas en la prueba.
//     */
//    private void clearData() {
//        em.createQuery("delete from PacienteEntity").executeUpdate();
//    }
//
//    /**
//     * Inserta los datos iniciales para el correcto funcionamiento de las
//     * pruebas.
//     *
//     */
//    private void insertData() {
//        PodamFactory factory = new PodamFactoryImpl();
//        for (int i = 0; i < 3; i++) {
//            PacienteEntity entity = factory.manufacturePojo(PacienteEntity.class);
//            em.persist(entity);
//            data.add(entity);
//        }
//    }
//
//    /**
//     * Prueba para crear un Paciente.
//     */
//    @Test
//    public void createPacienteTest() {
//        PodamFactory factory = new PodamFactoryImpl();
//        PacienteEntity newEntity = factory.manufacturePojo(PacienteEntity.class);
//
//        PacienteEntity result = pacientePersistence.create(newEntity);
//
//        Assert.assertNotNull(result);
//        PacienteEntity entity = em.find(PacienteEntity.class, result.getId());
//        Assert.assertNotNull(entity);
//        Assert.assertEquals(newEntity.getNombres(), entity.getNombres());
//    }
//
//    /**
//     * Prueba para consultar la lista de Pacientes.
//     *
//     *
//     */
//    @Test
//    public void getPacientesTest() 
//    {
//        List<PacienteEntity> list = pacientePersistence.findAll();
//        Assert.assertEquals(data.size(), list.size());
//        for (PacienteEntity ent : list) 
//        {
//            boolean found = false;
//            for (PacienteEntity entity : data) 
//            {
//                if (ent.getId().equals(entity.getId())) 
//                {
//                    found = true;
//                }
//            }
//            Assert.assertTrue(found);
//        }
//    }
//
//    /**
//     * Prueba para consultar una Paciente.
//     */
//    @Test
//    public void getPacienteTest() 
//    {
//        PacienteEntity entity = data.get(0);
//        PacienteEntity newEntity = pacientePersistence.find(entity.getId());
//        Assert.assertNotNull(newEntity);
//        Assert.assertEquals(entity.getNombres(), newEntity.getNombres());
//    }
//
//   /**
//     * Prueba para consultar una Paciente que existe.
//     */
//    @Test
//    public void getPacienteByCedualTest1() {
//        PacienteEntity entity = data.get(0);
//        PacienteEntity newEntity = pacientePersistence.findByCedula(entity.getCedula());
//        Assert.assertNotNull(newEntity);
//        Assert.assertEquals(entity.getCedula(), newEntity.getCedula());
//    }
//   /**
//     * Prueba para consultar una Paciente que no existe.
//     */
//    @Test
//    public void getPacienteByCedulaTest2() {
//        
//        PacienteEntity newEntity = pacientePersistence.findByCedula(-1);
//        Assert.assertNull(newEntity);
//     
//    }
//
//    /**
//     * Prueba para eliminar un Paciente.
//     */
//    @Test
//    public void deletePacienteTest() 
//    {
//        PacienteEntity entity = data.get(0);
//        pacientePersistence.delete(entity.getId());
//        PacienteEntity deleted = em.find(PacienteEntity.class, entity.getId());
//        Assert.assertNull(deleted);
//    }
//
//    /**
//     * Prueba para actualizar un Paciente.
//     */
//    @Test
//    public void updatePacienteTest() 
//    {
//        PacienteEntity entity = data.get(0);
//        PodamFactory factory = new PodamFactoryImpl();
//        PacienteEntity newEntity = factory.manufacturePojo(PacienteEntity.class);
//
//        newEntity.setId(entity.getId());
//
//        pacientePersistence.update(newEntity);
//
//        PacienteEntity resp = em.find(PacienteEntity.class, entity.getId());
//
//        Assert.assertEquals(newEntity.getNombres(), resp.getNombres());
//    }
//}
