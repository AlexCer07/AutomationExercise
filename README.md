# AutomationExercise

Proyecto realizado como práctica de automatización de pruebas.

Este proyecto valida los flujos críticos de un e-commerce utilizando Selenium WebDriver + TestNG en Java, aplicando buenas prácticas de diseño de pruebas y automatización.

🔗 Sistema bajo prueba (SUT): https://automationexercise.com/


**Nota: pendiente de cambiar rutas para que funcione en distintos equipos**


> ⚠️ Este sitio es propiedad de un tercero y se utiliza únicamente con fines educativos, por lo que la responsabilidad del mantenimiento del sitio está fuera de alcance.

## 🎯 Objetivo del proyecto

Demostrar habilidades en:
* Automatización de pruebas funcionales end-to-end
* Diseño de frameworks de automatización escalables
* Implementación de buenas prácticas (POM, separación de capas)
* Validación de flujos críticos de negocio

## 🧠 Enfoque de automatización
Se implementa el patrón Page Object Model (POM) para:
* Separar la lógica de negocio y de la interfaz de usuario
* Facilitar el mantenimiento
* Reutilizar componentes

También se utilizan:
* TestNG para la organización de pruebas
* Data Providers para pruebas parametrizadas
* Aserciones para la validación de resultados

## 🚀 Características

* Automatización de flujos funcionales de un comercio electrónico

* Ejecución de pruebas mediante Selenium WebDriver

* Manejo de Page Object Model (POM)

* Uso de Data Providers para pruebas parametrizadas

* Validaciones de comportamiento del sistema mediante TestNG

* Reportes básicos de ejecución

## 🧱 Tecnologías

* Java 17
* Selenium
* TestNG
* Maven

## 📋 Requisitos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

* Java 17
* Maven
* Navegador Google Chrome
* ChromeDriver compatible con tu versión de Chrome
* Conexión a Internet

## ⚙️ Instalación

1. Clona el repositorio:

   ```bash
   git clone https://github.com/AlexCer07/AutomationExercise
   ```

2. Ingresa al proyecto:

   ```bash
   cd AutomationExercise
   ```

3. Instala las dependencias con Maven:

   ```bash
   mvn clean install
   ```

## ▶️ Ejecución de pruebas

El proyecto utiliza **TestNG** para organizar y ejecutar los casos de prueba. Es posible ejecutar una suite específica desde IntelliJ IDEA o mediante Maven, siempre que el proyecto tenga configurado el plugin **Maven Surefire** para admitir la propiedad `suiteXmlFiles`.

### Suites de pruebas

| Archivo | Propósito |
|---|---|
| `suit-login.xml` | Ejecutar las pruebas relacionadas con el registro, el inicio y el cierre de sesión. |
| `suit-product.xml` | Ejecutar las pruebas relacionadas con productos. |
| `suit-secundaryFunctionalities.xml` | Ejecutar las pruebas de funcionalidades secundarias. |

> **Nota:** Los nombres de los archivos deben coincidir exactamente con los existentes en el proyecto. Las funcionalidades incluidas en cada suite dependen de las clases y métodos declarados en sus archivos XML.

### Ejecutar una suite desde IntelliJ IDEA

1. Abre el proyecto en IntelliJ IDEA.
2. Localiza el archivo XML de la suite que deseas ejecutar.
3. Haz clic derecho sobre el archivo y selecciona **Run** (Ejecutar).
4. Consulta los resultados en la ventana **Run** de IntelliJ IDEA.

### Ejecutar una suite desde Maven

Desde una terminal situada en la raíz del proyecto, ejecuta el siguiente comando, reemplazando la ruta por la ubicación real del archivo XML:

```bash
mvn test -Dsurefire.suiteXmlFiles=ruta/a/suit-login.xml
```

Por ejemplo, **si las suites están almacenadas en la raíz del proyecto**, los comandos serían:

**Suite de login:**

```bash
mvn test -Dsurefire.suiteXmlFiles=suit-login.xml
```

**Suite de productos:**

```bash
mvn test -Dsurefire.suiteXmlFiles=suit-product.xml
```

**Suite de funcionalidades secundarias:**

```bash
mvn test -Dsurefire.suiteXmlFiles=suit-secundaryFunctionalities.xml
```

