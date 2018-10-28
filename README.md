# Aplicación para la Gestión de datos sobre Aves

## Manual de usuario:

### Pagina inicial

En la página inicial el usuario puede observar cuatro botones principales, cada uno le permite acceder a una opción distinta, a saber:
<br>
<br>
<img src="https://github.com/AlvaroFernandezRamos-Servidores/CRUDPoolAlvaroFR/blob/master/src/main/webapp/icons/add.svg" width="60px" style="display:inline-block;vertical-align:middle;">
#### Insertar un Ave en la base de datos. 
Si el usuario escoge esta opción será dirigido a un formulario donde debe introducir la Anilla, la Especie, el Lugar de visualización del ave y la Fecha.
De no insertar estos datos o no ceñirse a las restricciones sobre cada uno de los campos, el usuario será reconducido al formulario y los campos erróneos se destacarán mientras se conservan los campos correctos.

Si la inserción fue exitosa, se le mostrará al usuario una página de confirmación donde podrá visualizar el ave que ha sido insertada. En el lateral derecho dispone de un botón para regresar a la página principal.

<br>
<img src="https://github.com/AlvaroFernandezRamos-Servidores/CRUDPoolAlvaroFR/blob/master/src/main/webapp/icons/read.svg" width="60px" style="display:inline-block;vertical-align:middle;">

#### Mostrar todas las aves almacenadas.
Si accede a esta opción el usuario dispondrá de un listado de aves representadas por bloques que muestran su Anilla. Si el usuario hace click en una anilla, esta se desplegará para mostrar su contenido. El usuario dispone de un botón a su izquierda para mostrar/colapsar todas las aves. A su derecha el usuario dispone de un botón que le permite volver a la página principal.

<br>
<img src="https://github.com/AlvaroFernandezRamos-Servidores/CRUDPoolAlvaroFR/blob/master/src/main/webapp/icons/update.svg" width="60px" style="display:inline-block;vertical-align:middle;">

#### Actualizar la información sobre una de las aves.
Se le presentará al usuario un listado de opciones entre las cuales habrá de escoger una. Cada una de las opciones es representativa de un ave, identificada por su nombre para el usuario pero por su anilla de manera interna para el sistema.

Una vez escoja un ave y pulse el botón de 'Update', el usuario navegará hasta un formulario donde puede cambiar los valores 

<br>
<img src="https://github.com/AlvaroFernandezRamos-Servidores/CRUDPoolAlvaroFR/blob/master/src/main/webapp/icons/delete.svg" width="60px" style="display:inline-block;vertical-align:middle;">

#### Eliminar una o más aves.

