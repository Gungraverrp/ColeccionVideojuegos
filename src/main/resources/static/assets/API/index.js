/**
 * FUNCIONES ASOCIADAS A LA PÁGINA INDEX.HTML
 */

 /**
  * FUNCIONES QUE SE DEBEN DE EJECUTAR AL INCIO
  */
 $(document).ready(function () {
	 inicializaDataTableVideojuegos();		
	 cargarSelectPlataformas(); 
	 cargarSelectFormatos(); 
	 cargarSelectGeneros(); 
	 cargarVideojuegos(0, 0, 0, "");
});
 
 
 /**
  *incializa la DataTable Videojuegos para poder trabajar con ella
  */
 function inicializaDataTableVideojuegos(){
	 var tabla = $('#tablaVideojuegos').DataTable({
        dom: 'Bfrtip', //PARA MOSTRAR LOS BOTONES DE EXPORTACION
        "initComplete": function () {
        // Apply the search
        this.api().columns().every(function () {
            var that = this;

            $('input', this.footer()).on('keyup change clear', function () {
                if (that.search() !== this.value) {
                    that
                        .search(this.value)
                        .draw();
                }
            });
          });
        },     
        "paging": true,
        "ordering": true,
        "responsive": true,
        "searching": true,
        "lengthChange": false,
        "autoWidth": true,
        columns: [
          /*00*/ { "data": "id" },
          /*01*/ { "data": "nombre" },
          /*02*/ { "data": "idPlataforma" },
          /*03*/ { "data": "plataforma" },
          /*04*/ { "data": "idGenero" },
          /*05*/ { "data": "genero" },
          /*06*/ { "data": "idFormato" },
          /*07*/ { "data": "formato" },
          /*08*/ { "defaultContent": "<button type='button' title='MODIFICAR' id='editar' name='editar' class='editar btn' style='color: #1c6daf; background: transparent;'> <i class='fa fa-edit icon-center' style='color: #1c6daf;'></i></button>"
           					  + "<button type='button' title='ELIMINAR' id='eliminar' name='eliminar' class='eliminar btn' style='color: #1c6daf; background: transparent;'> <i class='fa fa-trash icon-center' style='color: #1c6daf;'></i></button>"
           					  , className: "text-center"}
          ],
                  
        "language": {
          "lengthMenu": "Mostrar _MENU_ Cargos por página",
          "zeroRecords": "No existen registros",
          "info": "Mostrando página _PAGE_ de _PAGES_ [ total de registros _MAX_ ]",
          "infoEmpty": "No existen datos",
          "infoFiltered": "(filtered from _MAX_ total records)",
          "search": "Buscar&nbsp;:",
          "paginate": {
            first:      "Primero",
            previous:   "Previo",
            next:       "Siguiente",
            last:       "Último" 
          }
        },
        "buttons": {
          buttons: [ //PARA MOSTRAR LOS BOTONES DE EXPORTACION
            //'excel', 'pdf'
           { 
           extend: 'pdf',
           text: '<i class="fa fa-file-pdf-o fa-2x" style="color: #1c6daf;"></i>',
           titleAttr: 'Exportar listado a PDF',
           orientation: 'landscape',                 
           exportOptions: {
             columns: [ 1, 2, 3, 4 ],
             }
           },
           { 
           extend: 'excel',
           text: '<i class="fa fa-file-excel-o fa-2x" style="color: #1c6daf;"></i>',
           titleAttr: 'Exportar listado a Excel',
           exportOptions: {
             columns: [ 1, 2, 3, 4 ],
             }
           }
         ]
       },
                 
        "pageLength": 0,
        "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "Todos"]],
        "columnDefs": [ 
         {// OCULTAMOS LA COLUMNA DEL IDCARGO
           "targets": [0, 2, 4, 6],
           "visible": false,
           "searchable": false
         },
         {
            "targets": -1,
            "responsivePriority": 0,
            "data": null        
          }],
          "order": [[ 1, "asc" ]]
      });
}	 
	 
	 
/**
 * CREAR LOS BUSCADORES POR COLUMNA
 */
$('#tablaVideojuegos tfoot td').each(function() {
    var title = $(this).text();    
    if(title=="Acciones") {
        var accion = document.getElementById(title);
        accion.style.visibility = 'hidden';
    }else{        
        $(this).html('<input type="text" placeholder="Buscar" />');
    }
});



/**
 * ESTA FUNCIÓN LA USAREMOS PARA ACTUALIZAR DESPUES DE GUARDAR
 */
