window.addEventListener('load', function () {
    (function(){

      //con fetch invocamos a la API de productos con el método GET
      //nos devolverá un JSON con una colección de productos
      const url = '/productos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
      //recorremos la colección de productos del JSON
         for(product of data){
            //por cada producto armaremos una fila de la tabla
            //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos el producto
            var table = document.getElementById("productTable");
            var productRow =table.insertRow();
            let tr_id = 'tr_' + product.id;
            productRow.id = tr_id;

            //por cada producto creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
            //dicho boton invocara a la funcion de java script deleteByKey que se encargará
            //de llamar a la API para eliminar un producto
            let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + product.id + '\"' +
                                      ' type="button" onclick="deleteBy('+product.id+')" class="btn btn-danger btn_delete">' +
                                      '&times' +
                                      '</button>';

            //por cada producto creamos un boton que muestra el id y que al hacerle clic invocará
            //a la función de java script findBy que se encargará de buscar el producto que queremos
            //modificar y mostrar los datos de la misma en un formulario.
            let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + product.id + '\"' +
                                      ' type="button" onclick="findBy('+product.id+')" class="btn btn-info btn_id">' +
                                      product.id +
                                      '</button>';

            //armamos cada columna de la fila
            //como primer columna pondremos el boton modificar
            //luego los datos del producto
            //como ultima columna el boton eliminar
            productRow.innerHTML = '<td>' + updateButton + '</td>' +
                    '<td class=\"td_id\">' + product.id + '</td>' +
                    '<td class=\"td_registration\">' + product.registration + '</td>' +
                    '<td class=\"td_name\">' + product.name.toUpperCase() + '</td>' +
                    '<td class=\"td_lastName\">' + product.lastName.toUpperCase() + '</td>' +
                    '<td>' + deleteButton + '</td>'
                    ;

        };

    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/productList.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })