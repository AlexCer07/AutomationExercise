# Documentación de casos de prueba — AutomationExercise

> **Criterio de documentación:** las precondiciones describen el estado inicial requerido, no la ejecución exitosa de otro caso. Los resultados esperados se limitan a comportamientos observables. Las condiciones añadidas deben contrastarse con la implementación automatizada.

## Precondiciones generales
| ID    | Descripción                                                                                                  |
|-------|--------------------------------------------------------------------------------------------------------------|
| PC-01 | La aplicación web se encuentra disponible y accesible mediante un navegador (https://automationexercise.com/) |
| PC-02 | Usuario dado de alta en el sistema                                                                           |
| PC-03 | Productos agregados previamente al carrito de compras                                                        |
| PC-04 | Usuario autenticado en el sistema                                                                            |

## Test Case 1
| ID        | Descripción        | Módulo    | Prioridad | Precondiciones               | Datos de prueba                            | Resultado esperado                                                 |
|-----------|--------------------|-----------|-----------|------------------------------|--------------------------------------------|--------------------------------------------------------------------|
| TC-REG-01 | Registrar usuario  | Registro  | Alta      | Precondición PC-01 cumplida  | correo electrónico válido y no registrado en el sistema | La cuenta se crea correctamente y el usuario queda autenticado |

| Pasos | Acción                                                                                      | Resultado esperado                                                                                                |
|-------|---------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------|
| 1     | Hacer clic en el botón 'Signup / Login'                                                     | Se muestra la sección de 'New user Signup!'                                                                       |
| 2     | Ingresar nombre y correo electrónico válidos en los campos correspondientes de la sección «New User Signup» | El sistema acepta los datos                                                                                        |
| 3     | Hacer clic en el botón Signup                                                               | Se muestra la pantalla 'Enter account information'                                                                |
| 4     | Completar la información obligatoria de la cuenta en el formulario                          | Los campos obligatorios se completan correctamente                                                                               |
| 5     | Seleccionar la opción 'sign up for our newsletter!                                          | Opción seleccionada                                                                                               |
| 6     | Seleccionar la opción 'Receive special offers from our partners!'                           | Opción seleccionada                                                                                               |
| 7     | Presionar el botón Create Account                                                           | Se muestra una pantalla con el mensaje 'Account Created!'                                                         |
| 8     | Hacer clic en el botón «Continue»                                                            | El usuario queda autenticado. El nombre del usuario autenticado aparece en el encabezado |

## Test Case 2
| ID        | Descripción    | Módulo | Prioridad | Precondiciones                      | Datos de prueba                            | Resultado esperado                                                                    |
|-----------|----------------|--------|-----------|-------------------------------------|--------------------------------------------|---------------------------------------------------------------------------------------|
| TC-REG-02 | Iniciar sesión | Login  | Alta      | Precondición PC-01 y PC-02 cumplida | Correo electrónico y contraseña de la cuenta registrada | Se debe iniciar sesión con la cuenta correctamente y el usuario quedará autenticado |

| Pasos | Acción                                                                                                                        | Resultado esperado                                                                                                |
|-------|-------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------|
| 1     | Hacer clic en el botón 'Signup / Login'                                                                                       | Se muestra la sección 'Login to your account'                                                                     |
| 2     | Ingresar el correo electrónico y contraseña de una cuenta ya registrada en los campos correspondientess de la sección «Login to your account» | El sistema acepta los datoss                                                                                       |
| 3     | Hacer clic en el botón 'Login'                                                                                                | El usuario queda autenticado. El nombre del usuario autenticado aparece en el encabezado |

## Test Case 3
| ID        | Descripción   | Módulo | Prioridad | Precondiciones                               | Datos de prueba | Resultado esperado                                                           |
|-----------|---------------|--------|-----------|----------------------------------------------|-----------------|------------------------------------------------------------------------------|
| TC-REG-03 | Cerrar sesión | Login  | Alta      | PC-01 y PC-04 (sesión iniciada) |                 | La sesión se cierra; desaparece el nombre del usuario y se muestra la vista de inicio de sesión. |

| Pasos | Acción                          | Resultado esperado                                                                                                                                                   |
|-------|---------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1     | Hacer clic en el botón 'Logout' | El usuario ya no estará autenticado. <br>No se muestra el nombre del usuario en el encabezado. <br>Automáticamente se redirecciona al módulo de Login |

## Test Case 4

| ID        | Descripción                                            | Módulo | Prioridad | Precondiciones              | Datos de prueba                   | Resultado esperado                                  |
|-----------|--------------------------------------------------------|--------|-----------|-----------------------------|-----------------------------------|-----------------------------------------------------|
| TC-REG-04 | Iniciar sesión con usuario no registrado en el sistema | Login  | Alta      | Precondición PC-01 cumplida | correo electrónico no registrado en el sistema | No se debe iniciar sesión con el usuario ingresado. |

| Pasos | Acción                                                                                                                    | Resultado esperado                                                                                            |
|-------|---------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------|
| 1     | Hacer clic en el botón 'Signup / Login'                                                                                   | Se muestra la sección 'Login to your account'                                                                 |
| 2     | Ingresar el correo electrónico y contraseña de una cuenta que no se encuentre registrada, en los campos correspondientess de la sección | El sistema acepta  los datos                                                                                  |
| 3     | Hacer clic en el botón 'Login'                                                                                            | El usuario no será autenticado. <br>Se muestra un mensaje indicando que el usuario o contraseña con inválidos |


## Test Case 5

| ID        | Descripción                                        | Módulo   | Prioridad | Precondiciones                      | Datos de prueba                | Resultado esperado       |
|-----------|----------------------------------------------------|----------|-----------|-------------------------------------|--------------------------------|--------------------------|
| TC-REG-05 | Registrar usuario con correo electrónico registrado previamente | Registro | Alta      | Precondición PC-01 y PC-02 cumplida | correo electrónico registrado en el sistema | La cuenta no se creará |

| Pasos | Acción                                                                                                                   | Resultado esperado                                                                                                                               |
|-------|--------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------|
| 1     | Hacer clic en el botón 'Signup / Login'                                                                                  | Se muestra la sección de 'New user Signup!'                                                                                                      |
| 2     | Ingresar nombre y correo electrónico de una cuenta registrada previamente en los campos correspondientes de la sección «New User Signup» | El sistema acepta los datos                                                                                                                       |
| 3     | Hacer clic en el botón Signup                                                                                            | No se muestra la pantalla 'Enter account information'. <br>Se muestra un mensaje indicando que el correo electrónico ya se encuentra registrado en el sistema |

## Test Case 6
| ID        | Descripción     | Módulo | Prioridad | Precondiciones                                                               | Datos de prueba | Resultado esperado         |
|-----------|-----------------|--------|-----------|------------------------------------------------------------------------------|-----------------|----------------------------|
| TC-REG-06 | Eliminar cuenta | Login  | Alta      | PC-01 y PC-04; cuenta existente que puede eliminarse |                 | La cuenta se elimina y no permite iniciar sesión con sus credenciales anteriores. |

| Pasos | Acción                                                                                                                    | Resultado esperado                                                                                        |
|-------|---------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------|
| 1     | Hacer clic en el botón 'Delete Account'                                                                                   | El usuario ya no estará autenticado. No se muestra el nombre del usuario en el encabezado.   |
| 2     | Hacer clic en el botón 'Signup / Login'                                                                                   | Se muestra la sección 'Login to your account'                                                             |
| 3     | Ingresar el correo electrónico y contraseña de la cuenta eliminada, en los campos correspondientess de la sección «Login to your account» | El sistema acepta  los datos                                                                              |
| 4     | Hacer clic en el botón 'Login'                                                                                            | El usuario no será autenticado. Se muestra un mensaje indicando que el usuario o contraseña son inválidos |

## Test Case 7
| ID        | Descripción                    | Módulo     | Prioridad | Precondiciones              | Datos de prueba | Resultado esperado                                                 |
|-----------|--------------------------------|------------|-----------|-----------------------------|-----------------|--------------------------------------------------------------------|
| TC-REG-07 | Enviar un mensaje de contacto  | Contact us | Media     | PC-01; datos de contacto válidos y archivo disponible | correo electrónico válido    | Se muestra la confirmación de envío del formulario de contacto (sin afirmar entrega de correo). |

| Pasos | Acción                                                                                  | Resultado esperado                                                                                                                |
|-------|-----------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------|
| 1     | Hacer clic en el botón 'Contact Us'                                                     | Se muestra la sección 'Get it Touch'.                                                                                             |
| 2     | Ingresar el nombre, email, asunto y mensaje en los campos correspondientes de la sección | El sistema acepta los datoss                                                                                                       |
| 3     | Hacer clic en el botón 'Seleccionar archivo'                                            | Se abre el explorador de archivos del equipo                                                                                    |
| 4     | Hacer clic en un archivo que se desee subir y hacer clic en aceptar                     | El sistema acepta el archivo                                                                                                      |
| 5     | Hacer clic en el botón 'submit'                                                         | Se muestra una alerta para confirmar el envío del mensaje                                                                         |
| 6     | Hacer clic en el botón aceptar                                                          | El formulario deja de mostrarse, en su lugar se mostrar el mensaje 'Success! Your details have been submitted successfully.' |
| 7     | Hacer clic en el botón Home                                                             | Se redirige a la página home correctamente                                                                                   |

## Test Case 8

| ID        | Descripción                   | Módulo     | Prioridad | Precondiciones              | Datos de prueba | Resultado esperado                                           |
|-----------|-------------------------------|------------|-----------|-----------------------------|-----------------|--------------------------------------------------------------|
| TC-REG-08 | Navegar a los casos de prueba | Test cases | Baja      | Precondición PC-01 cumplida |                 | Se deben mostrar los casos de prueba que tiene el sitio web |

| Pasos | Acción                              | Resultado esperado                                                                |
|-------|-------------------------------------|-----------------------------------------------------------------------------------|
| 1     | Hacer clic en el botón 'Test cases' | Se muestra la sección 'Test cases' con los casos de prueba que tiene el sitio web |

## Test Case 9

| ID        | Descripción                   | Módulo         | Prioridad | Precondiciones              | Datos de prueba | Resultado esperado                                |
|-----------|-------------------------------|----------------|-----------|-----------------------------|-----------------|---------------------------------------------------|
| TC-REG-09 | Mostrar detalle del producto | Product detail | Alta      | Precondición PC-01 cumplida |                 | Se deben mostrar todos los detalles del producto |

| Pasos | Acción                                                   | Resultado esperado                                                                                                                   |
|-------|----------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------|
| 1     | Hacer clic en el botón 'Products'                        | Se muestra la sección 'All Products' y la lista de productos será visible                                                            |
| 2     | Hacer clic en el botón 'View Product' del primer producto | Se redirige a la vista con los detalles del producto, mostrándose el product name, category, price, availability, condition, brand |

## Test Case 10

| ID        | Descripción      | Módulo   | Prioridad | Precondiciones               | Datos de prueba               | Resultado esperado                                                                 |
|-----------|------------------|----------|-----------|------------------------------|-------------------------------|------------------------------------------------------------------------------------|
| TC-REG-10 | Buscar producto  | Product  | Alta      | Precondición PC-01 cumplida  | Nombre del producto existente | Se deben mostrar solo los productos que cumplan con los criterios de la búsqueda  |

| Pasos | Acción                                                         | Resultado esperado                                                        |
|-------|----------------------------------------------------------------|---------------------------------------------------------------------------|
| 1     | Hacer clic en el botón 'Products'                              | Se muestra la sección 'All Products' y la lista de productos será visible |
| 2     | Ingresar el nombre de un producto en el campo 'Search Product' | El campo permite introducir los datos proporcionados                                   |
| 3     | Hacer clic en el botón de búsqueda                             | Solo se muestra el producto buscado                                       |

## Test Case 11

| ID        | Descripción                                   | Módulo             | Prioridad | Precondiciones              | Datos de prueba | Resultado esperado                                         |
|-----------|-----------------------------------------------|--------------------|-----------|-----------------------------|-----------------|------------------------------------------------------------|
| TC-REG-11 | Realizar suscripción desde la vista principal | Home - Suscripción | Media     | Precondición PC-01 cumplida | Correo electrónico válido    | Se debe completar la suscripción a las novedades de la tienda |

| Pasos | Acción                                                                             | Resultado esperado                                             |
|-------|------------------------------------------------------------------------------------|----------------------------------------------------------------|
| 1     | Hacer scroll hasta el pie de página                                                | Se muestra la sección de suscripción                           |
| 2     | Ingresar un email en el campo 'Your mail address'                                  | El sistema acepta los datoss                                    |
| 3     | Hacer clic en el botón arrow que se encuentra al lado del campo 'Your mail address' | Se muestra el mensaje 'You have been successfully subscribed!' |


## Test Case 12

| ID        | Descripción                                      | Módulo              | Prioridad | Precondiciones              | Datos de prueba | Resultado esperado                                         |
|-----------|--------------------------------------------------|---------------------|-----------|-----------------------------|-----------------|------------------------------------------------------------|
| TC-REG-12 | Realizar suscripción desde el carrito de compras | Cart -  Suscripción | Media     | Precondición PC-01 cumplida | Correo electrónico válido    | Se debe completar la suscripción a las novedades de la tienda |

| Pasos | Acción                                                                             | Resultado esperado                                                 |
|-------|------------------------------------------------------------------------------------|--------------------------------------------------------------------|
| 1     | Hacer clic en el botón 'Cart'                                                      | Se muestran los productos que se han agregado al carrito de compras |
| 2     | Hacer scroll hasta el pie de página                                                | Se muestra la sección de suscripción                               |
| 3     | Ingresar un correo electrónico en el campo 'Your mail address'                                     | El campo permite introducir los datos proporcionados                            |
| 4     | Hacer clic en el botón arrow que se encuentra al lado del campo 'Your mail address' | Se muestra el mensaje 'You have been successfully subscribed!'     |


## Test Case 13

| ID        | Descripción                            | Módulo          | Prioridad | Precondiciones              | Datos de prueba | Resultado esperado                               |
|-----------|----------------------------------------|-----------------|-----------|-----------------------------|-----------------|--------------------------------------------------|
| TC-REG-13 | Agregar productos a carrito de compras | Products - Cart | Alta      | PC-01; carrito vacío al iniciar si se valida cantidad exacta |                 | Los dos productos seleccionados aparecen en el carrito con sus nombres, cantidades y precios correspondientes. |

| Pasos | Acción                                                | Resultado esperado                                                                               |
|-------|-------------------------------------------------------|--------------------------------------------------------------------------------------------------|
| 1     | Hacer clic en el botón products                       | Se muestra la sección 'All Products' y la lista de productos será visible                        |
| 2     | Poner el cursor sobre el primer producto              | Se muestra un recuadro naranja sobre el producto con el botón 'Add to cart'                      |
| 3     | Hacer clic en el botón 'Add to cart'                  | Se muestra una ventana emergente que indica que el producto se agregó                                        |
| 4     | Hacer clic en el botón 'Continue shopping' de la ventana emergente | Se cierra el ventana emergente                                                                              |
| 5     | Poner el cursor sobre el segundo producto             | Se muestra un recuadro naranja sobre el producto con el botón 'Add to cart'                      |
| 6     | Hacer clic en el botón 'Add to cart'                  | Se muestra una ventana emergente que indica que el producto se agregó                                        |
| 7     | Hacer clic en el botón 'view Cart' de la ventana emergente         | Se redirige a la página del carrito de compras con los productos agregados, con el total a pagar |


## Test Case 14

| ID        | Descripción                                                  | Módulo                | Prioridad | Precondiciones              | Datos de prueba | Resultado esperado                               |
|-----------|--------------------------------------------------------------|-----------------------|-----------|-----------------------------|-----------------|--------------------------------------------------|
| TC-REG-14 | Agregar más de un mismo tipo de producto a carrito de compras | Detail product - Cart | Alta      | PC-01; producto disponible y carrito vacío |                 | El carrito muestra el producto seleccionado con la cantidad configurada y el importe correspondiente. |

| Pasos | Acción                                                      | Resultado esperado                                                                                                                    |
|-------|-------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------|
| 1     | Hacer clic en el botón 'view product' de cualquier producto | Se redirige a la vista con los detalles del producto, mostrándose el product name, category, price, availability, condition, brand |
| 2     | Hacer clic en la flecha hacia arriba del campo quantity     | El campo permite introducir los datos proporcionados                                                                                               |
| 3     | Hacer clic en el botón 'Add to cart'                        | Se muestra una ventana emergente que indica que el producto se agregó                                                                             |
| 4     | Hacer clic en el botón 'view Cart' de la ventana emergente               | Se muestra la página del carrito de compras con la cantidad de productos agregados y el total a pagar                                 |


## Test Case 15

| ID        | Descripción                                             | Módulo         | Prioridad | Precondiciones              | Datos de prueba                          | Resultado esperado                  |
|-----------|---------------------------------------------------------|----------------|-----------|-----------------------------|------------------------------------------|-------------------------------------|
| TC-REG-15 | Realizar un pedido, registrarse durante el proceso de pago | Cart - payment | Alta      | PC-01; sesión no iniciada; correo no registrado | Correo electrónico válido no registrado en el sistema | El usuario se registra durante el flujo de compra y se muestra la confirmación del pedido. |

| Pasos | Acción                                                                                      | Resultado esperado                                                                                                                                                             |
|-------|---------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1     | Hacer clic en el botón 'Add to cart' de cualquier producto                                  | Se muestra una ventana emergente que indica que el producto se agregódo                                                                                                                    |
| 2     | Hacer clic en el botón 'Continue shopping' de la ventana emergente                                       | Se cierra el ventana emergente                                                                                                                                                           |
| 3     | Hacer clic en el botón 'Cart'                                                               | Se muestra la página del carrito de compras con los productos agregados, con el total a pagar                                                                                  |
| 4     | Hacer clic en el botón 'Proceed To Checkout'                                                | Se muestra una ventana emergente que indica que se debe iniciar sesión para poder continuar con la compra                                                                                   |
| 5     | Hacer clic en el botón 'Register / Login'                                                   | Se muestra la sección de 'New user Signup!'                                                                                                                                    |
| 6     | Ingresar nombre y correo electrónico válidos en los campos correspondientes de la sección «New User Signup» | El campo permite introducir los datos proporcionados                                                                                                                                        |
| 7     | Hacer clic en el botón Signup                                                               | Se muestra la pantalla 'Enter account information'                                                                                                                             |
| 8     | Completar la información obligatoria de la cuenta en el formulario                          | Información aceptada                                                                                                                                                           |
| 9     | Presionar el botón Create Account                                                           | Se muestra una pantalla con el mensaje 'Account Created!'                                                                                                                      |
| 10    | Hacer clic en el botón «Continue»                                                            | El usuario queda autenticado. El nombre del usuario autenticado aparece en el encabezado                                                              |
| 11    | Hacer clic en el botón 'Cart'                                                               | Se muestra la página del carrito de compras con los productos agregados, con el total a pagar                                                                                  |
| 12    | Hacer clic en el botón 'Proceed To Checkout'                                                | Se muestra la sección address detail con la dirección de envío y facturación. también se muestra la sección review order, con el resumen de los artículos seleccionados        |
| 13    | Ingresar una descripción en el área de texto, para agregar un comentario sobre la orden     | El campo permite introducir los datos proporcionados                                                                                                                                        |
| 14    | Hacer clic en el botón 'Place order'                                                        | Se muestra la sección payment                                                                                                                                                  |
| 15    | Ingresar los datos de la forma de pago(name on card, card number, cvc, expiration date)     | El campo permite introducir los datos proporcionados                                                                                                                                        |
| 16    | Hacer clic en el botón 'Pay and Confirm'                                                     | Se muestra el mensaje 'Your order has been placed successfully!' y se redirige automáticamente a una pantalla con el mensaje 'Congratulations! Your order has been confirmed!' |


## Test Case 16

| ID        | Descripción                         | Módulo         | Prioridad | Precondiciones                      | Datos de prueba                 | Resultado esperado                  |
|-----------|-------------------------------------|----------------|-----------|-------------------------------------|---------------------------------|-------------------------------------|
| TC-REG-16 | Realizar un pedido con usuario autenticado | Cart - payment | Alta      | PC-01 y PC-02; sesión no iniciada (según los pasos actuales) | Cuenta registrada en el sistema | El usuario inicia sesión durante el flujo de compra y se muestra la confirmación del pedido. |

| Pasos | Acción                                                                                                                        | Resultado esperado                                                                                                                                                             |
|-------|-------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1     | Hacer clic en el botón 'Add to cart' de cualquier producto                                                                    | Se muestra una ventana emergente que indica que el producto se agregódo                                                                                                                    |
| 2     | Hacer clic en el botón 'Continue shopping' de la ventana emergente                                                                         | Se cierra el ventana emergente                                                                                                                                                           |
| 3     | Hacer clic en el botón 'Cart'                                                                                                 | Se muestra la página del carrito de compras con los productos agregados, con el total a pagar                                                                                  |
| 4     | Hacer clic en el botón 'Proceed To Checkout'                                                                                  | Se muestra una ventana emergente que indica que se debe iniciar sesión para poder continuar con la compra                                                                                   |
| 5     | Hacer clic en el botón 'Register / Login'                                                                                     | Se muestra la sección de 'Login to your account'                                                                                                                               |
| 6     | Ingresar el correo electrónico y contraseña de una cuenta ya registrada en los campos correspondientess de la sección «Login to your account» | El campo permite introducir los datos proporcionados                                                                                                                                        |
| 7     | Hacer clic en el botón login                                                                                                  | El usuario queda autenticado. El nombre del usuario autenticado aparece en el encabezado                                                              |
| 8     | Hacer clic en el botón 'Cart'                                                                                                 | Se muestra la página del carrito de compras con los productos agregados, con el total a pagar                                                                                  |
| 9     | Hacer clic en el botón 'Proceed To Checkout'                                                                                  | Se muestra la sección address detail con la dirección de envío y facturación. también se muestra la sección review order, con el resumen de los artículos seleccionados        |
| 10    | Ingresar una descripción en el área de texto, para agregar un comentario sobre la orden                                       | El campo permite introducir los datos proporcionados                                                                                                                                        |
| 11    | Hacer clic en el botón 'Place order'                                                                                          | Se muestra la sección payment                                                                                                                                                  |
| 12    | Ingresar los datos de la forma de pago(name on card, card number, cvc, expiration date)                                       | El campo permite introducir los datos proporcionados                                                                                                                                        |
| 13    | Hacer clic en el botón 'Pay and Confirm'                                                                                       | Se muestra el mensaje 'Your order has been placed successfully!' y se redirige automáticamente a una pantalla con el mensaje 'Congratulations! Your order has been confirmed!' |


## Test Case 17

| ID        | Descripción                   | Módulo         | Prioridad | Precondiciones                      | Datos de prueba | Resultado esperado                                 |
|-----------|-------------------------------|----------------|-----------|-------------------------------------|-----------------|----------------------------------------------------|
| TC-REG-17 | Remover productos del carrito | Cart - payment | Alta      | PC-01 y PC-03; productos identificados previamente en el carrito |                 | Los productos seleccionados se eliminan del carrito y se muestra el estado de carrito vacío. |

| Pasos | Acción                                            | Resultado esperado                                                                                                      |
|-------|---------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------|
| 1     | Hacer clic en el botón 'Cart'                     | Se muestra la página del carrito de compras con los productos agregados, con el total a pagar                           |
| 2     | Hacer clic en el botón 'X' de todos los productos | Ya no se muestra el producto en el carrito de compras; se muestra el mensaje 'Cart is empty! Click here to buy products |


## Test Case 18

| ID        | Descripción                        | Módulo   | Prioridad | Precondiciones              | Datos de prueba | Resultado esperado                                                                 |
|-----------|------------------------------------|----------|-----------|-----------------------------|-----------------|------------------------------------------------------------------------------------|
| TC-REG-18 | Visualizar categorías de productos | Products | Alta      | Precondición PC-01 cumplida |                 | Se muestran únicamente productos de las subcategorías seleccionadas de Women y Men. |

| Pasos | Acción                                                                | Resultado esperado                                                                                                                                                                                |
|-------|-----------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1     | Hacer clic en la categoría 'Women'                                    | Se muestra una lista con los tipos de productos de la categoría «Women»                                                                                                              |
| 2     | Hacer clic en una de las subcategorías que tiene la categoría women   | Se muestra la sección 'Women - categoryName Products' (nameProduct, que representa la subcategoría seleccionada), mostrando únicamente los productos correspondientes a la categoría seleccionada |
| 3     | Hacer clic en el botón view product de uno de los productos mostrados | Se redirige a la vista con los detalles del producto, mostrándose el product name, price, availability, condition, brand y la categoría correspondientes a la seleccionada                       |
| 4     | Hacer clic en la categoría 'Men'                                      | Se muestra una lista con los tipos de productos de la categoría «Men»                                                                                                                |
| 5     | Hacer clic en una de las subcategorías que tiene la categoría men     | Se muestra la sección 'Men - categoryName Products' (nameProduct, que representa la subcategoría seleccionada), mostrando únicamente los productos correspondientes a la categoría seleccionada   |
| 6     | Hacer clic en el botón view product de uno de los productos mostrados | Se redirige a la vista con los detalles del producto, mostrándose el product name, price, availability, condition, brand y la categoría correspondientes a la seleccionada                       |


## Test Case 19

| ID        | Descripción                    | Módulo   | Prioridad | Precondiciones              | Datos de prueba | Resultado esperado                                                             |
|-----------|--------------------------------|----------|-----------|-----------------------------|-----------------|--------------------------------------------------------------------------------|
| TC-REG-19 | Visualizar marcas de productos | Products | Media     | Precondición PC-01 cumplida |                 | Se muestran únicamente productos de las marcas seleccionadas. |

| Pasos | Acción                                                                | Resultado esperado                                                                                                                                                                            |
|-------|-----------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1     | Hacer clic en una marca deseada                                       | Se muestra la sección 'Brand - nameBrand Products' (nameBrand, que representa la marca seleccionada), mostrando únicamente los productos correspondientes a la marca seleccionada     |
| 2     | Hacer clic en el botón view product de uno de los productos mostrados | Se redirige a la vista con los detalles del producto, mostrándose el product name, price, availability, condition, categoría y la marca correspondientes a la seleccionada                   |
| 3     | Hacer clic en otra marca deseada                                      | Se muestra la sección 'Brand - nameBrand Products' (nameBrand, que representa la marca seleccionada), mostrando únicamente los productos correspondientes a la categoría seleccionada |
| 4     | Hacer clic en el botón view product de uno de los productos mostrados | Se redirige a la vista con los detalles del producto, mostrándose el product name, price, availability, condition, categoría y la marca correspondientes a la seleccionada                   |

## Test Case 20

| ID        | Descripción                                           | Módulo          | Prioridad | Precondiciones              | Datos de prueba | Resultado esperado                                                                                                       |
|-----------|-------------------------------------------------------|-----------------|-----------|-----------------------------|-----------------|--------------------------------------------------------------------------------------------------------------------------|
| TC-REG-20 | Realizar una búsqueda y agregar al carrito de compras | Products - Cart | Alta      | Precondición PC-01 cumplida |                 | Se muestran únicamente los productos correspondientess a la búsqueda realizada y se pueden agregar al carrito de compras |

| Pasos | Acción                                                    | Resultado esperado                                                                                                           |
|-------|-----------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------|
| 1     | Hacer clic en el botón 'Products' del menú de navegación  | Se muestra la sección 'All Products' y la lista de productos será visible                                                    |
| 2     | Ingresar el nombre de un producto en el campo de búsqueda | El campo permite introducir los datos proporcionados                                                                                      |
| 3     | Hacer clic en el botón de buscar (icono de lupa)          | Se muestra la sección 'Searched Products', mostrando únicamente los productos correspondientes a la búsqueda seleccionada |
| 4     | Poner el cursor sobre el primer producto                  | Se muestra un recuadro naranja sobre el producto con el botón 'Add to cart'                                                  |
| 5     | Hacer clic en el botón 'Add to cart'                      | Se muestra una ventana emergente que indica que el producto se agregó                                                                    |
| 6     | Hacer clic en el botón 'view Cart' de la ventana emergente             | Se muestra la página del carrito de compras con los productos agregados, con el total a pagar                                |


## Test Case 21

| ID        | Descripción                         | Módulo    | Prioridad | Precondiciones              | Datos de prueba | Resultado esperado                                      |
|-----------|-------------------------------------|-----------|-----------|-----------------------------|-----------------|---------------------------------------------------------|
| TC-REG-21 | Agregar una opinión de un producto  | Products  | Media     | Precondición PC-01 cumplida | correo electrónico válido    | Se publicará una reseña de un producto correctamente |

| Pasos | Acción                                                           | Resultado esperado                                                                      |
|-------|------------------------------------------------------------------|-----------------------------------------------------------------------------------------|
| 1     | Hacer clic en el botón 'Products' del menú de navegación         | Se muestra la sección 'All Products' y la lista de productos será visible               |
| 2     | Hacer clic en el botón 'View Product'                            | Se redirige a la vista con los detalles del producto y la sección 'Write your review' |
| 3     | Ingresar el nombre del usuario en el campo your name             | El campo permite introducir los datos proporcionados                                                 |
| 4     | Ingresar el correo electrónico del usuario en el campo email address          | El campo permite introducir los datos proporcionados                                                 |
| 5     | Ingresar una opinión del producto en el campo 'Add Review here!' | El campo permite introducir los datos proporcionados                                                 |
| 6     | Hacer clic en el botón 'submit'                                  | Se muestra el mensaje 'Thank you for your review.'                                       |


## Test Case 22

| ID        | Descripción                                                              | Módulo   | Prioridad | Precondiciones              | Datos de prueba | Resultado esperado                            |
|-----------|--------------------------------------------------------------------------|----------|-----------|-----------------------------|-----------------|-----------------------------------------------|
| TC-REG-22 | Agregar un producto al carrito de compras de los productos recomendados | Products | Media     | Precondición PC-01 cumplida |                 | Se agrega el producto al carrito de compras |

| Pasos | Acción                                                              | Resultado esperado                                                                            |
|-------|---------------------------------------------------------------------|-----------------------------------------------------------------------------------------------|
| 1     | Hacer scroll hacia abajo, hasta el final de la página               | Se muestra la sección 'RECOMMENDED ITEMS' con algunos productos                               |
| 2     | Hacer clic en el botón 'Add to cart' de un producto recomendado     | Se muestra una ventana emergente que indica que el producto se agregó                                     |
| 3     | Hacer clic en el botón 'view Cart'                                  | Se muestra la página del carrito de compras con los productos agregados, con el total a pagar |


## Test Case 23

| ID        | Descripción                                      | Módulo         | Prioridad | Precondiciones                      | Datos de prueba | Resultado esperado                                                                                          |
|-----------|--------------------------------------------------|----------------|-----------|-------------------------------------|-----------------|-------------------------------------------------------------------------------------------------------------|
| TC-REG-23 | Descargar factura después de realizar una compra | Cart - payment | Alta      | PC-01 y PC-04; carpeta de descargas accesible |                 | Se descarga un archivo .txt y su contenido coincide con los datos que el caso verifica. |

| Pasos | Acción                                                                                   | Resultado esperado                                                                                                                                                             |
|-------|------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1     | Hacer clic en el botón 'Add to cart' de cualquier producto                               | Se muestra una ventana emergente que indica que el producto se agregódo                                                                                                                    |
| 2     | Hacer clic en el botón 'Continue shopping' de la ventana emergente                                    | Se cierra el ventana emergente                                                                                                                                                           |
| 3     | Hacer clic en el botón 'Cart' del menú de navegación                                     | Se muestra la página del carrito de compras con los productos agregados, con el total a pagar                                                                                  |
| 4     | Hacer clic en el botón 'Proceed To Checkout'                                             | Se muestra la sección address detail con la dirección de envío y facturación. también se muestra la sección review order, con el resumen de los artículos seleccionados        |
| 5     | Ingresar una descripción en el área de texto, para agregar un comentario sobre la orden  | El campo permite introducir los datos proporcionados                                                                                                                                        |
| 6     | Hacer clic en el botón 'Place order'                                                     | Se muestra la sección payment                                                                                                                                                  |
| 7     | Ingresar los datos de la forma de pago (name on card, card number, cvc, expiration date) | El campo permite introducir los datos proporcionados                                                                                                                                        |
| 8     | Hacer clic en el botón 'Pay and Confirm'                                                  | Se muestra el mensaje 'Your order has been placed successfully!' y se redirige automáticamente a una pantalla con el mensaje 'Congratulations! Your order has been confirmed!' |
| 9     | Hacer clic en el botón 'Download Invoice'                                                | Se descarga un archivo `.txt` que contendrá el nombre del usuario y el monto a pagar                                                                                           |
| 10    | Hacer clic en el botón 'Continue'                                                        | Se redirecciona automáticamente a la página de inicio                                                                                                                              |


## Test Case 24

| ID        | Descripción                                                        | Módulo | Prioridad | Precondiciones              | Datos de prueba | Resultado esperado                                 |
|-----------|--------------------------------------------------------------------|--------|-----------|-----------------------------|-----------------|----------------------------------------------------|
| TC-REG-24 | Desplazarse a través de la página usando las flechas direccionales | Home   | Baja      | Precondición PC-01 cumplida |                 | Se desplaza al final y al principio de la página |

| Pasos | Acción                                                                           | Resultado esperado                                                           |
|-------|----------------------------------------------------------------------------------|------------------------------------------------------------------------------|
| 1     | Mantener presionada la tecla de flecha hacia abajo hasta el pie de página        | Se muestra la sección 'SUBSCRIPTION'                                         |
| 2     | Mantener presionada la tecla de flecha hacia arriba hasta el inicio de la página | Se muestra el texto 'Full-Fledged practice website for Automation Engineers' |

