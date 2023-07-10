## Installation

**Notes:**
- Test hechos con java 20
- If you do not have `maven` installed, you could use `mvnw` for `Linux` and `mvnw.cmd` for `Windows`
- Use `mvn test` to execute all tests

Execute `mvn spring-boot:run`



Consideraciones:

- He montado un servicio rest en el que se puede realizar las llamadas. Y obtenemos como respuesta un json con la respuesta. [http://localhost:8099/api/v1/products](http://localhost:8099/api/v1/products) (La respuesta está en formato json con un lista de ids ordenados por su secuencia y su valor)
 
- He añadido un servicio de swagger para documentar el servicio publicado ([swagger](http://localhost:8099/))

- En términos de persistencia de datos, utilicé JPA con mis propias entidades DAO en lugar de usarlo directamente en las entidades de dominio. Esto se hizo para mantener una separación clara entre el dominio y el framework, una práctica clave en el Diseño Guiado por el Dominio (DDD) puro.

- En el nivel de aplicación, utilicé el decorador @Service de Spring para facilitar la inyección de dependencias a pesar de implementar DDD de manera un poco más relajada. Podría haber optado por gestionar esta inyección de dependencias a través de un Bean separado.

- He añadido una verificación de salud (healthcheck) y modificado la ruta por defecto del actuador por razones de seguridad.

- En cuanto a las pruebas, se realizaron pruebas de extremo a extremo (ws) según se solicitó. Además, se crearon pruebas unitarias y de integración simplificadas. Nuestra aplicación está adecuadamente asegurada con las pruebas e2e definidas. A pesar de que usamos una base de datos H2, las pruebas se dirigen a una base de datos de prueba embebida independiente como una buena práctica.

- He usado @SpringBootTest en cada prueba definida, lo que implica que se inicia una instancia para cada prueba. Aunque Spring está optimizado para manejar esto eficientemente, en algunos casos puede ser útil tener un controlador de prueba por cada contexto, que se encargue de ejecutar todas las pruebas bajo ese contexto. Por ejemplo, podríamos tener un controlador de este tipo para cada capa: dominio, infraestructura, aplicación

- He utilizado el profile de test para ejecutar la carga de datos para cada test que se quiere realizar y no se carguen los datos por defectos.


Mejoras posibles:

- Dockerizar la aplicación.
- Utilizar el sistema de versiones Flyway.
- Gestión de logs
- Añadir seguridad a la llamadas de los servicios, ya sea con la gestión de una autenticación, 
- Envío de métricas con prometheus


## Respondiendo a la preguntas

### Estructuras de datos utilizadas en el algoritmo
Una vez realizado el problema del algoritmo de visibilidad, comenta qué estructuras de datos (Listas,
Sets, etc) has seleccionado para resolverlo y porque las has considerado como las más adecuadas en
cada caso.

#### Respuesta
```
    En este caso lo he gestionado con list para gestionar el orden, ya que con el Set es más eficiente, no podemos ordenarlos.
    También se podría gestionar con Map, pero tenemos el mismo problema, necesitariamos una implementación que permitiria obtener el orden. como LinkedHashMap  
```


### Complejidad temporal del algoritmo 
Una vez resuelto el algoritmo de visibilidad. 
- ¿Qué complejidad temporal expresada en notación “O”
crees que tiene? 
```
    Cargar los datos de los CSVs en nuestras estructuras de datos: O(n).
    Iterar sobre cada producto y verificar las condiciones de visibilidad: O(n).
    Ordenar los productos visibles por su secuencia: O(n log n).
    Por lo tanto, la complejidad temporal total sería O(n + n + n log n) = O(n log n).  
``` 
- ¿Consideras que se podría mejorar de alguna manera?
```
    Podemos reducir el tiempo de ordenación almacenando los productos en una estructura de datos que mantenga el orden (como un TreeMap)
    La eliminación anticipada de los elementos antes de añadirlos a la lista. 
    Aunque con el ejemplo que he dado sería gestionarlo directamente desde la base de datos, añadiendo la logica a la queries.
    Se podría utilizar alguna caché para mantener el resultado de la búsqueda, en la primera ejecución no se notará el cambio, pero si en la siguientes. 
```