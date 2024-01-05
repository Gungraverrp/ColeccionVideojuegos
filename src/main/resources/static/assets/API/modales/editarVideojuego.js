/**
 * FUNCIONES ASOCIADAS A LA PÁGINA INDEX.HTML
 */

 /**
  * FUNCIONES QUE SE DEBEN DE EJECUTAR AL INCIO
  */
 $(document).ready(function () {	
	 EVcargarNombres(); 
	 EVcargarSelectPlataformas(); 
	 EVcargarSelectFormatos(); 
	 EVcargarSelectGeneros();
});
 

/**
 * CARGAR EL VIDEOJUEGO
 */
function getVideojuego(idVideojuego){
	
  fetch("getVideojuego?idVideojuego=" + idVideojuego, {
  method: 'GET',
  cache: 'default'
  }).then((response) => {
		return response.json();
  }).then((content) => {
		if (content.data != null || content.data != undefined) {
			$("#tituloModal").text("EDITAR");
			$("#EV-id").val(idVideojuego);
			$("#EV-operacion").val("UPDATE");
			$("#EV-nombre").val(content.data.nombre);
			$("#EV-select-plataforma").val(content.data.plataforma.id);
			$("#EV-select-genero").val(content.data.genero.id);
			$("#EV-select-formato").val(content.data.formato.id);
			
			buscarEnMetacritic(content.data.plataforma.nombre, content.data.nombre);
			
		} else {
			alert("NO SE HA ENCONTRADO NINGÚN VIDEOJUEGO CON EL ID " + idVideojuego);
		}	
  })
}


/**
 * ADAPTAR EL MODAL PARA CREAR UN NUEVO VIDEOJUEGO
 */
function cargarModalNuevo(){
	$("#tituloModal").text("NUEVO");
	$("#EV-id").val(0);
	$("#EV-operacion").val("INSERT");
	
	
	$("#EV-nombre").val("");
	$("#EV-select-plataforma").val("");
	$("#EV-select-genero").val("");
  	$("#EV-select-formato").val("");
  	
  	$("#EV-form").removeClass("was-validated");
  	
}



/**
 * CARGA EL DATALIST CON LOS NOMBRES DE JUEGOS REGISTRADOS
 */
function EVcargarNombres(){
  fetch("getVideojuegos?idPlataforma=0&idGenero=0&idFormato=0&nombre=", {
  method: 'GET',
  cache: 'default'
  }).then((response) => {
    	return response.json();
  }).then((content) => {
	  	$('#EV-datalistNombres').empty();
	  	content.data.forEach(function(item) {
			$('#EV-datalistNombres').append('<option data-value='+ item.id +'>'+ item.nombre + ' (' + item.plataforma.nombre + ')' + '</option>');
  		})
  })
};

/**
 * CARGA EL SELECT CON LAS PLATAFORMAS
 */
function EVcargarSelectPlataformas(){
  fetch("getPlataformas", {
  method: 'GET',
  cache: 'default'
  }).then((response) => {
    	return response.json();
  }).then((content) => {
		$('#EV-select-plataforma').empty();
		$('#EV-select-plataforma').append("<option selected disabled value=''>...</option>");
	  	content.data.forEach(function(item) {
			$('#EV-select-plataforma').append(new Option(item.nombre, item.id));
  		})
  })
};


/**
 * CARGA EL SELECT CON LOS GENEROS
 */
function EVcargarSelectGeneros(){
  fetch("getGeneros", {
  method: 'GET',
  cache: 'default'
  }).then((response) => {
    	return response.json();
  }).then((content) => {
		$('#EV-select-genero').empty();
		$('#EV-select-genero').append("<option selected disabled value=''>...</option>");
	  	content.data.forEach(function(item) {
			$('#EV-select-genero').append(new Option(item.nombre, item.id));
  		})
  })
};



/**
 * CARGA EL SELECT CON LOS FORMATOS
 */
function EVcargarSelectFormatos(){
  fetch("getFormatos", {
  method: 'GET',
  cache: 'default'
  }).then((response) => {
    	return response.json();
  }).then((content) => {
		$('#EV-select-formato').empty();
		$('#EV-select-formato').append("<option selected disabled value=''>...</option>");
	  	content.data.forEach(function(item) {
			$('#EV-select-formato').append(new Option(item.nombre, item.id));
  		})
  })
};


/**
 * GUARDAR LOS CAMBIOS
 */
function guardar(){
	
	var validado = true;
	
	//COMPRBAMOS SI HAY ALGÚN CONTROL QUE NO SE HAYA INTRODUCIDO CORRECTAMENTE
	//BUSCANDO CUALQUIER CONTROL CON LA CLASE :invalid
	 $(":invalid").each(function() {
      validado = false;
    });

	
	if (validado == true) {
		 var operacion = $("#EV-operacion").val();
		
		 var xhr = new XMLHttpRequest();
		 xhr.open("POST", 'guardarVideojuego?operacion=' + operacion, true);
		 xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
		 xhr.onreadystatechange = function () {
		   if (xhr.readyState === 4 && xhr.status === 200) {
			     if (xhr.responseText.indexOf("Error") != -1) {
				  alert("ERROR: No se ha podido registar el videojuego. Por favor, revise los datos introducidos.");
			     } else {
			       cargarDatatableVideojuegos(); //ESTA FUNCIÓN ESTÁ EN INDEX.JS
			       EVcargarNombres();			       
			     }
		   }
		 };
		 
		 var plataforma = {
			 "id": $("#EV-select-plataforma").val(),
			 "nombre": $("#EV-select-plataforma").text(),
			 "compania": null
		 }
		 
		 var genero = {
			 "id":$("#EV-select-genero").val(),
			 "nombre": $("#EV-select-genero").text()
		 }
		 
		 var formato = {
			 "id": $("#EV-select-formato").val(),
			 "nombre": $("#EV-select-formato").text()
		 }
		 
		 var params={
		     "id": $("#EV-id").val(),
		     "nombre": $("#EV-nombre").val(),
		     "plataforma": plataforma,
		     "genero": genero,
		     "formato": formato
	     }                  
	             
	     xhr.send(JSON.stringify(params)); 
	  }   	
}
	
	

/**
 * BUSCAMOS LOS DATOS EN METACRITIC POR EL TÍTULO
 */	
function buscarEnMetacritic(plataforma, tituloJuego){
  const titulo = tituloJuego.replace(/\s/g, '-');
  const consola = plataforma.replace(/\s/g, '-');
  
  /*
  {
  method: 'GET',
  cache: 'default'
  }
  */
  fetch('https://www.metacritic.com/game/' + consola.toLowerCase() + '/' + titulo.toLowerCase() , {
	  		'method': 'GET',
	        'mode': 'cors',
	        'headers': {
            	'Access-Control-Allow-Origin': '*',
            	'Access-Control-Allow-Credentials': true
            }
  }).then((response) => {
	    if (!response.ok) {
		    alert("ERROR: " + response.error);
	    }
    	return response.json();
  }).then((content) => {
	  	console.log("NOTA: " + content.data.metascritic_score);
	  	console.log("Thumbnail: " + content.data.thumbnail_url);
  })
	
}	
	
	