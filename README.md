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
| ID    | Descripción                                                                                                 |
|-------|-------------------------------------------------------------------------------------------------------------|
| PC-01 | La aplicacion we se encuentra disponible y accesible mediante un navegador(https://automationexercise.com/) |

### Test Case 1
|ID        |Descripcion      |Modulo|Prioridad|Precondiciones|Datos de prueba|Resultado esperado|
|----------|-----------------|------|---------|--------------|---------------|------------------|
|TC-REG-01 |Registrar usuario|Registro |Alta     |Precondicion PC-01 cumplida|email valido y no registrado en el sistema|La cuenta se crea de manera exitosa y el usuario queda autenticado|

|Pasos|Acción                    |Resultado esperado|
|-----|--------------------------|----------------------|
|1    |Hacer clic en el botón 'Signup / login'| Se muestra la sección de 'New user Signup!'|
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

| Pasos |Acción                    | Resultado esperado                                      |
|-------|--------------------------|---------------------------------------------------------|
| 1     |Hacer clic en el botón 'Signup / login'| Se muestra la sección de 'New user Signup!'|
| 2     |Ingresar nombre y email validos de una cuenta registrada previamente en los campos correspondiente de la sección| El sistema acepta los dato |
| 3     |Hacer clic en el botón Signup| No se muestra la pantalla 'Enter account information'. Se muestra un mensaje indicando que el email ya se encuentra registrado en el sistema |

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

|ID       | Descripción                                   |Modulo|Prioridad|Precondiciones|Datos de prueba|Resultado esperado|
|---------|-----------------------------------------------|------|---------|--------------|---------------|------------------|
|TC-REG-11| Realizar suscripcion desde la vista principal |Home -  Suscripción|Media     |Precondición PC-01 cumplida|Email valido |Se deben realizar la suscripción de novedades de la tienda|

|Pasos|Acción|Resultado esperado|
|-----|------|------------------|
|1    |Hacer scroll hasta el pie de pagina|Se muestra la sección se suscripción|
|2    |Ingresar email en el campo 'Your mail address'|El sistema acepta los datos|
|3    |Hacer clic en el botón arrow que se encuentra a lado del campo 'Your mail address'|Se muestra el mensaje 'You have been successfully subscribed!' |


### Testcase 12

|ID       | Descripción                                      |Modulo|Prioridad|Precondiciones|Datos de prueba|Resultado esperado|
|---------|--------------------------------------------------|------|---------|--------------|---------------|------------------|
|TC-REG-12| Realizar suscripcion desde el carrito de compras |Cart -  Suscripción|Media     |Precondición PC-01 cumplida|Email valido |Se deben realizar la suscripción de novedades de la tienda|

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

|ID       | Descripción                                             | Modulo         | Prioridad |Precondiciones| Datos de prueba                          | Resultado esperado                  |
|---------|---------------------------------------------------------|----------------|-----------|--------------|------------------------------------------|-------------------------------------|
|TC-REG-15| Hacer una order, registrarse durante el proceso de pago | Cart - payment | Alta      |Precondición PC-01 cumplida| Email valido no registrado en el sistema | Se debe realizar el proceso de pago |

| Pasos | Acción                                                                                  | Resultado esperado                                                                                                                                                             |
|-------|-----------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1     | Hacer clic en el botón 'Add to cart' de cualquier producto                              | Se mostra un pop up indicando que el producto fue agregado                                                                                                                     |
| 2     | Hacer clic en el botón 'Continue shopping'                                              | Se cerrara el pop up                                                                                                                                                           |
| 3     | Hacer clic en el botón 'Cart'                                                           | Se mostrara la pagina del carrito de compras con los productos agregados, con el total a pagar                                                                                 |
| 4     | Hacer clic en el botón 'Proceed To Checkout'                                            | Se mostrara un pop up indicando que se debe iniciar sesion para poder continuar con la compra                                                                                  |
| 5     | Hacer clic en el botón 'Register / Login'                                               | Se muestra la sección de 'New user Signup!'                                                                                                                                    |
| 6     | Ingresar nombre y email validos en los campos correspondiente de la sección             | El sistema acepta los datos                                                                                                                                                    |
| 7     | Hacer clic en el botón Signup                                                           | Se muestra la pantalla 'Enter account information'                                                                                                                             |
| 8     | Completar la información obligatoria de la cuenta                                       | Los campos se validan correctamente                                                                                                                                            |
| 9     | Completar la información de dirección obligatoria                                       | Información aceptada                                                                                                                                                           |
| 10    | Precionar el botón Create Account                                                       | Se muestra una pantalla con el mensaje 'Account Created!'                                                                                                                      |
| 11    | Hacer clic en el botón continuar                                                        | El usuario queda autenticado. Se muestra entre las opciones del header el usuario con el que se entra autenticado                                                              |
| 12    | Hacer clic en el botón 'Cart'                                                           | Se mostrara la pagina del carrito de compras con los productos agregados, con el total a pagar                                                                                 |
| 13    | Hacer clic en el botón 'Proceed To Checkout'                                            | Se mostrara la sección address detail con la dirección de envio y facturación. tambien se mostrara la sección review order, con el resumen de los articulos seleccionados      |
| 14    | Ingresar una descripción en el area de texto, para agregar un comentario sobr la orden  | El sistema acepta los datos                                                                                                                                                    |
| 15    | Hacer clic en el botón 'Place order'                                                    | Se mostrara se sección payment                                                                                                                                                 |
| 16    | Ingresar los datos de la forma de pago(name on card, card number, cvc, expiration date) | El sistema acepta los datos                                                                                                                                                    |
| 17    | Hacer clic en el botón 'pay an confirm'                                                 | Se muestra el mensaje 'Your order has been placed successfully!' y se redirige automaticamente a una pantalla con el mensaje 'Congratulations! Your order has been confirmed!' |


### Testcase 16

| ID        | Descripción                         | Modulo         | Prioridad |Precondiciones| Datos de prueba                 | Resultado esperado                  |
|-----------|-------------------------------------|----------------|-----------|--------------|---------------------------------|-------------------------------------|
| TC-REG-16 | Hacer una order con usuario logeado | Cart - payment | Alta      |Precondición PC-01 cumplida| Cuenta registrada en el sistema | Se debe realizar el proceso de pago |

| Pasos | Acción                                                                                  | Resultado esperado                                                                                                                                                             |
|-------|-----------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1     |Hacer clic en el botón 'Signup / login' | Se muestra la sección 'Login to your account' |
| 2     |Ingresar el email y contraseña de una cuenta ya registrada en los campos correspondientes de la sección|El sistema acepta  los datos|
| 3     |Hacer clic en el botón 'login' | El usuario queda autenticado. Se muestra entre las opciones del header el usuario con el que se entra autenticado|
| 4     | Hacer clic en el botón 'Add to cart' de cualquier producto                              | Se mostra un pop up indicando que el producto fue agregado                                                                                                                     |
| 5     | Hacer clic en el botón 'Continue shopping'                                              | Se cerrara el pop up                                                                                                                                                           |
| 6     | Hacer clic en el botón 'Cart'                                                           | Se mostrara la pagina del carrito de compras con los productos agregados, con el total a pagar                                                                                 |
| 7     | Hacer clic en el botón 'Proceed To Checkout'                                            | Se mostrara la sección address detail con la dirección de envio y facturación. tambien se mostrara la sección review order, con el resumen de los articulos seleccionados      |
| 8     | Ingresar una descripción en el area de texto, para agregar un comentario sobre la orden | El sistema acepta los datos                                                                                                                                                    |
| 9     | Hacer clic en el botón 'Place order'                                                    | Se mostrara se sección payment                                                                                                                                                 |
| 10    | Ingresar los datos de la forma de pago(name on card, card number, cvc, expiration date) | El sistema acepta los datos                                                                                                                                                    |
| 11    | Hacer clic en el botón 'pay an confirm'                                                 | Se muestra el mensaje 'Your order has been placed successfully!' y se redirige automaticamente a una pantalla con el mensaje 'Congratulations! Your order has been confirmed!' |


### Testcase 17

| ID        | Descripción                   | Modulo         | Prioridad |Precondiciones| Datos de prueba                 | Resultado esperado                  |
|-----------|-------------------------------|----------------|-----------|--------------|---------------------------------|-------------------------------------|
| TC-REG-17 | Remover productos del carrito | Cart - payment | Alta      |Precondición PC-01 cumplida| | El producto no se mostrara en el carrito de compras |

| Pasos | Acción                                                     | Resultado esperado                                                                                                                         |
|-------|------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------|
| 1     |Poner el cursor sobre el primer producto|Se mostrara un recuadro naranja sobre el producto con el botón 'Add to cart'|
| 2     |Hacer clic en el botón 'Add to cart'|Se mostra un pop up indicando que el producto fue agrega|
| 3     |Hacer clic en el botón 'Continue shopping'|Se cerrara el pop up|
| 4     |Poner el cursor sobre el segundo producto|Se mostrara un recuadro naranja sobre el producto con el botón 'Add to cart'|
| 5     |Hacer clic en el botón 'Add to cart'|Se mostra un pop up indicando que el producto fue agrega|
| 6     |Hacer clic en el botón 'view Cart'|Se mostrara la pagina del carrito de compras con los productos agregados, con el total a pagar|
| 7     | Hacer clic en el botón 'X' de un producto                  | Ya no se mostrara el producto en el carrito de compras, el monto a pagar sera el correspondiente con lo productos que siguen en el carrito |


### Testcase 18

| ID        | Descripción                        | Modulo   | Prioridad |Precondiciones| Datos de prueba                 | Resultado esperado                                               |
|-----------|------------------------------------|----------|-----------|--------------|---------------------------------|------------------------------------------------------------------|
| TC-REG-18 | Visualizar categorias de productos | Products | Alta      |Precondición PC-01 cumplida| | Se mostraran unicamente los productos correspondiente con la categoria seleccionada |

| Pasos | Acción                                                             | Resultado esperado                                                                                                                                                                              |
|-------|--------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1     | Hacer clic en la categoria 'Women'                                 | Se mostrara una lista con los nombres tipos de productos que tiene la categoria women                                                                                                           |
| 2     | Hacer clic en el uno de los productos que tiene la categoria women | Se mostra la sección 'Women - nameProduct Products' (nameProduct, siendo el nombre de la marca seleccionada), mostrando unicamente los productos correspondientes con la categoria seleccionada |
| 2     | Hacer clic en la categoria 'Men'                                   | Se mostrara una lista con los nombres tipos de productos que tiene la categoria men                                                                                                             |
| 3     | Hacer clic en el uno de los productos que tiene la categoria men   | Se mostra la sección 'Men - nameProduct Products' (nameProduct, siendo el nombre de la marca seleccionada), mostrando unicamente los productos correspondientes con la categoria seleccionada   |


### Testcase 19

| ID        | Descripción                    | Modulo   | Prioridad |Precondiciones| Datos de prueba                 | Resultado esperado                                           |
|-----------|--------------------------------|----------|-----------|--------------|---------------------------------|--------------------------------------------------------------|
| TC-REG-19 | Visualizar marcas de productos | Products | Media     |Precondición PC-01 cumplida| | Se mostraran unicamente los productos correspondiente con la marca seleccionada |

| Pasos | Acción                           | Resultado esperado                                                                                                                                                                          |
|-------|----------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1     | Hacer clic en una marca deseada  | Se mostra la sección 'Brand - nameBrand Products' (nameBrand, siendo el nombre de la marca seleccionada), mostrando unicamente los productos correspondientes con la marca seleccionada     |
| 2     | Hacer clic en otra marca deseada | Se mostra la sección 'Brand - nameBrand Products' (nameBrand, siendo el nombre de la marca seleccionada), mostrando unicamente los productos correspondientes con la categoria seleccionada |

### Testcase 20

| ID        | Descripción                                           | Modulo          | Prioridad |Precondiciones| Datos de prueba                 | Resultado esperado                                                                                                        |
|-----------|-------------------------------------------------------|-----------------|-----------|--------------|---------------------------------|---------------------------------------------------------------------------------------------------------------------------|
| TC-REG-20 | Realizar una busqueda y agregar al carrito de compras | Products - Cart | Alta      |Precondición PC-01 cumplida| | Se mostraran unicamente los productos correspondiente con la busqueda realizada y se podran agregar al carrito de compras |

| Pasos | Acción                                                    | Resultado esperado                                                                                                         |
|-------|-----------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------|
| 1     | Hacer clic en el boton 'Products'                         | Se muestra la sección 'All Products' y la lista de productos sera visible                                                  |
| 2     | Ingresar el nombre de un producto en el campo de busqueda | El sistema acepta los datos                                                                                                |
| 3     | Hacer clic en el botón de buscar (icono de lupa)          | Se mostra la sección 'Searched Products', mostrando unicamente los productos correspondientes con la busqueda seleccionada |
| 4     | Poner el cursor sobre el primer producto                  | Se mostrara un recuadro naranja sobre el producto con el botón 'Add to cart'                                               |
| 5     | Hacer clic en el botón 'Add to cart'                      | Se mostra un pop up indicando que el producto fue agrega                                                                   |
| 6     | Hacer clic en el botón 'view Cart'                        | Se mostrara la pagina del carrito de compras con los productos agregados, con el total a pagar                             |


### Testcase 21

| ID        | Descripción                         | Modulo          | Prioridad |Precondiciones| Datos de prueba                 | Resultado esperado                          |
|-----------|-------------------------------------|-----------------|-----------|--------------|---------------------------------|---------------------------------------------|
| TC-REG-21 | Agregar una opinion de un producto  | Products | Media      |Precondición PC-01 cumplida| email valido | Se agregara una reseaña de un producto de forma exitosa |

| Pasos | Acción                                                           | Resultado esperado                                                                      |
|-------|------------------------------------------------------------------|-----------------------------------------------------------------------------------------|
| 1     | Hacer clic en el boton 'Products'                                | Se muestra la sección 'All Products' y la lista de productos sera visible               |
| 2     | Hacer clic en el botón 'View Product'                            | Se redirigira a la vista con los detalles del producto y la sección 'Write your review' |
| 3     | Ingresar el nombre del usuario en el campo your name             | El sistema acepta los datos                                                             |
| 4     | Ingresar el email del usuario en el campo email address          | El sistema acepta los datos                                                             |
| 5     | Ingresar una opinion del producto en el campo 'Add Review here!' | El sistema acepta los datos                                                             |
| 6     | Hacer clic en el botón 'submit'                                  | Se mustra el mensaje 'Thank you for your review.'                                       |


### Testcase 22

| ID        | Descripción                                                              | Modulo          | Prioridad |Precondiciones| Datos de prueba                 | Resultado esperado                            |
|-----------|--------------------------------------------------------------------------|-----------------|-----|--------------|---------------------------------|-----------------------------------------------|
| TC-REG-22 | Agregar una producto al carrito de compras de los productos recomendados | Products | Media |Precondición PC-01 cumplida| | Se agregara el producto al carrito de compras |

| Pasos | Acción                                                          | Resultado esperado                                                                            |
|-------|-----------------------------------------------------------------|------------------------------------------------------------------------------------------------|
| 1     | Hacer scroll hacia abajo, hasta el final de la pagina           | Se muestra la sección 'RECOMMENDED ITEMS' con algunos productos                        n       |
| 2     | Hacer clic en el botón 'View Product'                           | Se redirigira a la vista con los detalles del producto y la sección 'Write your review'        |
| 3     | Hacer clic en el botón 'Add to cart' de un producto recomendado | Se mostra un pop up indicando que el producto fue agrega                                       |
| 4     | Hacer clic en el botón 'view Cart'                              | Se mostrara la pagina del carrito de compras con los productos agregados, con el total a pagar |


### Testcase 23

| ID        | Descripción                                      | Modulo         | Prioridad |Precondiciones| Datos de prueba                 | Resultado esperado                                                                                          |
|-----------|--------------------------------------------------|----------------|-----------|--------------|---------------------------------|-------------------------------------------------------------------------------------------------------------|
| TC-REG-23 | Descargar factura despues de realizar una compra | Cart - payment | Alta      |Precondición PC-01 cumplida| Cuenta registrada en el sistema | Se dede descargar la factura en el equipo del usuario, la factura debe contener la información de la compra |

| Pasos | Acción                                                                                                  | Resultado esperado                                                                                                                                                             |
|-------|---------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1     | Hacer clic en el botón 'Signup / login'                                                                 | Se muestra la sección 'Login to your account'                                                                                                                                  |
| 2     | Ingresar el email y contraseña de una cuenta ya registrada en los campos correspondientes de la sección | El sistema acepta  los datos                                                                                                                                                   |
| 3     | Hacer clic en el botón 'login'                                                                          | El usuario queda autenticado. Se muestra entre las opciones del header el usuario con el que se entra autenticado                                                              |
| 4     | Hacer clic en el botón 'Add to cart' de cualquier producto                                              | Se mostra un pop up indicando que el producto fue agregado                                                                                                                     |
| 5     | Hacer clic en el botón 'Continue shopping'                                                              | Se cerrara el pop up                                                                                                                                                           |
| 6     | Hacer clic en el botón 'Cart'                                                                           | Se mostrara la pagina del carrito de compras con los productos agregados, con el total a pagar                                                                                 |
| 7     | Hacer clic en el botón 'Proceed To Checkout'                                                            | Se mostrara la sección address detail con la dirección de envio y facturación. tambien se mostrara la sección review order, con el resumen de los articulos seleccionados      |
| 8     | Ingresar una descripción en el area de texto, para agregar un comentario sobre la orden                 | El sistema acepta los datos                                                                                                                                                    |
| 9     | Hacer clic en el botón 'Place order'                                                                    | Se mostrara se sección payment                                                                                                                                                 |
| 10    | Ingresar los datos de la forma de pago(name on card, card number, cvc, expiration date)                 | El sistema acepta los datos                                                                                                                                                    |
| 11    | Hacer clic en el botón 'pay an confirm'                                                                 | Se muestra el mensaje 'Your order has been placed successfully!' y se redirige automaticamente a una pantalla con el mensaje 'Congratulations! Your order has been confirmed!' |
| 12    | Hacer clic en el botón 'Download Invoice'                                                               | Se descargara un archivo .txt que contendra el nombre del usuario y el monto a pagar                                                                                           |
| 13    | Hacer clic en el botón 'Continue'                                                                       | Se redireccionara automaticamente a la vista Home                                                                                                                              |


## 📂 Estructura del proyecto

## 📈 Roadmap (opcional)

## 🤝 Contribución

Las contribuciones son bienvenidas.  
Por favor abre un issue o un pull request.

## 👤 Autor

Alejandro Cervantes - Analista QA
