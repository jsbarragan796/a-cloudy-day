# SisTeam_Restaurantes

Ejemplo de un servicio REST implementado usando JAX-RS. 
El servicio permite manipular datos de una cadena de restaurantes. 

## Estructura del proyecto

El proyecto sigue la estructura de los proyectos Maven. 
Por lo tanto, el código fuente Java está ubicado en la carpeta `src/main/java` dentro del proyecto.
El código de la página web está ubicado en la carpeta `src/main/webapp`. 

Para entender el código fuente del ejemplo, es necesario revisar los siguientes archivos:

```

│   README.md
│
└───src/main/java
    ├───co.edu.uniandes.rest.restaurantes.resources
    │   │   RestConfig.java
    │   │   SucursalResource.java
    │
    ├───co.edu.uniandes.rest.restaurantes.dtos
    │   │   SucursalDTO.java
    │
    ├───co.edu.uniandes.rest.restaurantes.mocks
    │   │   SucursalLogicMock.java 
    │
    ├───co.edu.uniandes.rest.restaurantes.exceptions
    │   │   SucursalLogicException.java
    │
    └───co.edu.uniandes.rest.restaurantes.converters
        │   SucursalLogicExceptionMapper.java        
```

| Clase | Descripción |
| ----- | ----------- |
| `RestConfig.java` | Indica que la aplicación expone recursos REST. Solo se requiere uno en la aplicación. Especifica la ruta `/api` como prefijo para los recursos REST |
| `SucursalResource.java` | Define el recurso con la ruta `/api/sucursales`. Contiene métodos para procesar las peticiones GET api/sucursales y POST de acuerdo con el API definido |
| `SucursalDTO.java` | Define los datos que se transfiere entre el cliente y el servidor. Como se usa como tipo de retorno en los métodos de `SucursalResource`, JAX-RS convierte automáticamente de JSON a esta clase y de esta clase a JSON.  |
| `SucursalLogicMock.java` | Simula un servicio de lógica de negocios. En este ejemplo, el Mock manipula los elementos en una lista de ciudades (Un lista de `SucursalDTO`). |
| `SucursalLogicException.java` | Excepción lanzada por SucursalLogicMock cuando se genera un error. |
| `SucursalLogicExceptionMapper.java` | Convertidor de la excepción `SucursalLogicException` a mensajes REST. |


## Documentación del API

### Entidad Sucursal

La comunicación entre el cliente y el servidor se realiza intercambiando objetos JSON que siguen el siguiente formato:

```javascript
{
    "idSucursal" : 1,     /* Tipo Long */
    "ubicacion" : 'Bogota'    /* Tipo String */
    "reservasSucursal" :    /* Tipo List<Reserva> */
    "mesas" :    /* Tipo List<Mesa> */
    "platosMenuEsp" :    /* Tipo List<Plato> */
}
```

Si se solicta la servidor una lista de ciudades, el servidor retorna un arreglo de esos objetos siguiendo el siguiente formato: 

 ```javascript
[ 
  {
    "idSucursal" : 1,     /* Tipo Long */
    "ubicacion" : 'Bogota'    /* Tipo String */
  }, {
    "idSucursal" : 2,     /* Tipo Long */
    "ubicacion" : 'Medellín'    /* Tipo String */
  } /* ... otras sucursales */   
]
```

### Servicios REST

Al ejecutarlo en su propia máquina, el recurso REST estará disponible en:
*  `http://localhost:8080/SisTeam_Restaurantes/api/sucursales` 

La descripción del API REST se presenta a continuación:

Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/sucursales|Lista los registros de Sucursal (READ)|||Colección de registros de Sucursal 
**GET**|/sucursal/*:idSucursal*|Obtener los atributos de una instancia de Sucursal (READ)|**@PathParam idSucursal**: Identificador del registro||Atributos de la instancia de Sucursal
**POST**|/sucursales|Crear una nueva instancia de la entidad Sucursal (CREATE)||Atributos de la instancia de Sucursal a crear|Instancia de Sucursal creada, incluyendo su nuevo ID
**PUT**|/sucursales/*:idSucursal*|Actualiza una instancia de la entidad Sucursal (UPDATE)|**@PathParam idSucursal**: Identificador del registro|Objeto JSON de Sucursal|Instancia de Sucursal actualizada
**DELETE**|/sucursales/*:idSucursal*|Borra instancia de Sucursal en el servidor (DELETE)|**@PathParam idSucursal**: Identificador del registro||



## Ejecutando y probando el proyecto

El proyecto se ejecuta como un proyecto web tradicional. 
En Netbeans basta con ejecutar "Clean and Build" en el proyecto y luego usar la opción de "Run".

Es posible usar [Postman](http://www.getpostman.com/) para probar el servicio REST.

| Ejemplo | Comando |
| ------- | ------- |
| Obtener las sucursales | GET http://localhost:8080/Servidor-JAXRS/api/sucursales |
| Obtener una sucursal   | GET http://localhost:8080/Servidor-JAXRS/api/sucursales/{idSucursal} donde idSucursal es el `idSucursal` de la sucursal a obtener. Por ejemplo, para traer la sucursal con el idSucursal=1 es posible usar el URL http://localhost:8080/Servidor-JAXRS/api/sucursales/1 | 
| Agregar una sucursal   | POST http://localhost:8080/Servidor-JAXRS/api/sucursales  incluyendo en la petición una sucursal. Por ejemplo, es posible usar `{ "idSucursal": 4, "ubicacion": "Medellín" }` |
| Modificar una sucursal | PUT http://localhost:8080/Servidor-JAXRS/api/sucursal/{idSucursal} donde idSucursal es el `idSucursal` de la sucursal a modificar. Por ejemplo, para modificar la sucursal con el id=1 es necesario usar el URL http://localhost:8080/Servidor-JAXRS/api/sucursal/1 y enviar como parámetro los datos de la nueva sucursal, por ejemplo, `{ "idSucursal": 1, "ubicacion": "Bogotá" }` |  
| Borrar una sucursal    | DELETE http://localhost:8080/Servidor-JAXRS/api/sucursal/{idSucursal} donde idSucursal es el `idSucursal` de la sucursal a eliminar. Por ejemplo, para eliminar la sucursal con el id=2 es necesario usar el URL http://localhost:8080/Servidor-JAXRS/api/sucursales/2 | 