> **Importante:** Para que estos comandos funcionen, Maven Surefire debe estar configurado con soporte para TestNG y la propiedad `surefire.suiteXmlFiles`. La ubicación de las suites y la configuración de `pom.xml` deben verificarse en el proyecto.

### Ejecutar las pruebas con Maven

```bash
mvn test
```

Este comando ejecuta las pruebas que estén configuradas para la fase `test` de Maven. **No implica necesariamente ejecutar todas las suites XML**: el comportamiento depende de la configuración de `pom.xml`.

### Resultados de ejecución

Al finalizar, revisa los resultados mostrados en IntelliJ IDEA o en la terminal. Maven Surefire normalmente genera reportes en `target/surefire-reports/`. Los reportes adicionales y registros de ejecución dependen de la configuración del proyecto.

## 🌐 Configuración de navegadores

El proyecto utiliza **Selenium WebDriver** para ejecutar las pruebas en navegadores web. La inicialización y el cierre del navegador se gestionan desde `BaseTest`.

### Seleccionar el navegador

La configuración del navegador se define mediante la propiedad `browser` del archivo `GlobalData.properties`, ubicado en el paquete `Resources` de la estructura documentada.

Ejemplo de configuración para **Google Chrome**:

```properties
browser=chrome
```

Para utilizar **Mozilla Firefox**, cambia el valor:

```properties
browser=firefox
```

Guarda el archivo antes de ejecutar las pruebas. El valor debe coincidir con los admitidos por la implementación actual de `BaseTest`.

> **Importante:** La compatibilidad de versiones, la resolución de los controladores y la configuración de perfiles o extensiones dependen del código de `BaseTest` y del entorno local. Antes de ejecutar el proyecto en otro equipo, revisa las rutas específicas de máquina y los requisitos de cada navegador.

### Manejo de anuncios

El proyecto incluye `AdHandler` y `AdListener` para gestionar anuncios que pueden interferir con la interacción de Selenium. Este mecanismo ayuda a reducir interferencias, pero no garantiza que todos los anuncios puedan eliminarse ni sustituye las esperas y validaciones de los casos de prueba.

## 📝 Registro de logs

Se utiliza **Log4j2** para registrar información sobre la ejecución de las pruebas y facilitar el análisis de incidencias.

- **Configuración:** `log4j2.xml`, dentro de los recursos del proyecto.
- **Registro de eventos de prueba:** `TestListener`, que implementa eventos de TestNG.
- **Diagnóstico de fallos:** puede incluir el nombre de la prueba, el error y datos del navegador, según la implementación del listener.

Para consultar los registros, ejecuta una suite y revisa la consola y el destino configurado en los *appenders* de `log4j2.xml`.

> **Nota:** La ruta, el nombre y la rotación de los archivos de log deben verificarse en `log4j2.xml`. No se documenta aquí una ubicación fija porque no se ha confirmado la configuración efectiva.

## 📊 Reportes de ejecución

El proyecto incorpora **ExtentReports** para presentar los resultados de las pruebas. La clase `ExtendReporterNG` centraliza la configuración del reporte y `TestListener` registra eventos de TestNG.

### Consultar los resultados

1. Ejecuta una suite desde IntelliJ IDEA o Maven.
2. Revisa el resultado de las pruebas en la ventana de ejecución o en la terminal.
3. Consulta los reportes de Maven Surefire, si están habilitados, en `target/surefire-reports/`.
4. Abre el archivo HTML generado por ExtentReports en un navegador, utilizando la ruta configurada en `ExtendReporterNG`.

Los reportes permiten analizar las pruebas ejecutadas y sus resultados. Las capturas de pantalla asociadas a fallos dependen de que `TestListener` las genere y adjunte correctamente.

> **Nota:** Es necesario confirmar en el código la ruta exacta del reporte HTML, el registro de capturas y el momento en que se guarda el reporte. Estos detalles pueden variar según la configuración actual.

## ✅ Reglas de negocio

Algunas reglas consideradas en la automatización:

* El usuario debe estar registrado para iniciar sesión
* Los campos obligatorios no pueden estar vacíos
* El sistema debe mostrar mensajes de error ante credenciales inválidas
* Los productos agregados al carrito deben reflejarse correctamente

