# Operation Quasar Project
Este proyecto fue realizado con las siguientes herramientas:
 - Java 11
 - Gradle
 - Spock (Groovy)
 - SpringBoot
 - SpringSecurity - JWT
 - Gradle
 - IntelliJ (IDE)
 - Postman 
 - Swagger

# Guia de levantamiento
Lo importa como un Gradle Project en su IDE, luego busca la clase Main QuasarFireOperationApplication.java, lo ejecuta como un Java Application y listo.

Se adjuntara Collection y variables de entorno asociado a la URL y token, en el repositorio para hacer las pruebas correspondientes (Apuntando al ms alojado en AWS).

Para probar los distintos endpoints, primero se debe consultar el /authenticate para obtener un token de acceso y asi poder consultar los otros endpoints

# Nota

El token tiene un tiempo de validez de 1.5 min.

El puerto por defecto en este proyeco es el 8080, en caso de tenerlo ocupado puede cambiarlo en el application.yml