function cargarDatatableVideojuegos(){
	var idPlataforma = $("#select-plataforma").val();
	var idGenero = $("#select-genero").val();
	var idFormato = $("#select-formato").val();
	var nombre = $("#nombre").val();	
	
	cargarVideojuegos(idPlataforma, idGenero, idFormato, nombre);
}

/**
 * CARGA EL DATATABLE SEGÚN EL FILTRO ENVIADO
 */
function cargarVideojuegos(idPlataforma, idGenero, idFormato, nombre){
  var tabla = $("#tablaVideojuegos").DataTable();
  tabla.clear().draw();
  	
  fetch('getVideojuegos?idPlataforma=' + idPlataforma + '&idGenero=' + idGenero + "&idFormato=" + idFormato + "&nombre=" + nombre, {
  method: 'GET',
  cache: 'default'
  }).then((response) => {
    	return response.json();
  }).then((content) => {
	  	content.data.forEach(function(item) {
			tabla.row.add({
			"id": item.id,
			"nombre": item.nombre,
			"idPlataforma":item.plataforma.id,
			"plataforma": item.plataforma.nombre,
			"idGenero": item.genero.id,
			"genero": item.genero.nombre,
			"idFormato": item.formato.id,
			"formato": item.formato.nombre	
			});
  		})
  		
  		tabla.draw();
  })
};




/**
 * CARGA EL SELECT CON LAS PLATAFORMAS
 */
function cargarSelectPlataformas(){
  fetch("getPlataformas", {
  method: 'GET',
  cache: 'default'
  }).then((response) => {
    	return response.json();
  }).then((content) => {
		$('#select-plataforma').empty();
		$('#select-plataforma').append(new Option("- Plataforma -", "0"));
	  	content.data.forEach(function(item) {
			$('#select-plataforma').append(new Option(item.nombre, item.id));
  		})
  })
};


/**
 * CARGA EL SELECT CON LOS GENEROS
 */
function cargarSelectGeneros(){
  fetch("getGeneros", {
  method: 'GET',
  cache: 'default'
  }).then((response) => {
    	return response.json();
  }).then((content) => {
		$('#select-genero').empty();
		$('#select-genero').append(new Option("- Genero -", "0"));
	  	content.data.forEach(function(item) {
			$('#select-genero').append(new Option(item.nombre, item.id));
  		})
  })
};



/**
 * CARGA EL SELECT CON LOS FORMATOS
 */
function cargarSelectFormatos(){
  fetch("getFormatos", {
  method: 'GET',
  cache: 'default'
  }).then((response) => {
    	return response.json();
  }).then((content) => {
		$('#select-formato').empty();
		$('#select-formato').append(new Option("- Formato -", "0"));
	  	content.data.forEach(function(item) {
			$('#select-formato').append(new Option(item.nombre, item.id));
  		})
  })
};





/**
 * BOTÓN BUSCAR JUEGOS
 */
$('#btn-buscar').click( function(e) {
	var nombre = $("#nombre").val();
	var plataforma = $("#select-plataforma").val();
	var genero = $("#select-genero").val();
	var formato = $("#select-formato").val();
	
	cargarVideojuegos(plataforma, genero, formato, nombre);
	
});
	
	
/**
 * ABRIR EL MODAL DE NUEVO VIDEOJUEGO
 */
$('#btn-nuevo').click( function(e) {
	cargarModalNuevo();
	$("#editarVideojuego").modal("show");
});


/**
 * EDITAR UN VIDEOJUEGO
 */
$("#tablaVideojuegos tbody").on("click", "button.editar", function () {  
  var table = $('#tablaVideojuegos').DataTable();
  var data = table.row($(this).parents("tr")).data();  
  getVideojuego(data.id);
  $("#editarVideojuego").modal("show");
});



/**
 * BORRAR UN VIDEOJUEGO
 */
$("#tablaVideojuegos tbody").on("click", "button.eliminar", function () {  
  	var table = $('#tablaVideojuegos').DataTable();
  	var data = table.row($(this).parents("tr")).data();
  	  	
	if (confirm("Se va a proceder a borrar '" + data.nombre + "'\n¿Desea continuar?")) {		
		fetch("borrarVideojuego?idVideojuego=" + data.id, {
		  method: 'POST',
		  cache: 'default'
		  }).then((response) => {
		    	return response.json();
		  }).then((content) => {
				cargarDatatableVideojuegos();
	  	  })
	  	
	}

});