## 🧪 Casos de prueba

El proyecto documenta **24 casos de prueba funcionales**, organizados por escenario, módulo y prioridad. Las precondiciones, los datos de prueba, los pasos y los resultados esperados se encuentran en [la documentación completa de casos de prueba](docs/test-cases.md).

| ID | Caso de prueba | Módulo | Prioridad |
|---|---|---|---|
| TC-REG-01 | Registrar usuario | Registro | Alta |
| TC-REG-02 | Iniciar sesión | Login | Alta |
| TC-REG-03 | Cerrar sesión | Login | Alta |
| TC-REG-04 | Iniciar sesión con usuario no registrado en el sistema | Login | Alta |
| TC-REG-05 | Registrar usuario con correo electrónico registrado previamente | Registro | Alta |
| TC-REG-06 | Eliminar cuenta | Login | Alta |
| TC-REG-07 | Enviar un mensaje de contacto | Contact us | Media |
| TC-REG-08 | Navegar a los casos de prueba | Test cases | Baja |
| TC-REG-09 | Mostrar detalle del producto | Product detail | Alta |
| TC-REG-10 | Buscar producto | Product | Alta |
| TC-REG-11 | Realizar suscripción desde la vista principal | Home - Suscripción | Media |
| TC-REG-12 | Realizar suscripción desde el carrito de compras | Cart -  Suscripción | Media |
| TC-REG-13 | Agregar productos a carrito de compras | Products - Cart | Alta |
| TC-REG-14 | Agregar más de un mismo tipo de producto a carrito de compras | Detail product - Cart | Alta |
| TC-REG-15 | Realizar un pedido, registrarse durante el proceso de pago | Cart - payment | Alta |
| TC-REG-16 | Realizar un pedido con usuario autenticado | Cart - payment | Alta |
| TC-REG-17 | Remover productos del carrito | Cart - payment | Alta |
| TC-REG-18 | Visualizar categorías de productos | Products | Alta |
| TC-REG-19 | Visualizar marcas de productos | Products | Media |
| TC-REG-20 | Realizar una búsqueda y agregar al carrito de compras | Products - Cart | Alta |
| TC-REG-21 | Agregar una opinión de un producto | Products | Media |
| TC-REG-22 | Agregar un producto al carrito de compras de los productos recomendados | Products | Media |
| TC-REG-23 | Descargar factura después de realizar una compra | Cart - payment | Alta |
| TC-REG-24 | Desplazarse a través de la página usando las flechas direccionales | Home | Baja |

## 🏗️ Arquitectura del framework

El proyecto utiliza **Page Object Model (POM)** para separar los casos de prueba de las interacciones con la aplicación. También incorpora componentes reutilizables para sincronización, manejo de anuncios, lectura de datos, validaciones y registro de resultados.

### Organización por responsabilidades

| Capa o componente | Clases principales | Responsabilidad |
|---|---|---|
| Casos de prueba | `LogInModule`, `ProductsModule`, `SecondaryFunctionalities` | Definir los escenarios y verificar los resultados esperados mediante TestNG. |
| Page Objects | `HomePage`, `LoginPage`, `ProductPage`, `ProductDetailPage`, `CartPage`, `CheckOutPage`, `PaymentPage`, `ContactUsPage`, `TestCasePage` | Encapsular los elementos y las acciones de cada página. |
| Componentes compartidos | `AbstractElements`, `ProductActions` | Centralizar interacciones reutilizables y operaciones relacionadas con productos. |
| Preparación y configuración | `BaseTest`, `GlobalData.properties` | Configurar el navegador y preparar el contexto de ejecución de las pruebas. |
| Datos y validaciones | `DataReader`, `getData`, `SoftValidation`, archivos JSON | Proporcionar datos de entrada y apoyar las verificaciones de los escenarios. |
| Eventos y anuncios | `AdHandler`, `AdListener`, `TestListener` | Gestionar interferencias de anuncios y registrar eventos o resultados de ejecución. |
| Reportes y registros | `ExtendReporterNG`, `log4j2.xml` | Configurar los reportes y los registros de diagnóstico. |

### Flujo general de una prueba

