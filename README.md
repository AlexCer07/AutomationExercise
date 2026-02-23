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
|PC-01 |Ingresar al sitio Web|1. Navegador disponible <br>2. Sito web disponible 'https://automationexercise.com/' <br>3. Navegar al sitio web |La pagina se visualiza correctamente|

### Test Case 1
|ID        |Descripcion      |Modulo|Prioridad|Precondiciones|Datos de prueba|Resultado esperado|
|----------|-----------------|------|---------|--------------|---------------|------------------|
|TC-REG-01 |Registrar usuario|login |Alta     |Precondicion PC-01 cumplida|email no registrado|La cuenta se crea de manera exitosa y el usuario queda autenticado|

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
|TC-REG-03|Cerrar sesion |login |Alta     |Precondición PC-01 cumplida <br>caso de prueba TC-REG-02 ejecutado y exitoso| |Se debe cerrar sesión de forma exitosa y el usaurio ya no estara autenticado|

|Pasos|Acción|Resultado esperado|
|-----|------|------------------|
|1    |Hacer clic en el botón 'Logout'|El usuario ya no estara autenticado. No se mostrara el nombre del usuario entre las opciones del header. Automaticamente se encontrara en el modulo de login|

### Test case 4
inicar sesión con usuario incorrecto

### Test case 5
Registrar usuario con email existente

### Test case 6
eliminar cuenta







## 📂 Estructura del proyecto

## 📈 Roadmap (opcional)

## 🤝 Contribución

Las contribuciones son bienvenidas.  
Por favor abre un issue o un pull request.

## 👤 Autor

Alejandro Cervantes - Analista QA
