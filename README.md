# AutomationExercise

Proyecto realizado como practica para automatiación con el Framework Selenium y TestNG con Java. El sitio web a automatizar es un ecommerce diseñado para pruebas (https://automationexercise.com/), el sitio web es propiedad de un tercero.

## 🚀 Características

## 🧱 Tecnologías

Java 17
Selenium
TestNG
Maven

## 📋 Requisitos

Java 17
Maven
Conexión a Internet

## ⚙️ Instalación

## ▶️ Ejecución

## ✅ Reglas de negocio

## 🧪 Casos de  Pruebas
### Precondicion generales
|ID|Descripción          |Pasos|Resultado esperado|
|------|---------------------|-----|------------------|
|PC-01 |La aplicacion we se encuentra disponible y accesible mediante un navegador(https://automationexercise.com/)|

### Test Case 1
|ID        |Descripcion      |Modulo|Prioridad|Precondiciones|Datos de prueba|Resultado esperado|
|----------|-----------------|------|---------|--------------|---------------|------------------|
|TC-REG-01 |Registrar usuario|Registro |Alta     |Precondicion PC-01 cumplida|email valido y no registrado en el sistema|La cuenta se crea de manera exitosa y el usuario queda autenticado|

|Pasos|Acción                    |Resultado esperado|
|-----|--------------------------|----------------------|
|1    |Hacer clic en el botón 'Signup / login'| Se muestra la sección de 'New user Signup!'
|2    |Ingresar nombre y email validos en los campos correspondiente de la sección|El sistema acepta los dato|
|3    |Hacer clic en el botón Signup|Se muestra la pantalla 'Enter account information'|
|4    |Completar la información obligatoria de la cuenta |Los campos se validan correctamente|
|5    |Seleccionar la opción 'sign up for our newsletter!|Opción seleccionada|
|6    |Seleccionar la opción 'Receive special offers from our partners!'|Opción seleccionada|
|7    |Completar la información de dirección obligatoria |Información aceptada|
|8    |Precionar el botón Create Account |Se muestra una pantalla con el mensaje 'Account Created!'|
|9    |Hacer clic en el botón continuar | El usuario queda autenticado. Se muestra entre las opciones del header el usuario con el que se entra autenticado|

### Test Case 2
|ID       |Descripción   |Modulo|Prioridad|Precondiciones|Datos de prueba|Resultado esperado|
|---------|--------------|------|---------|--------------|---------------|------------------|
|TC-REG-02|Iniciar sesion|login |Alta     |Precondición PC-01 cumplida|Cuenta ya registrada |Se debe inicar sesión con la cuenta de forma exitosa y el usuario quedara autenticado|

|Pasos|Acción|Resultado esperado|
|-----|------|------------------|
|1    |Hacer clic en el botón 'Signup / login' | Se muestra la sección 'Login to your account' |
|2    |Ingresar el email y contraseña de una cuenta ya registrada en los campos correspondientes de la sección|El sistema acepta  los datos|
|3    |Hacer clic en el botón 'login' | El usuario queda autenticado. Se muestra entre las opciones del header el usuario con el que se entra autenticado|

### Test case 3
|ID       |Descripción   |Modulo|Prioridad|Precondiciones|Datos de prueba|Resultado esperado|
|---------|--------------|------|---------|--------------|---------------|------------------|
|TC-REG-03|Cerrar sesion |login |Alta     |Precondición PC-01 cumplida <br>caso de prueba TC-REG-02 ejecutado y exitoso| |Se debe cerrar sesión de forma exitosa y el usuario ya no estara autenticado|

|Pasos|Acción|Resultado esperado|
|-----|------|------------------|
|1    |Hacer clic en el botón 'Logout'|El usuario ya no estara autenticado. No se mostrara el nombre del usuario entre las opciones del header. Automaticamente se encontrara en el modulo de login|

### Test case 4

|ID       |Descripción   |Modulo|Prioridad|Precondiciones|Datos de prueba|Resultado esperado|
|---------|--------------|------|---------|--------------|---------------|------------------|
|TC-REG-04|Iniciar sesión con usuario no registrado previamente |login |Alta     |Precondición PC-01 cumplida|Cuenta no registrada en el sistema |No se debe iniciar sesión con el usuario ingresado.|

|Pasos|Acción|Resultado esperado|
|-----|------|------------------|
|1    |Hacer clic en el botón 'Signup / login' | Se muestra la sección 'Login to your account' |
|2    |Ingresar el email y contraseña de una cuenta que no se encuentre registrada, en los campos correspondientes de la sección|El sistema acepta  los datos|
|3    |Hacer clic en el botón 'login' | El usuario no sera autenticado. Se muestra un mensaje indicando que el usuario o contraseña con invalidos|


### Test case 5

|ID        |Descripcion      |Modulo|Prioridad|Precondiciones|Datos de prueba|Resultado esperado|
|----------|-----------------|------|---------|--------------|---------------|------------------|
|TC-REG-05 |Registrar usuario con email registrado previamente|Registro |Alta     |Precondicion PC-01 cumplida|email valido y registrado en el sistema|La cuenta no sera creada|

|Pasos|Acción                    |Resultado esperado|
|-----|--------------------------|----------------------|
|1    |Hacer clic en el botón 'Signup / login'| Se muestra la sección de 'New user Signup!'
|2    |Ingresar nombre y email validos de una cuenta registrada previamente en los campos correspondiente de la sección|El sistema acepta los dato|
|3    |Hacer clic en el botón Signup|No se muestra la pantalla 'Enter account information'.Se muestra un mensaje indicando que el email ya se encuentra registrado en el sistema|

### Test case 6
|ID       |Descripción     |Modulo|Prioridad|Precondiciones|Datos de prueba|Resultado esperado|
|---------|----------------|------|---------|--------------|---------------|------------------|
|TC-REG-06|Eliminar cuenta |login |Alta     |Precondición PC-01 cumplida <br>caso de prueba TC-REG-02 ejecutado y exitoso| |Se debe eliminar la cuenta|

|Pasos|Acción|Resultado esperado|
|-----|------|------------------|
|1    |Hacer clic en el botón 'Delete Account'|El usuario ya no estara autenticado. No se mostrara el nombre del usuario entre las opciones del header.|
|2    |Hacer clic en el botón 'Signup / login' | Se muestra la sección 'Login to your account' |
|3    |Ingresar el email y contraseña de la cuenta eliminada, en los campos correspondientes de la sección|El sistema acepta  los datos|
|4    |Hacer clic en el botón 'login' | El usuario no sera autenticado. Se muestra un mensaje indicando que el usuario o contraseña son invalidos|

### Test case 7
|ID       |Descripción     |Modulo|Prioridad|Precondiciones|Datos de prueba|Resultado esperado|
|---------|----------------|------|---------|--------------|---------------|------------------|
|TC-REG-07|Enviar un mensaje de contacto |Contact us |Media     |Precondición PC-01 cumplida|email valido |Se debe mandar una mensaje de notificación por correo a la empresa|

|Pasos|Acción|Resultado esperado|
|-----|------|------------------|
|1    |Hacer clic en el botón 'Contact Us'|Se muestra la sección 'Get it Touch'.|
|2    |Ingresar el nombre, email, asunto y mensaje en los campos correspondiente de la sección | El sistem acepta los datos |
|3    |Hacer clic en el botón 'Seleccionar archivo'|Se abrira el explorador de archivos del equipo|
|4    |Hacer clic en un archivo que se desee subir y hacer clic en aceptar| El sistema acepta el archivo |
|5    |Hacer clic en el botón 'submit'|Se mostrara una alerta para confirmar el envio del mensaje|
|6    |Hacer clic en el botón aceptar|El formulario dejeara de mostrarse, en su lugar se mostrar el mensaje 'Success! Your details have been submitted successfully.'|
|7    |Hacer clic en el botón Home|Se redirigira a la pagina home de forma exitosa|

### Testcase 8

|ID       |Descripción     |Modulo|Prioridad|Precondiciones|Datos de prueba|Resultado esperado|
|---------|----------------|------|---------|--------------|---------------|------------------|
|TC-REG-08|Navegar a los casos de prueba |Test cases|baja     |Precondición PC-01 cumplida| |Se debn mostrar los casos de prueba que tiene el sitio web|

|Pasos|Acción|Resultado esperado|
|-----|------|------------------|
|1    |Hacer clic en el botón 'Test cases'|Se muestra la sección 'Test cases' con los casos de prueba que tiene el sitio web|

### Testcase 9

|ID       |Descripción     |Modulo|Prioridad|Precondiciones|Datos de prueba|Resultado esperado|
|---------|----------------|------|---------|--------------|---------------|------------------|
|TC-REG-09|Mostrar detalle del producto |Product detail|Alta     |Precondición PC-01 cumplida| |Se deben mostrar todos los detalles del producto|

|Pasos|Acción|Resultado esperado|
|-----|------|------------------|
|1    |Hacer clic en el botón 'Products'|Se muestra la sección 'All Products' y la lista de productos sera visible|
|2    |Hacer clic en el botón 'View Produt' del primer producto|Se redirigira a la vista con los detalles del producto, mostrandose el product name, category, price, availability, condition, brand|

### Testcase 10

|ID       |Descripción     |Modulo|Prioridad|Precondiciones|Datos de prueba|Resultado esperado|
|---------|----------------|------|---------|--------------|---------------|------------------|
|TC-REG-10|Buscar producto |Product|Alta     |Precondición PC-01 cumplida|Nombre del producto existente |Se deben mostrar solo los procustos que cumplan con los criterios de la busqueda|

|Pasos|Acción|Resultado esperado|
|-----|------|------------------|
|1    |Hacer clic en el botón 'Products'|Se muestra la sección 'All Products' y la lista de productos sera visible|
|2    |Ingresar el nombre de un producto en el campo 'Search Product'|El sistema acepta los datos|
|3    |Hacer clic en el botón de busqueda | Solo se mostra el producto buscado|

### Testcase 11

|ID       |Descripción     |Modulo|Prioridad|Precondiciones|Datos de prueba|Resultado esperado|
|---------|----------------|------|---------|--------------|---------------|------------------|
|TC-REG-11|Realizar suscripcion |Home -  Suscripción|Media     |Precondición PC-01 cumplida|Email valido |Se deben realizar la suscripción de novedades de la tienda|

|Pasos|Acción|Resultado esperado|
|-----|------|------------------|
|1    |Hacer scroll hasta el pie de pagina|Se muestra la sección se suscripción|
|2    |Ingresar email en el campo 'Your mail address'|El sistema acepta los datos|
|3    |Hacer clic en el botón arrow que se encuentra a lado del campo 'Your mail address'|Se muestra el mensaje 'You have been successfully subscribed!' |


### Testcase 12

|ID       |Descripción     |Modulo|Prioridad|Precondiciones|Datos de prueba|Resultado esperado|
|---------|----------------|------|---------|--------------|---------------|------------------|
|TC-REG-12|Realizar suscripcion |Cart -  Suscripción|Media     |Precondición PC-01 cumplida|Email valido |Se deben realizar la suscripción de novedades de la tienda|

|Pasos|Acción|Resultado esperado|
|-----|------|------------------|
|1    |Hacer clic en el botón 'Cart'|Se muestra los productos que se han agregado al carrito de compras|
|2    |Hacer scroll hasta el pie de pagina|Se muestra la sección se suscripción|
|3    |Ingresar email en el campo 'Your mail address'|El sistema acepta los datos|
|4    |Hacer clic en el botón arrow que se encuentra a lado del campo 'Your mail address'|Se muestra el mensaje 'You have been successfully subscribed!' |


### Testcase 13

|ID       |Descripción     |Modulo|Prioridad|Precondiciones|Datos de prueba|Resultado esperado|
|---------|----------------|------|---------|--------------|---------------|------------------|
|TC-REG-13|Agregar produtos a carrito de compras |Products - Cart|Alta     |Precondición PC-01 cumplida|Email valido |Se deben agregar productos al carrito de compras|

|Pasos|Acción|Resultado esperado|
|-----|------|------------------|
|1    |Hacer clic en el botón products|Se muestra la sección 'All Products' y la lista de productos sera visible|
|2    |Poner el cursor sobre el primer producto|Se mostrara un recuadro naranja sobre el producto con el botón 'Add to cart'|
|3    |Hacer clic en el botón 'Add to cart'|Se mostra un pop up indicando que el producto fue agrega|
|4    |Hacer clic en el botón 'Continue shopping'|Se cerrara el pop up|
|5    |Poner el cursor sobre el segundo producto|Se mostrara un recuadro naranja sobre el producto con el botón 'Add to cart'|
|6    |Hacer clic en el botón 'Add to cart'|Se mostra un pop up indicando que el producto fue agrega|
|7    |Hacer clic en el botón 'view Cart'|Se mostrara la pagina del carrito de compras con los productos agregados, con el total a pagar|


### Testcase 14

|ID       |Descripción     |Modulo|Prioridad|Precondiciones|Datos de prueba|Resultado esperado|
|---------|----------------|------|---------|--------------|---------------|------------------|
|TC-REG-14|Agregar mas de un mismo tipo de produto a carrito de compras |Detail product -Cart|Alta     |Precondición PC-01 cumplida|Email valido |Se deben agregar productos al carrito de compras|

|Pasos|Acción|Resultado esperado|
|-----|------|------------------|
|1    |Hacer clic en el botón 'view product' de cualquier producto|Se redirigira a la vista con los detalles del producto, mostrandose el product name, category, price, availability, condition, brand |
|2    |Ingresar 4 en el campo de quatity|El sistema acepta los datos|
|3    |Hacer clic en el botón 'Add to cart'|Se mostra un pop up indicando que el producto fue agrega|
|4    |Hacer clic en el botón 'view Cart'|Se mostrara la pagina del carrito de compras con la cantidad de productos agregados y el total a pagar|


### Testcase 15

|ID       |Descripción     |Modulo|Prioridad|Precondiciones|Datos de prueba|Resultado esperado|
|---------|----------------|------|---------|--------------|---------------|------------------|
|TC-REG-15|Agregar mas de un mismo tipo de produto a carrito de compras |Detail product -Cart|Alta     |Precondición PC-01 cumplida|Email valido |Se deben agregar productos al carrito de compras|

|Pasos|Acción|Resultado esperado|
|-----|------|------------------|
|1    |Hacer clic en el botón 'Add to cart' de cualquier producto|Se mostra un pop up indicando que el producto fue agrega|
|2    |Hacer clic en el botón 'Continue shopping'||
|3    |Hacer clic en el botón 'Cart'||
|4    |Hacer clic en el botón 'Proceed To Checkout'||
|5    |Hacer clic en el botón 'Register / Login'||
|6    |Ingresar name y email en la |

## 📂 Estructura del proyecto

## 📈 Roadmap (opcional)

## 🤝 Contribución

Las contribuciones son bienvenidas.  
Por favor abre un issue o un pull request.

## 👤 Autor

Alejandro Cervantes - Analista QA