```text
TestNG (suite XML)
    |
    v
Caso de prueba (módulo)
    |
    v
BaseTest: configuración e inicialización de WebDriver
    |
    v
Page Objects + acciones reutilizables
    |
    v
Aplicación web (Automation Exercise)
    |
    v
Aserciones y validaciones
    |
    v
Listeners, logs y reportes
```

Este esquema es **conceptual**: los listeners pueden actuar durante distintas etapas de la ejecución, no solamente al finalizar una prueba.

### Componentes destacados

**`BaseTest`** centraliza la preparación y finalización de las pruebas, incluida la selección del navegador mediante las propiedades de configuración. Esto evita repetir la inicialización del WebDriver en cada clase de pruebas.

**Page Objects** representan las pantallas de la aplicación. Los casos de prueba llaman a sus métodos en lugar de repetir localizadores y operaciones de Selenium, lo que facilita actualizar la automatización cuando cambia la interfaz.

**`AbstractElements`** reúne utilidades comunes, como esperas y acciones sobre elementos web. **`ProductActions`** concentra operaciones específicas de productos que pueden reutilizarse en distintos escenarios sin sobrecargar la clase de utilidades generales.

**`AdHandler` y `AdListener`** apoyan el manejo de anuncios que pueden interferir con las interacciones automatizadas. Su presencia reduce la necesidad de implementar esa lógica en cada caso de prueba, aunque no elimina por completo la posibilidad de fallos intermitentes.

**`TestListener`, `ExtendReporterNG` y Log4j2** permiten separar el registro y la presentación de resultados de la lógica funcional de las pruebas. Para conocer las rutas exactas de los archivos generados, consulta las secciones de logs y reportes.

### Principios de diseño

- **Separación de responsabilidades:** las pruebas describen escenarios; los Page Objects encapsulan las interacciones con la interfaz.
- **Reutilización:** las operaciones comunes se implementan en componentes compartidos.
- **Mantenibilidad:** los cambios en localizadores o interacciones se concentran en las clases correspondientes.
- **Trazabilidad:** los casos de prueba están documentados en [`docs/test-cases.md`](docs/test-cases.md) y los resultados pueden consultarse mediante los mecanismos de reporte configurados.

> **Alcance de esta documentación:** las responsabilidades se describen a partir de la estructura del proyecto y del diseño trabajado. La configuración exacta, las rutas de salida y las relaciones de herencia deben verificarse contra la versión actual del código fuente.

## 📂 Estructura del proyecto

```text

+---main
|   \---java
|       +---AbstractElements
|       |       AbstractElements.java
|       |       AdHandler.java
|       |       ProductActions.java
|       |       
|       +---Listeners
|       |       AdListener.java
|       |       
|       +---PagesObjects
|       |       CartPage.java
|       |       CheckOutPage.java
|       |       ContactUsPage.java
|       |       HomePage.java
|       |       LoginPage.java
|       |       PaymentPage.java
|       |       ProductDetailPage.java
|       |       ProductPage.java
|       |       TestCasePage.java
|       |       
|       \---Resources
|               ExtendReporterNG.java
|               GlobalData.properties
|               
\---test
+---java
|   +---data
|   |   |   DataReader.java
|   |   |   getData.java
|   |   |   
|   |   +---fieldsJson
|   |   |       loginData.json
|   |   |       productBlueTop.json
|   |   |       SleevesPrintedTop.json
|   |   |       
|   |   \---images
|   |           imgTest.png
|   |           
|   +---Listeners
|   |       TestListener.java
|   |       
|   +---resources
|   |   \---extensions
|   |           adblock-6.36.0_0.crx
|   |           
|   +---Test
|   |   |   LogInModule.java
|   |   |   ProductsModule.java
|   |   |   SecondaryFunctionalities.java
|   |   |   test.java
|   |   |   
|   |   \---softvalidation
|   |           SoftValidation.java
|   |           
|   \---TestComponents
|           BaseTest.java
|           
\---resources
log4j2.xml
```


## 📈 Roadmap (opcional)

_Pendiente de definir._

## 🤝 Contribución

Las contribuciones son bienvenidas.  
Por favor, abre un issue o un pull request.

## 👤 Autor

Alejandro Cervantes - Analista QA
