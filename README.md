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

Una vez escoja un ave y pulse el botón de 'Update', el usuario navegará hasta un formulario donde puede cambiar los valores, que debe introducir correctamente o será redirigido a ésta pantalla y se le indicarán los campos erróneos.

Si ha introducido los campos correctamentes y acepta, pasará a visualizar una página donde se muestran los valores antíguos y los nuevos. Desde ahí puede ir a la página principal mediante el botón de la derecha.

<br>
<img src="https://github.com/AlvaroFernandezRamos-Servidores/CRUDPoolAlvaroFR/blob/master/src/main/webapp/icons/delete.svg" width="60px" style="display:inline-block;vertical-align:middle;">

#### Eliminar una o más aves.

De manera similar a la modificación de datos, el usuario podrá elegir un o, en este caso, más de un registro para eliminar.
Si el usuario no selecciona ningun registro y pulsa en 'Delete' de todas maneras, será redirigido de nuevo a esta pantalla de selección. Si quiere abortar la operación hay un botón para salir a la derecha de la pantalla.

Una vez haya escogido uno o más registros y aceptado, navegará a una pantalla de confirmación donde se muestran los elementos que van a ser eliminados. El usuario aún dispone de un botón a su derecha para cancelar la operación.

Si vuelve a confirmar la eliminación, últimamente, será redirigido a una pantalla de confirmación, que le indicará que los registros han sido eliminados. Desde ahí puede volver a la pagina principal con el botón de la derecha.

